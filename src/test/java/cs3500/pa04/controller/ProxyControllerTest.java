package cs3500.pa04.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.json.CoordinatesJson;
import cs3500.pa04.model.json.EndGameJson;
import cs3500.pa04.model.json.JoinJson;
import cs3500.pa04.model.json.JsonUtils;
import cs3500.pa04.model.json.MessageJson;
import cs3500.pa04.model.json.SetupJson;
import cs3500.pa04.model.mock.Mocket;
import cs3500.pa04.model.mock.MocketFail;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.other.GameResult;
import cs3500.pa04.model.other.GameType;
import cs3500.pa04.model.player.ComputerPlayer;
import cs3500.pa04.model.player.Player;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.view.BattleShipView;
import cs3500.pa04.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ProxyController.
 */
class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;

  Appendable output;
  View view;
  Random random;

  String separation;

  Socket socket;

  Map<ShipType, Integer> fleetSpec;

  Player playerCont; // the continuous player for the full play through


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
    this.output = System.out;
    this.view = new BattleShipView(output);
    this.random = new Random(1);
    this.separation = System.lineSeparator();
    this.fleetSpec = Map.of(
        ShipType.BATTLESHIP, 1,
        ShipType.DESTROYER, 1,
        ShipType.SUBMARINE, 1,
        ShipType.CARRIER, 1);
    this.playerCont = new ComputerPlayer(view, random);
    playerCont.setup(6, 6, fleetSpec);
  }


  @Test
  public void testHandleSetup() {

    SetupJson setupJson = new SetupJson(6, 6, fleetSpec);
    JsonNode sampleMessage = createSampleMessage("setup", setupJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a proxy controller
    this.controller = new ProxyController(socket, new ComputerPlayer(view, random));

    this.controller.playGame();

    StringBuilder expected = new StringBuilder(
        "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":["
            + "{\"coord\":{\"x\":0,\"y\":2},\"length\":6,\"direction\":\"HORIZONTAL\"},"
            + "{\"coord\":{\"x\":0,\"y\":3},\"length\":5,\"direction\":\"HORIZONTAL\"},"
            + "{\"coord\":{\"x\":0,\"y\":4},\"length\":4,\"direction\":\"HORIZONTAL\"},"
            + "{\"coord\":{\"x\":2,\"y\":1},\"length\":3,\"direction\":\"HORIZONTAL\"}]}}"
            + separation);
    responseToClass(JsonNode.class);
    assertEquals(expected.toString(), logToString());

  }

  @Test
  public void testHandleJoin() {
    JoinJson joinJson = new JoinJson("bderbs30", GameType.SINGLE);
    JsonNode sampleMessage = createSampleMessage("join", joinJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a proxy controller
    this.controller = new ProxyController(socket, new ComputerPlayer(view, random));

    this.controller.playGame();

    StringBuilder expected = new StringBuilder(
        "{\"method-name\":\"join\",\"arguments\":{\"name\":\"bderbs30\",\"game-type\":\"SINGLE\"}}"
            + separation);
    responseToClass(JsonNode.class);
    assertEquals(expected.toString(), logToString());
  }


  @Test
  public void testSuccessfulHits() {
    List<Coord> shots = List.of(
        new Coord(0, 0),
        new Coord(1, 1),
        new Coord(2, 2),
        new Coord(3, 3));
    CoordinatesJson coordinatesJson = new CoordinatesJson(shots);
    JsonNode sampleMessage = createSampleMessage("successful-hits", coordinatesJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a proxy controller
    this.controller = new ProxyController(socket, new ComputerPlayer(view, random));

    this.controller.playGame();
    responseToClass(JsonNode.class);

    StringBuilder expected = new StringBuilder("{}" + separation);
    assertEquals(expected.toString(), logToString());
  }

  @Test
  public void testHandleEndGame() {
    EndGameJson endGameJson = new EndGameJson(GameResult.LOSE, "You lost!");
    JsonNode sampleMessage = createSampleMessage("end-game", endGameJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a proxy controller
    this.controller = new ProxyController(socket, new ComputerPlayer(view, random));

    this.controller.playGame();
    responseToClass(JsonNode.class);

    StringBuilder expected = new StringBuilder("{}" + separation);
    assertEquals(expected.toString(), logToString());
  }


  @Test
  public void testReportDamage() {

    CoordinatesJson coordinatesJson = new CoordinatesJson(List.of(new Coord(1, 0)));
    JsonNode sampleMessage2 = createSampleMessage("report-damage", coordinatesJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage2.toString()));

    this.controller = new ProxyController(socket, playerCont);

    this.controller.playGame();

    responseToClass(JsonNode.class);

    StringBuilder expected = new StringBuilder("{\"method-name\":\"report-damage\",\"arguments\":"
        + "{\"coordinates\":[{\"x\":1,\"y\":0}]}}" + separation);
    assertEquals(expected.toString(), logToString());
  }

  @Test
  public void testTakeShots() {

    CoordinatesJson coordinatesJson = new CoordinatesJson(List.of());
    JsonNode sampleMessage2 = createSampleMessage("take-shots", coordinatesJson);

    socket = new Mocket(this.testLog, List.of(sampleMessage2.toString()));

    this.controller = new ProxyController(socket, playerCont);

    this.controller.playGame();

    responseToClass(JsonNode.class);

    StringBuilder expected = new StringBuilder("{\"method-name\":\"take-shots\",\"arguments\":"
        + "{\"coordinates\":[{\"x\":2,\"y\":2},{\"x\":3,\"y\":1},{\"x\":0,\"y\":5},"
        + "{\"x\":5,\"y\":0}]}}" + separation);
    assertEquals(expected.toString(), logToString());
  }

  @Test
  public void testTryCatch() {
    JoinJson joinJson = new JoinJson("bderbs30", GameType.SINGLE);
    JsonNode sampleMessage = createSampleMessage("message", joinJson);

    socket = new MocketFail(this.testLog, List.of(sampleMessage.toString()));

    // Create a proxy controller

    Exception exc = assertThrows(IllegalStateException.class,
        () -> this.controller = new ProxyController(socket, new ComputerPlayer(view, random)),
        "Could not get input stream from server");
    assertEquals("Could not get input stream from server", exc.getMessage());

  }


  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

}
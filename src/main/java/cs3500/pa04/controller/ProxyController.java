package cs3500.pa04.controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.model.json.CoordinatesJson;
import cs3500.pa04.model.json.EndGameJson;
import cs3500.pa04.model.json.FleetJson;
import cs3500.pa04.model.json.JoinJson;
import cs3500.pa04.model.json.JsonUtils;
import cs3500.pa04.model.json.MessageJson;
import cs3500.pa04.model.json.SetupJson;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.other.GameResult;
import cs3500.pa04.model.other.GameType;
import cs3500.pa04.model.player.Player;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipAdapter;
import cs3500.pa04.model.ship.ShipType;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a proxy controller. Used for networking.
 */
public class ProxyController implements Controller {

  private Socket server;
  private Player player;


  private final InputStream in;
  private final PrintStream out;

  private final ObjectMapper mapper = new ObjectMapper();

  JsonNode emptyNode = mapper.createObjectNode();

  /**
   * Construct an instance of a ProxyController.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   */
  public ProxyController(Socket server, Player player) {
    this.server = server;
    try {
      this.in = server.getInputStream();
    } catch (IOException e) { // todo: refactor this code to not just rethrow the exception
      throw new IllegalStateException("Could not get input stream from server");
    }
    try {
      this.out = new PrintStream(server.getOutputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Could not get output stream from server");
    }
    this.player = player;
  }


  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void playGame() {
    try {

      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.err.println("Error reading from server");
    }
  }

  /**
   * Determines the type of request the server has sent ("guess" or "win") and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.methodName();
    JsonNode arguments = message.arguments();

    switch (name) {
      case "join" -> handleJoin();
      case "setup" -> handleSetup(arguments);
      case "take-shots" -> handleTakeShots();
      case "report-damage" -> handleReportDamage(arguments);
      case "successful-hits" -> handleSuccessfulHits(arguments);
      case "end-game" -> handleEndGame(arguments);
      default -> throw new IllegalArgumentException("Invalid message name");
    }
  }

  /**
   * Handles the join game message. The server sends a message asking the client to join the game
   * The client then responds with a message containing the GitHub name of the player and the
   * game type.
   */
  private void handleJoin() {
    String gitHubName = "bderbs30";
    GameType gameType = GameType.SINGLE;
    JoinJson joinResponse = new JoinJson(gitHubName, gameType);
    serializeAggregated(joinResponse, "join");
  }


  /**
   * Handles the setup of the game. The server sends a message asking the client to setup the
   * game with the given parameters. The player is then notified of the setup and the server
   * is notified with the list of the ships that were placed.
   *
   * @param arguments the arguments of the message
   */
  private void handleSetup(JsonNode arguments) {
    SetupJson setupArgs = this.mapper.convertValue(arguments, SetupJson.class);

    int height = setupArgs.height();
    int width = setupArgs.width();
    Map<ShipType, Integer> specifications = setupArgs.fleetSpec();
    List<Ship> responseShips = player.setup(height, width, specifications);
    List<ShipAdapter> convertedResponseShips = new ArrayList<>();
    // convert the list of response ships to a list of shipAdapter needed for the FleetJson
    for (Ship ship : responseShips) {
      convertedResponseShips.add(new ShipAdapter(ship));
    }
    FleetJson responseSetUp = new FleetJson(convertedResponseShips);
    serializeAggregated(responseSetUp, "setup");
  }

  /**
   * Handles the take shots message from the server. The server sends a message asking the client
   * for a list of shots to take. The player is then notified of the shots and the server is
   * notified with the coordinates of the shots that were taken.
   */
  private void handleTakeShots() {
    List<Coord> responseShots = player.takeShots();
    CoordinatesJson responseVolley = new CoordinatesJson(responseShots);
    serializeAggregated(responseVolley, "take-shots");
  }

  /**
   * Handles the report damage message from the server. The server sends a message with the shots
   * that were taken by the opponent. The player is then notified of the shots and the server is
   * notified with the coordinates of the ships that were damaged.
   *
   * @param arguments the arguments of the message
   */
  private void handleReportDamage(JsonNode arguments) {
    List<Coord> opponentShots =
        this.mapper.convertValue(arguments, CoordinatesJson.class).coordinates();
    List<Coord> damagedCoords = player.reportDamage(opponentShots);
    CoordinatesJson responseCoordinates = new CoordinatesJson(damagedCoords);
    serializeAggregated(responseCoordinates, "report-damage");

  }

  /**
   * Handles the successful hits message from the server. The server sends a message with the
   * coordinates of the successful hits. The player is then notified of the successful hits and the
   * server is notified that the message was received.
   *
   * @param arguments the arguments of the message
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    List<Coord> successfulHits =
        this.mapper.convertValue(arguments, CoordinatesJson.class).coordinates();
    player.successfulHits(successfulHits);
    this.out.println(emptyNode);


  }

  /**
   * Handles the end game message from the server. The server sends a message with the result of the
   * game and the reason for the result. The player is then notified of the result and the reason
   * and the server is closed.
   *
   * @param arguments the arguments of the message
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameJson endGameJson = this.mapper.convertValue(arguments, EndGameJson.class);
    GameResult result = endGameJson.result();
    String reason = endGameJson.reason();
    player.endGame(result, reason);
    this.out.println(emptyNode);
    try {
      this.server.close();
    } catch (IOException e) {
      System.err.println("Error closing server");
    }
  }

  /**
   * Serializes the given record and method name into a MessageJSON and then serializes the
   * MessageJSON into a JSON Node and sends it to the output stream.
   *
   * @param record     the record to be serialized
   * @param methodName the method name to be serialized in the MessageJSON
   */
  private void serializeAggregated(Record record, String methodName) {
    JsonNode responseNode = JsonUtils.serializeRecord(record);
    MessageJson responseMessage = new MessageJson(methodName, responseNode);
    JsonNode responseNodeFinal = JsonUtils.serializeRecord(responseMessage);
    this.out.println(responseNodeFinal);
  }
}

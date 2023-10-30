package cs3500.pa04.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.BattleShipController;
import cs3500.pa04.controller.Controller;
import cs3500.pa04.view.BattleShipView;
import cs3500.pa04.view.View;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the BattleShipController. (Mainly Integration Tests)
 */
class BattleShipControllerTest {

  private Readable input;
  private Appendable output;

  Controller controller;
  private Random seededRandom;
  private String playThroughShots1;
  private String playThroughShots2;
  private String playThroughShots3;
  private String playThroughShots4;
  private String playThroughShots5;
  private String playThroughShots6;
  private String playThroughShots7;
  private String playThroughShots8;
  private String playThroughShots9;
  private String playThroughShots10;
  private String playThroughShots11;
  private String playThroughShots12;
  private String playThroughShots13;
  private String playThroughShots14;
  private String playThroughShots15;
  private String playThroughShots16;
  private String playThroughShots17;
  private String playThroughShots18;
  private String playThroughShots19;
  private String playThroughShots20;
  private String gameOverShotRepeat; // a shot to repeat until the AI beats the player

  private String validDimension;

  private String validShipPlacement;
  private View view;


  @BeforeEach
  public void setUp() {
    input = new StringReader("");
    this.output = new StringBuffer();
    this.seededRandom = new Random(1);
    this.view = new BattleShipView(output);
    this.controller = new BattleShipController(input, view, seededRandom);


    this.playThroughShots1 = "0 2\n";
    this.playThroughShots2 = "0 3\n";
    this.playThroughShots3 = "0 4\n";
    this.playThroughShots4 = "1 2\n";
    this.playThroughShots5 = "1 3\n";
    this.playThroughShots6 = "1 4\n";
    this.playThroughShots7 = "2 1\n";
    this.playThroughShots8 = "2 2\n";
    this.playThroughShots9 = "2 3\n";
    this.playThroughShots10 = "2 4\n";
    this.playThroughShots11 = "3 1\n";
    this.playThroughShots12 = "3 2\n";
    this.playThroughShots13 = "3 3\n";
    this.playThroughShots14 = "3 4\n";
    this.playThroughShots15 = "4 1\n";
    this.playThroughShots16 = "4 2\n";
    this.playThroughShots17 = "4 3\n";
    this.playThroughShots18 = "5 2\n";
    this.playThroughShots19 = "5 3\n";
    this.playThroughShots20 = "5 4\n";
    this.gameOverShotRepeat = "0 0\n";
    this.validDimension = "6 6\n";
    this.validShipPlacement = "1 1 1 1\n";


  }


  /**
   * Tests that the controller can successfully prompt the user for dimensions and then
   * successfully create a game with those dimensions. Also runs through the entire game.
   */
  @Test
  public void testValidPlayGameRunThrough() {
    this.controller = new BattleShipController(input, view);

    Readable playThroughInput = new StringReader(
        validDimension + validShipPlacement + playThroughShots1 + playThroughShots2
            + playThroughShots3 + playThroughShots4 + playThroughShots5 + playThroughShots6
            + playThroughShots7 + playThroughShots8 + playThroughShots9 + playThroughShots10
            + playThroughShots11 + playThroughShots12 + playThroughShots13
            + playThroughShots14 + playThroughShots15
            + playThroughShots16 + playThroughShots17 + playThroughShots18 + playThroughShots19
            + playThroughShots20);
    this.controller = new BattleShipController(playThroughInput, view, seededRandom);
    Appendable playThroughOutput =
        new StringBuffer("""
            Hello! Welcome to the OOD BattleSalvo Game!
            Please enter a valid height and width below:
            ------------------------------------------------------
            Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
            Remember, your fleet may not exceed size 6.
            ------------------------------------------------------
            Human Board:
                0  1  2  3  4  5
            0   -  C  D  D  D  D\s
            1   -  C  B  -  -  S\s
            2   -  C  B  -  -  S\s
            3   -  C  B  -  -  S\s
            4   -  C  B  -  -  -\s
            5   -  C  B  -  -  -\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  -  -  -  -\s
            2   -  -  -  -  -  -\s
            3   -  -  -  -  -  -\s
            4   -  -  -  -  -  -\s
            5   -  -  -  -  -  -\s
            Please enter your shots in the format [col, row].
            Human Board:
                0  1  2  3  4  5
            0   -  C  D  D  D  D\s
            1   -  C  B  -  -  S\s
            2   -  C  B  -  -  S\s
            3   -  C  B  *  -  S\s
            4   -  C  B  *  -  -\s
            5   *  C  B  -  -  *\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  -  -  -  -\s
            2   X  X  -  -  -  -\s
            3   X  -  -  -  -  -\s
            4   X  -  -  -  -  -\s
            5   -  -  -  -  -  -\s
            Please enter your shots in the format [col, row].
            Human Board:
                0  1  2  3  4  5
            0   *  C  D  D  D  D\s
            1   -  C  B  -  -  S\s
            2   -  C  B  -  -  S\s
            3   *  C  X  *  -  S\s
            4   -  C  B  *  -  -\s
            5   *  C  X  -  -  *\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  X  -  -  -\s
            2   X  X  X  -  -  -\s
            3   X  X  -  -  -  -\s
            4   X  X  -  -  -  -\s
            5   -  -  -  -  -  -\s
            Please enter your shots in the format [col, row].
            Human Board:
                0  1  2  3  4  5
            0   *  X  D  D  D  D\s
            1   -  X  B  -  -  S\s
            2   -  C  B  -  -  S\s
            3   *  C  X  *  -  S\s
            4   *  C  B  *  -  -\s
            5   *  X  X  -  -  *\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  X  X  -  -\s
            2   X  X  X  X  -  -\s
            3   X  X  X  -  -  -\s
            4   X  X  X  -  -  -\s
            5   -  -  -  -  -  -\s
            Please enter your shots in the format [col, row].
            Human Board:
                0  1  2  3  4  5
            0   *  X  D  D  X  D\s
            1   -  X  B  -  -  S\s
            2   -  C  B  -  *  X\s
            3   *  C  X  *  -  X\s
            4   *  C  B  *  -  -\s
            5   *  X  X  -  -  *\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  X  X  X  -\s
            2   X  X  X  X  X  -\s
            3   X  X  X  X  -  -\s
            4   X  X  X  X  -  -\s
            5   -  -  -  -  -  -\s
            Please enter your shots in the format [col, row].
            Human Board:
                0  1  2  3  4  5
            0   *  X  D  D  X  D\s
            1   -  X  B  -  -  S\s
            2   -  C  B  -  *  X\s
            3   *  C  X  *  -  X\s
            4   *  X  B  *  -  -\s
            5   *  X  X  *  -  *\s
            Computer Board:
                0  1  2  3  4  5
            0   -  -  -  -  -  -\s
            1   -  -  X  X  X  -\s
            2   X  X  X  X  X  X\s
            3   X  X  X  X  X  *\s
            4   X  X  X  X  -  *\s
            5   -  -  -  -  -  -\s
            Congratulations! You sunk all of the computer's ships!
            """);
    this.controller.playGame();
    assertEquals(playThroughOutput.toString(), output.toString());
  }

  /**
   * Tests the response from invalid inputs.
   */
  @Test
  public void testInvalidPlayGameRunThrough() {
    String invalidDimension = "1 1\n";
    String invalidDimension2 = "1 1 1\n";
    String invalidDimension3 = "string string\n";
    String invalidDimension4 = "10 16\n";
    String invalidShipPlacement = "1 5 1 1\n";
    String invalidShipPlacement2 = "1 1 1 1 1\n";
    String invalidShipPlacement3 = "string string string string\n";
    String invalidShipPlacement4 = "1 0 4 1\n";
    String invalidShot = "10 1\n";
    String invalidShot2 = "1 10\n";
    String invalidShot3 = "string string\n";
    String invalidShot4 = "1 1 1\n";

    Readable playThroughInput = new StringReader(invalidDimension + invalidDimension2
        + invalidDimension3 + invalidDimension4 + validDimension + invalidShipPlacement
        + invalidShipPlacement2 + invalidShipPlacement3 + invalidShipPlacement4
        + validShipPlacement + invalidShot + invalidShot2 + invalidShot3 + invalidShot4
        + playThroughShots1 + playThroughShots2 + playThroughShots3 + playThroughShots4
        + playThroughShots5 + playThroughShots6 + playThroughShots7 + playThroughShots8
        + playThroughShots9 + playThroughShots10 + playThroughShots11 + playThroughShots12
        + playThroughShots13 + playThroughShots14 + playThroughShots15 + playThroughShots16
        + playThroughShots17 + playThroughShots18 + playThroughShots19 + playThroughShots20);

    Appendable playThroughOutput = new StringBuilder("""
        Hello! Welcome to the OOD BattleSalvo Game!
        Please enter a valid height and width below:
        ------------------------------------------------------
        Uh Oh! You've entered invalid dimensions. Please remember that the height and width
        of the game must be in the range (6, 15), inclusive. Try again!
        Invalid input. Please enter both height and width.
        Invalid input. Please enter valid integers for height and width.
        Uh Oh! You've entered invalid dimensions. Please remember that the height and width
        of the game must be in the range (6, 15), inclusive. Try again!
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed size 6.
        ------------------------------------------------------
        Uh Oh! You've entered invalid ship numbers. Please remember that the number of ships
        must be in the range (4, 6), inclusive. Try again!
        Invalid input. Please enter an Integer for all ShipTypes.
        Invalid input. Please enter valid integers for ship numbers.
        Uh Oh! You've entered invalid ship numbers. Please remember that the number of ships
        must be in the range (4, 6), inclusive. Try again!
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  -  -  S\s
        4   -  C  B  -  -  -\s
        5   -  C  B  -  -  -\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Invalid coordinates, must be in the range (0, 5) and (0, 5), respectively. Try again!
        Invalid coordinates, must be in the range (0, 5) and (0, 5), respectively. Try again!
        Invalid input. Please enter valid integers for row and col.
        Invalid input. Please enter both row and col.
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  B  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   X  X  -  -  -  -\s
        3   X  -  -  -  -  -\s
        4   X  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  -  -  -\s
        2   X  X  X  -  -  -\s
        3   X  X  -  -  -  -\s
        4   X  X  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  D  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  X  -  -\s
        2   X  X  X  X  -  -\s
        3   X  X  X  -  -  -\s
        4   X  X  X  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  -  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  B  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  X\s
        3   X  X  X  X  X  *\s
        4   X  X  X  X  -  *\s
        5   -  -  -  -  -  -\s
        Congratulations! You sunk all of the computer's ships!
        """);
    this.controller = new BattleShipController(playThroughInput, this.view, seededRandom);
    this.controller.playGame();
    assertEquals(playThroughOutput.toString(), output.toString());
  }

  /**
   * Tests that the game ends when the computer wins.
   */
  @Test
  public void testComputerWins() {
    // repeat the repeat shot 20 times for input
    Readable playThroughInput =
        new StringReader(
            validDimension + validShipPlacement + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
                + gameOverShotRepeat);

    Appendable playThroughOutput = new StringBuilder("""
        Hello! Welcome to the OOD BattleSalvo Game!
        Please enter a valid height and width below:
        ------------------------------------------------------
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed size 6.
        ------------------------------------------------------
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  -  -  S\s
        4   -  C  B  -  -  -\s
        5   -  C  B  -  -  -\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  B  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  D  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  D\s
        1   -  X  B  -  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  X\s
        2   -  X  X  *  *  X\s
        3   *  X  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Uh oh! The computer sunk all of your ships!
        """);
    this.controller = new BattleShipController(playThroughInput, view, seededRandom);
    this.controller.playGame();
    assertEquals(playThroughOutput.toString(), output.toString());
  }

  /**
   * Tests that the draw condition works.
   */
  @Test
  public void testIsDraw() {
    Readable playThroughInput =
        new StringReader(validDimension + validShipPlacement + playThroughShots1
            + playThroughShots2
            + playThroughShots3 + playThroughShots4 + playThroughShots5 + playThroughShots6
            + playThroughShots7 + playThroughShots8 + playThroughShots9 + playThroughShots10
            + playThroughShots11 + playThroughShots12 + playThroughShots13
            + playThroughShots14 + playThroughShots15
            + playThroughShots16 + playThroughShots17 + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat
            + gameOverShotRepeat + gameOverShotRepeat + gameOverShotRepeat + playThroughShots18);

    Appendable playThroughOutput = new StringBuilder("""
        Hello! Welcome to the OOD BattleSalvo Game!
        Please enter a valid height and width below:
        ------------------------------------------------------
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed size 6.
        ------------------------------------------------------
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  -  -  S\s
        4   -  C  B  -  -  -\s
        5   -  C  B  -  -  -\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   -  -  -  -  -  -\s
        3   -  -  -  -  -  -\s
        4   -  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   -  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   -  C  B  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  B  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  -  -  -  -\s
        2   X  X  -  -  -  -\s
        3   X  -  -  -  -  -\s
        4   X  -  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  C  D  D  D  D\s
        1   -  C  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   -  C  B  *  -  -\s
        5   *  C  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  -  -  -\s
        2   X  X  X  -  -  -\s
        3   X  X  -  -  -  -\s
        4   X  X  -  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  D  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  -  S\s
        3   *  C  X  *  -  S\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  X  -  -\s
        2   X  X  X  X  -  -\s
        3   X  X  X  -  -  -\s
        4   X  X  X  -  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  C  B  *  -  -\s
        5   *  X  X  -  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   -  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  -  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  D  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  B  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  B  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  -  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  *  S\s
        2   -  C  B  -  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  -  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  D  X  D\s
        1   -  X  B  -  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  D\s
        1   -  X  B  -  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  D\s
        1   -  X  B  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  B  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  -  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  B  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  C  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  S\s
        2   -  C  B  *  *  X\s
        3   *  X  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  S\s
        2   -  C  X  *  *  X\s
        3   *  X  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  S\s
        2   -  X  X  *  *  X\s
        3   *  X  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  -\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        Please enter your shots in the format [col, row].
        Human Board:
            0  1  2  3  4  5
        0   *  X  X  X  X  X\s
        1   -  X  X  *  *  X\s
        2   -  X  X  *  *  X\s
        3   *  X  X  *  *  X\s
        4   *  X  X  *  *  -\s
        5   *  X  X  *  -  *\s
        Computer Board:
            0  1  2  3  4  5
        0   *  -  -  -  -  -\s
        1   -  -  X  X  X  -\s
        2   X  X  X  X  X  X\s
        3   X  X  X  X  X  -\s
        4   X  X  X  X  -  -\s
        5   -  -  -  -  -  -\s
        It's a tie! You both sunk each other's ships!
        """);
    this.controller = new BattleShipController(playThroughInput, view, seededRandom);
    this.controller.playGame();
    assertEquals(playThroughOutput.toString(), output.toString());


  }


}
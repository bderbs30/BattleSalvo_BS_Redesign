package cs3500.pa04.controller;


import cs3500.pa04.model.other.BattleShipDataModel;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.other.Validation;
import cs3500.pa04.model.player.ComputerPlayer;
import cs3500.pa04.model.player.HumanPlayer;
import cs3500.pa04.model.player.Player;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a controller for the game of BattleSalvo.
 */
public class BattleShipController implements Controller {

  private final Readable readable;
  private Appendable appendable;

  private Scanner scanner;

  private final View view;

  private int width;

  private int height;

  private BattleShipDataModel model;

  private Player humanPlayer;

  private Player computerPlayer;

  private Map<ShipType, Integer> specifications;

  private final Validation validation = new Validation();

  private List<Integer> shipNumbers;

  private List<Coord> currentPlayerShots;

  private List<Ship> humanShips;

  private List<Ship> computerShips;

  private Random rand;


  /**
   * Constructs a controller for the game of BattleSalvo.
   *
   * @param input the input to read from
   * @param view  the view to render to
   */
  public BattleShipController(Readable input, View view) {
    this.readable = Objects.requireNonNull(input);
    this.scanner = new Scanner(input);
    this.view = view;
    currentPlayerShots = new ArrayList<>();
    model = new BattleShipDataModel();
    specifications = new HashMap<>();
    shipNumbers = new ArrayList<>();
    width = 0;
    height = 0;
    humanShips = new ArrayList<>();
    computerShips = new ArrayList<>();
    computerPlayer = new ComputerPlayer(view);
    humanPlayer = new HumanPlayer(model, view);
  }

  /**
   * Constructs a controller for the game of BattleSalvo. Testing purposes only.
   *
   * @param input the input to read from
   * @param view  the view to render to
   * @param rand  the random number generator
   */
  public BattleShipController(Readable input, View view, Random rand) {
    this.readable = Objects.requireNonNull(input);
    this.scanner = new Scanner(input);
    this.view = view;
    currentPlayerShots = new ArrayList<>();
    model = new BattleShipDataModel();
    specifications = new HashMap<>();
    shipNumbers = new ArrayList<>();
    width = 0;
    height = 0;
    humanShips = new ArrayList<>();
    computerShips = new ArrayList<>();
    this.rand = rand;
    computerPlayer = new ComputerPlayer(view, rand);
    humanPlayer = new HumanPlayer(model, view, rand);
  }


  /**
   * Plays the game of BattleSalvo.
   */
  public void playGame() {
    promptDimensions();
    promptShipNumbers();

    view.renderMessage("Human Board:\n");
    humanShips = humanPlayer.setup(height, width, specifications);
    view.renderMessage("Computer Board:\n");
    computerShips = computerPlayer.setup(height, width, specifications);
    // setup loop completed!

    while (!isGameOver(humanShips, computerShips)) {
      currentPlayerShots.clear();
      promptShots();
      model.setCurrentShots(currentPlayerShots);
      List<Coord> humanShots = humanPlayer.takeShots(); // get human shots
      List<Coord> computerShots = computerPlayer.takeShots(); // get computer shots
      view.renderMessage("Human Board:\n");
      List<Coord> shotsThatHitHuman = humanPlayer.reportDamage(computerShots);
      view.renderMessage("Computer Board:\n");
      List<Coord> shotsThatHitComputer = computerPlayer.reportDamage(humanShots);
      computerPlayer.successfulHits(shotsThatHitHuman); // report to the computer which shots hit
      humanPlayer.successfulHits(shotsThatHitComputer);
    }
  }

  /**
   * Prompts the user for the dimensions of the board.
   * Checks to make sure the dimensions are valid. (Not negative and between 6 and 15 inclusive)
   */
  private void promptDimensions() {
    view.renderMessage("Hello! Welcome to the OOD BattleSalvo Game!\n");
    view.renderMessage("Please enter a valid height and width below:\n");
    view.renderMessage("------------------------------------------------------\n");
    boolean validInput = false;
    while (!validInput) {

      String input = scanner.nextLine();
      String[] dimensions = input.split(" ");

      if (dimensions.length != 2) {
        view.renderMessage("Invalid input. Please enter both height and width.\n");
        continue; // skip this iteration
      }
      try {
        height = Integer.parseInt(dimensions[0]);
        width = Integer.parseInt(dimensions[1]);

        if (!validation.isValidUserDimension(height, 6, 15)
            || !validation.isValidUserDimension(width, 6, 15)) {
          view.renderMessage("Uh Oh! You've entered invalid dimensions. "
              + "Please remember that the height and width\n");
          view.renderMessage("of the game must be in the range (6, 15), inclusive. Try again!\n");

        } else {
          validInput = true;
        }
      } catch (NumberFormatException e) {
        view.renderMessage("Invalid input. Please enter valid integers for height and width.\n");
      }
    }
  }

  /**
   * Prompts the user for the number of ships they want to play with.
   * Checks to make sure the number of ships is valid.
   */
  private void promptShipNumbers() {
    int numShipTypes = ShipType.values().length;
    int maxNumShips = Math.min(width, height);
    view.renderMessage("Please enter your fleet in the order [Carrier, Battleship, Destroyer, "
        + "Submarine].\n");
    view.renderMessage(
        "Remember, your fleet may not exceed size " + maxNumShips + ".\n");
    view.renderMessage("------------------------------------------------------\n");
    boolean validInput = false;
    while (!validInput) {

      String input = scanner.nextLine();
      String[] shipNumberInput = input.split(" ");

      if (shipNumberInput.length != numShipTypes) {
        view.renderMessage("Invalid input. Please enter an Integer for all ShipTypes.\n");
        continue; // skip this iteration
      }
      validInput = shipNumbersHelper(shipNumberInput, maxNumShips);
      if (validInput) {
        for (int i = 0; i < shipNumbers.size(); i++) {
          specifications.put(ShipType.values()[i], shipNumbers.get(i));
        }
      }
    }
  }

  /**
   * Prompts the user for the coordinates of their shots.
   * Checks to make sure the coordinates are within the bounds of the board.
   */
  private void promptShots() {
    view.renderMessage("Please enter your shots in the format [col, row].\n");
    int allowedShots = numAliveShips(humanShips);
    for (int i = 0; i < allowedShots; i++) {
      boolean validInput = false;
      while (!validInput) {

        String input = scanner.nextLine();
        String[] shotInput = input.split(" ");

        if (shotInput.length != 2) {
          view.renderMessage("Invalid input. Please enter both row and col.\n");
          continue;
        }
        try {
          int col = Integer.parseInt(shotInput[0]);
          int row = Integer.parseInt(shotInput[1]);

          if (!validation.isValidUserShot(row, col, height, width)) {
            view.renderMessage(
                "Invalid coordinates, must be in the range (0, " + (height - 1) + ") and (0, "
                    + (width - 1) + "), respectively. Try again!\n");
          } else {
            currentPlayerShots.add(new Coord(col, row));
            validInput = true;
          }
        } catch (NumberFormatException e) {
          view.renderMessage("Invalid input. Please enter valid integers for row and col.\n");
        }
      }
    }
  }

  /**
   * Helper method for promptShipNumbers.
   *
   * @param shipNumberInput - the list of integer input from the user
   * @param maxNumShips     - the maximum number of ships allowed
   * @return true if the input is valid, false otherwise
   */
  private boolean shipNumbersHelper(String[] shipNumberInput, int maxNumShips) {
    try {
      for (String s : shipNumberInput) {
        shipNumbers.add(Integer.parseInt(s));
      }
      if (!validation.isValidShipNumbers(shipNumbers, maxNumShips)) {
        view.renderMessage("Uh Oh! You've entered invalid ship numbers. "
            + "Please remember that the number of ships\n");
        view.renderMessage(
            "must be in the range (4, " + maxNumShips + "), inclusive. Try again!\n");
        shipNumbers.clear();
      } else {
        return true;
      }
    } catch (Exception e) {
      view.renderMessage("Invalid input. Please enter valid integers for ship numbers.\n");
    }
    return false;
  }

  private int numAliveShips(List<Ship> ships) {
    List<Ship> aliveShips = new ArrayList<>();
    for (Ship s : ships) {
      if (!s.isSunk()) {
        aliveShips.add(s);
      }
    }
    return aliveShips.size();
  }

  /**
   * Checks if the game is over. || Made public for testing purposes. ||
   *
   * @return true if the game is over, false otherwise
   */
  private boolean isGameOver(List<Ship> humanShips, List<Ship> computerShips) {
    if (numAliveShips(computerShips) == 0 && numAliveShips(humanShips) == 0) {
      view.renderMessage("It's a tie! You both sunk each other's ships!\n");
      return true;
    } else if (numAliveShips(computerShips) == 0 && numAliveShips(humanShips) > 0) {
      view.renderMessage("Congratulations! You sunk all of the computer's ships!\n");
      return true;
    } else if (numAliveShips(computerShips) > 0 && numAliveShips(humanShips) == 0) {
      view.renderMessage("Uh oh! The computer sunk all of your ships!\n");
      return true;
    } else {
      return false;
    }
  }

}


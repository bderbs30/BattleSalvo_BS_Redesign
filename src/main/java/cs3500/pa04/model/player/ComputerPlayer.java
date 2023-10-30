package cs3500.pa04.model.player;

import cs3500.pa04.model.board.Board;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an AI player in the game of Battleship.
 */
public class ComputerPlayer extends AbstractPlayer {


  private List<Coord> availableCoords;


  /**
   * Constructs a computer player with the given height, width, and view.
   *
   * @param view the view of the game
   */
  public ComputerPlayer(View view) {
    super(view);

    this.hitsFromLastSalvo = new ArrayList<>();
    this.missesFromLastSalvo = new ArrayList<>();
    this.shotsFromLastSalvo = new ArrayList<>();
  }

  /**
   * Constructs a computer player with the given height, width, and view. Testing purposes only.
   *
   * @param view the view of the game
   * @param rand the random number generator
   */
  public ComputerPlayer(View view, Random rand) {
    super(view, rand);
    this.hitsFromLastSalvo = new ArrayList<>();
    this.missesFromLastSalvo = new ArrayList<>();
    this.shotsFromLastSalvo = new ArrayList<>();
  }

  /**
   * Returns the name of this player.
   *
   * @return the name of this player
   */
  @Override
  public String name() {
    return "Computer Player";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return a list of ships with their locations on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {

    this.thisBoard = new Board(height, width, this.rand);
    this.otherBoard = new Board(height, width, this.rand);
    this.availableCoords = otherBoard.generateAvailableCoords();
    List<Ship> tempShips = thisBoard.placeShips(specifications);
    view.renderBoard(otherBoard.toString());
    return tempShips;
  }


  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    shotsFromLastSalvo.clear(); // clear the list of shots from the last salvo
    for (int i = 0; i < thisBoard.getAliveShips().size(); i++) {
      if (availableCoords.isEmpty()) {
        break; // if no more available coordinates don't try to get an index from an empty list
      }
      int randIndex = rand.nextInt(availableCoords.size());
      if (availableCoords.size() > 0) { // if there are still available coordinates
        shotsFromLastSalvo.add(availableCoords.remove(randIndex));
      } else {
        break;
      }
    }
    return shotsFromLastSalvo;
  }

  /**
   * Updates the damage on a mirror board for the human to observe.
   *
   * @param misses the locations of misses on the opponent's board
   * @param hits   the locations of hits on the opponent's board
   */
  @Override
  protected void updateDamage(List<Coord> misses, List<Coord> hits) {
    otherBoard.updateBoard(misses, hits);
    view.renderBoard(otherBoard.toString());
  }
}

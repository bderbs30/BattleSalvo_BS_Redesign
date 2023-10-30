package cs3500.pa04.model.player;

import cs3500.pa04.model.board.Board;
import cs3500.pa04.model.other.BattleShipDataModel;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.view.View;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a human player in the game of Battleship.
 */
public class HumanPlayer extends AbstractPlayer {

  private BattleShipDataModel model;

  /**
   * Constructs a HumanPlayer object.
   *
   * @param model the model
   * @param view  the view
   */
  public HumanPlayer(BattleShipDataModel model, View view) {
    super(view);
    this.model = model;
  }

  /**
   * Constructs a HumanPlayer object. For testing
   *
   * @param model the model to get the shots from
   * @param view  the view to display the board
   * @param rand  the random number generator
   */
  public HumanPlayer(BattleShipDataModel model, View view, Random rand) {
    super(view, rand);
    this.model = model;
  }

  /**
   * Returns the name of this player.
   *
   * @return the name of this player
   */
  @Override
  public String name() {
    return "Human Player";
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
    final List<Ship> tempShips = thisBoard.placeShips(specifications);
    view.renderBoard(thisBoard.toString());
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
    return model.getCurrentShots();
  }


  /**
   * Updates the damage on this player's board.
   *
   * @param misses the list of misses
   * @param hits   the list of hits
   */
  @Override
  protected void updateDamage(List<Coord> misses, List<Coord> hits) {
    thisBoard.updateBoard(misses, hits);
    view.renderBoard(thisBoard.toString());
  }


}

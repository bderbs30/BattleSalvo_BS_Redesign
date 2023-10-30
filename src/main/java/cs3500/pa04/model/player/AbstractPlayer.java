package cs3500.pa04.model.player;

import cs3500.pa04.model.board.Board;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.other.GameResult;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an abstract player in the game of Battleship.
 */
public abstract class AbstractPlayer implements Player {

  protected Random rand;

  protected Board thisBoard; // this meaning this instance's board

  protected Board otherBoard; // other meaning the other player's board viewed by this player

  protected View view;

  /**
   * The hits from the last salvo.
   */
  protected List<Coord> hitsFromLastSalvo; // used in improved AI

  /**
   * The misses from the last salvo.
   */
  protected List<Coord> missesFromLastSalvo; // used in improved AI

  /**
   * The shots from the last salvo.
   */
  protected List<Coord> shotsFromLastSalvo; // used in improved AI

  /**
   * Constructs an AbstractPlayer object.
   *
   * @param view the view
   */
  public AbstractPlayer(View view) {
    this.hitsFromLastSalvo = new ArrayList<>();
    this.missesFromLastSalvo = new ArrayList<>();
    this.shotsFromLastSalvo = new ArrayList<>();
    this.rand = new Random();
    this.view = view;
  }

  /**
   * Constructs an AbstractPlayer object. Testing purposes only.
   *
   * @param view the view
   * @param rand the random number generator
   */
  public AbstractPlayer(View view, Random rand) {
    this.hitsFromLastSalvo = new ArrayList<>();
    this.missesFromLastSalvo = new ArrayList<>();
    this.shotsFromLastSalvo = new ArrayList<>();
    this.rand = rand;
    this.view = view;
  }


  @Override
  public abstract String name();

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public abstract List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications);


  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a list of the coordinates of shots that hit a ship on this player's board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Ship> shipsToCheck = thisBoard.getAliveShips();
    List<Coord> hits = new ArrayList<>();
    List<Coord> misses = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      for (Ship s : shipsToCheck) {
        if (s.takeHit(c)) {
          hits.add(c);
        } else {
          misses.add(c);
        }
      }
    }
    updateDamage(misses, hits);
    return hits;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    this.hitsFromLastSalvo = shotsThatHitOpponentShips;
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    view.renderMessage(result.toString() + " " + reason);
  }

  /**
   * Updates the damage on the board. Helper to abstract duplicate code
   *
   * @param misses the list of misses
   * @param hits   the list of hits
   */
  protected abstract void updateDamage(List<Coord> misses, List<Coord> hits);
}

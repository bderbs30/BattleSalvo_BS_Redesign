package cs3500.pa04.model.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data model for the game of BattleShip.
 */
public class BattleShipDataModel {

  private List<Coord> currentShots;

  /**
   * Constructs a BattleShipDataModel object.
   */
  public BattleShipDataModel() {
    this.currentShots = new ArrayList<>();
  }

  /**
   * Sets the current shots.
   *
   * @param currentShots the current shots
   */
  public void setCurrentShots(List<Coord> currentShots) {
    this.currentShots = currentShots;
  }

  /**
   * Gets the current shots.
   *
   * @return the current shots
   */
  public List<Coord> getCurrentShots() {
    return currentShots;
  }
}

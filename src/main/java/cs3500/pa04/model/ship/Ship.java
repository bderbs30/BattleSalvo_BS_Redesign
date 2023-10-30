package cs3500.pa04.model.ship;

import cs3500.pa04.model.other.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship in the game of Battleship.
 */
public class Ship {

  private final ShipType type;
  private List<Coord> shipCoords;
  private List<Coord> hits;


  /**
   * Constructs a Ship object.
   *
   * @param type       the type of the ship
   * @param shipCoords the coordinates of the ship
   */
  public Ship(ShipType type, List<Coord> shipCoords) {
    this.type = type;
    this.shipCoords = shipCoords;
    this.hits = new ArrayList<>();
  }


  /**
   * Gets the coordinates of the ship.
   *
   * @return the coordinates of the ship
   */
  public List<Coord> getCoords() {
    return shipCoords;
  }

  /**
   * Determines if the ship has been sunk.
   *
   * @return true if all the ship's coordinates have been hit, false otherwise
   */
  public boolean isSunk() {
    return hits.size() == type.getSize();
  }

  /**
   * Determines if the ship has been hit at the given coordinate.
   *
   * @param coord the given coordinate to check
   * @return true if the ship has been hit at the given coordinate, false otherwise
   */
  public boolean takeHit(Coord coord) {
    if (shipCoords.contains(coord) && !hits.contains(coord)) {
      hits.add(coord);
      return true;
    }
    return false;
  }
}



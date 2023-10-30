package cs3500.pa04.model.ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.other.Coord;
import java.util.List;

/**
 * Represents the shipAdapter used to adapt the ship to the format need for the server.
 */
public class ShipAdapter {

  private final Coord coord;
  private final int length;
  private final Direction direction;

  /**
   * Constructs a shipAdapter with the given ship.
   *
   * @param ship the ship to adapt
   */
  public ShipAdapter(Ship ship) {
    this.coord = ship.getCoords().get(0); // the first coord in the list of coords for a ship
    this.length = ship.getCoords().size(); // the number of coords in the list of coords for a ship
    // if the next coord in the list of coords for a ship has a greater x value than the first coord
    // in the list of coords for a ship, then the ship is horizontal
    List<Coord> shipCoords = ship.getCoords();
    Coord firstCoord = shipCoords.get(0);
    Coord secondCoord = shipCoords.get(1);
    if (secondCoord.getX() > firstCoord.getX()) {
      this.direction = Direction.HORIZONTAL;
    } else {
      this.direction = Direction.VERTICAL;
    }
  }


  /**
   * Constructs a shipAdapter with the given coord, length, and direction.
   *
   * @param coord     the coord of the ship
   * @param length    the length of the ship
   * @param direction the direction of the ship
   */
  @JsonCreator
  public ShipAdapter(
      @JsonProperty("coord") Coord coord,
      @JsonProperty("length") int length,
      @JsonProperty("direction") Direction direction) {
    this.coord = coord;
    this.length = length;
    this.direction = direction;
  }

  /**
   * Gets the coord of the ship.
   *
   * @return the coord of the ship
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * Gets the length of the ship.
   *
   * @return the length of the ship
   */
  public int getLength() {
    return length;
  }

  /**
   * Gets the direction of the ship.
   *
   * @return the direction of the ship
   */
  public Direction getDirection() {
    return direction;
  }
}

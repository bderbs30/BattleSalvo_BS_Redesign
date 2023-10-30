package cs3500.pa04.model.ship;

/**
 * Represents the type of ship.
 */
public enum ShipType {


  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);


  private final int size;

  ShipType(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }
}

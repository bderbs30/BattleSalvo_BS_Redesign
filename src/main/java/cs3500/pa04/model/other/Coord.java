package cs3500.pa04.model.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a coordinate on the board.
 */
public class Coord {

  private final int coordX;
  private final int coordY;

  /**
   * Constructs a coordinate with the given x and y values
   *
   * @param coordX the x value of the coordinate
   * @param coordY the y value of the coordinate
   */
  @JsonCreator
  public Coord(@JsonProperty("x") int coordX,
               @JsonProperty("y") int coordY) {
    this.coordX = coordX;
    this.coordY = coordY;
  }

  /**
   * Returns the x value of the coordinate.
   *
   * @return the x value of the coordinate
   */
  public int getX() {
    return coordX;
  }

  /**
   * Returns the y value of the coordinate.
   *
   * @return the y value of the coordinate
   */
  public int getY() {
    return coordY;
  }

  /**
   * Overrides the equals method to compare two coordinates.
   *
   * @param o the object to compare to
   * @return true if the coordinates are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Coord that)) {
      return false;
    } else {
      return this.coordX == that.coordX && this.coordY == that.coordY;
    }
  }
}

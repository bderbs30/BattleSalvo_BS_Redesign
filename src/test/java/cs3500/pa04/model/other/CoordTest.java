package cs3500.pa04.model.other;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa04.model.other.Coord;
import org.junit.jupiter.api.Test;

/**
 * Tests the Coord class.
 */
public class CoordTest {

  /**
   * Tests the getCoordX method.
   */
  @Test
  public void testGetCoordX() {
    Coord coord = new Coord(3, 4);
    assertEquals(3, coord.getX());
  }

  /**
   * Tests the getCoordY method.
   */
  @Test
  public void testGetCoordY() {
    Coord coord = new Coord(3, 4);
    assertEquals(4, coord.getY());
  }

  /**
   * Tests the equals method.
   */
  @Test
  public void testEquals() {
    Coord coord1 = new Coord(3, 4);
    Coord coord2 = new Coord(3, 4);
    Coord coord3 = new Coord(5, 6);

    assertEquals(coord1, coord2);
    assertNotEquals(coord1, coord3);
  }
}
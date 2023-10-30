package cs3500.pa04.model.ship;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests the Ship class.
 */
public class ShipTest {

  @Test
  public void testGetCoords() {
    List<Coord> coords =
        new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
            new Coord(1, 3)));
    Ship ship = new Ship(ShipType.SUBMARINE, coords);
    assertEquals(coords, ship.getCoords());
  }

  @Test
  public void testIsSunk() {
    List<Coord> coords = new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
        new Coord(1, 3)));
    Ship ship = new Ship(ShipType.SUBMARINE, coords);

    assertFalse(ship.isSunk());

    ship.takeHit(new Coord(1, 1));
    assertFalse(ship.isSunk());

    ship.takeHit(new Coord(1, 2));
    assertFalse(ship.isSunk());

    ship.takeHit(new Coord(1, 3));
    assertTrue(ship.isSunk());
  }

  @Test
  public void testTakeHit() {
    List<Coord> coords =
        new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(1, 2),
            new Coord(1, 3)));
    Ship ship = new Ship(ShipType.SUBMARINE, coords);

    assertFalse(ship.takeHit(new Coord(2, 2))); // Invalid hit

    assertTrue(ship.takeHit(new Coord(1, 1))); // Valid hit
    assertFalse(ship.takeHit(new Coord(1, 1))); // Already hit
  }

}
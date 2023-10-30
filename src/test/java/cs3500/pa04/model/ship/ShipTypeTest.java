package cs3500.pa04.model.ship;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.ship.ShipType;
import org.junit.jupiter.api.Test;


/**
 * Tests the ShipType class.
 */
public class ShipTypeTest {

  @Test
  public void testShipTypeValues() {
    assertEquals(6, ShipType.CARRIER.getSize());
    assertEquals(5, ShipType.BATTLESHIP.getSize());
    assertEquals(4, ShipType.DESTROYER.getSize());
    assertEquals(3, ShipType.SUBMARINE.getSize());
  }
}



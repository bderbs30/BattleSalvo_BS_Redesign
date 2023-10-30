package cs3500.pa04.model.other;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.other.Validation;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationTest {

  private Validation validation;


  private int width;
  private int height;
  private int shotX1;
  private int shotY1;
  private int shotX2;
  private int shotY2;
  private int shipTypeAmt1;
  private int shipTypeAmt2;
  private int shipTypeAmt3;
  private int shipTypeAmt4;
  private int shipTypeAmt5;
  private List<Integer> shipNumbersBig;
  private List<Integer> shipNumbersSmall;
  private int maxShips;

  @BeforeEach
  public void setup() {
    validation = new Validation();

    width = 10;
    height = 10;

    shotX1 = 0;
    shotY1 = 0;

    shotX2 = 20;
    shotY2 = 20;

    shipTypeAmt1 = 1;
    shipTypeAmt2 = 2;
    shipTypeAmt3 = 3;
    shipTypeAmt4 = 1;
    shipTypeAmt5 = 9;

    maxShips = 10;

    shipNumbersBig = List.of(shipTypeAmt2, shipTypeAmt3, shipTypeAmt4, shipTypeAmt5);
    shipNumbersSmall = List.of(shipTypeAmt1, shipTypeAmt1, shipTypeAmt3, shipTypeAmt4);
  }

  @Test
  public void testIsValidUserShot() {
    assertTrue(validation.isValidUserShot(shotX1, shotY1, height, width));
    assertFalse(validation.isValidUserShot(shotX2, shotY2, height, width));
  }

  @Test
  public void testIsValidShipNumber() {
    assertTrue(validation.isValidShipNumbers(shipNumbersSmall, maxShips));
    assertFalse(validation.isValidShipNumbers(shipNumbersBig, maxShips));
  }

  @Test
  public void testIsValidUserDimension() {
    assertTrue(validation.isValidUserDimension(height, 6, 15));
    assertTrue(validation.isValidUserDimension(width, 6, 15));
    assertFalse(validation.isValidUserDimension(20, 5, 15));
  }


}
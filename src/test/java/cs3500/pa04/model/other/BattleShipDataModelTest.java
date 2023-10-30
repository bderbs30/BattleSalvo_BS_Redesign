package cs3500.pa04.model.other;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the BattleShipDataModel class.
 */
public class BattleShipDataModelTest {
  private BattleShipDataModel dataModel;

  @BeforeEach
  public void setUp() {
    dataModel = new BattleShipDataModel();
  }

  @Test
  public void testGetCurrentShots_EmptyList() {
    List<Coord> currentShots = dataModel.getCurrentShots();

    assertNotNull(currentShots);
    assertTrue(currentShots.isEmpty());
  }

  @Test
  public void testSetCurrentShots() {
    List<Coord> shots = new ArrayList<>(Arrays.asList(new Coord(1, 1), new Coord(2, 2)));

    dataModel.setCurrentShots(shots);
    List<Coord> currentShots = dataModel.getCurrentShots();

    assertEquals(shots, currentShots);
  }
}
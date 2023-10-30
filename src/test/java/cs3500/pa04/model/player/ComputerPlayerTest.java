package cs3500.pa04.model.player;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.view.BattleShipView;
import cs3500.pa04.view.View;
import org.junit.jupiter.api.Test;

/**
 * Tests the ComputerPlayer class.
 */
public class ComputerPlayerTest {
  @Test
  public void testName() {
    View view = new BattleShipView(System.out);

    ComputerPlayer computerPlayer = new ComputerPlayer(view);

    String expectedName = "Computer Player";
    String actualName = computerPlayer.name();

    assertEquals(expectedName, actualName);
  }

}
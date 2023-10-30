package cs3500.pa04.model.other;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.other.GameResult;
import org.junit.jupiter.api.Test;

class GameResultTest {

  @Test
  public void testGameResultValues() {
    assertEquals("WIN", GameResult.WIN.name());
    assertEquals("LOSE", GameResult.LOSE.name());
    assertEquals("DRAW", GameResult.DRAW.name());
  }


}
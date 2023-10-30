package cs3500.pa04.model.board;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.board.CellState;
import org.junit.jupiter.api.Test;

class CellStateTest {

  @Test
  public void testCellStateValues() {
    assertEquals(" C ", CellState.CARRIER.getCharIndicator());
    assertEquals(" B ", CellState.BATTLESHIP.getCharIndicator());
    assertEquals(" D ", CellState.DESTROYER.getCharIndicator());
    assertEquals(" S ", CellState.SUBMARINE.getCharIndicator());
    assertEquals(" X ", CellState.HIT.getCharIndicator());
    assertEquals(" * ", CellState.MISS.getCharIndicator());
    assertEquals(" - ", CellState.HIDDEN.getCharIndicator());
  }

  @Test
  public void testCellStateNames() {
    assertEquals("CARRIER", CellState.CARRIER.name());
    assertEquals("BATTLESHIP", CellState.BATTLESHIP.name());
    assertEquals("DESTROYER", CellState.DESTROYER.name());
    assertEquals("SUBMARINE", CellState.SUBMARINE.name());
    assertEquals("HIT", CellState.HIT.name());
    assertEquals("MISS", CellState.MISS.name());
    assertEquals("HIDDEN", CellState.HIDDEN.name());
  }
}
package cs3500.pa04.model.board;

/**
 * Represents the state of a cell on the board.
 */
public enum CellState {
  CARRIER(" C "),
  BATTLESHIP(" B "),
  DESTROYER(" D "),
  SUBMARINE(" S "),
  HIT(" X "),
  MISS(" * "),
  HIDDEN(" - ");

  private final String charRepresentation;

  CellState(String state) {
    this.charRepresentation = state;
  }

  public String getCharIndicator() {
    return this.charRepresentation;
  }
}


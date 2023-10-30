package cs3500.pa04.view;

import java.io.IOException;

/**
 * Represents a view for the game of Battleship.
 */
public class BattleShipView implements View {

  private final Appendable appendable;

  /**
   * Constructs a BattleShipView object.
   *
   * @param output - the output to use
   */
  public BattleShipView(Appendable output) {

    this.appendable = output;
  }

  /**
   * Renders the given message.
   *
   * @param message - the message to render
   */
  @Override
  public void renderMessage(String message) {
    try {
      this.appendable.append(message);
    } catch (Exception e) {
      throw new IllegalStateException("Could not render message");
    }
  }

  /**
   * Renders the given board.
   *
   * @param board - the board to render
   */
  public void renderBoard(String board) {
    try {
      this.appendable.append(board);
    } catch (IOException e) {
      throw new IllegalStateException("Could not render board");
    }
  }
}








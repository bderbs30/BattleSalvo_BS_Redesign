package cs3500.pa04.model.other;

import java.util.List;

/**
 * A class that contains methods for validating user input.
 */
public class Validation {

  /**
   * Checks if the given dimension is valid for a board.
   *
   * @param dimension the dimension to check
   * @param min       the minimum dimension
   * @param max       the maximum dimension
   * @return true if the dimension is valid, false otherwise
   */
  public boolean isValidUserDimension(int dimension, int min, int max) {
    return dimension <= max && dimension >= min;
  }

  /**
   * Checks if the given input for fleet specifications is valid.
   *
   * @param shipNumbers the list of ship numbers inputted by the user
   * @param maxNumShips the maximum number of ships allowed
   * @return true if the input is valid, false otherwise
   */
  public boolean isValidShipNumbers(List<Integer> shipNumbers, int maxNumShips) {
    int sum = 0;
    for (Integer shipNumber : shipNumbers) {
      if (shipNumber < 1) {
        return false;
      }
      sum += shipNumber;
    }
    return sum <= maxNumShips;


  }

  /**
   * Checks if the user shot is valid.
   *
   * @param row    the row of the shot
   * @param col    the column of the shot
   * @param height the height of the board
   * @param width  the width of the board
   * @return true if the shot is valid, false otherwise
   */
  public boolean isValidUserShot(int row, int col, int height, int width) {
    return row >= 0 && row < height && col >= 0 && col < width;
  }
}

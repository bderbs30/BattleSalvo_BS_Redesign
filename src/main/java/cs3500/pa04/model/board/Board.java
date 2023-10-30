package cs3500.pa04.model.board;

import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a board in the game of Battleship.
 */
public class Board {

  private CellState[][] board;

  private int boardHeight;

  private int boardWidth;

  private List<Ship> ships;

  private Random rand;

  private List<Coord> shipAvailableCoords; // the coordinates that are available to place ships on

  /**
   * Constructs a Board object.
   *
   * @param height the height of the board
   * @param width  the width of the board
   * @param rand   the random object
   */
  public Board(int height, int width, Random rand) {
    this.ships = new ArrayList<>();
    this.rand = rand;
    this.board = new CellState[height][width];
    this.boardHeight = height;
    this.boardWidth = width;
    initializeGrid();
    this.shipAvailableCoords = generateAvailableCoords();
  }

  /**
   * Initializes the grid with hidden cells. This is the default
   * state of the board with no ships or actions taken.
   */
  public void initializeGrid() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        this.board[i][j] = CellState.HIDDEN;
      }
    }
  }

  /**
   * Gets the alive ships on the board. Will be used in the TakeShots method.
   *
   * @return the alive ships on the board
   */
  public List<Ship> getAliveShips() {
    List<Ship> aliveShips = new ArrayList<>();
    for (Ship s : this.ships) {
      if (!s.isSunk()) {
        aliveShips.add(s);
      }
    }
    return aliveShips;
  }

  /**
   * Gets the available coordinates to shoot at.
   *
   * @return the available coordinates to shoot at
   */
  public List<Coord> generateAvailableCoords() {
    List<Coord> availableShots = new ArrayList<>();
    for (int y = 0; y < this.boardHeight; y++) {
      for (int x = 0; x < this.boardWidth; x++) {
        availableShots.add(new Coord(x, y));
      }
    }
    return availableShots;
  }

  /**
   * Converts the board to a string.
   *
   * @return the board as a string
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("  ");
    for (int x = 0; x < this.boardWidth; x++) {
      if (x > 9) {
        sb.append(" ").append(x);
      } else {
        sb.append("  ").append(x);
      }
    }
    sb.append("\n");
    for (int y = 0; y < this.boardHeight; y++) {
      // for each row
      if (y > 9) {
        sb.append(y).append(" ");
      } else {
        sb.append(y).append("  ");
      }
      for (int j = 0; j < this.boardWidth; j++) { // for each column
        sb.append(this.board[y][j].getCharIndicator());
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * Places the ships on the board.
   *
   * @param specifications the specifications of the ships
   * @return the list of ships
   */
  public List<Ship> placeShips(Map<ShipType, Integer> specifications) {
    for (ShipType type : ShipType.values()) { // for each ship type
      for (int i = 0; i < specifications.get(type); i++) { // for the amount of ships in that type
        for (int j = 0; j < 1000; j++) { // try a lot of times to place the ship
          Coord coord = this.shipAvailableCoords.get(rand.nextInt(this.shipAvailableCoords.size()));
          int x = coord.getX();
          int y = coord.getY();
          boolean isHorizontal = rand.nextBoolean();
          if (isHorizontal) { // horizontal
            if (x + type.getSize() <= this.boardWidth) { // if the ship fits
              if (checkOverlap(x, y, type, isHorizontal)) { // if the ship doesn't overlap
                placeShip(x, y, type, isHorizontal);
                break;
              }
            }
          } else { // vertical
            if (y + type.getSize() <= this.boardHeight) { // if the ship fits
              if (checkOverlap(x, y, type, isHorizontal)) { // if the ship doesn't overlap
                placeShip(x, y, type, isHorizontal);
                break;
              }
            }
          }
        }
      }
    }
    return this.ships;
  }

  /**
   * Places a ship on the board.
   *
   * @param x            the starting x coordinate
   * @param y            the starting y coordinate
   * @param type         the type of ship
   * @param isHorizontal whether the ship is horizontal or not
   */
  public void placeShip(int x, int y, ShipType type, boolean isHorizontal) {
    List<Coord> shipCoords = new ArrayList<>();
    for (int i = 0; i < type.getSize(); i++) {
      if (isHorizontal) { // horizontal
        switch (type) {
          case CARRIER -> this.board[y][x + i] = CellState.CARRIER;
          case BATTLESHIP -> this.board[y][x + i] = CellState.BATTLESHIP;
          case DESTROYER -> this.board[y][x + i] = CellState.DESTROYER;
          case SUBMARINE -> this.board[y][x + i] = CellState.SUBMARINE;
          default -> {
          }
        }
        Coord tempCoord = new Coord(x + i, y);
        shipCoords.add(tempCoord);
      } else { // vertical
        switch (type) {
          case CARRIER -> this.board[y + i][x] = CellState.CARRIER;
          case BATTLESHIP -> this.board[y + i][x] = CellState.BATTLESHIP;
          case DESTROYER -> this.board[y + i][x] = CellState.DESTROYER;
          case SUBMARINE -> this.board[y + i][x] = CellState.SUBMARINE;
          default -> {
          }
        }
        Coord tempCoord = new Coord(x, y + i);
        shipCoords.add(tempCoord);
      }
    }
    Ship newShip = new Ship(type, shipCoords);
    removePlacedCoords(shipCoords);
    this.ships.add(newShip);
  }

  /**
   * Removes the coordinates that have been placed on.
   *
   * @param shipCoords the coordinates that have been placed on
   */
  public void removePlacedCoords(List<Coord> shipCoords) {
    for (Coord coord : shipCoords) {
      for (Coord availableCoord : this.shipAvailableCoords) {
        if (coord.equals(availableCoord)) {
          this.shipAvailableCoords.remove(coord);
          break;
        }
      }
    }
  }

  /**
   * Checks if a ship overlaps with another ship.
   *
   * @param x            the starting x coordinate
   * @param y            the starting y coordinate
   * @param type         the type of ship
   * @param isHorizontal whether the ship is horizontal or not
   * @return true if the ship doesn't overlap, false otherwise
   */
  public boolean checkOverlap(int x, int y, ShipType type, boolean isHorizontal) {
    for (int i = 0; i < type.getSize(); i++) {
      if (isHorizontal) { // horizontal
        if (this.board[y][x + i] == CellState.SUBMARINE
            || this.board[y][x + i] == CellState.DESTROYER
            || this.board[y][x + i] == CellState.BATTLESHIP
            || this.board[y][x + i] == CellState.CARRIER) {
          return false;
        }
      } else { // vertical
        if (this.board[y + i][x] == CellState.SUBMARINE
            || this.board[y + i][x] == CellState.DESTROYER
            || this.board[y + i][x] == CellState.BATTLESHIP
            || this.board[y + i][x] == CellState.CARRIER) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Updates the board with the given misses and hits.
   * If the player shoots at the same spot twice, it will be remarked as a miss.
   *
   * @param misses the misses
   * @param hits   the hits
   */
  public void updateBoard(List<Coord> misses, List<Coord> hits) {
    for (Coord miss : misses) {
      this.board[miss.getY()][miss.getX()] = CellState.MISS;
    }
    for (Coord hit : hits) {
      this.board[hit.getY()][hit.getX()] = CellState.HIT;
    }
  }

  /**
   * Gets the board.
   *
   * @return the board
   */
  public CellState[][] getBoard() {
    return board;
  }
}




package cs3500.pa04.model.board;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.board.Board;
import cs3500.pa04.model.board.CellState;
import cs3500.pa04.model.other.Coord;
import cs3500.pa04.model.ship.Ship;
import cs3500.pa04.model.ship.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Board class.
 */
public class BoardTest {

  private Board board;
  private Board board2;

  private Map<ShipType, Integer> specifications = new HashMap<>();

  /**
   * Sets up the tests.
   */
  @BeforeEach
  public void setUp() {
    int height = 10;
    int width = 10;
    int height2 = 15;
    int width2 = 15;
    Random rand = new Random(1234); // Seed with a fixed value
    this.board = new Board(height, width, rand);
    this.board2 = new Board(height2, width2, rand);
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 2);

  }

  @Test
  public void testInitializeGrid() {
    CellState[][] expectedGrid = new CellState[10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        expectedGrid[i][j] = CellState.HIDDEN;
      }
    }
    CellState[][] expectedGrid2 = new CellState[15][15];
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 15; j++) {
        expectedGrid2[i][j] = CellState.HIDDEN;
      }
    }

    board.initializeGrid();
    board2.initializeGrid();

    assertArrayEquals(expectedGrid, board.getBoard());
    assertArrayEquals(expectedGrid2, board2.getBoard());
  }

  @Test
  public void testGetAliveShips() {
    List<Ship> aliveShips = board.getAliveShips();

    assertTrue(aliveShips.isEmpty());
    // adda ship to the ships field of board

    board.placeShips(specifications);

    List<Ship> aliveShipsUpdated = board.getAliveShips();
    assertEquals(6, aliveShipsUpdated.size());
  }

  @Test
  public void testPlaceShips() {

    List<Ship> ships = board.placeShips(specifications);

    assertEquals(6, ships.size());
  }

  @Test
  public void testToString() {
    String expectedString = """
            0  1  2  3  4  5  6  7  8  9
        0   -  -  -  -  -  -  -  -  -  -\s
        1   -  -  -  -  -  -  -  -  -  -\s
        2   -  -  -  -  -  -  -  -  -  -\s
        3   -  -  -  -  -  -  -  -  -  -\s
        4   -  -  -  -  -  -  -  -  -  -\s
        5   -  -  -  -  -  -  -  -  -  -\s
        6   -  -  -  -  -  -  -  -  -  -\s
        7   -  -  -  -  -  -  -  -  -  -\s
        8   -  -  -  -  -  -  -  -  -  -\s
        9   -  -  -  -  -  -  -  -  -  -\s
        """;

    String expectedString2 = """
            0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        0   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        1   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        2   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        3   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        4   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        5   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        6   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        7   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        8   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        9   -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        10  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        11  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        12  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        13  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        14  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -\s
        """;

    assertEquals(expectedString, board.toString());
    assertEquals(expectedString2, board2.toString());
  }

  @Test
  public void testUpdateBoard() {
    List<Coord> misses = new ArrayList<>(Arrays.asList(new Coord(0, 0), new Coord(1, 1)));
    List<Coord> hits = new ArrayList<>(Arrays.asList(new Coord(2, 2), new Coord(3, 3)));

    board.updateBoard(misses, hits);

    CellState[][] expectedBoard = new CellState[10][10];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        expectedBoard[i][j] = CellState.HIDDEN;
      }
    }
    expectedBoard[0][0] = CellState.MISS;
    expectedBoard[1][1] = CellState.MISS;
    expectedBoard[2][2] = CellState.HIT;
    expectedBoard[3][3] = CellState.HIT;

    assertArrayEquals(expectedBoard, board.getBoard());
  }

  @Test
  public void testCheckOverlap_NoOverlap() {
    board.initializeGrid();

    boolean result = board.checkOverlap(0, 0, ShipType.SUBMARINE, true);

    assertTrue(result);
  }

  @Test
  public void testCheckOverlap_HorizontalOverlap() {
    board.initializeGrid();
    board.getBoard()[0][1] = CellState.BATTLESHIP;
    board.getBoard()[0][2] = CellState.BATTLESHIP;

    board2.initializeGrid();
    board2.getBoard()[0][1] = CellState.SUBMARINE;
    board2.getBoard()[0][2] = CellState.SUBMARINE;

    board2.getBoard()[0][3] = CellState.SUBMARINE;
    board2.getBoard()[0][4] = CellState.SUBMARINE;


    boolean result = board.checkOverlap(1, 0, ShipType.SUBMARINE, true);
    boolean result2 = board2.checkOverlap(0, 0, ShipType.SUBMARINE, true);
    boolean result3 = board2.checkOverlap(3, 0, ShipType.BATTLESHIP, true);

    assertFalse(result);
    assertFalse(result2);
    assertFalse(result3);
  }

  @Test
  public void testCheckOverlap_VerticalOverlap() {
    board.initializeGrid();
    board.getBoard()[1][0] = CellState.CARRIER;
    board.getBoard()[2][0] = CellState.CARRIER;

    board2.initializeGrid();
    board2.getBoard()[1][0] = CellState.SUBMARINE;
    board2.getBoard()[2][0] = CellState.SUBMARINE;


    boolean result = board.checkOverlap(0, 0, ShipType.BATTLESHIP, false);
    boolean result2 = board2.checkOverlap(0, 0, ShipType.BATTLESHIP, false);

    assertFalse(result);
    assertFalse(result2);
  }
}
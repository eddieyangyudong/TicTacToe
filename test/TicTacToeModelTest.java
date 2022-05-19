import org.junit.Test;

import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the tic-tac-toe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1 = new TicTacToeModel();

  /** Test if the method move works */
  @Test
  public void testMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
  }

  /** Test the if the starter player is Player X */
  @Test
  public void testStartPlayer() {
    assertEquals(Player.X, ttt1.getTurn());
  }

  /** Test the if player would change after each move */
  @Test
  public void testPlayerChangeWhenMove() {
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(0, 1);
    assertEquals(Player.X, ttt1.getTurn());
  }

  /** Test the situation when game is not over*/
  @Test
  public void testGameNotOver() {
    ttt1.move(0, 0);
    ttt1.move(0, 1);
    assertFalse(ttt1.isGameOver());
    assertEquals(" X | O |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", ttt1.toString());
  }

  /** Test the method of getWinner when player X wins the game by occupying the first row */
  @Test
  public void testGetWinner() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
        + "-----------\n"
        + " O |   |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  /** Test the method of IsGameOver when player X wins the game by occupying the first row */
  @Test
  public void testGameIsOver() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(" X | X | X\n"
        + "-----------\n"
        + " O |   |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  /** Test the situation when player X wins the game by occupying the first row */
  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
                          + "-----------\n"
                          + " O |   |  \n"
                          + "-----------\n"
                          + " O |   |  ", ttt1.toString());
  }

  /** Test the situation when player O wins the game by occupying the first column */
  @Test
  public void testVerticalWin() {
    ttt1.move(0, 1); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 2); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 1); // X takes upper right
    ttt1.move(0, 0); // O takes upper left
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" O | X | X\n"
        + "-----------\n"
        + " O | X |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  /** Test the situation when player O wins the game by occupying one of the diagonals */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " O |   |  ", ttt1.toString());
  }

  /** Make a board with Player O occupies one of the diagonals */
  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }

  /** Test the if exception being thrown when illegal move occur */
  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position is occupied!", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position is invalid!", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  /** Test the if exception being thrown when move occurs after game is over */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }

  /** Test when the game ends in tied */
  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals( " X | O | X\n"
            + "-----------\n"
            + " O | O | X\n"
            + "-----------\n"
            + " X | X | O", ttt1.toString());
  }

  /** Test valid position of getting the mark*/
  @Test
  public void testValidGetMark() {
    ttt1.move(0,0);
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    ttt1.move(1,0);
    assertEquals(Player.O, ttt1.getMarkAt(1,0));
  }

  /** Test the if exception being thrown when illegal argument of row of method getMark occur*/
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }

  /** Test the if exception being thrown when illegal argument of column of method getMark occur*/
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }

  /** Test the method of get board when player o wins*/
  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  // TODO: test case where board is full AND there is a winner
  /** Test when player x wins with the full board occupied*/
  @Test
  public void testFullBoardWithWinner() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(0, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(1, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 1);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals( " X | O | X\n"
        + "-----------\n"
        + " X | O | O\n"
        + "-----------\n"
        + " X | X | O", ttt1.toString());
  }
}

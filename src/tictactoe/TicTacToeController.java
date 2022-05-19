package tictactoe;

/**
 * This is a controller interface for TicTacToe
 */
public interface TicTacToeController {

  /**
   * Start play the ttt game
   */
  void playGame();

  /**
   * Make a move for given position
   *
   * @param pos the position of the users' click when playing
   */
  void getClick(int pos);
}

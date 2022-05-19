package tictactoe;

/**
 * This is a GUI View Interface for TicTacToe
 */
public interface TicTacToeView {

  /**
   * Show the game board
   *
   * @param pos the position of the cell
   * @param player the player's name
   */
  void showState(int pos, Player player);

  /**
   * Show the start of the new ttt game
   */
  void startGame();

  /**
   * Set the controller as the listener
   * @param l the listener we need in our game
   */
  void addListenerOfClick(TicTacToeController l);

  /**
   * Display game information on the view
   * @param str the information will be displayed
   */
  void setShowText(String str);

  /**
   * Make all the view visible
   */
  void makeVisible();
}

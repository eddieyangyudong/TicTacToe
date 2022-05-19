package tictactoe;

/**
 * This class represents a controller for the TicTacToe Game,
 * which implements the TicTacToe controller interface with two field
 * m represents ttt model and v represents ttt view
 */
public class SwingTicTacToeController implements TicTacToeController {

  private final TicTacToe m;
  private final TicTacToeView v;

  /**
   * Construct a TicTacToe Game controller, and initialize it with
   * Given TicTacToe model and view
   *
   * @param v the view
   * @param m the TicTacToe model
   */
  public SwingTicTacToeController(TicTacToeView v,TicTacToe m) {
    this.m = m;
    this.v = v;
    v.addListenerOfClick(this);
  }

  @Override
  public void playGame() {
    v.startGame();
    v.setShowText("Turn: X");
    v.makeVisible();
  }

  @Override
  public void getClick(int index) {

    int row = index / 3;
    int col = index % 3;

    if(m.isGameOver()){
      v.setShowText("Game is over!");
    }

    else if(m.getMarkAt(row,col) != null){
         v.setShowText("The position is occupy!!");
    }

    else if (!m.isGameOver()) {

        Player player = m.getTurn();
        m.move(row, col);
        v.showState(index, player);

        // if game is not over
        if(!m.isGameOver()) {
          v.setShowText("Turn: " + m.getTurn());
        }
        // if game over
        else if (m.isGameOver()) {

          if (m.getWinner() == null) {
            v.setShowText("Game is over! Tie game.");
          }

          else if(m.getWinner() != null) {
            v.setShowText("Game is over! " + m.getWinner() + " wins!");
          }

        }
    }

  }
}
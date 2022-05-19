package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents a tic-tac-toe model. The tic-tac-toe model has three fields:
 * board represents its current board, currentPlayer represents the current player and
 * count represents the number of the chess.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private final Player[][] board;
  private Player currentPlayer;
  private int count;

  /**
   * Constructs a tic-tac-toe model object and initializes it with setting the board with
   * all empty, the first player x and count equals 0
   *
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
    this.count = 0;
  }
  @Override
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException{
    if(r > 2 || r < 0 || c > 2 || c < 0) {
      throw new IllegalArgumentException("Position is invalid!");
    }
    if(board[r][c] != null) {
      throw new IllegalArgumentException("Position is occupied!");
    }
    if(isGameOver()){
      throw new IllegalStateException("Game is over, cannot move!");
    }
    getTurn();
    board[r][c] = this.currentPlayer;
    count++;
  }

  @Override
  public Player getTurn() throws IllegalStateException{
    if(isGameOver()){
      throw new IllegalStateException("Game is over!");
    }
    if(count % 2 == 0) {
      currentPlayer = Player.X;
    } else {
      currentPlayer = Player.O;
    }
    return currentPlayer;
  }

  @Override
  public boolean isGameOver() {
    return count > 8 || getWinner() != null;
  }

  @Override
  public Player getWinner() {
    //check horizontal
    for(int i = 0; i < 3; i++){
      if((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != null)){
        return board[i][0];
      }
    }
    //check vertical
    for(int j = 0; j < 3; j++){
      if((board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != null)){
        return board[0][j];
      }
    }
    //check for diagonals
    if((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != null)){
      return board[0][0];
    }
    if((board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != null)){
      return board[0][2];
    }
    //no winner return null
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for(int i = 0; i < 3; i++) {
      copyBoard[i] = Arrays.copyOf(board[i],board[i].length);
    }
    return copyBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException{
    if(r > 2 || r < 0 || c > 2 || c < 0){
      throw new IllegalArgumentException("Position is invalid!");
    }
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using 
    // the helpful built-in String.join method.
    /**********
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
    ************/
  }


}

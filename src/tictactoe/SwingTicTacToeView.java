package tictactoe;

import javax.swing.*;
import java.awt.*;

/**
 * This is a GUI view for the TicTacToe
 */
public class SwingTicTacToeView extends JFrame implements TicTacToeView {

  private final JLabel showText;
  private final JPanel showTable;
  private JButton[] cells;


  /**
   * Add 9 cells to the panel with the gird layout
   * @return the board panel
   */

  private JPanel addCells(int row , int col) {
    // build new object
    JPanel panel = new JPanel(new GridLayout(row, col));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    cells = new JButton[9];
    int i = 0;
    while(i < cells.length) {
      cells[i] = new JButton(" ");
      cells[i].setFont(new Font("Verdana", Font.PLAIN, 60));
      cells[i].setPreferredSize(new Dimension(100,100));
      cells[i].setHorizontalAlignment(JLabel.CENTER);
      cells[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
      panel.add(cells[i]);
      i++;
    }
    return panel;
  }


  /**
   * Construct a TicTacToe GUI View
   *
   * @param title the title of the window
   */
  public SwingTicTacToeView(String title) {
    // set the title
    super(title);

    // set location and size of the board
    // this.setPreferredSize(new Dimension(500,500))
    // this.setResizable(true);
    this.setLocation(500, 100);
    this.setSize(500, 500);


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    // set the layout of the table
    this.showTable = new JPanel();
    this.showTable.setLayout(new FlowLayout());
    // add 9 cells to the blank table
    this.showTable.add(addCells(3,3));

    // initialize label
    this.showText = new JLabel();
    // set size of text on the top
    this.showText.setFont(new Font("Courier New",Font.PLAIN, 30));

    // make the all central
    this.showText.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.showTable.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.add(showText);
    this.add(showTable);

    makeVisible();

  }


  @Override
  public void showState(int pos, Player player) {

    String playerName = player.toString();
    cells[pos].setText(playerName);

  }

  @Override
  public void startGame() {

    for(int i = 0; i <cells.length;i++){
      cells[i].setText(" ");
    }

   // showText.setText("Turn: X");
  }

  @Override
  public void addListenerOfClick(TicTacToeController l) {
    for (int pos = 0; pos < cells.length; pos++) {
      int row = pos / 3;
      int col = pos % 3;
      int finalPos = pos;
      cells[pos].addActionListener(evt -> l.getClick(finalPos));
    }
  }

  @Override
  public void setShowText(String s) {
    showText.setText(s);
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}

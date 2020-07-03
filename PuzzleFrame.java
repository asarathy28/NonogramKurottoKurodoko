//superclass for setting up puzzles
import java.awt.*;
import javax.swing.*;

public class PuzzleFrame extends JFrame {

  Container ct;
  private int row;
  private int col;
  private int count;
  private JButton[] buttons;
  private int buttonCount;
  private JLabel message;
  private SetPuzzle puzzle;


  public void setFrame(){
 //if col is less than number of menu buttons, extend col
    System.out.println("col: " + col);
    count = row*col;
    buttons = new JButton[count];
    buttonCount = 0;
    ct = this.getContentPane();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ct.setLayout(new GridLayout(row,col));
    puzzle.setUpPuzzle();
  }

  public void choosePuzzle(SetPuzzle x){
    puzzle = x;
    puzzle.setSize();
  }

  public void setGame(GameInterface x){
    puzzle.setGame(x);
  }

  public void ctAddPanel(CellPanel x){
    ct.add(x);
  }

  public void ctAddButton(JButton x){
    ct.add(x);
  }
  public int getRow(){
    return row;
  }

  public void setRow(int x){
    row = x;
  }

  public void setCol(int x){
    col = x;
  }

  public int getCol(){
    System.out.println("col1: " + col);
    return col;
  }

  public int getCount(){
    return count;
  }

  public int getButtonCount(){
    return buttonCount;
  }

  public void setButtons(int i, JButton value){
    buttons[i] = value;
  }

  public void incimentButtonCount(){
    buttonCount++;
  }


}

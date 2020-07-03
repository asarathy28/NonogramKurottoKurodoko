import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Solver implements ActionListener {
  private GameInterface g;
  PuzzleFrame f;
  private SetPuzzle sp;

  public Solver(PuzzleFrame frame, GameInterface game1, SetPuzzle s){
    f = frame;
    g = game1;
    sp = s;
  }

  public boolean label(int row, int col){
    //for PBN col and row starts at 1
    //System.out.println("row: " + row + "col: " + col);
    KuroCell[][] grid = g.giveGrid();

    boolean[] possibleValues = new boolean [2];
    possibleValues [0] = false;
    possibleValues [1] = true;

    if (row == grid.length) { //row after the last row
      boolean solved = g.checkGame(row, col);
      System.out.println("FINAL!!!!!!!!!!! " + solved);
      return solved;
    }
    if (g.isCellCircle(row, col)){//cell is prelabeled
      int newCol = col + 1;
      int newRow = row;
      if (newCol == grid.length){
        newCol = 0;
        newRow = row + 1;
      }
      if (label(newRow, newCol)){
        return true;
      }
    } else {
      for (boolean v: possibleValues){
        System.out.println("Solver - row: " + row + "  col: " + col + " "+v);
        if (v) g.makeCellDark(row, col);
        else if (!v) g.makeCellBlank(row, col);
        boolean check = g.checkGame(row, col);//so far
        if (check) {
          System.out.println("CHECK PASSED");
          int newCol = col + 1;
          int newRow = row;
          if (newCol == grid[row].length){
            newCol = 0;
            newRow = row + 1;
          }
          if (label(newRow, newCol)){
            return true;
          }
        }
      }
    }
    if (g.isCellColored(row, col)) g.makeCellBlank(row, col);
    else if (!g.isCellColored(row, col)) g.makeCellDark(row, col);
    return false;
  }

  public void actionPerformed(ActionEvent e) {
    g.generateGame();
    boolean solved = label(0, 0);
    System.out.println("solved: "+solved);
    f.getContentPane().removeAll();
		//f.choosePuzzle(sp);
    f.setGame(g);
    f.setFrame();
		f.validate();
    f.repaint();
  }

  public void specialDo() {
    f.choosePuzzle(new SetPuzzle(f));
  }
}

import java.util.ArrayList;

public class KurottoGame implements GameInterface {

  private KuroCell[][] kurottoGrid;

  public KurottoGame(){
    generateGame();
  }

  public void generateGame(){
    ReadFile rf = new ReadFile();
    ArrayList<Integer> gridList = rf.readGridFile("kurotto.txt");
    kurottoGrid = new KuroCell [rf.getRow()][rf.getCol()];
    //int total = gridList.size();
    for (int i = 0; i < rf.getRow(); i++) {
      for (int j = 0; j < rf.getCol(); j++) {
        int value = gridList.remove(0);
        if (value == -2) kurottoGrid[i][j] = new KuroCell (false);
        else kurottoGrid[i][j] = new KuroCell (true, value);
      }
    }
  }


  public KuroCell[][] giveGrid(){
    return kurottoGrid;
  }
/*
  public boolean checkGameSoFar(){
    return false; //for now
  }

  public boolean checkFullGame(){
    return false; //for now
  }*/
    //check constraints of the game
  public void makeCellDark(int row, int col){
    kurottoGrid[row][col].makeDark();
  }

  public void makeCellBlank(int row, int col){
    kurottoGrid[row][col].makeBlank();
  }

  public boolean isCellColored(int row, int col){
    return kurottoGrid[row][col].isCellDark();
  }

  public boolean isCellCircle(int row, int col){
    return kurottoGrid[row][col].isCircle();
  }
  public boolean checkGame(int r, int c) {
    //search from circle cells recursively like a web
    if (r == kurottoGrid.length){
      System.out.println("LAST step");
      for (int i = 0; i < kurottoGrid.length; i++) {
        for (int j = 0; j < kurottoGrid.length; j++) {
          if (isCellCircle(i , j)){
            int value = kurottoGrid[i][j].getValue();
            if (value > -1){
              System.out.println("the value " + value);
              int numColored = countFilled(i, j, true) - 1;
              uncheckAll();
              if (numColored != value) {
                return false;
              }
            }
          }
        }
      }
      return true;
    } else {
      for (int i = 0; i < kurottoGrid.length; i++) {
        for (int j = 0; j < kurottoGrid.length; j++) {
          if (isCellCircle(i , j)){
            int value = kurottoGrid[i][j].getValue();
            if (value > -1){
              System.out.println("the value " + value);
              int numColored = countFilled(i, j, true) - 1;
              System.out.println("Filled: " + numColored);
              uncheckAll();
              if (numColored > value) {
                return false;
              }
            }
          }
        }
      }
      return true;
    }
  }
  private int countFilled(int r, int c, boolean skip){
    if (r<0 || r>=kurottoGrid.length || c<0 || c>=kurottoGrid.length || isCellChecked(r, c) || (!isCellColored(r, c) && !skip)) {
  	    return 0;
  	}
    /*if (skip){
      skip = false;
    }*/
    System.out.println("worked?");
    makeCellChecked(r, c);
    int numFilled = 1;
    numFilled = numFilled + countFilled(r-1, c, false);
    numFilled = numFilled + countFilled(r+1, c, false);
    numFilled = numFilled + countFilled(r, c-1, false);
    numFilled = numFilled + countFilled(r, c+1, false);
    return numFilled;

  }

  private void makeCellChecked(int row, int col){
    kurottoGrid[row][col].makeChecked();
  }

  private boolean isCellChecked(int r, int c){
    return kurottoGrid[r][c].isChecked();
  }

  private void uncheckAll(){
    for (int i = 0; i < kurottoGrid.length; i++) {
      for (int j = 0; j < kurottoGrid.length; j++) {
        kurottoGrid[i][j].makeUnchecked();
      }
    }
  }


}

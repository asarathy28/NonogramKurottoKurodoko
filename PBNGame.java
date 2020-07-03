import java.util.ArrayList;

public class PBNGame implements GameInterface {

  private KuroCell[][] PBNGrid;
  private int rows;
  private int columns;

  public PBNGame(){
    generateGame();
  }

  public void generateGame(){
    ReadFile rf = new ReadFile();
    ArrayList<Integer> valueList = rf.readPBNFile("PBNtwo.txt");
    rows = rf.getRow()+1;
    columns = rf.getCol()+1;
    PBNGrid = new KuroCell [rf.getRow()+1][rf.getCol()+1];
    ArrayList<Integer> size = rf.getHints();
    //int total = valueList.size();
    for (int i = 0; i < rf.getRow()+1; i++) {
      for (int j = 0; j < rf.getCol()+1; j++) {
        if (i == 0){
          if (j > 0) {
            System.out.println("J: " + j);
            int[] temp = new int[size.remove(0)];
            for (int s = 0; s < temp.length; s++){
              System.out.println("S: " + s);
              temp[s] = valueList.remove(0);
            }
            PBNGrid[i][j] = new KuroCell (true, temp);
          } else PBNGrid[i][j] = new KuroCell (true);
        } else if(j == 0) {
          if (i > 0) {
            int[] temp = new int[size.remove(0)];
            for (int s = 0; s < temp.length; s++){
              temp[s] = valueList.remove(0);
            }
            PBNGrid[i][j] = new KuroCell (true, temp);
          } else PBNGrid[i][j] = new KuroCell (true);
        } else PBNGrid[i][j] = new KuroCell (false);
      }
    }
  }


  public KuroCell[][] giveGrid(){
    return PBNGrid;
  }



  public void makeCellDark(int row, int col){
    PBNGrid[row][col].makeDark();
  }

  public void makeCellBlank(int row, int col){
    PBNGrid[row][col].makeBlank();
  }

  public boolean isCellColored(int row, int col){
    return PBNGrid[row][col].isCellDark();
  }

  public boolean isCellCircle(int row, int col){
    return PBNGrid[row][col].isCircle();
  }
/*
  public boolean checkGameSoFar(){
    return false; //for now
  }*/

  public boolean checkGame(int r, int c) {
    boolean correct = true;
    if (r == PBNGrid.length) return correct;
    for (int i = 1; i < r; i++) {
      correct = checkRow (PBNGrid[i][0], i, 0, 0, 0);
      if (!correct) return correct;
    }
    correct = checkRowSoFar (PBNGrid[r][0], r, 0, 0, 0);
    if (!correct) return correct;
    if (r == PBNGrid.length-1){
      for (int i = 1; i < c; i++) {
        correct = checkCol (PBNGrid[0][i], i, 0, 0, 0);
        if (!correct) return correct;
      }
    }
    return correct;
  }

  private boolean checkRowSoFar(KuroCell hint, int row, int hintsCompleted, int darkSpots, int spotCount) {
    spotCount++;
    System.out.println("checkRow: " + row+ " col: " + spotCount);
    if (isCellColored(row, spotCount)) {
      System.out.println("dark");
      darkSpots++;
      if (darkSpots == 1 && hintsCompleted >= hint.getPBNValues().length) return false;
      else if (hint.getPBNValues()[hintsCompleted] == darkSpots) {
        if (spotCount < columns-1){
          if (!isCellColored(row, spotCount+1)) hintsCompleted++;
          else return false;
        } else hintsCompleted++;
      }
    } else {
      darkSpots = 0;
      System.out.println("blank");
    }
    if (hintsCompleted > hint.getPBNValues().length) return false;
    if (spotCount == columns-1){
      return true;
    }
    else return checkRowSoFar(hint, row, hintsCompleted, darkSpots, spotCount);
  }

  //check constraints of the game
  private boolean checkCol(KuroCell hint, int col, int hintsCompleted, int darkSpots, int spotCount) {
    spotCount++;
    if (isCellColored(spotCount, col)) {
      darkSpots++;
      if (darkSpots == 1 && hintsCompleted >= hint.getPBNValues().length) return false;
      else if (hint.getPBNValues()[hintsCompleted] == darkSpots) {
        if (spotCount < rows-1){
          if (!isCellColored(spotCount+1, col)) hintsCompleted++;
          else return false;
        } else hintsCompleted++;
      }
    } else darkSpots = 0;
    if (spotCount == rows-1) {
      if (hintsCompleted == hint.getPBNValues().length) return true;
      else return false;
    }
    else return checkCol(hint, col, hintsCompleted, darkSpots, spotCount);
  }

  private boolean checkRow(KuroCell hint, int row, int hintsCompleted, int darkSpots, int spotCount) {
    spotCount++;
    if (PBNGrid[row][spotCount].isCellDark()) {
      darkSpots++;
      if (darkSpots == 1 && hintsCompleted > hint.getPBNValues().length) return false;
      else if (hint.getPBNValues()[hintsCompleted] == darkSpots) {
        if (spotCount < columns-1){
          if (!isCellColored(row, spotCount+1)) hintsCompleted++;
          else return false;
        } else hintsCompleted++;
      }
    } else darkSpots = 0;
    if (spotCount == columns-1){
      if (hintsCompleted == hint.getPBNValues().length) return true;
      else return false;
    }
    else return checkRow(hint, row, hintsCompleted, darkSpots, spotCount);
  }
}

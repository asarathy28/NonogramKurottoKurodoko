import java.util.ArrayList;

public class KurodokoGame implements GameInterface {

  private KuroCell[][] kurodokoGrid;

  public KurodokoGame(){
    generateGame();
  }

  public void generateGame(){
    ReadFile rf = new ReadFile();
    ArrayList<Integer> gridList = rf.readGridFile("kurodoko.txt");
    kurodokoGrid = new KuroCell [rf.getRow()][rf.getCol()];
    for (int i = 0; i < rf.getRow(); i++) {
      for (int j = 0; j < rf.getCol(); j++) {
        int value = gridList.remove(0);
        if (value == -2) kurodokoGrid[i][j] = new KuroCell (false);
        else kurodokoGrid[i][j] = new KuroCell (true, value);
      }
    }
  }


  public KuroCell[][] giveGrid(){
    return kurodokoGrid;
  }


  public void makeCellDark(int row, int col){
    kurodokoGrid[row][col].makeDark();
  }

  public void makeCellBlank(int row, int col){
    kurodokoGrid[row][col].makeBlank();
  }

  public boolean isCellColored(int row, int col){
    return kurodokoGrid[row][col].isCellDark();
  }

  public boolean isCellCircle(int row, int col){
    return kurodokoGrid[row][col].isCircle();
  }

  public boolean checkGame(int r, int c) {
    int greyCellCount = 0;
    if (r == kurodokoGrid.length){
      System.out.println("LAST step");
      for (int i = 0; i < kurodokoGrid.length; i++) {
        for (int j = 0; j < kurodokoGrid.length; j++) {
          if (isCellColored(i, j)){
            if (!checkSurrounding(i, j)){
              return false;
            }
          } else {
            greyCellCount++;
          }
          if (isCellCircle(i, j)){
            int value = kurodokoGrid[i][j].getValue();
            System.out.println("the value " + value);
            int numColored = countCross(i, j);
            System.out.println("Filled: " + numColored);
            //uncheckAll();
            if (numColored != value) {
              return false;
            }
          }
        }
      }
      uncheckAll();
      if (countGrey(0, firstGreyCell()) != greyCellCount){
        System.out.print("grey island");
        return false;
      }
      return true;
    } else {
      for (int i = 0; i < kurodokoGrid.length; i++) {
        for (int j = 0; j < kurodokoGrid.length; j++) {
          if (isCellColored(i, j)){
            if (!checkSurrounding(i, j)){
              System.out.println("SURROUNDING fail");
              //uncheckAll();
              return false;
            }
          } else {
            greyCellCount++;
          }
          if (isCellCircle(i, j)){
            System.out.println("checker row: " + i + "  col: " + j);
            int value = kurodokoGrid[i][j].getValue();
            System.out.println("the value " + value);
            int numColored = countCross(i, j);
            System.out.println("Filled: " + numColored);
            if (numColored < value) {
              return false;
            }
          }
        }
      }
      uncheckAll();
      int recurGrey = countGrey(0, firstGreyCell());
      if (recurGrey != greyCellCount){

        System.out.println("grey island - recur: "+recurGrey+" greycouont: "+greyCellCount);
        uncheckAll();
        return false;
      }
      return true;
    }
  }

  private int countCross(int r, int c){
    if (r<0 || r>=kurodokoGrid.length || c<0 || c>=kurodokoGrid.length  || isCellColored(r, c)) {
  	    return 0;
  	}

    System.out.println("worked?");
    //makeCellChecked(r, c);

    int up =  countUp(r-1, c);
    int down = countDown(r+1, c);
    int left = countLeft(r, c-1);
    int right = countRight(r, c+1);
    System.out.println("up: "+up+" down: "+down+" left: "+left+" right: "+right);
    return 1 + up + down + left + right;
  }

  private int countUp(int r, int c){
    if (r<0  || isCellColored(r, c)) {
        return 0;
    }
    return 1 + countUp(r-1, c);
  }

  private int countDown(int r, int c){
    if (r>=kurodokoGrid.length  || isCellColored(r, c)) {
        return 0;
    }
    return 1 + countDown(r+1, c);
  }

  private int countLeft(int r, int c){
    if (c<0 || isCellColored(r, c)) {
        return 0;
    }
    return 1 + countLeft(r, c-1);
  }

  private int countRight(int r, int c){
    if (c>=kurodokoGrid.length  || isCellColored(r, c)) {
        return 0;
    }
    return 1 + countRight(r, c+1);
  }

  private boolean checkSurrounding(int r, int c){
    if (r-1 >0){
      if (isCellColored(r-1, c)){
        return false;
      }
    }
    if (r+1 < kurodokoGrid.length){
      if (isCellColored(r+1, c)){
        return false;
      }
    }
    if (c-1 >0){
      if (isCellColored(r, c-1)){
        return false;
      }
    }
    if (c+1 < kurodokoGrid.length){
      if (isCellColored(r, c+1)){
        return false;
      }
    }
    return true;
  }

  private void makeCellChecked(int row, int col){
    kurodokoGrid[row][col].makeChecked();
  }

  private boolean isCellChecked(int r, int c){
    return kurodokoGrid[r][c].isChecked();
  }

  private void uncheckAll(){
    for (int i = 0; i < kurodokoGrid.length; i++) {
      for (int j = 0; j < kurodokoGrid.length; j++) {
        kurodokoGrid[i][j].makeUnchecked();
      }
    }
  }

  private int countGrey(int r, int c){
    if (r<0 || r>=kurodokoGrid.length || c<0 || c>=kurodokoGrid.length || isCellChecked(r, c) || isCellColored(r, c)) {
  	    return 0;
  	}

    System.out.println("worked?");
    makeCellChecked(r, c);
    int numFilled = 1;
    numFilled = numFilled + countGrey(r-1, c);
    numFilled = numFilled + countGrey(r+1, c);
    numFilled = numFilled + countGrey(r, c-1);
    numFilled = numFilled + countGrey(r, c+1);
    return numFilled;

  }

  private int firstGreyCell(){
    for (int i = 0; i < kurodokoGrid.length; i++) {
      if (!isCellColored(0,i)){
        System.out.println("GREYYYY COL: "+i);
        return i;
      }
    }
    return -1;
  }

}

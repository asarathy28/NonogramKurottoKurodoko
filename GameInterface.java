

interface GameInterface {
  //private KuroCell[][] grid;
  void generateGame();
    //makegrid
  KuroCell[][] giveGrid();
    //return grid
  /*boolean checkFullGame();
    //check constraints of the game
  boolean checkGameSoFar();*/

  boolean checkGame(int r, int c);

  void makeCellDark(int row, int col);

  void makeCellBlank(int row, int col);

  boolean isCellColored(int row, int col);

  boolean isCellCircle(int row, int col);
}

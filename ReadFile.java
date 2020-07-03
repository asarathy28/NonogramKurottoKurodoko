import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
  ArrayList<Integer> gridList;
  int numberOfRows;
  int numberOfColumns;
  ArrayList<Integer> PBNList = new ArrayList<Integer>();
  ArrayList<Integer> Hints = new ArrayList<Integer>();

  public ReadFile() {

  }

  public ArrayList<Integer> readGridFile(String fn) {
    gridList = new ArrayList<Integer>();
    numberOfRows = 0;
    numberOfColumns = 0;
    File f = new File(fn);
    try{
      Scanner sc = new Scanner(f);
      while (sc.hasNext()) {
        if (sc.hasNextInt()){
          int temp = sc.nextInt();
          gridList.add(temp);
        } else {
          if (sc.hasNext("nl")){
            numberOfRows++;
          } else if (sc.hasNext("b")){
            int x = -2;
            gridList.add(x);
          }
          sc.next();
        }
      }
      sc.close();
      numberOfColumns = gridList.size()/numberOfRows;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return gridList;
  }


  public ArrayList<Integer> readPBNFile(String fn) {
    PBNList = new ArrayList<Integer>();
    Hints = new ArrayList<Integer>();
    numberOfRows = 0;
    numberOfColumns = 0;
    File f = new File(fn);
    try{
      Scanner sc = new Scanner(f);
      int colCount = 0;
      int hintCount = 0;
      while (sc.hasNext()) {
        if (sc.hasNextInt()){
          int temp = sc.nextInt();
          PBNList.add(temp);
          hintCount++;
        } else {
          if (sc.hasNext("nl")){
            Hints.add(hintCount);
            hintCount = 0;
            colCount++;
          } else if (sc.hasNext("break")){
            numberOfColumns = colCount;
          }
          sc.next();
        }
      }
      sc.close();
      numberOfRows = colCount-numberOfColumns;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return PBNList;
  }

  public ArrayList<Integer> getHints(){
    return Hints;
  }

  public int getRow(){
    return numberOfRows;
  }

  public int getCol(){
    return numberOfColumns;
  }

  public static void main(String[] args) {
    ReadFile rf = new ReadFile();
    ArrayList<Integer> grid = rf.readPBNFile("PBN.txt");
  //  for (Integer s1 : grid) System.out.println(s1);

    for (Integer s1: grid){
      System.out.println(s1);
    }

        System.out.println("rows: " + rf.getRow());
        System.out.println("colunmn: " + rf.getCol());

        ArrayList<Integer> size = rf.getHints();
        int hold = size.size();
        System.out.println("HOLD: " + hold);
      for (Integer s1: size){
        System.out.println(s1);
      }

  }
}

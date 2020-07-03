import java.awt.*;
import javax.swing.*;

public class SetPuzzle {

  private PuzzleFrame PF;
  private GameInterface game;
  private KuroCell[][] gameGrid;


  public SetPuzzle(PuzzleFrame x){
    PF = x;
  }

  public void setUpPuzzle() {
    System.out.println("super");
    for (int i=0; i<PF.getCol()-1; i++) {
      if (i == 0){
        MenuButton menu = new MenuButton(PF);
        menu.addActionListener(new SwitchToKurotto(PF));
        //menu.setText("KUROTTO");
        JLabel kurottomeu = new JLabel ("KUROTTO", JLabel.CENTER);
        menu.add(kurottomeu);
        PF.ctAddButton(menu);
      }
      else if (i == 1){
        MenuButton menu = new MenuButton(PF);
        menu.addActionListener(new SwitchToPBN(PF));
        //menu.setText("PAINT BY NUMBER");
        JLabel PBNmenu = new JLabel("PAINT BY NUMBER", JLabel.CENTER);
        menu.add(PBNmenu);
        PF.ctAddButton(menu);
      }
      else if (i == 2){
        MenuButton menu = new MenuButton(PF);
        menu.addActionListener(new SwitchToKurodoko(PF));
        //menu.setText("KURODOKO");
        JLabel kurodokomenu = new JLabel("KURODOKO", JLabel.CENTER);
        menu.add(kurodokomenu);
        PF.ctAddButton(menu);
      }
      else{
        CellPanel panel = new CellPanel();
        panel.setWords("0__0");
        panel.randomSet();
        PF.ctAddPanel(panel);
      }
    }
    //if (i == PF.getCol()-1)
    MenuButton menu = new MenuButton(PF);
    menu.addActionListener(new Solver(PF, game, this));
    //menu.setText("SOLVE");
    JLabel solvemenu = new JLabel ("SOLVE", JLabel.CENTER);
    menu.add(solvemenu);
    PF.ctAddButton(menu);
    for (int i=PF.getCol(); i<PF.getCount(); i++) {

      int rowCount = i/PF.getCol()-1;
      int colCount = i%PF.getCol();

      if (gameGrid[rowCount][colCount].isCircle()) {
        CellPanel panel = new CellPanel();
        int value = gameGrid[rowCount][colCount].getValue();
        if (value > -1) {
          //panel.setWords("value: " + value);
          String x = "value: "+value;
          JLabel val = new JLabel(x);
          val.setForeground(new Color(randomColorCoord(),randomColorCoord(),randomColorCoord()));
          panel.add(val);

        }
        if (value == -1) {
          //panel.setWords("value: " + value);
          String x = "value: EMPTY";
          JLabel val = new JLabel(x);
          val.setForeground(new Color(randomColorCoord(),randomColorCoord(),randomColorCoord()));
          panel.add(val);

        }
        if (value == -3) {
          int[] hints = gameGrid[rowCount][colCount].getPBNValues();
          String text = "";
          for (int x: hints){
            text = text + " " + x;
          }
          panel.setWords(text);
        }
        panel.randomSet();
        PF.ctAddPanel(panel);
      } else {
        Color c = new Color(randomColorCoord(),randomColorCoord(),randomColorCoord());
        JButton jb = new SpaceButton(c, game, PF, rowCount, colCount);
        /*if (game1.isButtonBad(i)) {
          jb.addActionListener(badHand);
        }
        else
        jb.addActionListener(goodHand);*/
        PF.ctAddButton(jb);
        PF.setButtons(PF.getButtonCount(),jb);
        PF.incimentButtonCount();
      }
    }

  }

  public void readGame(){}


  public void setGame(GameInterface x){
    game = x;
    gameGrid = game.giveGrid();
  }
  public void setSize(){
    PF.setRow(gameGrid.length + 1);
    PF.setCol(gameGrid[0].length);
  }
  public Color makeRandomColor() {
		int red = (int) (Math.random()*255);
		int green = (int) (Math.random()*255);
		int blue = (int) (Math.random()*255);

		Color col = new Color(red,green,blue);
		return col;
	}
  public int randomColorCoord() {
    return (int)(Math.random()*256);
  }
}

import java.awt.*;
import javax.swing.*;

public class SetPaintByNumber extends SetPuzzle {


  public SetPaintByNumber(PuzzleFrame x){
    super(x);
    readGame();

  }

  @Override
  public void readGame(){
        setGame(new PBNGame());
  }

}

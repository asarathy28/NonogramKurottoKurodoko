import java.awt.*;
import javax.swing.*;

public class SetKurotto extends SetPuzzle {

  public SetKurotto(PuzzleFrame x) {
    super(x);
    readGame();
  }

  @Override
  public void readGame(){
    setGame(new KurottoGame());
  }



}

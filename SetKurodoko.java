import java.awt.*;
import javax.swing.*;

public class SetKurodoko extends SetPuzzle {

  public SetKurodoko(PuzzleFrame x) {
    super(x);
    readGame();
  }

  @Override
  public void readGame(){
    setGame(new KurodokoGame());
  }

}

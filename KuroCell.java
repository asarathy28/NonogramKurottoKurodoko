

public class KuroCell {

  private int value; //-1 = no value
  private boolean circleCell;
  private boolean dark; // in this case dark means
  private int[] PBNvalues;
  private boolean checked;

  public KuroCell (boolean circ, int val){
    dark = false;
    circleCell = circ;
    value = val;
    checked = false;
  }

  public KuroCell (boolean circ){
    dark = false;
    circleCell = circ;
    if (circleCell) value = -1;
    checked = false;
  }

  public KuroCell (boolean circ, int[] vals){
    dark = false;
    circleCell = circ;
    value = -3;
    PBNvalues = vals;
    checked = false;
  }

  public void makeChecked(){
    checked = true;
  }

  public void makeUnchecked(){
    checked = false;
  }

  public boolean isChecked(){
    return checked;
  }

  public boolean isCellDark() {
    return dark;
  }

  public void makeDark() {
    if (!circleCell) dark = true;
  }

  public int getValue() {
    return value;
  }

  public void makeBlank() {
    dark = false;
  }

  public boolean isCircle() {
    return circleCell;
  }

  public int[] getPBNValues() {
    return PBNvalues;
  }

}

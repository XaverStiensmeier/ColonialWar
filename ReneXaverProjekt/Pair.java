import javafx.scene.shape.Rectangle;
/*
 * Pair ist eine Wrapper-Class (Verbindungs Klasse) um Units mit einem Int wert zu versehen. Der Int Wert steht für das Feld und die Unit für die Unit UDONTSAY
 */
public class Pair {
  //
  private int zFirst; //was für ein Feld ?
  private Unit zSecond; //welche Einheit steht darauf ?
  public Pair(int pFirst, Unit pSecond, Rectangle pRectangle) {
    this.zFirst = pFirst;
    this.zSecond = pSecond;
  }
  public int getFirst() { return zFirst; }
  public Unit getSecond() { return zSecond; }
  public boolean isEmpty() {return zSecond == null;}
  public void setSecond(Unit pSecond) {this.zSecond = pSecond;}
  public String toString() {
    return "Material:" + getFirst() + "\nEinheit:\n" + getSecond();
    }
  }
public class Pair {
  //
  private int first; //was für ein Feld ?
  private Unit second; //welche Einheit steht darauf ?
  public Pair(int pFirst, Unit pSecond) {
    this.first = pFirst;
    this.second = pSecond;
  }
  public int getFirst() { return first; }
  public Unit getSecond() { return second; }
  public boolean isEmpty() {return second == null;}
  public void setSecond(Unit pSecond) {this.second = pSecond;}
  public String toString() {
    return "Material:" + getFirst() + "\nEinheit:\n" + getSecond();
    }
  }
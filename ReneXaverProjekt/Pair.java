public class Pair {
  //
  private final int first;
  private final Unit second;

  public Pair(int pFirst, Unit pSecond) {
    this.first = pFirst;
    this.second = pSecond;
  }

  public int getFirst() { return first; }
  public Unit getSecond() { return second; }
  
  public String toString() {
    return "Feld:" + getFirst() + "\nEinheit:\n" + getSecond();
    }
  }
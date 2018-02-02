public class Piraten extends Unit {
  public Piraten(int pLP, int pDirection, int pXPosition, int pYPosition) {
    //erfüllt die Notwendigen Kriterien der Einheit Pirat.
    super(pLP, 3, 2);
    setDirection(pDirection);
    setXPosition(pXPosition);
    setYPosition(pYPosition);
    }
 }
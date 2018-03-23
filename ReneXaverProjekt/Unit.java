public class Unit {
  /* 
   * 
   * Unit ist die Oberklasse f�r alle Einheiten. Es beschreibt die generellen M�glichkeiten (Z�ge usw)
   * ben�tigt Namen bzw. Key f�r Bild Zuweisung sp�ter.
   */
  private int zMoveEnergy = 2;
  private int zATK = 1;
  private int zDEF = 1;
  private int zDistATK;
  private int zDistDEF;
  private double zLP;
  private int zDirection;
  private int zXPosition;
  private int zYPosition;
  public Unit (double pLP, int pATK, int pDEF)
  {
    setLP(pLP);;
    setATK(pATK);
    setDEF(pDEF);
  }
  public Unit (double pLP, int pATK, int pDEF, int pXPosition, int pYPosition)
  {
    setLP(pLP);;
    setATK(pATK);
    setDEF(pDEF);
    setXPosition(pXPosition);
    setYPosition(pYPosition);
  }
  public Unit (double pLP, int pATK, int pDEF, int pDistATK, int pDistDEF, int pXDirection, int pYDirection)
  {
    setLP(pLP);;
    setATK(pATK);
    setDEF(pDEF);
    setDistATK(pDistATK);
    setDistDEF(pDistDEF);
  }
  //Anfang der get / set Methoden
  public int getMoveEnergy() {
    //gibt die Variable "zMoveEnergy" zur�ck (Wie viele Z�ge ? zMoveEnergy!)
    return zMoveEnergy;
  }
  public void setMoveEnergy(int pMoveEnergy) {
    //setzt die Variable "zMoveEnergy" auf "pMoveEnergy"
    zMoveEnergy = pMoveEnergy;
  }
  public int getATK() {
    //gibt die Variable "zATK" zur�ck (Angriffswert der Soldaten)
    return zATK;
  }
  public void setATK(int pATK) {
    //setzt die Variable "zATK" auf "pATK" (Angriffswert der Soldaten)
    zATK = pATK;
  }
  public int getDEF() {
    //gibt die Variable "zDEF" zur�ck (Verteidigungswert der Soldaten)
    return zDEF;
  }
  public void setDEF(int pDEF) {
    //setzt die Variable "zDEF" auf "pDEF"
    zDEF = pDEF;
  } 
  public int getDistATK() {
    //gibt die Variable "zDistATK" zur�ck (z.b. Bogen, Musketen usw Schaden)
    return zDistATK;
  }
  public void setDistATK(int pDistATK) {
    //setzt die Variable "zDistATK" auf "pDistATK"
    zDistATK = pDistATK;
  }
  public int getDistDEF() {
    //gibt die Variable "zDistDEF" zur�ck (Fernkampf-Schutz)
    return zDistDEF;
  }
  public void setDistDEF(int pDistDEF) {
    //setzt die Variable "zDistDEF" auf "pDistDEF"
    zDistDEF = pDistDEF;
  }
  public double getLP() {                                                                                                                                                   
    //gibt die Variable "zLP" zur�ck (Lebenspunkte: 1 Lebenspunkt gleich 1 Einheit. Gute Gr��e 30)
    return zLP;
  }
  public void setLP(double pLP) {
    //setzt die Variable "zLP" zu "pLP";
    zLP = pLP;
  }
  public int getDirection() {
    //gibt die Variable "zDirection" zur�ck (Richtung)
    return zDirection;
  }
  public void setDirection(int pDirection) {
    //setzt die Variable "zDiretion" zu "pDirection"
    zDirection = pDirection;
  }
  public int getXPosition() {
    //gibt die Variable "zXPosition" zur�ck.
    return zXPosition;
  }
  public void setXPosition(int pXPosition) {
    //setzt die Variable "zXPosition" zu "pXPosition"
    zXPosition = pXPosition;
  }
  public int getYPosition() {
    //gibt die Variable "zYPosition" zur�ck.
    return zYPosition;
  }                                                                          
  public void setYPosition(int pYPosition) {
    //setzt die Variable "zYPosition" zu "pYPosition"
    zYPosition = pYPosition;
  }
  public void setDefaultValueMoveEnergy() {
	  setMoveEnergy(2);
  }
  public String toString() {
    String part1 = "Leben: " + this.zLP + "\nATK: " + this.zATK + "\nDEF: " + this.zDEF;
    if (this.zATK > 0) {
      part1 = part1 + "\nDistATK = " + this.zATK;
    } // end of if
    part1 = part1 + "\nDistDEF = " + this.zDistDEF + "\nRichtung = " + this.zDirection + "\nPosition (X,Y) = (" + this.getXPosition() + "," + this.getYPosition() + ")";
    return part1;
  }
}
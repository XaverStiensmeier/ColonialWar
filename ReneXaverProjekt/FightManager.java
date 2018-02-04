/*
 * Alle Kampf Berechnungen werden hier ausgeführt.
 * Alle ? Ja Alle!
 * Rückgabe ist bei Einheit Gewinn per boolean (Angreifende Einheit gewonnen?)
 *  */
public class FightManager {
	public boolean inFight(Unit pUnit1, Unit pUnit2)
	  {
	    //solange noch einer Lebt
	    while (pUnit1.getLP() >= 1 && pUnit2.getLP() >= 1)
	    {
	      //hole dir die Lebenswerte 
	      double LP1 = pUnit1.getLP();
	      double LP2 = pUnit2.getLP();
	      //gesetzter MindestSchadensWert
	      double lMinDamageValue = 1;
	      //Angriff 1 : Leben * Attacke - Leben * Defensiv-Wert / 10 (empirisch errechneter Wert)
	      double ATK1 = (LP1*pUnit1.getATK()-LP2*pUnit2.getDEF())/10;
	      //wenn Schaden größer Mindest-Schaden dann verrechne Schaden. Sonst Mindest-Schaden verrechnen.
	      if (ATK1 > lMinDamageValue)
	        pUnit2.setLP(LP2 - ATK1);
	      else
	      pUnit2.setLP(LP2 - lMinDamageValue);
	      
	      //Angriff2 (nur Falls Einheit nicht tot. Dann aber mit den davor errechneten Leben (gleichzeitig, empirisch errechneter Wert
	      if (pUnit2.getLP() >= 1) {
	        double ATK2 = (LP2*pUnit2.getATK()-LP1*pUnit1.getDEF())/10;
	        if (ATK2 > lMinDamageValue)
	          pUnit1.setLP(LP1 - ATK2);                                                                                                                                                      
	        else
	        pUnit1.setLP(LP1 - lMinDamageValue);
	      } // end of if
	      //am Ende jeder Runde: zeige uns den Zwischen-Stand

	    } // end of while
	    //zeige uns den Gewinner
	    return pUnit1.getLP() > 0;
	    } // end of if 
	    
	  public void distFight(Unit pUnit1, Unit pUnit2)
	  {
	    //Unit 1 -greift an-> Unit 2
	    //hole dir die Lebenswerte 
	    double LP2 = pUnit2.getLP();
	    double LP1 = pUnit1.getLP();
	    //gesetzter MindestSchadensWert
	    double lMinDamageValue = 1;
	    //Angriff 1 : Leben1 * DistAttacke + Leben2 * (DistATK - DistDefensiv-Wert) / 100 (empirisch errechneter Wert)
	    double lATKmultiplier = pUnit1.getDistATK() - pUnit2.getDistDEF();
	    double lATKmultiplier2 = pUnit1.getDistATK() - pUnit2.getDistDEF();
	    //lATKmultiplier 
	    if (lATKmultiplier < 0) {
	      lATKmultiplier = 0;
	    } // end of if
	    if (lATKmultiplier2 <0) {
	      lATKmultiplier = 0;
	    } // end of if
	    double ATK1 = (LP1*lATKmultiplier2+LP2*lATKmultiplier)/100;
	    //wenn Schaden größer Mindest-Schaden dann verrechne Schaden. Sonst Mindest-Schaden verrechnen.
	    if (ATK1 > lMinDamageValue)
	      pUnit2.setLP(LP2 - ATK1);
	    else
	    pUnit2.setLP(LP2 - lMinDamageValue);
	  }
}

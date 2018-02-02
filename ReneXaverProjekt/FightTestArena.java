import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 01.02.2018
  * @author 
  */

public class FightTestArena extends JFrame {
  // Anfang Attribute
  private JButton jButton1 = new JButton();
  private JTextArea arena = new JTextArea("");
  private JScrollPane arenaScrollPane = new JScrollPane(arena);
  // Ende Attribute
  
  public FightTestArena() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300; 
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("FightTestArena");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jButton1.setBounds(64, 8, 123, 25);
    jButton1.setText("Create Unit");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    arenaScrollPane.setBounds(32, 48, 200, 204);
    cp.add(arenaScrollPane);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public FightTestArena
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new FightTestArena();
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    arena.setText("FIGHT\n");
    
    //bekomme Werte für den ersten Fighter
    double lLP1 = Double.parseDouble(JOptionPane.showInputDialog("Leben Unit 1"));
    int   lATK1 = Integer.parseInt(JOptionPane.showInputDialog("ATK 1"));
    int   lDEF1 = Integer.parseInt(JOptionPane.showInputDialog("DEF 1"));
    
    //bekomme Werte für den zweiten Fighter 
    double lLP2 = Double.parseDouble(JOptionPane.showInputDialog("Leben Unit 2"));
    int   lATK2 = Integer.parseInt(JOptionPane.showInputDialog("ATK 2"));
    int   lDEF2 = Integer.parseInt(JOptionPane.showInputDialog("DEF 2"));
    
    //erstelle die Units
    Unit lUnit1 = new Unit(lLP1,lATK1,lDEF1);
    Unit lUnit2 = new Unit(lLP2,lATK2,lDEF2);
    
    //zeige die Units an
    arena.append("1");
    show(lUnit1);
    arena.append("\n2");
    show(lUnit2);
    //starte den Fight
    inFight(lUnit1, lUnit2);
  } // end of jButton1_ActionPerformed
  public void show (Unit pUnit) {
    // zeige zLP, zATK, zDEF eines Fighters
    arena.append("\n" + pUnit.getLP() + "," + pUnit.getATK() + "," + pUnit.getDEF()+ ";");
  }
  public void inFight(Unit pUnit1, Unit pUnit2)
  {
    //solange noch einer Lebt
    while (pUnit1.getLP() > 0 && pUnit2.getLP() > 0) {
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
      if (pUnit2.getLP() > lMinDamageValue) {
        double ATK2 = (LP2*pUnit2.getATK()-LP1*pUnit1.getDEF())/10;
        if (ATK2 > 1)
          pUnit1.setLP(LP1 - ATK2);                                                                                                                                                      
        else
        pUnit1.setLP(LP1 - lMinDamageValue);
      } // end of if
      //am Ende jeder Runde: zeige uns den Zwischen-Stand
      show(pUnit1);
      show(pUnit2);
    } // end of while
    //zeige uns den Gewinner
    if (pUnit1.getLP() > 0) {
      arena.append("\n WINNER IS Unit1 with" + pUnit1.getLP()); 
    } // end of if
    else
      arena.append("\n WINNER IS Unit2 with" + pUnit2.getLP());
  }
  // Ende Methoden
} // end of class FightTestArena

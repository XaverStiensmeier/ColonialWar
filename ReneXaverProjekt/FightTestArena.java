import java.awt.Color;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FightTestArena extends Application
{

	public static void main(String [] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		VBox vBox = new VBox();
		Button b = new Button();
		ScrollPane text = new ScrollPane();
		TextArea textarea = new TextArea();
		
		b.setText("Create Unit");
		b.setPrefSize(128, 8);
		b.setTranslateX(150);
		b.setTranslateY(250);
		text.setPrefSize(300, 200);
		text.setTranslateY(0);
		text.setContent(textarea);
		
		vBox.getChildren().addAll(b, text);		
		
		Scene scene = new Scene(vBox, 300, 300);
		stage.setScene(scene);
		stage.setTitle("FightTestArena");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				Platform.exit();
				System.exit(0);
				
			}
		});
		stage.show();
		
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				textarea.setText("FIGHT\n");
				
				
				double lLP1 = Double.parseDouble(JOptionPane.showInputDialog("Leben Unit 1"));
			    int   lATK1 = Integer.parseInt(JOptionPane.showInputDialog("ATK von Unit 1"));
			    int   lDEF1 = Integer.parseInt(JOptionPane.showInputDialog("DEF von Unit 1"));
			    
			    //bekomme Werte für den zweiten Fighter 
			    double lLP2 = Double.parseDouble(JOptionPane.showInputDialog("Leben Unit 2"));
			    int   lATK2 = Integer.parseInt(JOptionPane.showInputDialog("ATK von Unit 2"));
			    int   lDEF2 = Integer.parseInt(JOptionPane.showInputDialog("DEF von Unit 2"));
			    
			    //erstelle die Units
			    Unit lUnit1 = new Unit(lLP1,lATK1,lDEF1);
			    Unit lUnit2 = new Unit(lLP2,lATK2,lDEF2);
			    
			    //zeige die Units an
			    textarea.appendText("1");
			    show(lUnit1);
			    textarea.appendText("\n2");
			    show(lUnit2);
			    //starte den Fight
			    inFight(lUnit1, lUnit2);
			  } // end of jButton1_ActionPerformed
			  
			
			public void show (Unit pUnit) {
			    // zeige zLP, zATK, zDEF eines Fighters
			    textarea.appendText("\nLeben: " + pUnit.getLP() + ", Angriffsschaden: " + pUnit.getATK() + ", Verteidigung: " + pUnit.getDEF()+ ";");
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
			      textarea.appendText("\n WINNER IS Unit1 with" + pUnit1.getLP()); 
			    } // end of if
			    else
			      textarea.appendText("\n WINNER IS Unit2 with" + pUnit2.getLP());
			  }
			
			
			
			
			
		});
		
		
	}
	
}
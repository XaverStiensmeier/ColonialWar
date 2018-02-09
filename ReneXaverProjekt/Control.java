import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Control {
	/*
	 * der eigentliche Arbeitskern des Programmes. hier und nur hier fügt sich
	 * alles zusammen. der einzige direkte Kontakt zum Server
	 */
	Map zCurrentMap;
	Player zPlayer1 = new Player();
	Player zPlayer2 = new Player();
	Player zCurrentPlayer = zPlayer1;
	Unit zCurrentUnit;
	//Rectangle test = new Rectangle(5, 5, 5, 5);
	FightManager zCurrentFightManager = new FightManager();
	// ColonialWarServer zCurrentServer;
	/*
	 * 
	 * Control(ColonialWarServer pServer) { zCurrentServer = pServer; }
	 */
	// behelfslösung
	ColonialWarGUI zCurrentGUI;

	public Control(ColonialWarGUI pCurrentGUI) {
		// wird später durch den Server ausgetauscht
		zCurrentGUI = pCurrentGUI;
	}

	public void createMap(int pMapX, int pMapY) {
		// erstellt eine Map;
		zCurrentMap = new Map(pMapX, pMapY);
	}

	public void createMapView(boolean pIsBackground) {
		// macht ein komplettes Update. Entweder nur der Hintergrund oder nur
		// Units;
		// alle GUI befehle werden später in den Client verschoben und von hier
		// über den Server erreicht.
		// Geht stumpf alle Elemente durch und gebe sie aus;
		zCurrentGUI.reset();
		Pair[][] larrayMap = zCurrentMap.getLarrayMap();
		for (int x = 0; x < larrayMap.length; x++) {
			for (int y = 0; y < larrayMap.length; y++) {
				// sage dem Server er soll dem Client sagen, dass er der
				// ClientGUI sage soll: schreibe
				// behelfslösung
				zCurrentGUI.createGrass(x * 30 + 10, y * 30 + 10);
				if (!larrayMap[x][y].isEmpty()) {
					zCurrentGUI.createUnitUpdate(x, y, pIsBackground);
				}
				// später switch case für verschiedene Landschaften

			}
		}

	}

	public void getUserInput(KeyCode pKey) {
		// User-Input-Handling;
		if (zCurrentUnit != null) {
			switch (pKey) {
			case NUMPAD1:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD2:
				move(zCurrentUnit.getXPosition(), zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD3:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD4:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition());
				break;
			case NUMPAD5:
				/* führe einen Schuß aus ; */ System.out.println(zCurrentMap + "\n" + zCurrentPlayer); break;
			case NUMPAD6:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition());
				break;
			case NUMPAD7:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD8:
				move(zCurrentUnit.getXPosition(), zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD9:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD0:
				this.getNextUnit();
				break;
			}
		}
	}

	public void move(int pToX, int pToY) {
		/*
		 * Bewegt die aktuelle Unit; zu X Y; WENN ZielFeld Pair nicht leer UND
		 * (pair.isEmpty ODER Kampf gewonnen); DANN mache deinen Zug MoveEnergy
		 * -1; Falls Kampf Verloren und nicht leer
		 */
		//System.out.print("OUPS");
		int lOldX = zCurrentUnit.getXPosition();
		int lOldY = zCurrentUnit.getYPosition();
		Pair zPair = zCurrentMap.getPair(pToX, pToY);
		if(zPair != null) {
		if (zPair.isEmpty()) {
			//wenn kein Kampf notwendig, da Material-Feld frei und in Map;
			if (zCurrentMap.moveUnit(pToX, pToY, lOldX, lOldY, zCurrentUnit)) {
				//Zug hat funktioniert; Passe Werte der Unit an;
				zCurrentUnit.setMoveEnergy(zCurrentUnit.getMoveEnergy() - 1);
				zCurrentUnit.setXPosition(pToX);
				zCurrentUnit.setYPosition(pToY);
			} else {
				// wenn zCurrentUnit = null oder Zug außerhalb des Spielfelds
			}
		//funktioniert noch nicht richtig :)
		} else if (zCurrentFightManager.inFight(zCurrentMap.getPair(lOldX, lOldY).getSecond(), zPair.getSecond())) {
			//gewonnener Kampf :)
			//zCurrentMap.removeUnit(pToX, pToY);
			//zCurrentMap.moveUnit(pToX, pToY, lOldX, lOldY, zCurrentUnit);
		}
		else {
			//verlorener Kampf :(
			//zCurrentMap.removeUnit(lOldX, lOldY);
		}
		if (zCurrentUnit.getMoveEnergy() == 0)
			getNextUnit();
		}
			
		createMapView(false);
	}

	public void getNextUnit() {
		//aufpassen: Fall, dass einer keine Armee mehr hat ist noch nicht implementiert! Das muss noch geklärt werden
		//aus irgendwelchen Gründen erhält es einen Aufruf am Anfang warum =?
		System.out.println("NEXT");
		if (zCurrentPlayer.hasAccess()) {
			System.out.println("TING GOES SKRAA");
			zCurrentUnit = zCurrentPlayer.getNext();
		}
		else if (!zCurrentPlayer.hasAccess()) {
			if (zPlayer2.equals(zCurrentPlayer)) {
				zCurrentPlayer = zPlayer1;
				System.out.print("PLAYA1");
				zCurrentPlayer.setCurrentUnitIndex(0);
				zCurrentUnit =  zCurrentPlayer.getFirst();
			} else {
				zCurrentPlayer = zPlayer2;
				zCurrentPlayer.setCurrentUnitIndex(0);
				zCurrentUnit = zCurrentPlayer.getFirst();
				System.out.println("PL>2");
			}
		}
		else
			System.out.println("ERROR");
			//error condition
			
	}

	public void lineSetUp() {
		int lsize = zCurrentMap.getLarrayMapLength();
		for (int i = 0; i < lsize; i++) {
			Unit one = new Unit(30, 1, 1, i, 0);
			Unit two = new Unit(30, 1, 1, i, lsize -1);
			zPlayer1.addUnit(one);
			zPlayer2.addUnit(two);
			zCurrentMap.setUnit(i, 0, one);
			zCurrentMap.setUnit(i, lsize - 1, two);
		}
		zCurrentUnit = zCurrentPlayer.getFirst();
	}
}

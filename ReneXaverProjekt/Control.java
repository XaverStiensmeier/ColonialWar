import javafx.scene.input.KeyCode;


public class Control {
	/*
	 * der eigentliche Arbeitskern des Programmes. hier und nur hier f�gt sich
	 * alles zusammen. der einzige direkte Kontakt zum Server
	 */
	Map zCurrentMap;
	Player zPlayer1 = new Player();
	Player zPlayer2 = new Player();
	Player zCurrentPlayer = zPlayer1;
	Unit zCurrentUnit;
	// Rectangle test = new Rectangle(5, 5, 5, 5);
	FightManager zCurrentFightManager = new FightManager();
	// ColonialWarServer zCurrentServer;
	/*
	 * 
	 * Control(ColonialWarServer pServer) { zCurrentServer = pServer; }
	 */
	// behelfsl�sung
	ColonialWarGUI zCurrentGUI;

	public Control(ColonialWarGUI pCurrentGUI) {
		// wird sp�ter durch den Server ausgetauscht
		zCurrentGUI = pCurrentGUI;
	}

	public void createMap(int pMapX, int pMapY) {
		// erstellt eine Map;
		zCurrentMap = new Map(pMapX, pMapY);
		for (int i = 0; i < pMapX; i++) {
			for (int x = 0; x < pMapY; x++)
				zCurrentGUI.createGrass(i * 30 + 10, x * 30 + 10);
		}
	}

	public void createMapView() {
		// macht ein komplettes Update. Entweder nur der Hintergrund oder nur
		// Units;
		// alle GUI befehle werden sp�ter in den Client verschoben und von hier
		// �ber den Server erreicht.
		// Geht stumpf alle Elemente durch und gebe sie aus;
		zCurrentGUI.reset();
		Pair[][] larrayMap = zCurrentMap.getLarrayMap();
		for (int x = 0; x < larrayMap.length; x++) {
			for (int y = 0; y < larrayMap.length; y++) {
				// sage dem Server er soll dem Client sagen, dass er der
				// ClientGUI sage soll: schreibe
				// behelfsl�sung
				if (!larrayMap[x][y].isEmpty()) {
					zCurrentGUI.createPartialUpdate(x, y);
				}
				// sp�ter switch case f�r verschiedene Landschaften

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
				/* f�hre einen Schu� aus ; */ System.out.println(zCurrentMap + "\n" + zCurrentPlayer);
				break;
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
				zCurrentPlayer.setzEnergyLeft(true);
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
		// System.out.print("OUPS");
		int lOldX = zCurrentUnit.getXPosition();
		int lOldY = zCurrentUnit.getYPosition();
		Pair zPair = zCurrentMap.getPair(pToX, pToY);
		if (zPair != null) {
			if (zPair.isEmpty() && zCurrentUnit.getMoveEnergy() != 0) {
				// wenn kein Kampf notwendig, da Material-Feld frei und in Map;
				if (zCurrentMap.moveUnit(pToX, pToY, lOldX, lOldY, zCurrentUnit)) {
					// Zug hat funktioniert; Passe Werte der Unit an;
					zCurrentUnit.setMoveEnergy(zCurrentUnit.getMoveEnergy() - 1);
					zCurrentUnit.setXPosition(pToX);
					zCurrentUnit.setYPosition(pToY);
				} else {
					// wenn zCurrentUnit = null oder Zug au�erhalb des
					// Spielfelds
				}
				// funktioniert noch nicht richtig :)
			} else if (zCurrentFightManager.inFight(zCurrentMap.getPair(lOldX, lOldY).getSecond(), zPair.getSecond()) && zCurrentUnit.getMoveEnergy() != 0) {
				// gewonnener Kampf :)
				// zCurrentMap.removeUnit(pToX, pToY);
				// zCurrentMap.moveUnit(pToX, pToY, lOldX, lOldY, zCurrentUnit);
			} else {
				// verlorener Kampf :(
				// zCurrentMap.removeUnit(lOldX, lOldY);
			}
			if (zCurrentUnit.getMoveEnergy() <= 0)
				getNextUnit();
		}

		createMapView();
	}

	public void getNextUnit() {
		// aufpassen: Fall, dass einer keine Armee mehr hat ist noch nicht
		// implementiert! Das muss noch gekl�rt werden
		// aus irgendwelchen Gr�nden erh�lt es einen Aufruf am Anfang warum =?
		System.out.println("NEXT");
		if (zCurrentPlayer.hasAccess())
			zCurrentUnit = zCurrentPlayer.getNext();
			//System.out.println("access");
		else if (!zCurrentPlayer.hasAccess() && !zCurrentPlayer.getzEnergyLeft()) {
			if (zPlayer2.equals(zCurrentPlayer)) {
				zCurrentPlayer = zPlayer1;
				System.out.print("PLAYA1");
				zCurrentPlayer.setzCurrentUnitIndex(0);
				zCurrentUnit = zCurrentPlayer.getNext();
				zCurrentPlayer.refreshAllUnitsEnergy();
			} else {
				zCurrentPlayer = zPlayer2;
				zCurrentPlayer.setzCurrentUnitIndex(0);
				zCurrentUnit = zCurrentPlayer.getNext();
				System.out.println("PL>2");
				zCurrentPlayer.refreshAllUnitsEnergy();
			}
		} else {
			zCurrentPlayer.setzCurrentUnitIndex(0);
			zCurrentUnit = zCurrentPlayer.getNext();
		}
		// error condition

	}

	public void lineSetUp() {
		int lsize = zCurrentMap.getLarrayMapLength();
		for (int i = 0; i < lsize; i++) {
			Unit one = new Unit(30, 1, 1, i, 0);
			Unit two = new Unit(30, 1, 1, i, lsize - 1);
			zPlayer1.addUnit(one);
			zPlayer2.addUnit(two);
			zCurrentMap.setUnit(i, 0, one);
			zCurrentMap.setUnit(i, lsize - 1, two);
		}
		zCurrentUnit = zCurrentPlayer.getNext();
	}
}

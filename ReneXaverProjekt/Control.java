import javafx.scene.input.KeyCode;

public class Control {
	// der eigentliche Arbeitskern des Programmes.
	// hier und nur hier fügt sich alles zusammen.
	// der einzige direkte Kontakt zum Server
	Map zCurrentMap;
	// ColonialWarServer zCurrentServer;
	/*
	 * 
	 * Control(ColonialWarServer pServer) { zCurrentServer = pServer; }
	 */
	// behelfslösung
	ColonialWarGUI zCurrentGUI;
	Player zPlayer1 = new Player();
	Player zPlayer2 = new Player();
	Unit zCurrentUnit;
	Unit myUnit;
	public Control(ColonialWarGUI pCurrentGUI) {
		zCurrentGUI = pCurrentGUI;
	}

	public void createMap(int pMapX, int pMapY) {
		zCurrentMap = new Map(pMapX, pMapY);
	}

	public void createMapView() {
		Pair[][] larrayMap = zCurrentMap.getLarrayMap();
		for (int x = 0; x < larrayMap.length; x++) {
			for (int y = 0; y < larrayMap.length; y++) {
				// sage dem Server er soll dem Client sagen, dass er der
				// ClientGUI sage soll: schreibe SpielMaterialFeld

				// behelfslösung
				zCurrentGUI.createGrass(x * 30 + 10, y * 30 + 10);
				if (!larrayMap[x][y].isEmpty())
					zCurrentGUI.createUnitUpdate(x * 30 + 10, y * 30 + 10);
				// später switch case für verschiedene Landschaften

			}
		}
	}

	public void getUserInput(KeyCode pKey) {
		if (zCurrentUnit != null) {
			switch (pKey) {
			case NUMPAD1:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD2:
				move(zCurrentUnit.getXPosition(), zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD3:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition() - 1);
				break;
			case NUMPAD4:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition());
				break;
			case NUMPAD5:
				/* führe einen Schuß aus ; */ break;
			case NUMPAD6:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition());
				break;
			case NUMPAD7:
				move(zCurrentUnit.getXPosition() - 1, zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD8:
				move(zCurrentUnit.getXPosition(), zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD9:
				move(zCurrentUnit.getXPosition() + 1, zCurrentUnit.getYPosition() + 1);
				break;
			case NUMPAD0:
				move(zCurrentUnit.getXPosition(), zCurrentUnit.getYPosition());
				break;
			}
		}
	}

	public void move(int pToX, int pToY) {
		int lOldX = zCurrentUnit.getXPosition();
		int lOldY = zCurrentUnit.getYPosition();
		zCurrentUnit.setXPosition(pToX);
		zCurrentUnit.setXPosition(pToY);
		if (!zCurrentMap.moveUnit(pToX, pToY, lOldX, lOldY, zCurrentUnit)) {
			// wenn zCurrentUnit = null oder Zug außerhalb des Spielfelds
			// wirf eine Fehlermeldung
		} else {
			zCurrentUnit.setMoveEnergy(zCurrentUnit.getMoveEnergy() - 1);
			zCurrentGUI.moveUnitUpdate(lOldX, lOldY, zCurrentUnit);
		}
		if (zCurrentUnit.getMoveEnergy() == 0)
			getNextUnit();
	}

	public void getNextUnit() {
		zCurrentUnit = new Unit (10,10,10);
		zCurrentUnit.setXPosition(5);
		zCurrentUnit.setYPosition(5);
		zCurrentGUI.createUnitUpdate(5, 5);
	}

	public void lineSetUp() {
		int lsize = zCurrentMap.getLarrayMapLength();
		for (int i = 0; i < lsize; i++) {
			zCurrentMap.setUnit(i, 0, new Unit(30, 1, 1));
			zCurrentMap.setUnit(i, lsize - 1, new Unit(30, 1, 1));
		}
	}
}

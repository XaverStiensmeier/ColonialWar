
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
					zCurrentGUI.createUnit(x * 30 + 10, y * 30 + 10);
				// später switch case für verschiedene Landschaften

			}
		}
	}

	public void lineSetUp() {
		int lsize = zCurrentMap.getLarrayMapLength();
		for (int i = 0; i < lsize; i++) {
			zCurrentMap.setUnit(i, 0, new Unit(30, 1, 1));
			zCurrentMap.setUnit(i, lsize-1, new Unit(30, 1, 1));
		}
	}
}

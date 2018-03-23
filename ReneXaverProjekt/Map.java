
/*
 * Map ist die Verwaltungs-Klasse des physisch reellen Teils der Klasse Unit.
 * Intern verwaltet Map ein Pair[][] Array (zweidimensionales Array)
 * 
 */
public class Map {
	Pair[][] larrayMap;

	public Map(int pMapSizeX, int pMapSizeY) {
		//erstellt ein Array der größe pValueX pValueY. Außerdem benutzt es einen Landschaftskonstruktor
		//hier ist es createEmptyMap;
		larrayMap = new Pair[pMapSizeX][pMapSizeY];
		createEmptyMap();
	}

	public void createEmptyMap() {
		//erstellt eine Map mit Feldern des Materials 0. Das entspricht Gras, also einer Fläche, die keinen Einfluss auf den Kampf nimmt.
		for (int i = 0; i < larrayMap.length; i++) {
			for (int i2 = 0; i2 < larrayMap.length; i2++) {
				larrayMap[i][i2] = new Pair(0, null, null);
				// System.out.println(larrayMap[i][i2]);
			}
		}
	}

	public void setUnit(int pToX, int pToY, Unit pUnit) {
		//wenn eine Unit übergeben wird, dann setze sie an die Stelle pToX pToY
		if (pUnit != null) {
			larrayMap[pToX][pToY].setSecond(pUnit);
		}
	}

	// eventuell später umschreiben! Man könnte auch die Unit übegeben
	// und daraus die Werte lesen. Jetzt noch Unklar was effizienter!
	public void removeUnit(int pFromX, int pFromY) {
		//entfernt eine Uni aus dem Pair an der Stelle pFromX pFromY
		larrayMap[pFromX][pFromY].setSecond(null);
		//System.out.print("(" + pFromX + ")(" + pFromY + ")0");
	}

	public boolean moveUnit(int pToX, int pToY, int pFromX, int pFromY, Unit pUnit) {
		//wenn eine Unit übergeben wird und das Zielfeld der Bewegung nicht außerhalb der Map ist,
		//DANN entferne die alte Unit und setze aufs neue Feld;
		//Wenn das erfolgt, DANN return true; falls nicht bewegt return false;
		int lMapLength = larrayMap.length;
		if (pUnit != null && pToX < lMapLength && pToY < lMapLength && pToX >= 0 && pToY >= 0) {
				removeUnit(pFromX, pFromY);
				setUnit(pToX, pToY, pUnit);
				return true;
			}
		return false;
	}

	public Pair[][] getLarrayMap() {
		//gib die gesamte Map zurück;
		return larrayMap;
	}

	public Pair getPair(int pLocationX, int pLocationY) {
		//gib das Pair aus an der Stelle X Y, falls es innerhalb der Map liegt;
		//sonst null;
		int lMapLength = larrayMap.length;
		if(pLocationX < lMapLength && pLocationY < lMapLength && pLocationX >= 0 && pLocationY >= 0)
		return larrayMap[pLocationX][pLocationY];
		return null;
	}

	public int getLarrayMapLength() {
		//gib die Länge aus. Man könnte auch darüber nachdenken die Länge am Anfang zu speichern und hier dann aus der Variable auszugeben. Das hätte Vorteile, da es effizienter ist.
		return larrayMap.length;
	}
    public String toString() {
    	String lMyString = "MapPosUnits1:";
    	int counter = 0;
    	for (int x = 0; x < larrayMap.length; x++) {
			for (int y = 0; y < larrayMap.length; y++) {
				// sage dem Server er soll dem Client sagen, dass er der
				// ClientGUI sage soll: schreibe SpielMaterialFeld
				if(larrayMap[x][y].getSecond() != null) {
				lMyString = lMyString + " (" + larrayMap[x][y].getSecond().getXPosition() + " ' " + larrayMap[x][y].getSecond().getYPosition() + ")";
				counter++;
				}
			} 
		}
    	return lMyString +" UNITS:" + counter;
    }
	
}

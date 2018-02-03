public class Map {
	Pair[][] larrayMap;

	public Map(int pValueX, int pValueY) {
		larrayMap = new Pair[pValueX][pValueY];
		createEmptyMap();
	}

	public void createEmptyMap() {

		for (int i = 0; i < larrayMap.length; i++) {
			for (int i2 = 0; i2 < larrayMap.length; i2++) {
				larrayMap[i][i2] = new Pair(0, null);
				// System.out.println(larrayMap[i][i2]);
			}
		}
	}
	public void setUnit(int pToX, int pToY, Unit pUnit) {
		if (pUnit != null)
			larrayMap[pToX][pToY].setSecond(pUnit);
	}

	// eventuell später umschreiben! Man könnte auch die Unit übegeben
	// und daraus die Werte lesen. Jetzt noch Unklar was effizienter!
	public void removeUnit(int pFromX, int pFromY) {
		larrayMap[pFromX][pFromY].setSecond(null);
	}

	public boolean moveUnit(int pToX, int pToY, int pFromX, int pFromY, Unit pUnit) {
		int lMapLength = larrayMap.length;
		if (pUnit != null && pToX < lMapLength && pToY < lMapLength) {
			System.out.println(pFromX + "," + pFromY + ";" + pToX + "," + pToY);
			removeUnit(pFromX, pFromY);
			setUnit(pToX, pToY, pUnit);
			return true;
		}
		return false;
	}

	public Pair[][] getLarrayMap() {
		return larrayMap;
	}
	
	public Pair getPair(int pLocationX, int pLocationY) {
		return larrayMap[pLocationX][pLocationY]; 
	}
	public int getLarrayMapLength() {
		return larrayMap.length;
	}
}

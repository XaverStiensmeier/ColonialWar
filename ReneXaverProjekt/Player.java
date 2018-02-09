import java.util.ArrayList;
import java.util.List;
/*
 * Player ist die Verwaltungseinheit der Units für die Spieler. Dazu wird eine Liste erstellt
 * sie regelt, welche Einheit die nächste ist.
 */
public class Player {
	List<Unit> zUnitList = new ArrayList<Unit>();
	int zCurrentUnitIndex=0;
	
	public Unit getNext() {
		if(!zUnitList.isEmpty()&&zCurrentUnitIndex < zUnitList.size()-1) {
			zCurrentUnitIndex++;
			System.out.println(zCurrentUnitIndex);
			return zUnitList.get(zCurrentUnitIndex);
		}
		System.out.println(zCurrentUnitIndex);
		return null;
	}
	public void setCurrentUnitIndex (int pIndex) {
		zCurrentUnitIndex = pIndex;
	}
	public Unit getFirst() {
		if(!zUnitList.isEmpty())
		return zUnitList.get(0);
		return null;
	}
	public boolean hasAccess() {
		return zCurrentUnitIndex < zUnitList.size()-1;
	}
	
	public void addUnit(Unit pUnit) {
		//System.out.println(" (" + pUnit.getXPosition() + " ' " + pUnit.getYPosition() + ")");
		zUnitList.add(pUnit);
		//System.out.println("BUT" + "( " + zUnitList.get(zCurrentUnitIndex).getXPosition() + " ' " + zUnitList.get(zCurrentUnitIndex).getYPosition() + ")" + zCurrentUnitIndex);
		//zCurrentUnitIndex++;
	}
	
	public String toString() {
		String helpString = "PlayerUnits:";
		for(int i = 0; i<zUnitList.size(); i++) {
			helpString = helpString + " (" + zUnitList.get(i).getXPosition() + " ' " + zUnitList.get(i).getYPosition() +")";
		}
		return helpString;
	}
	
}

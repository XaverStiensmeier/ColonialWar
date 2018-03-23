import java.util.ArrayList;
import java.util.List;
/*
 * Player ist die Verwaltungseinheit der Units für die Spieler. Dazu wird eine Liste erstellt
 * sie regelt, welche Einheit die nächste ist.
 */
public class Player {
	List<Unit> zUnitList = new ArrayList<Unit>();
	int zCurrentUnitIndex=0;
	boolean zEnergyLeft = false;
	
	public void setzEnergyLeft(boolean pEnergyLeft) {
		this.zEnergyLeft = pEnergyLeft;
	}
	public Unit getNext() {
		if(!zUnitList.isEmpty()) {
			Unit lCurrentUnit = zUnitList.get(zCurrentUnitIndex);
			zCurrentUnitIndex++;
//			System.out.println(zUnitList.size()-1);
//			System.out.println(zCurrentUnitIndex);
			return lCurrentUnit;
		}
		System.out.println(zCurrentUnitIndex);
		return null;
	}
	public boolean getzEnergyLeft() {
		return zEnergyLeft;
	}
	public void setzCurrentUnitIndex(int zCurrentUnitIndex) {
		this.zCurrentUnitIndex = zCurrentUnitIndex;
	}

	public boolean hasAccess() {
		return zCurrentUnitIndex < zUnitList.size();
	}
	
	public void addUnit(Unit pUnit) {
		//System.out.println(" (" + pUnit.getXPosition() + " ' " + pUnit.getYPosition() + ")");
		zUnitList.add(pUnit);
		//System.out.println("BUT" + "( " + zUnitList.get(zCurrentUnitIndex).getXPosition() + " ' " + zUnitList.get(zCurrentUnitIndex).getYPosition() + ")" + zCurrentUnitIndex);
		//zCurrentUnitIndex++;
	}
	public void refreshAllUnitsEnergy() {
		for(Unit lCurrentUnit: zUnitList) {
			lCurrentUnit.setDefaultValueMoveEnergy();
//			System.out.println("test EnergyRefresh");
		}
	}
	public String toString() {
		String helpString = "PlayerUnits:";
		for(int i = 0; i<zUnitList.size(); i++) {
			helpString = helpString + " (" + zUnitList.get(i).getXPosition() + " ' " + zUnitList.get(i).getYPosition() +")";
		}
		return helpString;
	}
	
}

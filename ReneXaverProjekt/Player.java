import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Unit> zUnitList = new ArrayList<Unit>();
	int zCurrentUnitIndex=0;
	
	public Unit getNext() {
		if(!zUnitList.isEmpty()&&zCurrentUnitIndex < zUnitList.size()) {
			zCurrentUnitIndex++;
			return zUnitList.get(zCurrentUnitIndex-1);
		}
		zCurrentUnitIndex=0;
		return null;
	}
	
	public boolean hasAccess() {
		return zCurrentUnitIndex < zUnitList.size();
	}
	
	public void addUnit(Unit pUnit) {
		zUnitList.add(pUnit);
	}
}

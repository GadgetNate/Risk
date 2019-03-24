import java.util.ArrayList;

public class BetterPlacerPlayer extends Player {
	public BetterPlacerPlayer(String name) {
		super(name);
	}
	
	public void allocateTroops(int newTroops) {
		ArrayList<Territory> territoriesClaimed = this.getBoard().getTerritoriesClaimedBy(this);
		Territory territoryToPlaceTroops = null;
		int leastNumberOfTroops = -1;
		int connectedTerritoryTroops = 0;
		
		for(Territory territory : territoriesClaimed) {
			for(Territory connectedTerritory : territory.getConnectedTerritories()) {
				if(!connectedTerritory.isClaimedBy(this)) {
					connectedTerritoryTroops = connectedTerritory.getTroopCount();
					if(leastNumberOfTroops < 0 || connectedTerritoryTroops < leastNumberOfTroops) {
						leastNumberOfTroops = connectedTerritoryTroops;
						territoryToPlaceTroops = territory;
					}
				}
			}
		}
		if(  territoryToPlaceTroops == null) {
			territoriesClaimed.get(0).placeTroops(newTroops);
		}
		else
		{
			territoryToPlaceTroops.placeTroops(newTroops);
		}
	}
	
}

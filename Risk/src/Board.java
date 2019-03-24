import java.util.ArrayList;

public class Board {
	private ArrayList<Territory> territories = new ArrayList<Territory>();
	
	public Board() {
		Territory alaska = new Territory("Alaska");
		Territory northwestTerritory = new Territory("NorthWest Territory");
		Territory greenland = new Territory("Greenland");
		Territory alberta = new Territory("Alberta");
		Territory ontario = new Territory("Ontario");
		Territory quebec = new Territory("Quebec");
		Territory westernUnitedStates = new Territory("WesternUnitedStates");
		Territory easternUnitedStates = new Territory("Eastern United States");
		Territory centralAmerica = new Territory("Central America");
		
		this.territories.add(alaska);
		this.territories.add(northwestTerritory);
		this.territories.add(greenland);
		this.territories.add(alberta);
		this.territories.add(ontario);
		this.territories.add(quebec);
		this.territories.add(westernUnitedStates);
		this.territories.add(easternUnitedStates);
		this.territories.add(centralAmerica);
		
		alaska.connectTerritory(northwestTerritory);
		alaska.connectTerritory(alberta);
		
		northwestTerritory.connectTerritory(greenland);
		northwestTerritory.connectTerritory(alberta);
		northwestTerritory.connectTerritory(ontario);
		
		greenland.connectTerritory(ontario);
		greenland.connectTerritory(quebec);
		
		alberta.connectTerritory(ontario);
		alberta.connectTerritory(westernUnitedStates);
		
		ontario.connectTerritory(quebec);
		ontario.connectTerritory(westernUnitedStates);
		ontario.connectTerritory(easternUnitedStates);
		
		quebec.connectTerritory(easternUnitedStates);
		
		westernUnitedStates.connectTerritory(easternUnitedStates);
		westernUnitedStates.connectTerritory(centralAmerica);
		
		easternUnitedStates.connectTerritory(centralAmerica);
		
	}
	
	ArrayList<Territory> getUnclaimedTerritories(){
		ArrayList<Territory> unclaimedTerritories = new ArrayList<Territory>();
		
		for(Territory territory : this.territories) {
			if(!territory.isClaimed()) {
				unclaimedTerritories.add(territory);
			}
		}
		
		return unclaimedTerritories;
	}
	
	ArrayList<Territory> getTerritoriesClaimedBy(Player player){
		ArrayList<Territory> claimedTerritories = new ArrayList<Territory>();
		
		for(Territory territory : this.territories) {
			if(territory.isClaimed() && territory.isClaimedBy(player)) {
				claimedTerritories.add(territory);
			}
		}
		
		return claimedTerritories;
	}
	
	
	
	public int getNewTroopsForPlayer(Player player) {
		int newTroops = 0;
		int claimedTerritoryCount = 0;
		
		for(Territory territory : this.territories) {
			if(territory.isClaimedBy(player)) claimedTerritoryCount++;
		}
		
		newTroops = claimedTerritoryCount/3;
		if(newTroops<3) newTroops = 3;
		
		return newTroops;
	}
	
	public void printBoardState() {
		for(Territory territory : this.territories) {
			if(territory.isClaimed()) {
				System.out.println(territory.toString() + " is claimed by " + territory.getClaimedByPlayer().toString() + " with " + territory.getTroopCount() + " troops");
			}
			else
			{
				System.out.println(territory.toString() + " is unclaimed");
			}
		}
	
	}
}

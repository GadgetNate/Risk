import java.util.ArrayList;

public class Territory {
	private String name;
	private Player claimedBy;
	private int troopCount;
	
	private ArrayList<Territory> connectedTerritories = new ArrayList<Territory>();
	
	public Territory(String name) {
		this.name = name;
	}

	public void connectTerritory(Territory territory) {
		if(!connectedTerritories.contains(territory)) {
			connectedTerritories.add(territory);
			territory.connectTerritory(this);
		}
	}
	
	public ArrayList<Territory> getConnectedTerritories(){
		return this.connectedTerritories;
	}
	
	public boolean isClaimed() {
		return claimedBy != null;
	}
	
	public void changeClaimBy(Player player){
		if(isClaimed()) {
			this.claimedBy.unclaimTerritory(this);
		}
		this.claimedBy = player;
	}
	
	public void placeTroops(int newTroops) {
		this.troopCount += newTroops;
	}
	
	public Player getClaimedByPlayer()
	{
		return this.claimedBy;
	}
	
	public int getTroopCount() {
		return this.troopCount;
	}
	
	public void setTroopCount(int troopCount) {
		this.troopCount = troopCount;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public boolean isClaimedBy(Player player)
	{
		return claimedBy == player;
	}
}

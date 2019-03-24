import java.util.ArrayList;

public class Player {
	private String name;
	private RiskGame game;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void setGame(RiskGame game) {
		this.game = game;
	}
	
	public void claimTerritory(Territory territory) {
		territory.changeClaimBy(this);
		
		if(territory.getTroopCount() == 0) territory.setTroopCount(1);
	}
	
	public void unclaimTerritory(Territory territory) {
		
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public Board getBoard() {
		return this.game.getBoard();
	}
	public void allocateTroops(int newTroops) {
		ArrayList<Territory> territoriesClaimed = this.game.getBoard().getTerritoriesClaimedBy(this);
		int territoryCount = territoriesClaimed.size();
		int averageTerritoryPlacement = newTroops/territoryCount;
		int extraTerritoryPlacement = newTroops % territoryCount;
		
		for(Territory territory : territoriesClaimed) {
			territory.placeTroops(averageTerritoryPlacement);
		}
		
		territoriesClaimed.get(0).placeTroops(extraTerritoryPlacement);
	}
	
	public void doBattle() {
		//Find connected territory with less troops and attack
		ArrayList<Territory> territoriesClaimed = this.game.getBoard().getTerritoriesClaimedBy(this);
		
		int myTroops=0;
		int otherTroops=0;
		Territory territoryToAttack = null;
		Territory territoryToAttackFrom = null;
		for(Territory myTerritory : territoriesClaimed) {
			myTroops = myTerritory.getTroopCount();
			for(Territory connectedTerritory : myTerritory.getConnectedTerritories()) {
				if(connectedTerritory.isClaimed() && !connectedTerritory.isClaimedBy(this)) {
					otherTroops = connectedTerritory.getTroopCount();
					if(otherTroops < myTroops) {
						territoryToAttack = connectedTerritory;
						territoryToAttackFrom = myTerritory;
					}
				}
				if(territoryToAttack != null ) break;
			}
			if(territoryToAttack != null ) break;
		}
		
		if(territoryToAttack != null && myTroops>1) {
			game.attack(territoryToAttack, territoryToAttackFrom, myTroops-1);
		}
	}
	
	public boolean isPlayerAlive() {
		
		return game.getBoard().getTerritoriesClaimedBy(this).size() > 0;
	}
}

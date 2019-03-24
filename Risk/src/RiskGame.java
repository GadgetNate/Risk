
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RiskGame {
	private int numberOfTurns = 0;
	private int maxNumberOfTurns = 0;

	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentPlayerIndex = 0;
	private Board board = new Board();
	
	public RiskGame(Board board) {
		this.board = board;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
		player.setGame(this);
	}
	
	public boolean gameOver() {
		int numberOfPlayersAlive = 0;
		
		for(Player player : this.players) {
			if(player.isPlayerAlive()) numberOfPlayersAlive++;
		}
		
		numberOfTurns++;
		return (maxNumberOfTurns != 0 && numberOfTurns >= maxNumberOfTurns) || numberOfPlayersAlive < 2;
	}
	
	public void setMaxNumberOfTurns(int maxNumberOfTurns ) {
		this.maxNumberOfTurns = maxNumberOfTurns;
	}
	
	public int getNumberOfTurns() {
		return this.numberOfTurns;
	}
	
	public boolean attack(Territory territoryToAttack, Territory territoryToAttackFrom, int attackerTroops) {
		System.out.println(territoryToAttackFrom.getClaimedByPlayer() + " is attacking " + territoryToAttack + " from "+ territoryToAttackFrom + " with " + attackerTroops + " troops");
		boolean didWin = false;
		int defenderTroops = territoryToAttack.getTroopCount();
		
		while(attackerTroops>0 && defenderTroops > 0) {
			//put dice rolling routine here.. For now if 
			
			int randomNum = ThreadLocalRandom.current().nextInt(Math.min(attackerTroops, defenderTroops), attackerTroops+defenderTroops);
			if(randomNum%2 == 0) {
				//defender losses troop
				defenderTroops--;
			}
			else
			{
				attackerTroops--;
			}
		}
		
		didWin = attackerTroops > 0;
		if(didWin) {
			territoryToAttack.changeClaimBy(territoryToAttackFrom.getClaimedByPlayer());
			territoryToAttack.setTroopCount(attackerTroops);
			System.out.println("Attack succeeded.  " + attackerTroops + " troops enter territory");
		} else {
			territoryToAttack.setTroopCount(defenderTroops);
			System.out.println("Attack failed... " + defenderTroops + " troops reamin");
		}
		
		
		return didWin;
	}
	
	public void allocateTerritories() {
		if(this.board == null) this.board = new Board();
		ArrayList<Territory> unclaimedTerritories = this.board.getUnclaimedTerritories();
		int currentPlayer = 0;
		
		
		
		for(Territory territory : unclaimedTerritories) {
			this.players.get(currentPlayer).claimTerritory(territory);
			currentPlayer++;
			if(currentPlayer >= this.players.size()) currentPlayer = 0;
		}
		
	}
	
	public Player getCurrentPlayer() {
		return this.players.get(currentPlayerIndex);
	}
	
	public void nextPlayersTurn() {
		this.currentPlayerIndex++;
		if(this.currentPlayerIndex >= this.players.size()) currentPlayerIndex = 0;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int getNewTroopsForPlayer(Player player) {
		return this.board.getNewTroopsForPlayer(player);
	}
	
	public void printPlayerSummary() {
		for(Player player : this.players) {
			System.out.println("player: " + player + " claims " + this.board.getTerritoriesClaimedBy(player).size() + " territories ");
		}
	}
}

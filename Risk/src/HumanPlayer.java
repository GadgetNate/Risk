import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
	public HumanPlayer(String name) {
		super(name);
	}
	
	public void allocateTroops(int newTroops) {
		ArrayList<Territory> territoriesClaimed = this.getBoard().getTerritoriesClaimedBy(this);
		Scanner keyboard = new Scanner(System.in);
		
		while(newTroops > 0) {
			System.out.println("You have " + newTroops + " troops");
			
			for(int i=0; i < territoriesClaimed.size(); i++) {
				System.out.println("You currently have " + territoriesClaimed.get(i).getTroopCount() + " on " + territoriesClaimed.get(i) + "   Press " + i + " to place more troops");
			}
			
			System.out.println("Which territory do you want to Add troops to?");
			
			int placeToAddTroops = keyboard.nextInt();
			
			System.out.println("How many troops do you want to add?");
			
			int troopCount = keyboard.nextInt();
			territoriesClaimed.get(placeToAddTroops).placeTroops(troopCount);
			
			newTroops -= troopCount;
			
		}
		
		
	}
	
//	public void doBattle() {
//		Scanner keyboard = new Scanner(System.in);
//		
//		int continueAttack=1;
//		ArrayList<Territory> territoriesClaimed = this.getBoard().getTerritoriesClaimedBy(this);
//		
//		while(continueAttack == 1) {
//			for(int i=0; i < territoriesClaimed.size(); i++) {
//				System.out.println("You currently have " + territoriesClaimed.get(i).getTroopCount() + " on " + territoriesClaimed.get(i) + "   Press " + i + " to place more troops");
//			}
//			
//			System.out.println("Which territory do you want to Add troops to?");
//			
//			int placeToAttackFrom = keyboard.nextInt();
//			
//		}
//		
//	}
}

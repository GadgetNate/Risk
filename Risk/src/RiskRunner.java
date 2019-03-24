import java.util.Scanner;

public class RiskRunner {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int numberOfPlayers;

		System.out.println("Welcome to risk!!");
		//System.out.println("How many players?");
		//numberOfPlayers = keyboard.nextInt();
		//RiskGame game = new RiskGame(numberOfPlayers);
		
		Board board = new Board();
		RiskGame game = new RiskGame(board);
		game.setMaxNumberOfTurns(10000);
		
		Player player1 = new BetterPlacerPlayer("Mike");
		Player player2 = new BetterPlacerPlayer("George");
		Player player3 = new Player("Mary");
		Player player4 = new Player("Suzzie");
		
		game.addPlayer(player1);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.addPlayer(player4);
		
		board.printBoardState();
		
		System.out.println("\n\n======Allocating Territories=======\n\n");
		game.allocateTerritories();
		
		board.printBoardState();
		
		Player currentPlayer;
		
		while(!game.gameOver()) {
			currentPlayer = game.getCurrentPlayer();
			if(currentPlayer.isPlayerAlive()) {
				System.out.println("\n\n" + currentPlayer + " turn");
				
				currentPlayer.allocateTroops(game.getNewTroopsForPlayer(currentPlayer));
				
				currentPlayer.doBattle();
				
				//currentPlayer.doTroopMovement();
				game.getBoard().printBoardState();
				
				game.printPlayerSummary();
			}
			game.nextPlayersTurn();
		
			
		}
		
		System.out.println("number of turns: " + game.getNumberOfTurns());
	}

}

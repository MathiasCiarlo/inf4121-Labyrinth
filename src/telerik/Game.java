package telerik;

import java.util.Scanner;

public class Game {
    private final static int boardSize = 6;

	public static void main(String[] args){
		Board labyrinth = new Board();
		labyrinth.initializeScoreBoard();

        // Main game loop, handles every game
        while(true){
			labyrinth.initializeMaze();

            // Inner game loop, active while the player is moving around
            while(labyrinth.playerNotOnEdge()) {
				labyrinth.inputCommand();
            }

			System.out.println();
			labyrinth.printMaze();
			System.out.println("Congratulations! You escaped in "+labyrinth.playersMovesCount+" moves.");
			System.out.println();
			
			
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your name : ");
			String name = scanner.next();
			Player player = new Player(name,labyrinth.playersMovesCount);
			if(labyrinth.board.addPlayerToChart(player)==true){
				System.out.println("Your score is in top 5!");
				labyrinth.board.printBoard(labyrinth.board.list);
			}
			labyrinth.isExit = false;
			labyrinth.playersCurrentColumn = 3;
			labyrinth.playersCurrentRow = 3;
			labyrinth.playersMovesCount = 0;
		}
		
		
		
	}
}

package telerik;

import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    private Board labyrinth;

	public static void main(String[] args){
        Game game = new Game();
        game.gameLoop();
	}

    private void gameLoop() {
        // Initializing the labyrinth
        labyrinth = new Board();

        // TODO Move to contr
        labyrinth.initializeScoreBoard();

        // Main game loop, handles every game
        while(true){

            // Create a new maze
            labyrinth.initializeMaze();

            // Inner game loop, active while the player is moving around
            while(labyrinth.playerNotOnEdge()) {
                labyrinth.inputCommand();
            }

            // Print game result
            printResult();

            // Prompt user for name
            String name = getPlayerName();

            // Add the player to the highscore list if he is top 5
            if(labyrinth.addPlayerToChart(name)){
                System.out.println("Your score is in top 5!");
                labyrinth.printHighscore();
            }

            // Resetting game state
            labyrinth.isExit = false;
            labyrinth.playersCurrentColumn = 3;
            labyrinth.playersCurrentRow = 3;
            labyrinth.playersMovesCount = 0;
        }
    }

    private void printResult() {
        System.out.println();
        labyrinth.printMaze();
        System.out.println("Congratulations! You escaped in " + labyrinth.getMovesCount() + " moves.");
        System.out.println();
    }

    // Promts for name on standard input and returns it
    private String getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name : ");
        return scanner.next();
    }
}

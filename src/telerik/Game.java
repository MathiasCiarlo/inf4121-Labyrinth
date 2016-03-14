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
            initializeMaze();

            // Inner game loop, active while the player is moving around
            while(playerNotOnEdge()) {
                inputCommand();
            }

            // Print game result
            printResult();

            // Prompt user for name
            String name = getPlayerName();

            // Add the player to the highscore list if he is top 5
            if(addPlayerToChart(name)){
                System.out.println("Your score is in top 5!");
                printHighscore();
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
        printMaze();
        System.out.println("Congratulations! You escaped in " + getMovesCount() + " moves.");
        System.out.println();
    }

    // Promts for name on standard input and returns it
    private String getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name : ");
        return scanner.next();
    }

    // Returns true if the player was top 5
    // and adds it to the high score list
    private boolean addPlayerToChart(String name) {
        return labyrinth.addPlayerToChart(name);
    }

    // Returns the players number of moves in the current game
    private int getMovesCount() {
        return labyrinth.getMoveCount();
    }

    // Prints the maze
    private void printMaze() {
        labyrinth.printMaze();
    }

    // Prompts user for command
    private void inputCommand() {
        labyrinth.inputCommand();
    }

    // Returns true if the player is not on an edge
    private boolean playerNotOnEdge() {
        return labyrinth.playerNotOnEdge();
    }

    // Prints the board
    private void printHighscore() {
        labyrinth.printHighscore();
    }

    // Initializes the maze
    private void initializeMaze() {
        labyrinth.initializeMaze();
    }
}

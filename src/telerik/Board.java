package telerik;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Board {
	public boolean isVisited[][] = new boolean[7][7];
	public char maze[][] = new char[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard scoreboard;

    // Creates a player object and inserts it in the HighScoreBoard
    public boolean addPlayerToChart(String name) {
        Player player = new Player(name, playersMovesCount);
        return scoreboard.addPlayerToChart(player);
    }

    // Generates a solvable maze and prints it
	void initializeMaze(){
		Random randomgenerator = new Random();

		// Generates a new maze until at least one solution is found
		do{
			for(int row = 0; row < maze.length; row++){
				for(int column = 0; column < maze.length; column++){

                    // Resetting the isVisited property for the whole board
                    isVisited[row][column] = false;

                    if(randomgenerator.nextInt(2) == 1){
						maze[row][column] = 'X';
					}
					else {
						maze[row][column] = '-';
					}
				}
			}
		}
		while(isSolvable(maze.length/2, maze.length/2) == false);

        // Setting the players position to center
        playersCurrentRow = maze.length/2;
		playersCurrentColumn = maze.length/2;
		
		// Adding the player
		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}

	public void initializeScoreBoard(){
		scoreboard = new HighScoreBoard();
	}

    // Check if the labyrinth is solvable
	public boolean isSolvable(int row, int col){

        // Base case - we have escaped the maze
        if((row == maze.length-1) || (col == maze.length-1) || (row == 0) || (col == 0)){
            isExit = true;
            return isExit;
        }

        // Mark the cell as visited
        isVisited[row][col] = true;

		if((maze[row-1][col] == '-') &&
                (isVisited[row-1][col] == false)) {
            isSolvable(row - 1, col);
		}
		if((maze[row+1][col] == '-') &&
                (isVisited[row+1][col] == false)){
			isSolvable(row + 1, col);
		}
		if((maze[row][col - 1]=='-') &&
                (isVisited[row][col-1] == false)) {
            isSolvable(row, col - 1);
        }
		if((maze[row][col+1]=='-') &&
                (isVisited[row][col+1] == false)) {
            isSolvable(row, col + 1);
        }
		return isExit;
	}

    // Prints the maze to standard output
	void printMaze(){
		for(int row = 0; row < 7; row++) {
			for(int column=0; column <7; column++){
				System.out.print(maze[row][column] + " ");
			}
            System.out.println();
		}
	}

    // Reads a command from standard input
	public void inputCommand(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your next move : L(left), " +
				"R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();

        if (!command.equals("exit")) {
			if(command.equals("restart")){
                isExit = false;
                initializeMaze();
            }
            else if(command.equals("top")){
                if(scoreboard.list.size() > 0){
                    scoreboard.printBoard();
                }
                else{
                    System.out.println("The High score board is empty!");
                }
            }
            else if(size > 1){
                System.out.println("Invalid command!");
            }
            else {
                movePlayer(command.charAt(0));
            }
		} else {
			System.out.println("Good bye!");
			System.exit(0);
		}
	}

    // Check which direction the player wants to move
	public  void movePlayer(char firstLetter){
		if (firstLetter == 'L' || firstLetter == 'l') {
            tryMoveLeft();

		} else if (firstLetter == 'R' || firstLetter == 'r') {
			tryMoveRight();

		} else if (firstLetter == 'U' || firstLetter == 'u') {
			tryMoveUp();

		} else if (firstLetter == 'D' || firstLetter == 'd') {
			tryMoveDown();

		} else {
			System.out.println("Invalid command!");
		}
	}

    // Check if the player can move down
    private void tryMoveDown() {
        if (maze[playersCurrentRow + 1][playersCurrentColumn] != 'X') {
            swapCells(playersCurrentRow, playersCurrentRow + 1,
                    playersCurrentColumn, playersCurrentColumn);
            playersCurrentRow++;
            playersMovesCount++;
        } else {
            System.out.println("Invalid move!");
            printMaze();
        }
    }

    // Check if the player can move up
    private void tryMoveUp() {
        if (maze[playersCurrentRow - 1][playersCurrentColumn] != 'X') {
            swapCells(playersCurrentRow, playersCurrentRow - 1,
                    playersCurrentColumn, playersCurrentColumn);
            playersCurrentRow--;
            playersMovesCount++;
        } else {
            System.out.println("Invalid move!");
            printMaze();
        }
    }

    // Check if the player can move to the right
    private void tryMoveRight() {
        if (maze[playersCurrentRow][playersCurrentColumn + 1] != 'X') {
            swapCells(playersCurrentRow, playersCurrentRow,
                    playersCurrentColumn, playersCurrentColumn + 1);
            System.out.println();
            printMaze();
            playersCurrentColumn++;
            playersMovesCount++;

        } else {
            System.out.println("Invalid move!");
            printMaze();
        }
    }

    // Check if the player can move to the left
    private void tryMoveLeft() {
        if(maze[playersCurrentRow][playersCurrentColumn - 1] != 'X') {
            swapCells(playersCurrentRow, playersCurrentRow,
                    playersCurrentColumn, playersCurrentColumn - 1);

            playersCurrentColumn--;
            playersMovesCount++;
        } else {
            System.out.println("Invalid move!");
            printMaze();
        }
    }

    // Returns true if the player is not on an edge
    boolean playerNotOnEdge() {
		return (playersCurrentColumn != 0) && (playersCurrentColumn != maze.length - 1) &&
				(playersCurrentRow != 0) && (playersCurrentRow != maze.length - 1);
	}
		
	// Update the players position on maze
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){
		boolean evaluate=true;//evaluate()
		if(evaluate) {
			char previousCell = maze[currentRow][currentColumn];
			maze[currentRow][currentColumn] = maze[newRow][newColumn];
			maze[newRow][newColumn] = previousCell;
			System.out.println();
			printMaze();
		}
	}

    // Prints the highscore
    public void printHighscore() {
        scoreboard.printBoard();
    }

    // Returns the number of moves
    public int getMovesCount() {
        return playersMovesCount;
    }
}
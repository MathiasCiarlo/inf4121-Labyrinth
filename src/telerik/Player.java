package telerik;

public class Player {
	private String name;
	private int movesCount;

    public Player(String name, int movesCount) {
        this.name = name;
        this.movesCount = movesCount;
    }

    // Returns the name
    String getName() {
        return name;
    }

    // Returns the number of moves
    int getMovesCount() {
        return movesCount;
    }
}
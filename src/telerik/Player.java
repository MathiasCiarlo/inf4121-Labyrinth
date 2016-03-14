package telerik;

public class Player {
	private String name;
	private int movesCount;

    public Player(String name, int movesCount) {
        this.name = name;
        this.movesCount = movesCount;
    }

    String getName() {
        return name;
    }

    int getMovesCount() {
        return movesCount;
    }
}
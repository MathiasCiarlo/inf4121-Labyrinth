package telerik;

import java.util.LinkedList;


public class HighScoreBoard {
	LinkedList<Player> list;
	public final int boardSize = 5;
	public HighScoreBoard(){
		list = new LinkedList<Player>();
	}

    // Adds the player to the list if he is top 5
    public boolean addPlayerToChart(Player player){

        // If the list is empty, just add the player
        if(list.size() == 0) {
            list.addFirst(player);
            return true;
        }

        // Insert the player at the right position
        for (int i = 0; i < list.size(); i++) {
            Player tmp = list.get(i);
            if (player.getMovesCount() <= tmp.getMovesCount()) {
                list.add(i, player);

                // If the list is full, we need to remove the last element
                if (list.size() > boardSize)
                    list.removeLast();
                return true;
            }
        }

        // Add the player to the end of list if there is room
        if (list.size() < boardSize) {
            list.addLast(player);
            return true;
        }

        // Player's score is not top 5, was not inserted
        return false;
    }

	void printBoard(){
		System.out.println("Score :");
		for(int i = 0; i < list.size(); i++){
			Player p = (Player) list.get(i);
			System.out.print((i + 1) + ". Name - " + p.getName() + " ");
			System.out.print("Escaped in " + p.getMovesCount() + " moves");
			System.out.println();
		}
	}
}

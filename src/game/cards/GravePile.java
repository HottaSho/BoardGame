package game.cards;

import game.gfx.Board;
import game.linklist.ListIterator;

public class GravePile extends CardPile {

	public GravePile(int x, int y, Board board) {
		super(x, y, board);
	}

	// return pile as string
	public String toString() {
		String result = "";
		ListIterator iterator = cardList.iterator();
		for (int i = 0; !iterator.atEnd(); iterator.next()) {
			result += (i++) + ". " + iterator.current().toString() + "\n";
		}
		return result;
	}

	public void select(int tx, int ty){
		//selected = true;
		
	}
	
}

package game.cards;

import game.TestApplet;
import game.linklist.ListIterator;

public class DeckPile extends CardPile{

	public DeckPile(int x, int y, Card[] list){
		super(x,y);
		addArray(list);
	}
	
	// return pile as string
	public String toString(){
		String result = "";
		ListIterator iterator = cardList.iterator();
		for(int i=0; !iterator.atEnd(); iterator.next()){
			result+=(i++)+". "+iterator.current().toString()+"\n";
		}
		return result;
	}
	
	public void select(int tx, int ty){
		
		TestApplet.drawCard();
		
	}
	
}

package game.cards;

import game.linklist.ListIterator;

public class GravePile extends CardPile{

	public GravePile(int x, int y){
		super(x,y);
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
	
}

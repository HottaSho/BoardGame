package game.cards;

public class DeckPile extends CardPile{

	public DeckPile(int x, int y, Card[] list){
		super(x,y);
		addArray(list);
	}
	
}

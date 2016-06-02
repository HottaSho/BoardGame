package game.cards;

public class BoardPile extends CardPile{

	public BoardPile(int x, int y){
		super(x, y);
	}
	
	public void select(int tx, int ty){
		
		if(top()!=null) top().flip();
		
	}
	
}

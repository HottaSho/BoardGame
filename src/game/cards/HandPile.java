package game.cards;

import game.gfx.Board;

public class HandPile extends CardPile{
	
	public HandPile(int x, int y, Board board){
		super(x,y,board);
	}
	
	public void select(int tx, int ty){
		if(!empty())
			selected = true;
		//board.useCard(this.top());
		
	}
	
}

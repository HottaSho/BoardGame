package game.cards;

import game.gfx.Board;

public class BoardPile extends CardPile{

	public BoardPile(int x, int y, Board board){
		super(x, y,board);
	}
	
	public void select(int tx, int ty){
		
		if(top()!=null) top().flip();
		
	}
	
}

package game.cards;

import game.gfx.Board;

public class BoardPile extends CardPile{

	private int position;
	
	public BoardPile(int x, int y, Board board, int position) {
		super(x, y, board);
		this.position = position;
	}
	
	public BoardPile(int x, int y, Board board){
		super(x, y,board);
	}
	
	public void select(int tx, int ty){
		
		if(top()!=null) top().flip();
		else {
			Card card = board.playCard(position);
			if(card!=null){
				addCard(card);
			}
		}
		//selected = true;
			
	}
	
}

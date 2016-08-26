package game.cards;

import game.gfx.Board;

public class BoardPile extends CardPile{

	private int position;
	private boolean own;
	
	public BoardPile(int x, int y, Board board, int position, boolean own) {
		super(x, y, board);
		this.position = position;
		this.own = own;
	}
	
	public BoardPile(int x, int y, Board board){
		super(x, y,board);
	}
	
	public void select(int tx, int ty){
		selected = true;
		
		if(top()!=null){
			//top().flip();
			if(position < 10 && own==false)
				board.attack(position);
		}
		else {
			Card card = board.playCard(position);
			if(card!=null){
				addCard(card);
			}
		}
		//selected = true;
			
	}
	
}

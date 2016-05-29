package game.cards;

import game.TestApplet;

public class HandPile extends CardPile{
	
	public HandPile(int x, int y){
		super(x,y);
	}
	
	public void select(int tx, int ty){
		TestApplet.useCard(this.top());
		
	}
	
}

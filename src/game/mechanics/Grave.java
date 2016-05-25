package game.mechanics;

import game.cards.Card;

public class Grave {

	int size = 0;
	Card[] grave = new Card[0];
	
	public void add(Card card){
		Card[] temp = grave;
		grave = new Card[++size];
		for(int i=0;i<size;i++){
			grave[i]=temp[i];
		}
		grave[size-1] = card;
	}
	
}

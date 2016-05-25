package game.mechanics;

import game.cards.Card;

public class Hand {

	int maxSize = 7;
	int size = 5;
	Card[] hand = new Card[size];
	
	// drawing a card... if hand is full return true, false otherwise
	public boolean draw(int num){
		if((size+1)>maxSize) return true;
		size+=1;
		draw(num-1);
		return false;
	}
	
}

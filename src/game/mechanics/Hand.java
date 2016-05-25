package game.mechanics;

import game.cards.Card;

public class Hand {

	int maxSize = 7;
	int size = 5;
	int oldSize = size;
	Card[] hand = new Card[size];
	
	// drawing a card... if hand is full return true, false otherwise
	public int draw(int num){
		int milled = 0;
		if((size+1)>maxSize){
			System.out.println("Hand if full! A card is discarded.");
			milled++;
		}else{size+=1;}
		System.out.println("Drawing a card...");
		draw(num-1);
		return milled;
	}
	
	public void add(Card card){
		hand[oldSize-1] = card;
		oldSize++;
	}
	
	public int getSize(){
		return size;
	}
	
}

package game.mechanics;

import game.cards.Card;

public class Hand {

	int maxSize;
	int size = 0;
	int oldSize = size;
	Card[] cards = new Card[size];
	
	public Hand(int maxSize){
		this.maxSize=maxSize;
	}
	
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
		increase();
		cards[oldSize-1] = card;
		oldSize++;
	}
	
	public int getSize(){
		return size;
	}
	
	public void decrease(){
		Card[] temp = cards;
		cards = new Card[--size];
		for(int i=0;i<size;i++){
			cards[i]=temp[i];
		}
	}
	
	public void increase(){
		Card[] temp = cards;
		cards = new Card[++size];
		for(int i=0;i<size-1;i++){
			cards[i]=temp[i];
		}
	}
	
}

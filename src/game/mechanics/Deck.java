package game.mechanics;

import game.cards.Card;

public class Deck {
	
	int size = 30;
	Card[] cards = new Card[30];
	
	public Card[] shuffle(){
		int curIndex = cards.length, randIndex;
		Card tempCard;
		
		System.out.println("Shuffling deck...");
		
		// While there remains elements to shuffle...
		while(0 != curIndex){
			
			// Pick a remaining element...
			randIndex = (int) Math.floor(Math.random()*curIndex);
			curIndex -= 1;
			
			// And swap it with the current element.
			tempCard = cards[curIndex];
			cards[curIndex] = cards[randIndex];
			cards[randIndex] = tempCard;
		}
		
		System.out.println("Shuffled.");
		return cards;
	}
	
	public void mill(int num, Grave grave){
		if(num<=0) return;
		grave.add(getCard());
		mill(num-1,grave);
	}
	
	public Card getCard(){
		if(size==0) return null;
		Card result = cards[size-1];
		decrease();
		return result;
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

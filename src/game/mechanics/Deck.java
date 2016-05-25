package game.cards;

public class Deck {
	
	int size = 30;
	Card[] cards = new Card[30];
	
	public Card[] shuffle(Card[] deck){
		int curIndex = deck.length, randIndex;
		Card tempCard;
		
		// While there remains elements to shuffle...
		while(0 != curIndex){
			
			// Pick a remaining element...
			randIndex = (int) Math.floor(Math.random()*curIndex);
			curIndex -= 1;
			
			// And swap it with the current element.
			tempCard = deck[curIndex];
			deck[curIndex] = deck[randIndex];
			deck[randIndex] = tempCard;
		}
		
		return deck;
	}
	
}

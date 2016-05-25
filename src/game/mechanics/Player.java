package game.mechanics;

public class Player {

	int startHand = 5;
	int maxHand = 7;
	int health = 1000;
	
	Deck myDeck;
	Hand myHand = new Hand(7);
	Grave myGrave = new Grave();
	
	public Player(Deck newDeck){
		myDeck = newDeck;
		myDeck.shuffle();
		draw(5);
	}
	
	public void draw(int num){
		int milled = myHand.draw(num);
		add(num-milled);
		myDeck.mill(milled,myGrave);
	}
	
	public void add(int num){
		if(num<=0) return;
		myHand.add(myDeck.getCard());
		add(num-1);
	}
	
}

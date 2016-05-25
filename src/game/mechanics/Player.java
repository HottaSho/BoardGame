package game.mechanics;

public class Player {

	Deck myDeck = new Deck();
	Hand myHand = new Hand();
	Grave myGrave = new Grave();
	int health = 1000;
	
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

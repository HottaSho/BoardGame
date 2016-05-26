package game.cards;

public class Card {
	// data fields
	final static int width = 50;
	final static int height = 70;
	
	private static String types[] = {"Monster", "Spell", "Trap"};
	
	private boolean faceup;
	private int type;
	private String name;
	private int attack = -1;
	private int health = -1;
	
	// constructor
	public Card(String n, int t){
		name = n;
		type = t;
		faceup = false;
	}
	
	// get type of card as an int in the interval [0, 2]
	public int getType(){
		return type;
	}
	
	// get name of card
	public String getName(){
		return name;
	}
	
	// true if card is face up, false otherwise
	public boolean faceUp(){
		return faceup;
	}
	
	// change value of faceup
	public void flip(){
		faceup = !faceup;
	}
	
	// draw the card
	public void draw(){
		
	}
	
	public String toString(){
		return name + ", " + type;
	}
	
}

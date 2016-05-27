package game.cards;

public class Card {
	// data fields
	final static int width = 50;
	final static int height = 70;
	
	private static String types[] = {"MONSTER", "SPELL", "TRAP"};
	
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
	
	// constructor to set attack and health
	public Card(String n, int t, int a, int h){
		name = n;
		type = t;
		attack = a;
		health = h;
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
		String stats = "";
		if(type==0) stats += ", " + "ATK: " + attack + " - VIT: " + health;
		return name + ", " + types[type] + stats;
	}
	
}

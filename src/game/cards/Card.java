package game.cards;

import java.awt.Color;
import java.awt.Graphics;

import game.TestApplet;

public class Card {
	// data fields
	public final static int width = 50;
	public final static int height = 70;
	
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
	
	public boolean equals(Card other){
		if(this.type==other.type && this.name.equals(other.name) && this.attack==other.attack && this.health==other.health) return true;
		return false;
	}
	
	// draw the card
	public void draw(Graphics g, int x, int y) {
		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// draw body of card
		if (faceUp()) {
			if(this.type==0){
				g.setColor(Color.red);
			}else if(this.type==1){
				g.setColor(Color.green);
			}else{
				g.setColor(Color.magenta);
			}
			g.drawLine(x + 15, y + 5, x + 15, y + 65);
			g.drawLine(x + 35, y + 5, x + 35, y + 65);
			g.drawLine(x + 5, y + 20, x + 45, y + 20);
			g.drawLine(x + 5, y + 35, x + 45, y + 35);
			g.drawLine(x + 5, y + 50, x + 45, y + 50);
			g.setColor(Color.black);
			g.setFont(TestApplet.myFont);
			g.drawString(name, x + (5), y + (height/7));
		}else{
			g.setColor(Color.black);
			g.drawLine(x + 15, y + 5, x + 15, y + 65);
			g.drawLine(x + 35, y + 5, x + 35, y + 65);
			g.drawLine(x + 5, y + 20, x + 45, y + 20);
			g.drawLine(x + 5, y + 35, x + 45, y + 35);
			g.drawLine(x + 5, y + 50, x + 45, y + 50);
		}
	}
	
}

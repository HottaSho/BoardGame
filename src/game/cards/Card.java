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
		if(other==null) return false;
		if(this.type==other.type && this.name.equals(other.name) && this.attack==other.attack && this.health==other.health) return true;
		return false;
	}
	
	private void drawSquare(Graphics g, int x, int y,  int type){
		Color colors[] = { new Color(0,102,204), new Color(204,0,0),
				new Color(0,204,0), new Color(102,0,204)
		};
		
		Color color = colors[type];
		
		g.setColor(color);
        g.fillRect(x + 1, y + 1, width - 2, height - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + height - 1, x, y);
        g.drawLine(x, y, x + width - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + height - 1,
                         x + width - 1, y + height - 1);
        g.drawLine(x + width - 1, y + height - 1,
                         x + width - 1, y + 1);
	}
	
	// draw the card
	public void draw(Graphics g, int x, int y) {
		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// draw body of card
		if (faceUp()) {
			drawSquare(g, x, y, type+1);
			if(this.type==0){
				g.setColor(Color.black);
				g.setFont(TestApplet.myFont);
				g.drawString("A:"+attack, x+2, y+height-1);
				g.drawString("H:"+health, x+width-21, y+height-1);
			}
			g.setColor(Color.black);
			g.setFont(TestApplet.myFont);
			g.drawString(name, x + (5), y + (height/7));
		}else{
			drawSquare(g, x, y, 0);
		}
	}
	
}

package game.cards;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Card {
	// data fields
	public final static int width = 50;
	public final static int height = 70;
	
	private static String types[] = {"MONSTER", "SPELL", "TRAP", "NULL"};
	
	private boolean faceup;
	private int type;
	private String name;
	private int mana = -1;
	private int attack = -1;
	private int health = -1;
	
	// constructors
	public Card(String n, int t, int m){
		name = n.toUpperCase();
		type = t;
		mana = m;
		faceup = false;
	}
	
	public Card(String n, int t, int m, int a, int h){
		name = n.toUpperCase();
		type = t;
		mana = m;
		attack = a;
		health = h;
		faceup = false;
	}
	
	public Card(Card other) {
		this.name = other.name;
		this.type = other.type;
		this.mana = other.mana;
		this.faceup = other.faceup;
		if(type==0) {
			this.attack = other.attack;
			this.health = other.health;
		}
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
	
	public int getAttack() {
		return attack;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean damage(int dmg) {
		boolean state = false;
		health-=dmg;
		if(health<=0) state = true;
		return state;
	}
	
	public String toString(){
		String stats = "-";
		if(type==0) stats += attack + "-" + health;
		return name + stats;
	}
	
	public boolean equals(Card other){
		if(other==null) return false;
		if(this.type==other.type && this.name.equals(other.name) && this.attack==other.attack && this.health==other.health) return true;
		return false;
	}
	
	private void drawMana(Graphics g, int x, int y) {
		int size = 20;
		
		g.setColor(Color.BLUE);
		g.fillOval(x, y, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, size, size);
		Font myFont = new Font("Serif", Font.PLAIN, 15);
		g.setFont(myFont);
		int offset = 7;
		if(mana > 9) offset = 6;
		g.drawString(""+mana, x+offset, y+14);
	}
	
	private void drawStats(Graphics g, int x, int y) {
		int size = 20;
		//g.setColor(Color.BLUE);
		//g.fillOval(x, y, size, size);
		
		g.setColor(Color.RED);
		g.fillOval(x-(size/4), y+height-(size/4)*3, size, size);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x+width-(size/4)*3, y+height-(size/4)*3, size, size);
		
		g.setColor(Color.BLACK);
		g.drawOval(x-(size/4), y+height-(size/4)*3, size, size);
		g.drawOval(x+width-(size/4)*3, y+height-(size/4)*3, size, size);
		//g.drawOval(x, y, size, size);
		
		g.setColor(Color.BLACK);
		Font myFont = new Font("Serif", Font.PLAIN, 15);
		g.setFont(myFont);
		int aOffset, bOffset, cOffset;
		if(attack>9)
			aOffset = -3;
		else
			aOffset = 1;
		if(health>9)
			bOffset = 12;
		else
			bOffset = 8;
		if(mana>9)
			cOffset = 6;
		else
			cOffset = 7;
		g.drawString(""+attack, x+aOffset, y+height);
		g.drawString(""+health, x+width-bOffset, y+height);
		//g.drawString(""+mana, x+cOffset, y+14);
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
		Font myFont = new Font("Serif", Font.PLAIN, 10);
		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// draw body of card
		if (faceUp() && type!=3) {
			drawSquare(g, x, y, type+1);
			if(this.type==0){
				g.setColor(Color.black);
				drawStats(g, x, y);
			}
			drawMana(g, x, y);
			g.setColor(Color.black);
			g.setFont(myFont);
			g.drawString(name, x+(width/2)-(name.length()/2)*5, y + (height/2));
		}else{
			drawSquare(g, x, y, 0);
		}
	}
	
}
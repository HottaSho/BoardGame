package game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Overlay {

	private int WIDTH = 395;
	private int HEIGHT = 560;
	private int offset = 10;
	private int size = 40;
	private int startx = HEIGHT / 2 - size / 2 - offset;

	private int bubblew = 80;
	private int bubbleh = 60;
	private int bubblex = WIDTH / 2 - bubblew / 2;
	private int bubbley = HEIGHT / 2 - bubbleh / 2 - offset;

	private int barh = HEIGHT / 2;
	private int barSize = 3;
	private int timer = 0;

	private int gameState = -1;
	private boolean displaying = false;
	private int move = 0;

	private String dispText = "";
	
	private Board board;
	
	public Overlay(Board set) {
		board = set;
	}
	
	private void displayMana(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString(""+board.mana, 5, HEIGHT/2);
		g.drawString(""+board.oMana, 5, HEIGHT/2 - 15);
	}
	
	private void displayBar(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, barh - barSize / 2 - offset, WIDTH, barSize);
		// g.fillRect(0, startx, WIDTH, size);
		if (timer > 40 && barSize < 40) {
			barSize++;
			timer = 0;
		} else {
			timer++;
		}
	}

	public void displayMessage(String word) {
		dispText = "";
		displaying = true;
	}
	
	private void displayMessage(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(bubblex, bubbley, bubblew, bubbleh);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString(dispText, bubblex + 4, bubbley + bubbleh / 2 + 1);
		if (bubblex <= WIDTH) {
			if (barSize >= 40) {
				if (timer > 20) {
					bubblex++;
					timer = 0;
				} else {
					timer++;
				}
			}
		} else {
			displaying = false;
		}
	}

	public void display(Graphics g) {
		if (displaying) {
			displayBar(g);
			displayMessage(g);
		}
		displayMana(g);
	}

}

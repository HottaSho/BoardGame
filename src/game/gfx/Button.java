package game.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import game.cards.Card;

public class Button {

	private String turnOne = "END TURN";
	private String turnTwo = "WAIT";

	private Game game;
	
	private int height = 50;
	private int width = 70;
	private int x;
	private int y;

	public Button(int tx, int ty, Game game) {
		x = tx;
		y = ty;
		this.game = game;
	}

	public boolean includes(int tx, int ty){
		return x<= tx && tx <= x + width &&
				y <= ty && ty <= y + height;
	}
	
	public void select(int tx, int ty) {
		if(game.board.yourTurn) game.board.encode(4);
	}
	
	private void drawSquare(Graphics g, int x, int y, int type, Color color) {
		g.setColor(color);
		g.fillRect(x + 1, y + 1, width - 2, height - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + height - 1, x, y);
		g.drawLine(x, y, x + width - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + height - 1, x + width - 1, y + height - 1);
		g.drawLine(x + width - 1, y + height - 1, x + width - 1, y + 1);
	}

	// draw the card
	public void display(Graphics g) {
		// clear rectangle, draw border
//		g.clearRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		drawSquare(g, x, y, 0, Color.CYAN);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.WHITE);
		if(game.board.yourTurn){
			g.drawString(turnOne, x + 3, y + 5 + height / 2);
		}else{
			g.drawString(turnTwo, x + width / 3 - 2, y + 5 + height / 2);
		}
	}

}

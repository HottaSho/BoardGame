package game.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import game.Game;

@SuppressWarnings("serial")
public class Painter extends JPanel implements MouseListener {

	public boolean selected = false;
	
	Game game;

	public Painter(Game game) {
		this.game = game;
		setFocusable(true);
		requestFocus();
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (game.accepted) {
			int x = e.getX();
			int y = e.getY();

			if (game.board.yourTurn) {
				if (game.board.select(x, y)) {
					//repaint();
					//game.server.writeData("bob");
					selected = true;
				}else {
					selected = false;
				}
				//System.out.println("DATA WAS SENT");
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

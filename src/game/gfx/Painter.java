package game.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import game.Game;

@SuppressWarnings("serial")
public class Painter extends JPanel implements MouseListener {

	public boolean selected = false;
	
	private Image dbImage;
	private Graphics dbg;
	Game game;

	public Painter(Game game) {
		this.game = game;
		setFocusable(true);
		requestFocus();
		setBackground(Color.DARK_GRAY);
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (game.accepted) {
			int x = e.getX();
			int y = e.getY();

			//game.board.overlay.toggleDisplay();
			
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

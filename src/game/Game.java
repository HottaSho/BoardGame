package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import game.Game;
import game.gfx.Board;
import game.gfx.Painter;
import game.net.GameServer;

public class Game implements Runnable {

	public static final int WIDTH = 395;
    public static final int HEIGHT = 560;
    public static final int SCALE = 3;
    public static final String NAME = "Game";

	public boolean accepted = false;
	public boolean cantConnect = false;
	public int errors = 0;
	
	private JFrame frame;
	public static Game game;
	public Board board;
	public Painter painter;
	public GameServer server;
	public Thread thread;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game(){
		board = new Board(0,0,this);
		
		painter = new Painter(this);
		painter.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		server = new GameServer(this);
		
		board.setServer(server);
		server.start();
		
		frame = new JFrame();
		frame.setTitle(NAME);
		frame.setContentPane(painter);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		thread = new Thread(this, "Game");
		thread.start();
	}

	public void run() {
		while (true) {
			tick();
			painter.repaint();
			if(!accepted) server.listenForServerRequest();
		}
	}
	
	public void tick() {
		if (errors >= 10) cantConnect = true;

		if (accepted && !cantConnect) {
			server.tick();
		}
	}
	
	public void render(Graphics g) {
		board.display(g);
	}
	
}
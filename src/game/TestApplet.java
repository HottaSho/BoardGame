package game;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import game.cards.*;
import game.net.*;

public class TestApplet extends Applet {
	
	final static int no_card_piles = 19;
	final static int no_hand_piles = 7;
	final static int no_boardA_piles = 5;
	final static int no_boardB_piles = 5;
	
	final static int topMargin = 5;
	final static int leftMargin = 5;
	final static int distBoard = 10;
	final static int distHand = 5;
	final static int distDeck = 30;
	final static int distBuffer = 20;
	final static int distPlayer = distBuffer + topMargin + 3 * (Card.height + distBoard);
	final static int distScreen = distPlayer - distBuffer + 5;
	
	static DeckPile deckPile;
	static GravePile gravePile;
	static HandPile hand[];
	static BoardPile boardA[];
	static BoardPile boardB[];
	static CardPile allPiles[];
	
	static DeckPile odeckPile;
	static GravePile ogravePile;
	static HandPile ohand[];
	static BoardPile oboardA[];
	static BoardPile oboardB[];
	static CardPile oallPiles[];
	//static Screen screen;
	
	final static int start_hand = 5;
	final static int max_hand = 7;
	static int cur_hand = 0;
	
	static int pHealth = 1000;
	static int oHealth = 1000;
	static int turnTime = 120;
	
	private GameClient socketClient;
	private GameServer socketServer;
	
	public final static Font myFont = new Font("Serif",Font.PLAIN,10);
	
	public void init(){
		
		// Define, instantiate and register a MouseListener object.
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				showStatus("Clicked at "+ x + ", " + y);
				
				for (int i = 0; i < no_card_piles; i++)
					if (allPiles[i].includes(x, y)) {
						allPiles[i].select(x, y);
						repaint();
					}
				}
			});

		initialize();
		
	}
	
	public void initialize(){
//		TextIO.putln("width = " + (leftMargin + (Card.width + distBoard) * (no_boardA_piles + 1) + distDeck));
//		TextIO.putln("height = " + 2*(topMargin + 2 * (Card.height + distBoard)));
		initPlayer();
		initOpponent();
		//initScreen();
		initNet();
		startGame();
		socketClient.sendData("ping".getBytes());
	}
	
	public void initNet(){
		if(JOptionPane.showConfirmDialog(this, "Do you want to run the server") == 0){
			socketServer = new GameServer(this);
			socketServer.start();
		}
		
		socketClient = new GameClient(this, "localhost");
		socketClient.start();
	}
	
	public void initPlayer(){
		// first allocate the arrays
		allPiles = new CardPile[no_card_piles];
		hand = new HandPile[no_hand_piles];
		boardA = new BoardPile[no_boardA_piles];
		boardB = new BoardPile[no_boardB_piles];
			
		// then fill them in
		int widthDeck = leftMargin + (Card.width + distBoard) * (no_boardA_piles) + distDeck;
		int widthHand = (Card.width + distHand);
		int widthBoard = (Card.width + distBoard);
			
		allPiles[0] = deckPile = new DeckPile(widthDeck, topMargin + Card.height + distBoard + distPlayer,TestDecks.getTestDeck0());
		allPiles[1] = gravePile = new GravePile(widthDeck, topMargin + distPlayer);
				
		for(int i=0; i<no_hand_piles; i++)
			allPiles[2+i] = hand[i] = new HandPile(leftMargin + widthHand * i, topMargin + 2 * (Card.height + distBoard) + distPlayer);
				
		for(int i=0; i<no_boardA_piles; i++)
			allPiles[(2+no_hand_piles)+i] = boardA[i] = new BoardPile(leftMargin + widthBoard * i, topMargin + distPlayer);
				
		for(int i=0; i<no_boardB_piles; i++)
			allPiles[(2+no_hand_piles+no_boardA_piles)+i] = boardB[i] = new BoardPile(leftMargin + widthBoard * i, topMargin + Card.height + distBoard + distPlayer);
	}
	
	public void initOpponent(){
		// first allocate the arrays
		oallPiles = new CardPile[no_card_piles];
		ohand = new HandPile[no_hand_piles];
		oboardA = new BoardPile[no_boardA_piles];
		oboardB = new BoardPile[no_boardB_piles];
			
		// then fill them in
		int widthDeck = leftMargin + (Card.width + distBoard) + distDeck;
		int widthHand = (Card.width + distHand);
		int widthBoard = (Card.width + distBoard);
					
		oallPiles[0] = odeckPile = new DeckPile(leftMargin, topMargin + Card.height + distBoard, TestDecks.getTestDeck0());
		oallPiles[1] = ogravePile = new GravePile(leftMargin, topMargin + 2 * (Card.height + distBoard));
						
		for(int i=0; i<no_hand_piles; i++)
			oallPiles[2+i] = ohand[i] = new HandPile(leftMargin + widthHand * i, topMargin);
						
		for(int i=0; i<no_boardA_piles; i++)
			oallPiles[(2+no_hand_piles)+i] = oboardA[i] = new BoardPile(widthDeck + widthBoard * i, topMargin + 2 * (Card.height + distBoard));
						
		for(int i=0; i<no_boardB_piles; i++)
			oallPiles[(2+no_hand_piles+no_boardA_piles)+i] = oboardB[i] = new BoardPile(widthDeck + widthBoard * i, topMargin + Card.height + distBoard);
	}
	
//	public void initScreen(){
//		int width = (Card.width + distBoard) * (no_boardA_piles) + distDeck + Card.width;
//		int height = 10;
//		screen = new Screen(leftMargin, distScreen, width, height);
//	}
	
	public void startGame(){
		deckPile.shuffle();
		drawCard(start_hand);
	}
	
	public void startTurn(){
		drawCard();
	}
	
	public static void useCard(Card aCard){
		for(int i=0;i<cur_hand;i++){
			if(hand[i].top().equals(aCard)){
				if(hand[i].top().getType()==0){
					if(playBoardA(hand[i].top())){
						hand[i].pop();
						cur_hand--;
						fixHand(i);
						return;
					}
				}else{
					if(playBoardB(hand[i].top())){
						hand[i].pop();
						cur_hand--;
						fixHand(i);
						return;
					}
				}
			}
		}
	}
	
	public void destCard(){
		
	}
	
	public static void fixHand(int x){
		for(int i=x;i<cur_hand;i++){
			if(i>=(max_hand-1)) return;
			hand[i].addCard(hand[i+1].pop());
		}
	}
	
	public static boolean playBoardA(Card aCard){
		for(int i=0;i<no_boardA_piles;i++){
			if(boardA[i].empty()){
				boardA[i].addCard(aCard);
				TextIO.putln("//card played at position A" + i);
				return true;
			}
		}
		TextIO.putln("//boardA is FULL");
		return false;
	}
	
	public static boolean playBoardB(Card aCard){
		for(int i=0;i<no_boardB_piles;i++){
			if(boardB[i].empty()){
				boardB[i].addCard(aCard);
				boardB[i].top().flip();
				TextIO.putln("//card played at position B" + i);
				return true;
			}
		}
		TextIO.putln("//boardB is FULL");
		return false;
	}
	
	public static void drawCard(){
		drawCard(1);
	}
	
	public static void drawCard(int x){
		if(x<=0) return;
		if((cur_hand+1)>max_hand){
			mill(1);
		}else{
			if(deckPile.empty()){
				TextIO.putln("//deck is EMPTY");
			}else{
				hand[cur_hand].addCard(deckPile.pop());
				hand[cur_hand].top().flip();
				cur_hand++;
				TextIO.putln("//a card is DRAWN to " + (cur_hand-1));
			}
		}
		drawCard(x-1);
	}
	
	public static void mill(){
		mill(1);
	}
	public static void mill(int x){
		if(x<=0) return;
		if(deckPile.empty()){
			TextIO.putln("//deck is EMPTY");
		}else{
			gravePile.addCard(deckPile.pop());
			TextIO.putln("//a card it MILLED");
			mill(x-1);
		}
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < no_card_piles; i++){
			allPiles[i].display(g);
			oallPiles[i].display(g);
		}
//		if(screen!=null){
//			screen.display(g);
//		}
	}
	
}
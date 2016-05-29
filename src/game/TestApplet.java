package game;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.cards.*;

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
	
	static DeckPile deckPile;
	static GravePile gravePile;
	static HandPile hand[];
	static BoardPile boardA[];
	static BoardPile boardB[];
	static CardPile allPiles[];
	
	final static int start_hand = 5;
	final static int max_hand = 7;
	static int cur_hand = 0;
	
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
		// first allocate the arrays
		allPiles = new CardPile[no_card_piles];
		hand = new HandPile[no_hand_piles];
		boardA = new BoardPile[no_boardA_piles];
		boardB = new BoardPile[no_boardB_piles];
		
		// then fill them in
		int deckWidth = leftMargin + (no_boardA_piles - 1) * (Card.width + distBoard);
		
		TextIO.putln("width = " + (leftMargin + (Card.width + distBoard) * (no_boardA_piles + 1) + distDeck));
		TextIO.putln("height = " + 2*(topMargin + 2 * (Card.height + distBoard)));
		
		allPiles[0] = deckPile = new DeckPile(leftMargin + (Card.width + distBoard) * (no_boardA_piles) + distDeck, topMargin + Card.height + distBoard,TestDecks.getTestDeck0());
		allPiles[1] = gravePile = new GravePile(leftMargin + (Card.width + distBoard) * (no_boardB_piles) + distDeck, topMargin);
		
		for(int i=0; i<no_hand_piles; i++)
			allPiles[2+i] = hand[i] = new HandPile(leftMargin + (Card.width + distHand) * i, topMargin + 2 * (Card.height + distBoard));
		
		for(int i=0; i<no_boardA_piles; i++)
			allPiles[(2+no_hand_piles)+i] = boardA[i] = new BoardPile(leftMargin + (Card.width + distBoard) * i, topMargin);
		
		for(int i=0; i<no_boardB_piles; i++)
			allPiles[(2+no_hand_piles+no_boardA_piles)+i] = boardB[i] = new BoardPile(leftMargin + (Card.width + distBoard) * i, topMargin + Card.height + distBoard);
	}
	
	public void start(){
		deckPile.shuffle();
		drawCard(start_hand);
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
		for (int i = 0; i < no_card_piles; i++)
			allPiles[i].display(g);
	}
	
}
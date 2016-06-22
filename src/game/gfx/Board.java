package game.gfx;

import java.awt.Graphics;

import game.Game;
import game.TextIO;
import game.cards.BoardPile;
import game.cards.Card;
import game.cards.CardPile;
import game.cards.DeckPile;
import game.cards.GravePile;
import game.cards.HandPile;
import game.cards.TestDecks;

public class Board {

	public int x;
	public int y;

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

	final static int start_hand = 5;
	final static int max_hand = 7;
	static int cur_hand = 0;

	public boolean yourTurn = false;
	
	public Game game;

	public Board(int tx, int ty, Game game) {
		this.game = game;
		x = tx;
		y = ty;
		
		initPlayer();
		initOpponent();
	}

	public void initPlayer() {
		// first allocate the arrays
		allPiles = new CardPile[no_card_piles];
		hand = new HandPile[no_hand_piles];
		boardA = new BoardPile[no_boardA_piles];
		boardB = new BoardPile[no_boardB_piles];

		// then fill them in
		int widthDeck = leftMargin + (Card.width + distBoard) * (no_boardA_piles) + distDeck;
		int widthHand = (Card.width + distHand);
		int widthBoard = (Card.width + distBoard);

		allPiles[0] = deckPile = new DeckPile(widthDeck, topMargin + Card.height + distBoard + distPlayer,
				this, TestDecks.getTestDeck0());
		allPiles[1] = gravePile = new GravePile(widthDeck, topMargin + distPlayer, this);

		for (int i = 0; i < no_hand_piles; i++)
			allPiles[2 + i] = hand[i] = new HandPile(leftMargin + widthHand * i,
					topMargin + 2 * (Card.height + distBoard) + distPlayer, this);

		for (int i = 0; i < no_boardA_piles; i++)
			allPiles[(2 + no_hand_piles) + i] = boardA[i] = new BoardPile(leftMargin + widthBoard * i,
					topMargin + distPlayer, this);

		for (int i = 0; i < no_boardB_piles; i++)
			allPiles[(2 + no_hand_piles + no_boardA_piles) + i] = boardB[i] = new BoardPile(leftMargin + widthBoard * i,
					topMargin + Card.height + distBoard + distPlayer, this);
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
					
		oallPiles[0] = odeckPile = new DeckPile(leftMargin, topMargin + Card.height + distBoard, this, TestDecks.getTestDeck0());
		oallPiles[1] = ogravePile = new GravePile(leftMargin, topMargin + 2 * (Card.height + distBoard), this);
						
		for(int i=0; i<no_hand_piles; i++)
			oallPiles[2+i] = ohand[i] = new HandPile(leftMargin + widthHand * i, topMargin, this);
						
		for(int i=0; i<no_boardA_piles; i++)
			oallPiles[(2+no_hand_piles)+i] = oboardA[i] = new BoardPile(widthDeck + widthBoard * i, topMargin + 2 * (Card.height + distBoard), this);
						
		for(int i=0; i<no_boardB_piles; i++)
			oallPiles[(2+no_hand_piles+no_boardA_piles)+i] = oboardB[i] = new BoardPile(widthDeck + widthBoard * i, topMargin + Card.height + distBoard, this);
	}
	
	public boolean select(int tx, int ty) {
		boolean flag = false;
		for (int i = 0; i < no_card_piles; i++)
			if (allPiles[i].includes(tx, ty)) {
				if (allPiles[i].empty()) {
					switch (i) {
					case 0:
						allPiles[0].addCard(allPiles[1].pop());
						break;
					case 1:
						allPiles[1].addCard(allPiles[0].pop());
						break;
					case 2:
						allPiles[2].addCard(allPiles[3].pop());
						break;
					case 3:
						allPiles[3].addCard(allPiles[2].pop());
						break;
					}
				} else
					allPiles[i].select(tx, ty);
				flag = true;
			}
		return flag;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < allPiles.length; i++) {
			if (allPiles[i].empty())
				result += "0";
			else {
				result += "1";
				if (allPiles[i].top().faceUp())
					result += "u";
				else
					result += "d";
			}
			if (i != (allPiles.length - 1))
				result += ",";
		}
		System.out.println(result);
		return result;
	}

	public void decode(String input) {
		System.out.println("decoding...");

	}

	public void startGame(){
		deckPile.shuffle();
		drawCard(start_hand);
	}
	
	public void startTurn(){
		drawCard();
	}
	
	public void useCard(Card aCard){
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
	
	public void fixHand(int x){
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
	
	public boolean playBoardB(Card aCard){
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
	
	public void drawCard(){
		drawCard(1);
	}
	
	public void drawCard(int x){
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
		game.painter.repaint();
		drawCard(x-1);
	}
	
	public void mill(){
		mill(1);
	}
	public void mill(int x){
		if(x<=0) return;
		if(deckPile.empty()){
			TextIO.putln("//deck is EMPTY");
		}else{
			gravePile.addCard(deckPile.pop());
			TextIO.putln("//a card it MILLED");
			mill(x-1);
		}
	}
	
	public void display(Graphics g) {
		for (int i = 0; i < no_card_piles; i++) {
			allPiles[i].display(g);
			oallPiles[i].display(g);
		}
	}

}

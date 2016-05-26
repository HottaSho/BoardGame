package game;

import game.cards.*;

public class Game {
	
	final static int no_card_piles = 19;
	final static int no_hand_piles = 7;
	final static int no_boardA_piles = 5;
	final static int no_boardB_piles = 5;
	
	static DeckPile deckPile;
	static GravePile gravePile;
	static HandPile hand[];
	static BoardPile boardA[];
	static BoardPile boardB[];
	static CardPile allPiles[];
	
	final static int start_hand = 5;
	final static int max_hand = 7;
	static int cur_hand = 0;
	
	public static void main(String[] args){
		TextIO.putln("Welcome to the Card Game\n");
		initialize();
		start();
		prompt();
		init();
	}
	
	public static void init(){
		int option = -1;
		while(option==-1){
			TextIO.putln("\nWhat would you like to do:");
			option = TextIO.getlnInt();
			if(option==1) displayHand();
			else if(option==2) useCard();
			else if(option==3) destCard();
			else if(option==4) displayBoardA();
			else if(option==5) displayBoardB();
			else if(option==6) displayGrave();
			else if(option==7) displayLife();
			else if(option==8) draw();
			else if(option==9) quit();
			else if(option==0) prompt();
			option=-1;
		}
	}
	
	public static void prompt(){
		TextIO.putln("\nEnter any of the following commands:");
		TextIO.putln("1: Diplay hand");
		TextIO.putln("2: Use card");
		TextIO.putln("3: Destroy card");
		TextIO.putln("4: Display monsters on field");
		TextIO.putln("5: Diplay spells on field");
		TextIO.putln("6: Disply cards in the grave");
		TextIO.putln("7: Display life");
		TextIO.putln("8: Draw a card");
		TextIO.putln("9: Quit");
		TextIO.putln("0: Display these commands");
	}
	
	public static void initialize(){
		// first allocate the arrays
		allPiles = new CardPile[no_card_piles];
		hand = new HandPile[no_hand_piles];
		boardA = new BoardPile[no_boardA_piles];
		boardB = new BoardPile[no_boardB_piles];
		
		// then fill them in
		allPiles[0] = deckPile = new DeckPile(0,0,TestDecks.getTestDeck0());
		allPiles[1] = gravePile = new GravePile(0,0);
		
		for(int i=0; i<no_hand_piles; i++)
			allPiles[2+i] = hand[i] = new HandPile(0,0);
		
		for(int i=0; i<no_boardA_piles; i++)
			allPiles[9+i] = boardA[i] = new BoardPile(0,0);
		
		for(int i=0; i<no_boardB_piles; i++)
			allPiles[14+i] = boardB[i] = new BoardPile(0,0);
	}
	
	public static void start(){
		deckPile.shuffle();
		draw(start_hand);
	}
	
	public static void displayHand(){
		for(int i=0;i<cur_hand;i++){
			TextIO.put(hand[i].toString());
		}
	}
	
	public static void useCard(){
		
	}
	
	public static void destCard(){
		
	}
	
	public static void displayBoardA(){
		TextIO.putln(deckPile.toString());
	}
	
	public static void displayBoardB(){
		
	}
	
	public static void displayGrave(){
		
	}
	
	public static void displayLife(){
		
	}
	
	public static void quit(){
		TextIO.putln("Would you like to quit yes(1) or no(0)");
		if(TextIO.getlnInt()==1) System.exit(0);
	}
	
	public static void draw(){
		draw(1);
	}
	
	public static void draw(int x){
		if(x<=0) return;
		if((cur_hand+1)>max_hand){
			mill(1);
		}else{
			hand[cur_hand].addCard(deckPile.pop());
			cur_hand++;
			TextIO.putln("//a card is DRAWN");
		}
		draw(x-1);
	}
	
	public static void mill(){
		mill(1);
	}
	public static void mill(int x){
		if(x<=0) return;
		gravePile.addCard(deckPile.pop());
		TextIO.putln("//a card it MILLED");
		mill(x-1);
	}
	
}

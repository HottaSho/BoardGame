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
		init();
	}
	
	public static void init(){
		int option = -1;
		prompt();
		while(option==-1){
			TextIO.putln("\nWhat would you like to do:");
			option = TextIO.getlnInt();
			switch(option){
				case 1: displayHand();
						break;
				case 2: useCard();
						break;
				case 3: destCard();
						break;
				case 4: displayBoardA();
						break;
				case 5: displayBoardB();
						break;
				case 6: displayGrave();
						break;
				case 7: displayDeck();
						break;
				case 8: draw();
						break;
				case 9: quit();
						break;
				case 0: prompt();
						break;
			}
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
		TextIO.putln("7: Display cards in the deck");
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
			TextIO.put(i + ". " + hand[i].toString());
		}
	}
	
	public static void useCard(){
		// check if hand is empty
		if(hand[0].empty()){
			TextIO.putln("Hand is EMPTY");
			return;
		}
		TextIO.putln("Which card would you like to use?");
		displayHand();
		int input = -1;
		while(input<0 || input>cur_hand){
			TextIO.putln("Enter between [0-"+(cur_hand-1)+"]");
			input = TextIO.getlnInt();
		}
		if(hand[input].top().getType()==0){
			if(playBoardA(hand[input].top())){
				hand[input].pop();
				fixHand(input);
				cur_hand--;
			}
		}else{
			if(playBoardB(hand[input].top())){
				hand[input].pop();
				fixHand(input);
				cur_hand--;
			}
		}
	}
	
	public static void destCard(){
		
	}
	
	public static void displayBoardA(){
		boolean flag = true;
		for(int i=0;i<no_boardA_piles;i++){
			if(!boardA[i].empty()){
				TextIO.put(i + ". " + boardA[i].toString());
				flag = false;
			}
		}
		if(flag) TextIO.putln("//boardA is EMPTY");
	}
	
	public static void displayBoardB(){
		boolean flag = true;
		for(int i=0;i<no_boardB_piles;i++){
			if(!boardB[i].empty()){
				TextIO.put(i + ". " + boardB[i].toString());
				flag = false;
			}
		}
		if(flag) TextIO.putln("//boardB is EMPTY");
	}
	
	public static void displayGrave(){
		TextIO.putln(gravePile.toString());
	}
	
	public static void displayDeck(){
		TextIO.putln(deckPile.toString());
	}
	
	public static void quit(){
		TextIO.putln("Would you like to quit yes(1) or no(0)");
		if(TextIO.getlnInt()==1) System.exit(0);
	}
	
	public static void fixHand(int x){
		for(int i=x;(i<cur_hand)||(i==(max_hand-1));i++){
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
				TextIO.putln("//card played at position B" + i);
				return true;
			}
		}
		TextIO.putln("//boardB is FULL");
		return false;
	}
	
	public static void draw(){
		draw(1);
	}
	
	public static void draw(int x){
		if(x<=0) return;
		if((cur_hand+1)>max_hand){
			mill(1);
		}else{
			if(deckPile.empty()){
				TextIO.putln("//deck is EMPTY");
			}else{
				hand[cur_hand].addCard(deckPile.pop());
				cur_hand++;
				TextIO.putln("//a card is DRAWN");
			}
		}
		draw(x-1);
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
	
}

package game.gfx;

import java.awt.Graphics;

import game.Game;
import game.TextIO;
import game.cards.BoardPile;
import game.cards.Card;
import game.cards.CardCollection2;
import game.cards.CardPile;
import game.cards.DeckPile;
import game.cards.GravePile;
import game.cards.HandPile;
import game.cards.TestDecks;
import game.net.GameServer;

public class Board {

	// enum for type of data
	enum Data {
		DRAW("DRAW", 0), PLAY("PLAY", 1), DESTROY("DESTROY", 2),
		ATTACK("ATTACK", 3), END("END", 4), START("START", 5), DECK("DECK", 6);

		private int id;
		private String name;

		private Data(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public String getGrave() {
			return name;
		}

		public boolean equals(String name) {
			if (this.name.equals(name))
				return true;
			else
				return false;
		}
	}

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
	final static int distButton = topMargin + 3 * (Card.height + distBoard);
	final static int distPlayer = distButton + 50;
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

	public int oHandCount = 0;
	public int oDeckCount = 30;

	public boolean yourTurn = false;
	public Button button;

	public Game game;
	public GameServer server;

	public Board(int tx, int ty, Game game) {
		this.game = game;
		x = tx;
		y = ty;

		initPlayer();
		initOpponent();
		initGUI();
	}

	public void initGUI() {
		button = new Button(315, distButton, game);
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

		allPiles[0] = deckPile = new DeckPile(widthDeck, topMargin + Card.height + distBoard + distPlayer, this,
				TestDecks.getTestDeck0());
		allPiles[1] = gravePile = new GravePile(widthDeck, topMargin + distPlayer, this);

		for (int i = 0; i < no_hand_piles; i++)
			allPiles[2 + i] = hand[i] = new HandPile(leftMargin + widthHand * i,
					topMargin + 2 * (Card.height + distBoard) + distPlayer, this);

		for (int i = 0; i < no_boardA_piles; i++)
			allPiles[(2 + no_hand_piles) + i] = boardA[i] = new BoardPile(leftMargin + widthBoard * i,
					topMargin + distPlayer, this, i);

		for (int i = 0; i < no_boardB_piles; i++)
			allPiles[(2 + no_hand_piles + no_boardA_piles) + i] = boardB[i] = new BoardPile(leftMargin + widthBoard * i,
					topMargin + Card.height + distBoard + distPlayer, this, i+10);
	}

	public void initOpponent() {
		// first allocate the arrays
		oallPiles = new CardPile[no_card_piles];
		ohand = new HandPile[no_hand_piles];
		oboardA = new BoardPile[no_boardA_piles];
		oboardB = new BoardPile[no_boardB_piles];

		// then fill them in
		int widthDeck = leftMargin + (Card.width + distBoard) + distDeck;
		int widthHand = (Card.width + distHand);
		int widthBoard = (Card.width + distBoard);

		oallPiles[0] = odeckPile = new DeckPile(leftMargin, topMargin + Card.height + distBoard, this);
		oallPiles[1] = ogravePile = new GravePile(leftMargin, topMargin + 2 * (Card.height + distBoard), this);

		for (int i = 0; i < no_hand_piles; i++)
			oallPiles[2 + i] = ohand[i] = new HandPile(leftMargin + widthHand * 6 - (widthHand * i), topMargin, this);

		for (int i = 0; i < no_boardA_piles; i++)
			oallPiles[(2 + no_hand_piles) + i] = oboardA[i] = new BoardPile(
					widthDeck + widthBoard * 4 - (widthBoard * i), topMargin + 2 * (Card.height + distBoard), this, i);

		for (int i = 0; i < no_boardB_piles; i++)
			oallPiles[(2 + no_hand_piles + no_boardA_piles) + i] = oboardB[i] = new BoardPile(
					widthDeck + widthBoard * 4 - (widthBoard * i), topMargin + Card.height + distBoard, this, i+10);
	}

	public void setServer(GameServer gs) {
		server = gs;
	}

	public boolean select(int x, int y) {
		boolean flag = false;
		for (int i = no_card_piles - 1; i >= 0; i--) {
			if (allPiles[i].includes(x, y)) {
				allPiles[i].select(x, y);
				game.painter.repaint();
				flag = true;
			} else {
				allPiles[i].unselect();
			}
		}
		if (button.includes(x, y)) {
			button.select(x, y);
			game.painter.repaint();
		}
		return flag;
	}

	public void encode(int type) {
		encode(type, "");
	}
	
	public void encode(int type, String addition) {
		String result = "";
		switch (type) {
		case 0:
			result += "DRAW,";
			result += deckPile.top().toString() + ",";
			break;
		case 1:
			result += "PLAY,";
			break;
		case 2:
			result += "DESTROY,";
			break;
		case 3:
			result += "ATTACK,";
			break;
		case 4:
			result += "END,";
			yourTurn = false;
			break;
		case 5:
			result += "START";
			break;
		case 6:
			result += "DECK,";
			result += deckPile.top().toString() + ",";
			break;
		}
		result+=addition;
		System.out.println("ENCODED - " + type + ": " + result);
		if (server == null)
			TextIO.putln("ERROR - NO SERVER");
		server.writeData(result);
	}

	public void decode(String input) {
		System.out.println("RECEIVED - " + input);
		String[] array = input.split(",");
		if (array[0].equals("START"))
			odeckPile.addCard(CardCollection2.get("NULL"));
		if (array[0].equals("END"))
			startTurn();
		if (array[0].equals("DRAW")) {
			String[] dataArray = array[1].split("-");
			ohand[oHandCount++].addCard(CardCollection2.get(dataArray[0]));
			oDeckCount--;
		}
		if (array[0].equals("DECK")) {
			String[] dataArray = array[1].split("-");
			odeckPile.pop();
			odeckPile.addCard(CardCollection2.get(dataArray[0]));
		}
		if (array[0].equals("PLAY")) {
			String[] dataArray = array[1].split("-");
			int posBoard = Integer.parseInt(dataArray[0].substring(1));
			int posHand = Integer.parseInt(dataArray[1].substring(1));
			if(posBoard<10){
				oboardA[posBoard].addCard(ohand[posHand].pop());
			}else{
				oboardB[(posBoard-10)].addCard(ohand[posHand].pop());
			}
			oHandCount--;
			oFixHand(posHand);
		}
		game.painter.repaint();
	}

	public boolean empty(CardPile[] pile) {
		for (int i = 0; i < pile.length; i++)
			if (!pile[i].empty())
				return false;
		return true;
	}

	public void startGame() {
		encode(Data.START.getId());
		deckPile.shuffle();
		drawCard(start_hand);
	}

	public void startTurn() {
		yourTurn = true;
		drawCard();
	}
	
	public Card playCard(int position) {
		for(int i=0; i<cur_hand; i++) {
			if(hand[i].selected()){
				int type = hand[i].top().getType();
				if(type == 0){
					if(position > 10) break;
				}
				else if(position < 10) break;
				Card result = hand[i].pop();
				cur_hand--;
				fixHand(i);
				encode(Data.PLAY.getId(), "B"+position+"-"+"H"+i+"-"+result.toString());
				return result;
			}
		}
		return null;
	}
	
	public void fixHand(int x) {
		for (int i = x; i < cur_hand; i++) {
			if (i >= (max_hand - 1))
				return;
			hand[i].addCard(hand[i + 1].pop());
		}
	}
	
	public void oFixHand(int x) {
		for (int i = x; i < oHandCount; i++) {
			if (i >= (max_hand - 1))
				return;
			ohand[i].addCard(ohand[i + 1].pop());
		}
	}

	public void drawCard() {
		drawCard(1);
	}

	public void drawCard(int x) {
		if (x <= 0)
			return;
		if ((cur_hand + 1) > max_hand) {
			mill(1);
		} else {
			if (deckPile.empty()) {
			} else {
				encode(Data.DRAW.getId());
				hand[cur_hand].addCard(deckPile.pop());
				hand[cur_hand].top().flip();
				cur_hand++;
			}
		}
		encode(Data.DECK.getId());
		game.painter.repaint();
		drawCard(x - 1);
	}

	public void mill() {
		mill(1);
	}

	public void mill(int x) {
		if (x <= 0)
			return;
		if (deckPile.empty()) {
		} else {
			gravePile.addCard(deckPile.pop());
			mill(x - 1);
		}
	}

	public void display(Graphics g) {
		for (int i = 0; i < no_card_piles; i++) {
			allPiles[i].display(g);
			oallPiles[i].display(g);
			button.display(g);
		}
	}

}

package game.cards;

import java.awt.Color;
import java.awt.Graphics;

import game.TextIO;
import game.gfx.Board;
import game.linklist.Link;
import game.linklist.LinkedList;
import game.linklist.ListIterator;

public class CardPile {
	// coordinates of the card pile
	private int x;
	private int y;
	
	// linked list of cards
	protected LinkedList cardList;
	
	protected Board board;
	
	CardPile(int tx, int ty, Board board){
		x = tx;
		y = ty;
		this.board = board;
		cardList = new LinkedList();
	}
	
	//////////////////////////////
	// not overridden
	
	//true if pile is empty, false otherwise
	public final boolean empty(){
		return cardList.empty();
	}
	
	// inspect card at the top of pile
	public final Card top(){
		return (Card)cardList.front();
	}
	
	// pop card  at the top of pile
	public final Card pop(){
		return (Card)cardList.pop();
	}
	
	//////////////////////////////
	// sometimes overridden
	
	// true if point falls inside pile, false otherwise
	public boolean includes(int tx, int ty){
		return x<= tx && tx <= x + Card.width &&
				y <= ty && ty <= y + Card.height;
	}
	
	// to be overridden
	public void select(int tx, int ty){
		
	}
	
	// add a card to pile
	public void addCard(Card aCard){
		cardList.add(aCard);
	}
	
	// return pile as string
	public String toString(){
		String result = "";
		ListIterator iterator = cardList.iterator();
		for(; !iterator.atEnd(); iterator.next()){
			result+=iterator.current().toString()+"\n";
		}
		return result;
	}
	
	// get number of cards in pile
	public int getNoCards(){
		return cardList.getSize();
	}
	
	public void addArray(Card[] list){
		for(int i=0;i<list.length;i++)
			addCard(list[i]);
	}
	
	public void shuffle(){
		Card[] list = shuffle(toArray());
		cardList.delete();
		addArray(list);
		TextIO.putln("//deck is SHUFFLED");
	}
	
	public Card[] shuffle(Card[] list){
		int x = list.length, i;
		Card temp;
		
		// while there remain elements to shuffle...
		while(x>0){
			// pick a remaining element...
			i = (int) Math.floor(Math.random() * x--);
			
			// and swap it with the current element.
			temp = list[x];
			list[x] = list[i];
			list[i] = temp;
		}
		
		return list;
	}
	
	private Card[] toArray(){
		Card[] result = new Card[cardList.getSize()];
		int i=0;
		for(Link c = cardList.getLink();c!=null;c=c.next())
			result[i++] = (Card) c.value();
		return result;
	}
	
	// draw pile
		public void display(Graphics g){
			g.setColor(Color.black);

		    if (cardList.empty())
		      g.drawRect(x, y, Card.width, Card.height);
		    else
		      top().draw(g, x, y);
		}
	
}

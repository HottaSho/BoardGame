package game.characters;

import game.cards.BoardPile;
import game.cards.CardPile;
import game.cards.DeckPile;
import game.cards.GravePile;
import game.cards.HandPile;

public class Player {

	static String username;
	
	public Player(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
	
}

package game.cards;

import java.util.HashMap;
import java.util.Map;

public class CardCollection {
	
	static HashMap <String, Card> mapper = new HashMap<String, Card>();
	
	static {
		mapper.put("NULL", new Card("NULL", 3));
		mapper.put("ONE", new Card("ONE", 2));
		mapper.put("TWO", new Card("TWO", 2));
		mapper.put("THREE", new Card("THREE", 2));
		mapper.put("FOUR", new Card("FOUR", 2));
		mapper.put("FIV", new Card("FIVE", 2));
		mapper.put("SIX", new Card("SIX", 2));
		
		mapper.put("SEVEN", new Card("SEVEN", 1));
		mapper.put("EIGHT", new Card("EIGHT", 1));
		mapper.put("NINE", new Card("NINE", 1));
		mapper.put("TEN", new Card("TEN", 1));
		mapper.put("ELEVEN", new Card("ELEVEN", 1));
		mapper.put("TWELVE", new Card("TWELVE", 1));
		
		mapper.put("THIRTEEN", new Card("THIRTEEN", 0, 1, 1));
		mapper.put("FOURTEEN", new Card("FOURTEEN", 0, 1, 7));
		mapper.put("FIFTEEN", new Card("FIFTEEN", 0, 2, 6));
		mapper.put("SIXTEEN", new Card("SIXTEEN", 0, 3, 5));
		mapper.put("SEVENTEEN", new Card("SEVENTEEN", 0, 4, 4));
		mapper.put("EIGHTEEN", new Card("EIGHTEEN", 0, 5, 3));
		mapper.put("NINETEEN", new Card("NINETEEN", 0, 6, 2));
		mapper.put("TWENTY", new Card("TWENTY", 0, 7, 1));
		mapper.put("TWENTYONE", new Card("TWENTYONE", 0, 9, 9));
		mapper.put("TWENTYTWO", new Card("TWENTYTWO", 0, 4, 12));
		mapper.put("TWENTYTHREE", new Card("TWENTYTHREE", 0, 5, 6));
		mapper.put("TWENTYFOUR", new Card("TWENTYFOUR", 0, 6, 7));
		mapper.put("TWENTYFIVE", new Card("TWENTYFIVE", 0, 8, 10));
		mapper.put("TWENTYSIX", new Card("TWENTYSIX", 0, 1, 2));
		mapper.put("TWENTYSEVEN", new Card("TWENTYSEVEN", 0, 2, 3));
		mapper.put("TWENTYEIGHT", new Card("TWENTYEIGHT", 0, 3, 4));
		mapper.put("TWENTYNINE", new Card("TWENTYNINE", 0, 4, 5));
		mapper.put("THIRTY", new Card("THIRTY", 0, 7, 9));
	}
	
	public static Card get(String card) {
		for(Card c : mapper.values()) {
			if(c.getName().equals(card)){
				return c;
			}
		}
		return null;
	}
	
}

package game.cards;

import java.util.HashMap;
import java.util.Map;

public class CardCollection {
	
	static HashMap <String, Card> mapper = new HashMap<String, Card>();
	
	static {
		mapper.put("NULL", new Card("NULL", 3, -1));
		mapper.put("ONE", new Card("ONE", 2, 0));
		mapper.put("TWO", new Card("TWO", 2, 1));
		mapper.put("THREE", new Card("THREE", 2, 1));
		mapper.put("FOUR", new Card("FOUR", 2, 2));
		mapper.put("FIV", new Card("FIVE", 2, 2));
		mapper.put("SIX", new Card("SIX", 2, 3));
		
		mapper.put("SEVEN", new Card("SEVEN", 1, 0));
		mapper.put("EIGHT", new Card("EIGHT", 1, 1));
		mapper.put("NINE", new Card("NINE", 1, 1));
		mapper.put("TEN", new Card("TEN", 1, 2));
		mapper.put("ELEVEN", new Card("ELEVEN", 1, 2));
		mapper.put("TWELVE", new Card("TWELVE", 1, 3));
		
		mapper.put("THIRTEEN", new Card("THIRTEEN", 0, 0, 1, 1));
		mapper.put("FOURTEEN", new Card("FOURTEEN", 0, 3, 1, 7));
		mapper.put("FIFTEEN", new Card("FIFTEEN", 0, 4, 2, 6));
		mapper.put("SIXTEEN", new Card("SIXTEEN", 0, 4, 3, 5));
		mapper.put("SEVENTEEN", new Card("SEVENTEEN", 0, 4, 4, 4));
		mapper.put("EIGHTEEN", new Card("EIGHTEEN", 0, 2, 5, 3));
		mapper.put("NINETEEN", new Card("NINETEEN", 0, 2, 6, 2));
		mapper.put("TWENTY", new Card("TWENTY", 0, 2, 7, 1));
		mapper.put("TWENTYONE", new Card("TWENTYONE", 0, 6, 9, 9));
		mapper.put("TWENTYTWO", new Card("TWENTYTWO", 0, 2, 4, 12));
		mapper.put("TWENTYTHREE", new Card("TWENTYTHREE", 0, 3, 5, 6));
		mapper.put("TWENTYFOUR", new Card("TWENTYFOUR", 0, 4, 6, 7));
		mapper.put("TWENTYFIVE", new Card("TWENTYFIVE", 0, 5, 8, 10));
		mapper.put("TWENTYSIX", new Card("TWENTYSIX", 0, 0, 1, 2));
		mapper.put("TWENTYSEVEN", new Card("TWENTYSEVEN", 0, 1, 2, 3));
		mapper.put("TWENTYEIGHT", new Card("TWENTYEIGHT", 0, 2, 3, 4));
		mapper.put("TWENTYNINE", new Card("TWENTYNINE", 0, 3, 4, 5));
		mapper.put("THIRTY", new Card("THIRTY", 0, 5, 7, 9));
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

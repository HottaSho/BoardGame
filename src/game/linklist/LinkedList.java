package game.linklist;

public class LinkedList {
	private Link firstLink;

	public LinkedList() {
		firstLink = null;
	}

	// true if empty, false otherwise
	public boolean empty() {
		return firstLink == null;
	}

	// add an Object to our list
	public void add(Object newValue) {
		firstLink = new Link(newValue, firstLink);
	}

	// inspect front of list
	public Object front() {
		if (firstLink == null)
			return null;

		return firstLink.value();
	}

	// pop front of list
	public Object pop() {
		if (firstLink == null)
			return null;

		Object result = firstLink.value();
		firstLink = firstLink.next();
		return result;
	}

	// return an iterator for the list
	public ListIterator iterator() {
		return new ListIterator(firstLink);
	}
	
	// get size of the list
	public int getSize(){
		int count = 0;
		ListIterator iterator = iterator();
		
		while(!iterator.atEnd()){
			count++;
			iterator.next();
		}
		return count;
	}
	
	// returns the list as an array
	public Object[] toArray(){
		Object[] result = new Object[getSize()];
		int i=0;
		for(Link e = firstLink;e!=null;e=e.next())
			result[i++] = e.value();
		return result;
	}
	
	public void delete(){
		firstLink = null;
	}
	
	public Link getLink(){
		return firstLink;
	}
}
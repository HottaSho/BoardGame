package game.linklist;

public class ListIterator {
	private Link currentLink;

	public ListIterator(Link firstLink) {
		currentLink = firstLink;
	}

	// true if we reached end of list, false otherwise
	public boolean atEnd() {
		return currentLink == null;
	}

	// move to next link
	public void next() {
		if (currentLink != null)
			currentLink = currentLink.next();
	}

	// return value of current link
	public Object current() {
		if (currentLink == null)
			return null;

		return currentLink.value();
	}
}
import java.util.*;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	private Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		
		super();
		comparator = compareableObject;
		
	}
	
	// add data to sorted linked list
	public void add(T data) {
		
		// if linked list is empty
		if(super.head == null) {
			super.addToFront(data);
		}
		
		// if data is smaller than head
		else if((comparator.compare(data, super.getFirst()) < 0)) {
			super.addToFront(data);
		}
		
		// if data is larger than tail
		else if(comparator.compare(data, super.getLast()) > 0) {
			super.addToEnd(data);
		}
		
		// if data is in between tail and head
		else {
			
			Node curr = head;
			
			while(curr != null && (comparator.compare(data, curr.data) < 0)) {		
				curr = curr.next;
			}
			
			Node toAdd = new Node(curr.next, data, curr);
			curr.next.prev = toAdd;
			curr.next = toAdd;
			
			size++;
			
		}
		
		
	}
	
	// removes specifed data
	public Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
	
	// instantiates iterator
	public BasicDoubleLinkedList.DoubleLinkedListIterator iterator() {
		return super.iterator();
	}
	
	// throw exception
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	// throw exception
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}

import java.util.*;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	
	protected Node head;
	protected Node tail;
	protected int size;
	
	
	public BasicDoubleLinkedList() {
		
		head = null;
		tail = null;
		size = 0;
		
	}
	
	// adds data to front of linked list
	public void addToFront(T data) {
		
		
		if(head == null)
			head = tail = new Node(null, data, null);
		
		else {
			head = new Node(head, data, null);
			head.next.prev = head;
		}
		
		size++;
		
	}
	
	// adds data to end of linked list
	public void addToEnd(T data) {
		
		if(tail == null) 
			head = tail = new Node(null, data, null);
		
		else {
			tail = new Node(null, data, tail);
			tail.prev.next = tail;
		}
		
		size++;
		
	}
	
	// returns head data
	public T getFirst() {
		return head.data;
	}
	
	// returns tail data
	public T getLast() {
		return tail.data;
	}
	
	// returns size
	public int getSize() {
		return size;
	}
	
	// removes specified data
	public Node remove(T target, Comparator<T> comparator) {
			
			Node curr = head;
			
			while(curr != null) {
				
				
				if(comparator.compare(target, curr.data) == 0) {
					
					// if data to be removed is the head
					if(curr == head) {
					
						head = head.next;
						head.prev = null;
						size--;
						
						return curr;
						
					}
					
					// if data to be removed is the tail
					else if(curr == tail) {
						
						tail = tail.prev;
						tail.next = null;
						size--;
						
						return curr;
						
					}
					
					// if data to removed is within head and tail
					else {
					
						curr.prev.next = curr.next;
						curr.next.prev = curr.prev;
						curr.next = null;
						curr.prev = null;
						size--;
					
						return curr;
					
					}
					
				}
				
				curr = curr.next;
				
			}
			
		
		return null;
		
	}
	
	// removes first element
	public T retrieveFirstElement() {
		
		if(size == 0)
			return null;
		
		T toRet = head.data;
		
		if(head == tail) {
			
			head = null;
			tail = null;
			
		}
		
		else {
		
			head = head.next;
			head.prev = null;
			
		}
		
		size--;
		
		return toRet;
	}
	
	// removes last element
	public T retrieveLastElement() {
		
		if(size == 0)
			return null;
		
		T toRet = tail.data;
		
		tail = tail.prev;
		tail.next = null;
		
		size--;
		
		return toRet;
		
	}
	
	// converts linked list to array
	public ArrayList<T> toArrayList() {
		
		Node curr = head;
		ArrayList<T> toRet = new ArrayList<T>();
		
		while(curr != null) {
			toRet.add(curr.data);
			curr = curr.next;
		}
		
		return toRet;
		
	}
	
	// instantiates iterator
	public DoubleLinkedListIterator iterator() {
		return new DoubleLinkedListIterator();
	}
	
	class DoubleLinkedListIterator implements ListIterator<T> {
		
		Node curr;
		Node prevCurr;
		
		public DoubleLinkedListIterator() {
			curr = head;
			prevCurr = null;
		}

		// checks if there is a next node
		public boolean hasNext() {
			return curr != null;
		}

		// moves pointer to next node, returns data
		public T next() throws NoSuchElementException {
			
			if(!hasNext())
				throw new NoSuchElementException();
			
			prevCurr = curr;
			curr = curr.next;
			return prevCurr.data;
		}

		// checks if there is a previous node
		public boolean hasPrevious() {
			return prevCurr != null;
		}

		// moves pointer to previous node, retuns data
		public T previous() throws NoSuchElementException {
			
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			curr = prevCurr;
			prevCurr = prevCurr.prev;
			return curr.data;
		}

		// Throw Exception
		public int nextIndex() throws UnsupportedOperationException {	
			throw new UnsupportedOperationException();
		}

		// Throw Exception
		public int previousIndex() throws UnsupportedOperationException {	
			throw new UnsupportedOperationException();
		}

		// Throw exception
		public void remove() throws UnsupportedOperationException {	
			throw new UnsupportedOperationException();
		}

		// Throw Exception
		public void set(T e) throws UnsupportedOperationException {	
			throw new UnsupportedOperationException();
		}

		// Throw Exception
		public void add(T e) throws UnsupportedOperationException {	
			throw new UnsupportedOperationException();
		}
		
		
	}
	
	// node inner class
	class Node {
		
		protected T data;
		protected Node next;
		protected Node prev;
		
		Node(Node n, T d, Node p) {
			
			next = n;
			data = d;
			prev = p;
			
		}
		
	}

}

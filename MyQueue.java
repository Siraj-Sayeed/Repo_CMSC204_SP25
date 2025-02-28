import java.util.*;

public class MyQueue<T> implements QueueInterface<T> {

	private T[] queue;
	private int cap, front, rear, size;
	
	public MyQueue(int c) {
		
		queue = (T[]) new Object[c];
		
		cap = c;
		front = 0;
		rear = -1;
		size = 0;
		
	}
	
	public MyQueue() {
		
		queue = (T[]) new Object[10];
		
		cap = 10;
		front = 0;
		rear = -1;
		size = 0;
		
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return cap == size;
	}

	// deletes first index of array and returns value, throws exception if queue is empty
	public T dequeue() throws QueueUnderflowException {
		// TODO Auto-generated method stub
		
		if(isEmpty())
			throw new QueueUnderflowException("Queue is empty");
		
		T toRet = queue[front];
		front = (front + 1) % cap;
		size--;
	
		
		return toRet;
	}

	// returns number of elements in queue
	public int size() {
		return size;
	}

	// adds elements at end of queue, thows exception if queue is full
	public boolean enqueue(T e) throws QueueOverflowException {
		
		if(isFull())
			throw new QueueOverflowException("Queue is full");
		
		rear = (front + size) % cap;
		queue[rear] = e;
		size++;
		
		return true;
	}
	
	public String toString() {
		
		String toRet = "";
		
		for(int i = front; i < rear; i++) {
			toRet += queue[i % cap];
		}
		
		toRet += queue[rear];
		return toRet;
		
	}

	// similar to toString but allows for special spacing character
	public String toString(String delimiter) {
		// TODO Auto-generated method stub
		
		String toRet = "";
		
		for(int i = front; i < rear; i++) {
			toRet += queue[i % cap] + delimiter;
		}
		
		toRet += queue[rear];
		return toRet;
	
	}

	// loads queue with values from array list, throws exception if arrlist has too many values
	public void fill(ArrayList<T> list) {
		// TODO Auto-generated method stub
		T data = null;
		
		for(int i = 0; i < list.size(); i++) {
			
			data = list.get(i);
			enqueue(data);
			
		}
		
	}

}

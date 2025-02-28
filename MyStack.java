import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
	
	private T[] stack;
	private int cap, topIndex;
	
	public MyStack(int c) {
		
		stack = (T[]) new Object[c];
		
		cap = c;
		topIndex = -1;
		
	}
	
	public MyStack() {
		
		stack = (T[]) new Object[10];
		cap = 10;
		topIndex = -1;
	
	}

	@Override
	public boolean isEmpty() {
		return topIndex == -1;
	}

	@Override
	public boolean isFull() {
		return topIndex+1 == cap;
	}

	// deletes and returns top of stack, throws exception if stack is empty
	public T pop() throws StackUnderflowException {
		
		if(topIndex == -1)
			throw new StackUnderflowException("Empty Stack");
		
		T toRet = stack[topIndex];
		stack[topIndex--] = null;
		return toRet;
		
	}

	// returns value of top of stack, throws exception if stack is empty
	public T top() throws StackUnderflowException {
		
		if(topIndex == -1)
			throw new StackUnderflowException("Empty Stack");
		
		return stack[topIndex];
	}

	// returns number of elements in stack
	public int size() {
		return topIndex+1;
	}

	// adds element to top of stack, throws exception if stack is full
	public boolean push(T e) throws StackOverflowException {
		
		if (topIndex == cap - 1)
			throw new StackOverflowException("Stack overflow");
		
		stack[++topIndex] = e;
		return true;
	
	}
	
	public String toString() {
		
		String toRet = "";
		
		for(int i = 0; i < topIndex; i++) {
			toRet += stack[i];
		}
		
		toRet += stack[topIndex];
		return toRet;
		
	}
	
	// similar to toString but allows for special spacing character
	public String toString(String delimiter) {
		
		String toRet = "";
		
		for(int i = 0; i < topIndex; i++) {
			toRet += stack[i] + delimiter;
		}
		
		toRet += stack[topIndex];
		return toRet;
	}

	// loads stack with values from array list, throws exception if arrlist has too many values
	public void fill(ArrayList<T> list) {
		
		T data = null;
		
		for(int i = 0; i < list.size(); i++) {
		
			data = list.get(i);
			push(data);
			
		}	
		
	}

}

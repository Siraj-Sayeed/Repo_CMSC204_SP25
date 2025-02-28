/*
 * Siraj Sayeed
 * CSMC204 CRN: 
 * Professor Monshi
 * This class allows for users to convert infix problems to its postfix variation and vice versa. Allows allows user to evaluate the solution to postfix problem
 */
public class Notation {
	
	// converts infix to post fix
	public static String convertInfixToPostfix(String convert) throws InvalidNotationFormatException {
	
		String toRet = "";
		
		MyQueue<Character> queue = new MyQueue<Character>(convert.length());
		MyStack<Character> stack = new MyStack<Character>(convert.length());	

		// loops through entire problem string
		for(int i = 0; i < convert.length(); i++) {

			// converts operand/operator to char for easier comparison
			char c = convert.charAt(i);

			// if char is digit enqueue
			if(Character.isDigit(c))
				queue.enqueue(c);

			// if char is '(' push onto stack
			else if(c == '(')
				stack.push(c);


			// if char is an operators, pop operators of equal or higher precedence until top of stack is no longer an operator
			else if(c == '+' || c == '-' || c == '*' || c == '/'){
				while(!stack.isEmpty() && (orderOfOperations(c) <= orderOfOperations(stack.top()))) {
					queue.enqueue(stack.pop());
				}
				// push char onto stack
				stack.push(c);
			}			
			
			// if char is ')' enqueue using values popped from stack
			else if(c == ')') {
				while(!stack.isEmpty() && stack.top() != '(')
					queue.enqueue(stack.pop());
				
				// if there is no other chars in stack, the problem has an invalid format
				if(stack.isEmpty())
					throw new InvalidNotationFormatException("Invalid Format");
				
				// pop '('
				stack.pop();
			}
		
		}
		
		// pop all remaining chars into queue
		while(!stack.isEmpty())
			queue.enqueue(stack.pop());
		
		// format solution and return 
		while(!queue.isEmpty())
			toRet += queue.dequeue() + "";


		return toRet;

	}
	
	// convert postfix to intfix
	public static String convertPostfixToInfix(String convert) throws InvalidNotationFormatException {
		
		String toRet = "";

		MyStack<String> stack = new MyStack<String>(convert.length());
		
		// loop through problem
		for(int i = 0; i < convert.length(); i++) {
			
			char c = convert.charAt(i);
			
			// if char is a digit, push onto stack
			if(Character.isDigit(c))
				stack.push(c + "");
			
			// if char is an operator
			else if(c == '+' || c == '-' || c == '*' || c == '/') {
				
				// if stack has less than 2 elements, problem notation is invalid
				if(stack.size() <=1)
					throw new InvalidNotationFormatException("Invalid Notation");
				
				// push first two elements onto stack with operator included
				String rightVal = stack.pop();
				String leftVal = stack.pop();
				
				stack.push("(" + leftVal + c + rightVal + ")");			
			}			
			
		}
		
		// if stack has one element, it is valid if not throw exception
		if(stack.size() == 1)
			return stack.pop();
		
		else
			throw new InvalidNotationFormatException("Invalid Notation");
		
	}
	
	// evaluate postfix notation and return solution
	public static double evaluatePostfixExpression(String pfe) {
		
		MyStack<Double> stack = new MyStack<Double>(pfe.length());
		
		// loop thru problem
		for(int i = 0; i < pfe.length(); i++) {
			
			char c = pfe.charAt(i);
			
			// if char is a digit push onto stack
			if(Character.isDigit(c))
				stack.push((double)c - '0');
			
			// if char is an operator pop first two values and assess the solution with operand, then push solution onto stack
			else {
				
				if(stack.size() <=1)
					throw new InvalidNotationFormatException("Invalid Notation");
				
				double rightVal = stack.pop();
				double leftVal = stack.pop();
				
				if(c == '+') 
					stack.push((double)(leftVal + rightVal));
				
				else if(c == '-') 
					stack.push((double)(leftVal - rightVal));
				
				else if(c == '*') 
					stack.push(leftVal * rightVal);
				
				else if(c == '/') 
					stack.push(leftVal / rightVal);
				
			}			
			
		}
		
		if(stack.size() == 1)
			return stack.pop();
		
		else
			throw new InvalidNotationFormatException("Invalid Notation");
		
		
	}
	
	
	// gets order of operations by returning a higher value for more important operators
	private static int orderOfOperations(char c) {
		
		if(c == '+' || c == '-')
			return 1;
		else if(c == '*' || c == '/')
			return 2;
		
		return 0;
		
	}


}

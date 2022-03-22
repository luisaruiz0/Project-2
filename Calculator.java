/**
 * Calculator class containing algorithms convertToPostfix and evaluatePostfix
 * and a main method to test
 * @author Shary Razo
 * @author Guillermo Roman
 * @author Luis Ruiz
 */

public class Calculator {
	
	/**
	 * Method to convert an infix expression to postfix
	 * @param infix expression (string) to convert
	 * @return Resulting postfix expression 
	 */
	 public static String convertToPostfix(String infix) {
	       infix += ")";
	       StackInterface<Character> operatorStack = new LinkedStack<Character>();
	       operatorStack.push('(');
	       String postfix = "";
	       //convert from infix to postfix
	       for(int i=0; i<infix.length(); i++) {
	           char ch = infix.charAt(i);
	           //check for variable
	           if(Character.isLetter(ch)) {
	               postfix = postfix + ch + " ";
	           }
	           //check for left parenthesis
	           else if(ch=='(') {
	               operatorStack.push(ch);
	           }
	           //check for righr parenthesis
	           else if(ch==')') {
	               while(operatorStack.peek()!='(') {
	                   postfix = postfix + operatorStack.peek() + " ";
	                   operatorStack.pop();
	               }
	               operatorStack.pop();
	           }
	           //operator
	           else {
	               int p1 = priorityOperator(ch);
	               int p2 = priorityOperator(operatorStack.peek());
	               while(p1<=p2) {
	                   postfix = postfix + operatorStack.peek() + " ";
	                   operatorStack.pop();
	                   p2 = priorityOperator(operatorStack.peek());
	               }
	               operatorStack.push(ch);
	           }
	       }
	       return postfix;
	 }
	 
	 private static int priorityOperator(char c) { // Method for order of operations
	       switch(c)
	       {
	           case '(': 
	        	   return 0;
	           case '/': 
	           case '*': 
	        	   return 2;
	           case '+': 
	           case '-': 
	        	   return 1;
	           default: 
	        	   return 999;
	       }
	 }
	
	/**
	 * Method to evaluate postfix expression
	 * @param postfix expression to evaluate, given as a string of characters
	 * @return The remaining int in the stack after postfix expression is evaluated
	 */
	public static int evaluatePostfix(String postfix) { // 
        char[] postfixChars = postfix.toCharArray();
        StackInterface<Integer> valueStack = new ResizeableArrayStack<Integer>(postfixChars.length);
        for(int i=0;i<postfixChars.length;i++){ // Check input string by each character
            if(Character.isDigit(postfixChars[i])){ // if number, convert to int and push to stack
                valueStack.push((int)(postfixChars[i]-'0'));
            } else {
            	int operandTwo = valueStack.pop();
            	int operandOne = valueStack.pop();
                int result;
                switch(postfixChars[i]){
                case '+':
                	result = operandOne+operandTwo;
                	valueStack.push(result);
                	break;
                case '*': 
                	result = operandOne*operandTwo;
                	valueStack.push(result);
                    break;
                case '-': 
                	result = operandOne-operandTwo;
                	valueStack.push(result);
                    break;
                case '/': 
                	result = operandOne/operandTwo;
                	valueStack.push(result);
                    break;
                }
            }
        }
        return valueStack.peek();
	}
	
	/** Main method to test convertToPostfix and evaluatePostfix method */
         public static void main(String args[])throws Exception {
            String infix = "a*b/(c-a)+d*e"; //use string from assignment
	    String postfix = convertToPostfix(infix);
	    System.out.println ("postfix exp: " + postfix);
		 
            String string1 = "23*42-/56*+";
	    System.out.println("Evalutating " + evaluatePostfix(string1));
	}
	
}

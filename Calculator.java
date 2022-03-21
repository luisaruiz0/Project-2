/**
 * Calculator class containing algorithms convertToPostfix and evaluatePostfix
 * and a main method to test
 * @author Shary Razo
 * @author Guillermo Roman
 * @author Luis Ruiz
 */

public class Calculator {
	
	public static String convertToPostfix(String infix) throws Exception {
	   StackInterface<Character> operatorStack = new LinkedStack<Character>();//empty stack  
	   String postfix = ""; //empty string
	   infix = infix + ")";
	   operatorStack.push('(');	      
	      for(int i=0; i<infix.length(); i++){ //converting from infix to post	    	  
	         char exp = infix.charAt(i);	          
	            if(Character.isLetter(exp)){
	               postfix = postfix + exp + " ";
	            }
	            else if(exp=='('){//checks parentheses
	            	operatorStack.push(exp);
	            }
	            else if(exp==')'){
	               while(operatorStack.peek()!='('){
	                  postfix = postfix + operatorStack.peek() + " ";
	                  operatorStack.pop();
	               }
	               operatorStack.pop();
	            }
	       }	      
	       if(!operatorStack.isEmpty())
	           System.out.println("Not valid please enter correctly");	  
	       return postfix;
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

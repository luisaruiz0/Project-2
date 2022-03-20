public class Calc
{
    public static int evaluatePostfix(String postfix) {
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
	
	public static void main(String args[]) {
		String string1 = "23*42-/56*+";
		System.out.println(evaluatePostfix(string1));
	}
}

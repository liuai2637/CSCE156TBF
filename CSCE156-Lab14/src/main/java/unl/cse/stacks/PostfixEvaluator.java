package unl.cse.stacks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PostfixEvaluator {

	private static final Set<String> OPERATORS = new HashSet<String>(Arrays.asList("+", "-", "*", "/"));
	private final Stack<String> stack;
	
    /**
     * Constructor
     */
    public PostfixEvaluator() {
        this.stack = new Stack<String>();
    }

    private boolean isOperator(String s){
    	return OPERATORS.contains(s);
    }
    
    private String evaluate(String a, String b, String op) {
    	Double d1 = Double.parseDouble(a);
    	Double d2 = Double.parseDouble(b);
    	if(op.equals("+"))
    		return new Double(d1+d2).toString();
    	else if(op.equals("-"))
    		return new Double(d1-d2).toString();
    	else if(op.equals("*"))
    		return new Double(d1*d2).toString();
    	else if(op.equals("/"))
    		return new Double(d1/d2).toString();
    	else
    		throw new IllegalStateException("Unrecognized operator: "+op);
    }

    /**
     * Evaluates the given arithmetic expression in postfix format
     * change this method
     * @param expression
     * @return
     */
    double evaluateExpression(String expression) {

    	String values[] = expression.split("\\s+");
    	for(String v : values) {
    		/*
    		 * TODO: implement this method
    		 * Hint: 
    		 *   If v is an operator:
    		 *      pop b, a off the stack and call this.evaluate
    		 *      push the result back onto the top of the stack
    		 *   Else
    		 *      push the operand (number) onto the stack
    		 */
    		if(isOperator(v)) {
    			String a = stack.pop();
    			String b = stack.pop();
    			stack.push(evaluate(b, a, v));
    		} else {
    			stack.push(v);
    		}
    		
    		
    	}
    	//At this point, the final result should be on the top of the stack,
    	//we pop it off, parse it and return the result
    	Double d = Double.parseDouble(this.stack.pop());
        return d;
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        PostfixEvaluator postfixEvaluator = new PostfixEvaluator();
        System.out.print("Please enter the Arithmatic Expression (Postfix form) to evaluate: ");
        Scanner myScanner = new Scanner(System.in);
        String expression = myScanner.nextLine();
        System.out.println(expression);
        System.out.println("Result: " + postfixEvaluator.evaluateExpression(expression));
    }
}

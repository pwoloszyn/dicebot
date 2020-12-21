package bananaland.dicebot.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import bananaland.dicebot.parser.util.Token;
import bananaland.dicebot.parser.util.TokenType;

public class Parser {

	private Stack<Token> stack;
	private LinkedList<Token> token_list;
	private Token token;
	private int result;
	private int current_highest_op;

	public Parser() {
		stack = new Stack<Token>();
		current_highest_op = 0;
		result = 0;
	}

	public void parse(LinkedList<Token> token_list) {
		
		System.out.println("PARSER CALLED, token list size: " + token_list.size());
		
		if(token_list.size() < 2) {
			result = Integer.parseInt(token_list.pop().getToken());
			return;
		}
		
		this.token_list = (LinkedList<Token>) token_list.clone();
		while (!this.token_list.isEmpty()) {		
			token = this.token_list.pop();
			
			System.out.println("Parser log: "+token.getToken());
			
			// If the token is a open bracket
			if (token.getToken_id() == TokenType.LEFTBRACK.getValue()) {
				LinkedList<Token> inner_token_list = new LinkedList<Token>();		
				int left_bracket = 1;
				int right_bracket = 0;	
				// Recursively parse the contents of the brackets
				while(true) {
					token = this.token_list.pop();
					if(token.getToken_id() == TokenType.LEFTBRACK.getValue())
						left_bracket++;
					else if(token.getToken_id() == TokenType.RIGHTBRACK.getValue())
						right_bracket++;
					if(left_bracket == right_bracket)
						break;
					inner_token_list.add(token);
				}
				
				Parser inner_parser = new Parser();
				inner_parser.parse(inner_token_list);
				
				stack.push(new Token(TokenType.CONSTANT.getValue(), inner_parser.result+""));
				
			// Perform operations in order of operation
			} else if (token.getToken_id() < current_highest_op) {
				stack.push(new Token(TokenType.CONSTANT.getValue(), runEval()+""));
				stack.push(token);
				current_highest_op = 0;
			// If the token is a constant
			} else {
				stack.push(token);
				if (token.getToken_id() <= TokenType.EXPONENT.getValue() && token.getToken_id() > current_highest_op)
					current_highest_op = token.getToken_id();
			}
		}
		// Perform any operations remaining on the stack
		result = runEval();

	}

	// Goes through the stack applying the evaluation method on the contents
	private int runEval() {
		int eval = eval();
		while (!stack.isEmpty()) {
			stack.push(new Token(TokenType.CONSTANT.getValue(), eval + ""));
			eval = eval();
		}
		return eval;
	}

	// Performs arithmetic operations on the 3 top most stack elements, assuming there are 3, otherwise just do 1
	private int eval() {
		
		if(stack.size() == 1)
			return Integer.parseInt(stack.pop().getToken());
		
		int rhs = Integer.parseInt(stack.pop().getToken());
		String op = stack.pop().getToken();
		int lhs = Integer.parseInt(stack.pop().getToken());

		if (op.equals("+")) {
			return lhs + rhs;
		} else if (op.equals("-")) {
			return lhs - rhs;
		} else if (op.equals("/")) {
			return lhs / rhs;
		} else if (op.equals("*")) {
			return lhs * rhs;
		} else {
			return (int) Math.pow(lhs, rhs);
		}
	}

	public int getResult() {
		return result;
	}

}

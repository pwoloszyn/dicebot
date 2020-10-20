package bananaland.dicebot.parser;

import java.text.ParseException;
import java.util.LinkedList;

import bananaland.dicebot.parser.expressions.*;
import bananaland.dicebot.parser.expressions.dice.*;
import bananaland.dicebot.tokenizer.Token;

public class Parser {
	LinkedList<Token> tokens;
	Token lookahead;
	
	public Expression parse(LinkedList<Token> tokens) {
		this.tokens = (LinkedList<Token>) tokens.clone();
		lookahead = this.tokens.getFirst();
		
		Expression expr = expression();
		
		if(lookahead.token != Token.EPSILON)
			throw new RuntimeException("Unexpected symbol "+lookahead.sequence+" is found");
		
		return expr;
	}
	
	private void nextToken() {
		tokens.pop();
		// At the end of the input we return an epsilon token
		if(tokens.isEmpty())
			lookahead = new Token(Token.EPSILON, "");
		else
			lookahead = tokens.getFirst();
	}
	
	private Expression expression() {
		// Expression -> signed_term sum_op
		Expression expr = signedTerm();
		return sumOp(expr);
	}
	
	private Expression sumOp(Expression expr) {
		if(lookahead.token == Token.PLUSMINUS) {
			// sum_op -> PLUSMINUS
			AdditionExpression sum;
			if(expr.getType() == Expression.ADDITION)
				sum = (AdditionExpression) expr;
			else
				sum = new AdditionExpression(expr, true);
			boolean positive = lookahead.sequence.equals("+");
			nextToken();
			Expression t = term();
			sum.add(t, positive);
			return sumOp(sum);
		} else {
			//sum_op -> EPSILON
			return expr;
		}
	}
	
	private Expression signedTerm() {
		if(lookahead.token == Token.PLUSMINUS) {
			//signed_term -> PLUSMINUS term
			boolean positive = lookahead.sequence.equals("+");
			nextToken();
			Expression t = term();
			if(positive)
				return t;
			else
				return new AdditionExpression(t, false);
		} else {
			// signed_term -> term
			return term();
		}
	}
	
	private Expression term() {
		// term -> factor term_op
		Expression f = factor();
		return termOp(f);
	}
	
	private Expression termOp(Expression expression) {
		if(lookahead.token == Token.MULTDIV) {
			// term_op -> MULTIDIV factor term_op
			MultiplicationExpression prod;
			
			if(expression.getType() == Expression.MULTIPLICATION)
				prod = (MultiplicationExpression) expression;
			else
				prod = new MultiplicationExpression(expression, true);
			
			boolean positive = lookahead.sequence.equals("*");
			nextToken();
			Expression f = signedFactor();
			prod.add(f, positive);
			return termOp(prod);
		} else {
			// term_op -> EPSILON
			return expression;
		}
	}
	
	private Expression signedFactor() {
		if(lookahead.token == Token.PLUSMINUS) {
			// signed_factor -> PLUSMINUS factor
			boolean positive = lookahead.sequence.equals("+");
			nextToken();
			Expression t = factor();
			if(positive)
				return t;
			else
				return new AdditionExpression(t, false);
		} else {
			// signed_factor -> factor
			return factor();
		}
	}
	
	private Expression factorOp(Expression expression) {
		// factor_op -> RAISED factor
		if(lookahead.token == Token.RAISED) {
			nextToken();
			Expression exponent = signedFactor();
			return new ExponentExpression(expression, exponent);
		}
		// factor_op -> EPSILON
		return expression;
	}
	
	private Expression factor() {
		// factor -> argument factor_op
		Expression a = argument();
		return factorOp(a);
	}
	
	private Expression argument() {
		if(lookahead.token == Token.DICE_ADV) {
			// Evaluates expressions of the form: [0-9]+d[0-9]+dl[1-9]+
			
			// PLACEHOLDER
			Expression expr = new ConstantExpression(1);
			
			System.out.println("LOOK HERE ADV: " + lookahead.sequence);
			//Expression exprz = new DiceRollExpression(lookahead.sequence);
			
			nextToken();
			return expr;
			
			
		} else if(lookahead.token == Token.DICE_DISADV) {
			// Evaluates expressions of the form: [0-9]+d[0-9]+dh[1-9]+
			
			// PLACEHOLDER
			Expression expr = new ConstantExpression(1);
			
			System.out.println("LOOK HERE DISADV: " + lookahead.sequence);
			//Expression exprz = new DiceRollExpression(lookahead.sequence);
			
			nextToken();
			return expr;
			
			
		} else if(lookahead.token == Token.DICE) {
			// Evaluates expressions of the form: [0-9]+d[0-9]+
			
			// PLACEHOLDER
			Expression expr = new ConstantExpression(1);
			
			System.out.println("LOOK HERE NORMAL: " + lookahead.sequence);
			//Expression exprz = new DiceRollExpression(lookahead.sequence);
			
			nextToken();
			return expr;
			
			
		} else if(lookahead.token == Token.OPEN_BRACKET) {
			// argument -> OPEN_BRACKET sum CLOSE_BRACKET
			nextToken();
			Expression expr = expression();
			
			if(lookahead.token != Token.CLOSE_BRACKET)
				throw new RuntimeException("Closing brackets expected "+lookahead.sequence+" is found instead");
					
			nextToken();
			return expr;
		} else {
			// argument -> value
			return value();
		}
	}
	
	private Expression value() {
		if(lookahead.token == Token.NUMBER) {
			// argument -> NUMBER
			Expression expr = new ConstantExpression(lookahead.sequence);
			nextToken();
			return expr;
		} else {
			if(lookahead.token == Token.EPSILON)
				throw new RuntimeException("Unexpected end of input");
			throw new RuntimeException("Unexpected symbol "+lookahead.sequence+" is found");
		}
	}
	
}
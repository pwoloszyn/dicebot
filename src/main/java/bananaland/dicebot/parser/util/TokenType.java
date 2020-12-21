package bananaland.dicebot.parser.util;

/** This Enum defines the token identification codes used throughout the parser **/
public enum TokenType {
	// The order of these operators matters, ADDSUB having the lowest priority while EXPNT having the highest
	ADDSUB (1),	// ADDITION / SUBTRACTION
	MULDIV (2), // MULTIPLICATION / DIVISION
	EXPONENT (3), // EXPONENTIATION

	LEFTBRACK (5), // LEFT BRACKET
	RIGHTBRACK (6), // RIGHT BRACKET
	CONSTANT (7), // CONSTANT
	ERROR (34), // ERROR PATTERN, used to recognize bad input syntax
	
	DICEDIS (21),
	DICEADV (22),
	DICEROLL (23);
	
	private final int token_code;
	
	TokenType(int token_code) {
		this.token_code = token_code;
	}
	
	public int getValue() {
		return token_code;
	}
}

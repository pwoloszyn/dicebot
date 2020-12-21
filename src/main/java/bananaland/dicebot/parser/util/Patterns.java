package bananaland.dicebot.parser.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** This class sets up and holds the recognized token definitions **/
public class Patterns {
	private final List<Token> pattern_list;

	public Patterns() {
		pattern_list = new ArrayList<>();
		
		// The order of adding these patterns is important, the dice patterns must be added before anything else
		
		// Comment pattern
		
		
		// Dice patterns
		pattern_list.add(new Token(TokenType.DICEDIS.getValue(), "[0-9]+d[0-9]+dl[1-9]+[0-9]*"));
		pattern_list.add(new Token(TokenType.DICEADV.getValue(), "[0-9]+d[0-9]+dh[1-9]+[0-9]*"));
		pattern_list.add(new Token(TokenType.DICEROLL.getValue(), "[0-9]+d[0-9]+"));
		
		// Operators
		pattern_list.add(new Token(TokenType.ADDSUB.getValue(), "[+-]")); // ADDITION / SUBTRACTION
		pattern_list.add(new Token(TokenType.MULDIV.getValue(), "[*/]")); // MULTIPLICATION / DIVISION
		pattern_list.add(new Token(TokenType.EXPONENT.getValue(), "\\^")); // EXPONENTIATION
		
		// Brackets
		pattern_list.add(new Token(TokenType.LEFTBRACK.getValue(), "\\(")); // LEFT BRACKET
		pattern_list.add(new Token(TokenType.RIGHTBRACK.getValue(), "\\)")); // RIGHT BRACKET
		
		// Constants
		pattern_list.add(new Token(TokenType.CONSTANT.getValue(), "[0-9]+")); // CONSTANT
		
		// Error Pattern
		pattern_list.add(new Token(TokenType.ERROR.getValue(), "[a-z]|[A-Z]")); // ERROR PATTERN
	}

	public List<Token> getPatternList() {
		return pattern_list;
	}
}

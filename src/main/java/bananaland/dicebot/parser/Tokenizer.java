package bananaland.dicebot.parser;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import bananaland.dicebot.parser.util.Patterns;
import bananaland.dicebot.parser.util.Token;
import bananaland.dicebot.parser.util.TokenType;

public class Tokenizer {
	private LinkedList<Token> tokens;

	public Tokenizer() {
		tokens = new LinkedList<>();
	}

	public LinkedList<Token> tokenize(Patterns patterns, String input) {		
		tokens.clear();
		
		Matcher m = Pattern.compile(
				patterns.getPatternList().stream().map(t -> t.getToken())
				.collect(Collectors.joining("|")).concat("")
				).matcher(input);
		
		tokenizer_main_loop:
		while (m.find()) {
			String token = m.group();
			for(Token pattern_token : patterns.getPatternList()) {
				if(token.matches(pattern_token.getToken())) {
					int pattern_code = pattern_token.getToken_id();
					if(pattern_code == TokenType.ERROR.getValue())
						throw new RuntimeException("Unexpected character is input");
					tokens.add(new Token(pattern_code, token));
					continue tokenizer_main_loop;
				}
			}
		}
		return tokens;
	}
}

package bananaland.dicebot.tokenizer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tokenizer {

	private LinkedList<TokenInfo> tokenInfos;
	private LinkedList<Token> tokens;

	public Tokenizer() {
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
		
		add("[+-]", 1);
		add("[*/]", 2);
		add("\\^", 3);
		add("\\(", 5);
		add("\\)", 6);
		
		// Dice Terms
		add("[0-9]+d[0-9]+dl1", 21);
		add("[0-9]+d[0-9]+dh1", 22);
		add("[0-9]+d[0-9]+", 23);
		
		add("[0-9]+", 7);
		
	}

	public void add(String regex, int token) {
		tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
	}

	public boolean tokenize(String str) {
		String s = new String(str);
		tokens.clear();

		while (!s.equals("")) {
			boolean match = false;
			for (TokenInfo info : tokenInfos) {
				Matcher m = info.regex.matcher(s);
				if (m.find()) {
					match = true;
					String tok = m.group().trim();
					tokens.add(new Token(info.token, tok));
					s = m.replaceFirst("");
					break;
				}
			}
			if (!match)
				return false;
		}
		return true;
	}
	
	public LinkedList<Token> getTokens() {
		return tokens;
	}

	private class TokenInfo {
		public final Pattern regex;
		public final int token;

		public TokenInfo(Pattern regex, int token) {
			super();
			this.regex = regex;
			this.token = token;
		}
	}
}
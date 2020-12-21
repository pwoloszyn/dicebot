package bananaland.dicebot.parser.util;
public class Token {
	
	private int token_id;
	private String token;
	
	public Token(int token_id, String token) {
		this.token_id = token_id;
		this.token = token;
	}

	public int getToken_id() {
		return token_id;
	}

	public String getToken() {
		return token;
	}
}

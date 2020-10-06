package bananaland.dicebot.tokenizer;

public class Token {
	
	public static final int EPSILON = 0;
	public static final int PLUSMINUS = 1;
	public static final int MULTDIV = 2;
	public static final int RAISED = 3;
	public static final int OPEN_BRACKET = 5;
	public static final int CLOSE_BRACKET = 6;
	public static final int NUMBER = 7;
	
	//Dice Terms
	public static final int DICE_ADV = 21;
	public static final int DICE_DISADV = 22;
	public static final int DICE = 23;

	
	
	public final int token;
	public final String sequence;
	
	public Token(int token, String sequence) {
		super();
		this.token = token;
		this.sequence = sequence;
		
	}
}
package bananaland.dicebot.parser.expressions;

public interface Expression {
	public static final int CONSTANT = 2;
	public static final int ADDITION = 3;
	public static final int MULTIPLICATION = 4;
	public static final int EXPONENTIAL  = 5;
	
	//Dice Terms
	public static final int DICE_ADV = 21;
	public static final int DICE_DISADV = 22;
	public static final int DICE = 23;
	
	public int getType();
	public double getValue();
}

package bananaland.dicebot.parser.expressions.dice;

import java.util.Random;

import bananaland.dicebot.parser.expressions.Expression;
import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollExpression extends SequenceExpression {

	private String dice_sequence;
	private String dice_roll_output;
	private int sum;

	public DiceRollExpression(String dice_sequence) {
		super();
		this.dice_sequence = dice_sequence;
		dice_roll_output = "";
		sum = eval();
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Expression.DICE;
	}

	@Override
	public double getValue() {
		return sum;
	}
	
	public int eval() {
		Random rng = new Random();

		String[] dice_values = dice_sequence.split("d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}
		
		for (int i = 0; i < dice_count; i++) {
			dice_roll_output +="["+dice_rolls[i]+"]";
			dice_sum += dice_rolls[i];
		}
		
		
		return dice_sum;
	}
	
	public String getDiceRollOutput() {
		return dice_roll_output;
	}

}

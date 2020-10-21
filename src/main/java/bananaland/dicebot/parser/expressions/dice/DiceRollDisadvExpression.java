package bananaland.dicebot.parser.expressions.dice;

import java.util.Arrays;
import java.util.Random;

import bananaland.dicebot.parser.expressions.Expression;
import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollDisadvExpression extends SequenceExpression {

	private String dice_sequence;
	private String dice_roll_output;
	private int sum;

	public DiceRollDisadvExpression(String dice_sequence) {
		super();
		this.dice_sequence = dice_sequence;
		dice_roll_output = "";
		sum = eval();
	}

	@Override
	public int getType() {
		return Expression.DICE_DISADV;
	}

	@Override
	public double getValue() {
		return sum;
	}
	
	private int eval() {
		Random rng = new Random();

		String[] dice_values = dice_sequence.split("dh|d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);
		int drop_count = Integer.parseInt(dice_values[2]);

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}
		
		Arrays.sort(dice_rolls);

		dice_roll_output += "[";
		for (int i = 0; i < dice_count - drop_count; i++) {
			dice_roll_output +=  "["+dice_rolls[i]+"]";
			dice_sum += dice_rolls[i];
		}
		
		dice_roll_output += " dropped: ";
		
		for (int i = dice_count - drop_count; i < dice_count; i++) {
			dice_roll_output += "["+dice_rolls[i]+"]";
		}
		dice_roll_output += "]";
		

		return dice_sum;
	}
	
	public String getDiceRollOutput() {
		return dice_roll_output;
	}

}

package bananaland.dicebot.parser.expressions.dice;

import java.util.Arrays;
import java.util.Random;

import bananaland.dicebot.parser.expressions.Expression;
import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollAdvExpression extends SequenceExpression {

	private String dice_sequence;

	public DiceRollAdvExpression(String dice_sequence) {
		super();
		this.dice_sequence = dice_sequence;
	}

	@Override
	public int getType() {
		return Expression.DICE_ADV;
	}

	@Override
	public double getValue() {

		// Naive implementation code:

		Random rng = new Random();

		String[] dice_values = dice_sequence.split("dl|d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);
		int drop_count = Integer.parseInt(dice_values[2]);

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}

		Arrays.sort(dice_rolls);

		for (int i = 0 + drop_count; i < dice_count; i++) {
			System.out.print("["+dice_rolls[i]+"]");
			dice_sum += dice_rolls[i];
		}

		System.out.print(" dropped: ");

		for (int i = 0; i < drop_count; i++) {
			System.out.print("["+dice_rolls[i]+"]");
		}
		
		System.out.println();

		return dice_sum;
	}

}

package bananaland.dicebot.parser;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import bananaland.dicebot.parser.util.Token;
import bananaland.dicebot.parser.util.TokenType;

public class DiceRollEvaluator {

	private LinkedList<Token> output_list;
	private String output_sequence;
	private Random rng;

	public DiceRollEvaluator() {
		rng = new Random();
		rng.setSeed(System.currentTimeMillis());
	}

	public LinkedList<Token> eval(LinkedList<Token> token_list) {
		output_list = new LinkedList<Token>();
		output_sequence = "";

		while (!token_list.isEmpty()) {
			Token token = token_list.pop();

			if (token.getToken_id() == TokenType.DICEDIS.getValue()) {
				output_list.add(new Token(TokenType.CONSTANT.getValue(), diceRollAdvEval(token.getToken()) + ""));
			} else if (token.getToken_id() == TokenType.DICEADV.getValue()) {
				output_list.add(new Token(TokenType.CONSTANT.getValue(), diceRollDisadvEval(token.getToken()) + ""));
			} else if (token.getToken_id() == TokenType.DICEROLL.getValue()) {
				output_list.add(new Token(TokenType.CONSTANT.getValue(), diceRollEval(token.getToken()) + ""));
			} else {
				output_sequence += token.getToken();
				output_list.add(token);
			}
			output_sequence += " ";
		}

		return output_list;
	}

	public String getOutput() {
		return output_sequence;
	}

	private int diceRollAdvEval(String input) {
		
		String[] dice_values = input.split("dl|d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);
		int drop_count = Integer.parseInt(dice_values[2]);
		
		if(drop_count >= dice_count)
			throw new RuntimeException("Invalid input detected: Can only drop less dice than are rolled");

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}

		Arrays.sort(dice_rolls);

		for (int i = 0 + drop_count; i < dice_count; i++) {
			output_sequence += "[" + dice_rolls[i] + "]";
			dice_sum += dice_rolls[i];
		}

		for (int i = 0; i < drop_count; i++) {
			output_sequence += "~~[" + dice_rolls[i] + "]~~";
		}

		return dice_sum;
	}

	private int diceRollDisadvEval(String input) {
		
		String[] dice_values = input.split("dh|d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);
		int drop_count = Integer.parseInt(dice_values[2]);
		
		if(drop_count >= dice_count)
			throw new RuntimeException("Invalid input detected: Can only drop less dice than are rolled");

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}

		Arrays.sort(dice_rolls);

		for (int i = 0; i < dice_count - drop_count; i++) {
			output_sequence += "[" + dice_rolls[i] + "]";
			dice_sum += dice_rolls[i];
		}

		for (int i = dice_count - drop_count; i < dice_count; i++) {
			output_sequence += "~~[" + dice_rolls[i] + "]~~";
		}

		return dice_sum;
	}

	private int diceRollEval(String input) {
		
		String[] dice_values = input.split("d");

		int dice_count = Integer.parseInt(dice_values[0]);
		int dice_type = Integer.parseInt(dice_values[1]);

		int dice_rolls[] = new int[dice_count];
		int dice_sum = 0;

		for (int i = 0; i < dice_count; i++) {
			dice_rolls[i] = rng.nextInt(dice_type) + 1;
		}

		for (int i = 0; i < dice_count; i++) {
			output_sequence += "[" + dice_rolls[i] + "]";
			dice_sum += dice_rolls[i];
		}

		return dice_sum;
	}

}

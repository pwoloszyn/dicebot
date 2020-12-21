package bananaland.dicebot.parser;

import java.util.LinkedList;
import java.util.List;

import bananaland.dicebot.parser.util.Patterns;
import bananaland.dicebot.parser.util.Token;

public class DiceInterpreter {

	private static DiceInterpreter diceInterpreter = null;

	private DiceInterpreter() {}

	public static DiceInterpreter getInstance() {
		if (diceInterpreter == null)
			diceInterpreter = new DiceInterpreter();
		return diceInterpreter;
	}

	public static String interpret(String input) throws RuntimeException {
		InputFormatter inputFormatter = new InputFormatter();
		Tokenizer tokenizer = new Tokenizer();
		DiceRollEvaluator diceRollEvalutatior = new DiceRollEvaluator();
		Parser parser = new Parser();

		LinkedList<Token> token_list;
		String output = "";
		
		List<String> suboberations_list = inputFormatter.format(input);

		for (int i = 0; i < suboberations_list.size(); i++)
			System.out.println(suboberations_list.get(i).trim());

		for (int i = 0; i < suboberations_list.size(); i++) {
			token_list = tokenizer.tokenize(new Patterns(), suboberations_list.get(i).trim());
			parser.parse(diceRollEvalutatior.eval(token_list));
			output += diceRollEvalutatior.getOutput() + "= **" + parser.getResult() + "**" + "\n";
			// Logging
			System.out.println("Result: " + parser.getResult());
		}

		return output;
	}
}

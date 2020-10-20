package bananaland.dicebot.parser.expressions.dice;

import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollExpression extends SequenceExpression{
	
	private String dice_sequence;
	
	public DiceRollExpression(String dice_sequence) {
		super();
		this.dice_sequence = dice_sequence;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValue() {
		// TODO This has to return the dice roll value
		return 0;
	}

}

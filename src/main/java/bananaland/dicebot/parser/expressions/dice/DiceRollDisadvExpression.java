package bananaland.dicebot.parser.expressions.dice;

import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollDisadvExpression extends SequenceExpression{
	
	private String dice_sequence;

	public DiceRollDisadvExpression(String dice_sequence) {
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
		// TODO Auto-generated method stub
		return 0;
	}

}

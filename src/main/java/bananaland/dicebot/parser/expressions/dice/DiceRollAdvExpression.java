package bananaland.dicebot.parser.expressions.dice;

import bananaland.dicebot.parser.expressions.SequenceExpression;

public class DiceRollAdvExpression extends SequenceExpression{
	
	private String dice_sequence;


	public DiceRollAdvExpression(String dice_sequence) {
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

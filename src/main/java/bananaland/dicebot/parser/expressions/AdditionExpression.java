package bananaland.dicebot.parser.expressions;

public class AdditionExpression extends SequenceExpression {
	
	public AdditionExpression() {
		super();
	}
	
	public AdditionExpression(Expression a, boolean positive) {
		super(a, positive);
	}

	@Override
	public int getType() {
		return Expression.ADDITION;
	}

	@Override
	public double getValue() {
		double sum = 0.0;
		for(Term t : terms) {
			if(t.positive)
				sum += t.expression.getValue();
			else
				sum -= t.expression.getValue();
		}
		return sum;
	}
}

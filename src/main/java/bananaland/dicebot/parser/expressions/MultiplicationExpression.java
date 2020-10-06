package bananaland.dicebot.parser.expressions;


public class MultiplicationExpression extends SequenceExpression {
	
	public MultiplicationExpression() {
		super();
	}
	
	public MultiplicationExpression(Expression a, boolean positive) {
		super(a, positive);
	}

	@Override
	public int getType() {
		return Expression.MULTIPLICATION;
	}

	@Override
	public double getValue() {
		double prod = 1.0;
		for(Term t : terms) {
			if(t.positive)
				prod *= t.expression.getValue();
			else
				prod /= t.expression.getValue();
		}
		return prod;
	}

}

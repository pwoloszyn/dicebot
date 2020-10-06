package bananaland.dicebot.parser.expressions;


public class ExponentExpression implements Expression {
	
	private Expression base;
	private Expression exponent;
	
	public ExponentExpression(Expression base, Expression exponent) {
		this.base = base;
		this.exponent = exponent;
	}

	@Override
	public int getType() {
		return Expression.EXPONENTIAL;
	}

	@Override
	public double getValue() {
		return Math.pow(base.getValue(), exponent.getValue());
	}

}
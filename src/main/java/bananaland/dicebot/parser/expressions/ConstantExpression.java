package bananaland.dicebot.parser.expressions;

public class ConstantExpression implements Expression {
	
	private double value;
	
	public ConstantExpression(double value) {
		this.value = value;
	}
	
	public ConstantExpression(String value) {
		this.value = Double.valueOf(value);
	}

	@Override
	public double getValue() {
		return value;
	}
	
	@Override
	public int getType() {
		return Expression.CONSTANT;
	}

	

}
package bananaland.dicebot.dice;

/**
 * Handles dice terms of the form [0-9]*d[0-9]+
 */
public class PluralDiceTerm extends DiceTerm {
    // TODO: Implement this class.
	
	private String dice_term;
	private double[] evaluated_expression;
	
	public PluralDiceTerm(String dice_term) {
		this.dice_term = dice_term;
	}

    public double[] evaluate() {
    	
        return new double[0];
    }

    public double evaluateTotal() {
        return 0;
    }

    public String toString() {
        return null;
    }
}

package bananaland.dicebot.dice;

/**
 * Handles dice terms of the form < valid java.lang.Double >
 */
public class ConstantDiceTerm extends DiceTerm {
    private double c;

    public ConstantDiceTerm(String text) {
        this.c = Double.parseDouble(text);
    }

    /**
     * @param text text representation of the dice term
     * @return true if the text corresponds to a ConstantDiceTerm
     */
    public static boolean validate(String text) {
        // TODO: Solve this problem without exception jank
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public double[] evaluate() {
        return new double[] {c};
    }

    public double evaluateTotal() {
        return c;
    }

    public String toString() {
        return Double.toString(c);
    }
}

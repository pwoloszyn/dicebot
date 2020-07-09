package bananaland.dicebot.dice;

/**
 * Handles dice terms of the form < valid java.lang.Double >
 */
public class ConstantDiceTerm extends DiceTerm {
    // TODO: Implement this class.

    /**
     * @param text text representation of the dice term
     * @return true if the text corresponds to a ConstantDiceTerm
     */
    public static boolean validate(String text) {
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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

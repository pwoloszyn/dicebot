package bananaland.dicebot.dice;

public abstract class DiceTerm {
    /**
     * First time this function is called, values are picked for each value of the dice term and they are returned as
     * an array. Subsequent calls simply return with those values.
     * @return a double array of each individual die in the dice term.
     */
    public abstract double[] evaluate();

    /**
     * Same effect as evaluate() but returns the aggregate value of the dice term.
     * @return a double aggregate of the dice term.
     */
    public abstract double evaluateTotal();

    /**
     * DiceTerm objects must implement toString(). This method
     * @return console-ready output of the evaluated DiceTerm.
     */
    public abstract String toString();
}

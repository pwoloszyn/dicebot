package bananaland.dicebot.parser.expressions;

import java.util.LinkedList;

public abstract class SequenceExpression implements Expression {
	protected LinkedList<Term> terms;

	public SequenceExpression() {
		this.terms = new LinkedList<Term>();
	}

	public SequenceExpression(Expression a, boolean positive) {
		this.terms = new LinkedList<Term>();
		this.terms.add(new Term(positive, a));
	}

	public void add(Expression a, boolean positive) {
		this.terms.add(new Term(positive, a));
	}

	public class Term {
		public boolean positive;
		public Expression expression;

		public Term(boolean positive, Expression expression) {
			super();
			this.positive = positive;
			this.expression = expression;
		}
	}
}

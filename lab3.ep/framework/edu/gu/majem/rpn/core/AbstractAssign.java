package edu.gu.majem.rpn.core;

import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.ITokens;

/**
 * Base class for assignment operators. Plug-in should implement
 * abstract method getCopy. Method should create a deep copy of
 * the argument.
 * @author hajo
 */
public abstract class AbstractAssign<T> implements IBinOp<T, T, T> {

	@Override
	public T execute(T left, T right) {
		@SuppressWarnings("unchecked")
		Interpreter<T> i = (Interpreter<T>) Interpreter.getInstance();
		ITokens tokens = i.getTokens();
		
		String leftName = i.getKey(left);
		String rightName = i.getKey(right);

		// Must be a left value
		// I.e. in memory (not on stack) and a variable
		if (leftName == null || !tokens.isVariable(leftName))
			i.exception("Bad left value for input " + leftName);
		else if (rightName != null && !tokens.isVariable(rightName))
			i.exception("Bad right value for input " + rightName);

		left = getCopy(right);
		// Will overwrite old left value
		i.put(leftName, left);
		
		return left;
	}
	
	public abstract T getCopy(T orig);
}

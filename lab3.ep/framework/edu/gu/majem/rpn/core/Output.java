package edu.gu.majem.rpn.core;

import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.IInOut;
import edu.gu.majem.rpn.ITokens;
/**
 * Class handling the output operator. Plug-in must 
 * supply IO.
 * @author hajo
 */
public class Output<T> implements IBinOp<T, T, T> {

	private final IInOut<T> io;

	public Output(IInOut<T> io) {
		this.io = io;
	}

	@Override
	public T execute(T left, T right) {
		@SuppressWarnings("unchecked")
		Interpreter<T> i = (Interpreter<T>) Interpreter.getInstance();
		ITokens tokens = i.getTokens();
		
		String leftName = i.getKey(left);
		String rightName = i.getKey(right);
		
		// Must be a left value
		// I.e. in memory (not on stack) and a variable
		if (leftName != null && !tokens.isVariable(leftName))
			i.exception("Bad left value for input " + leftName);		
		
		if (tokens.isStdio(rightName))
			io.stdout(left);
		else if (tokens.isFile(rightName))
			io.fileOut(rightName, left);
		else
			i.exception("Bad right value for input " + rightName);
		
		// Clean up, right was just a dummy
		i.remove(rightName, right);
		return left;
	}
}

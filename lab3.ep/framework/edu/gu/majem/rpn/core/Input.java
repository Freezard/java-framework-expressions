package edu.gu.majem.rpn.core;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.IInOut;
import edu.gu.majem.rpn.ITokens;

/**
 * Class handling the input operator. Plug-in must 
 * supply IO.
 * 
 * NOTHING TO DO HERE
 * 
 * @author hajo
 */
public class Input<T> implements IBinOp<T, T, T>{

	private final IInOut<T> io;
	
	public Input( IInOut<T> io) {
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
		if (leftName == null || !tokens.isVariable(leftName))
			i.exception("Bad left value for input " + leftName);

		T tmp = null;
		try {
			if (tokens.isStdio(rightName))
				tmp = io.stdin();
			else if (tokens.isFile(rightName))
				tmp = io.fileIn(rightName);
			else
				i.exception("Bad right value for input " + rightName);

		} catch (FileNotFoundException e) {
			i.exception("File not found " + rightName);
		} catch (IOException e) {
			i.exception("Can't read input");
		}
		
		// Will overwrite old left value
		i.put(leftName, tmp);
		// Clean up, right was just a dummy
		i.remove(rightName, right);
		return tmp;
	}

}

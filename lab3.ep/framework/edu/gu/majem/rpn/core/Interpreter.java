package edu.gu.majem.rpn.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.IOpsFactory;
import edu.gu.majem.rpn.IScalarOp;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.IUnOp;

/**
 * Simple interpreter for evaluation of (generic operands) expressions
 * using (RPN) language
 * 
 * @author hajo
 */
public final class Interpreter<T> /*implements IInterpreter<T>*/{

	// The memory
	private final Map<String, T> mem = new HashMap<String, T>();
	// Stack for RPN calculations
	private final Stack<T> stack = new Stack<T>();
	// Supplied by plug-in
	private final IOpsFactory<T> factory;
	// Defining tokens
	private final ITokens tokens;
	// The instance of this interpreter
	private static Interpreter<?> instance;
	
	/**
	 * Private constructor
	 * @param factory - IOpsFactory<T>
	 * @param tokens - ITokens
	 */
	private Interpreter(IOpsFactory<T> factory, ITokens tokens) {
		this.factory = factory;
		this.tokens = tokens;
	}
	
	/**
	 * getInstance - singleton pattern
	 * @param factory - IOpsFactory<?>
	 * @param tokens - ITokens
	 * @return Interpreter<?>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Interpreter<?> getInstance(IOpsFactory<?> factory, ITokens tokens) {
		if (instance == null)
			instance = new Interpreter(factory, tokens);
		return instance;
	}
	
	/**
	 * getInstance - singleton patttern
	 * @return Interpreter<?>
	 */
	public static Interpreter<?> getInstance() {
		return instance;
	}

	public void eval(List<String> tokenList) {
		stack.reset();
		if (tokenList.size() == 0) {
			exception("Tokenlist empty");
		}
		if (tokenList.size() < 2) {
			exception("Bad tokenlist. Too short");
		}
		String lastToken = null;
		for (String token : tokenList) {
			if (!tokens.isOperand(token)) {
				T result = null;
				if (factory.isBinOp(token)) {					
					if (stack.size() < 2)
						exception("Missing operand for " + token);
					
					T rightValue = stack.pop();
					T leftValue = stack.pop();					
					IBinOp<T, T, T> impl = factory.getBinOp(token);
					result = impl.execute(leftValue, rightValue);
				}
				else if (factory.isUnOp(token)) {					
					if (stack.size() < 1)
						exception("Missing operand for " + token);
					
					T value = stack.pop();										
					IUnOp<T, T> impl = factory.getUnOp(token);
					result = impl.execute(value);					
				}
				else if (factory.isScalarOp(token)) {
					if (stack.size() < 2)
						exception("Missing operand for " + token);
					
					T rightValue = stack.pop();
					T leftValue = stack.pop();									
					IScalarOp<T, T, T> impl = factory.getScalarOp(token);
					result = impl.execute(leftValue, rightValue);
				}
					stack.push(result);
			}
			else if (tokens.isOperand(token)) {
				T value;
				
				if (mem.containsKey(token))
					value = mem.get(token);
				else {
					value = factory.getEmpty();
					mem.put(token, value);
				}
				
				stack.push(value);
			}
			else
				exception("Not an operand or operator");
			
			lastToken = token;
		}
		// Final result on stack top
		if (stack.size() != 1) {
			exception("Missing operator after " + lastToken);
		}
	}

	public void exception(String msg) {
		throw new IllegalArgumentException(msg);
	}

	public String getKey(T value) {
		for (Entry<String, T> e : mem.entrySet()) {
			if (e.getValue() == value) {
				return e.getKey();
			}
		}
		return null;
	}

	public void put(String key, T value) {
		mem.put(key, value);
	}

	// Used to clean up dummy values like stdin token
	public void remove(String key, T value) {
		mem.remove(value);
		mem.keySet().remove(key);
	}

	public ITokens getTokens() {
		return tokens;
	}

	// ---------- Testing  ------------------

	public T getResult() {
		return stack.top();
	}
}

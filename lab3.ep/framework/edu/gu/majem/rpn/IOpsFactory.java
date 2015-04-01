package edu.gu.majem.rpn;



/**
 * Implemented by plug-in to supply operators (and their implementations) 
 * to interpreter
 * @author hajo
 *
 */
public interface IOpsFactory<T> {
	
	// Get an "empty" value of type T
	// Possible 0, empty matrix, empty string, ...
	public T getEmpty();

	// Get a binary operator
	public IBinOp<T,T,T> getBinOp(String token);
	// Get a unary operator
	public IUnOp<T,T> getUnOp(String token);
	// Get a scalar operator
	public IScalarOp<T,T,T> getScalarOp(String token);

	public boolean isBinOp(String s);
	public boolean isUnOp(String s);
	public boolean isIOOp(String s);
	public boolean isScalarOp(String s);
}

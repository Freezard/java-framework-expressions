package edu.gu.majem.rpn;

/**
 * A unary operator must implement this 
 * @author hajo
 *
 */

public interface IUnOp<T1,T2> {
	public T2 execute(T1 t1);
}

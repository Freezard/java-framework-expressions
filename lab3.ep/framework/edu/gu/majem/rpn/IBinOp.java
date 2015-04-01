package edu.gu.majem.rpn;

/**
 * A binary operator must implement this 
 * @author hajo
 *
 */

public interface IBinOp<T1,T2,T3> {
	public T3 execute(T1 t1, T2 t2);
}

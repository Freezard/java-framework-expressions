package edu.gu.majem.rpn;

/**
 * A scalar operator must implement this 
 * @author hajo
 *
 */

public interface IScalarOp<T1, T2, T3> {
	public T3 execute(T1 t1, T2 t2);
}

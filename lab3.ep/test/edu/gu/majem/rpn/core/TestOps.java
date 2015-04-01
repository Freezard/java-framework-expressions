package edu.gu.majem.rpn.core;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.matrix.op.MulDoubleMatrix;


public class TestOps {

	@Test 
	public void testMul(){
		Matrix<Double> m1 = new Matrix<Double>(0.0);
		Matrix<Double> m2 = new Matrix<Double>(0.0);
		
		m1.set(0, 0, 1.0);
		m2.set(0, 0, 1.0);
		
		Matrix<Double> r = new MulDoubleMatrix().execute(m1, m2);
		
		Assert.assertTrue( r.get(0, 0) == 1);
	}
}

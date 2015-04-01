package edu.gu.majem.rpn.core;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.core.Stack;

public class TestStack {

	@Test
	public void testPush() {
		Stack<Matrix<Double>> s = new Stack<Matrix<Double>> ();
		Matrix<Double> a = new Matrix<Double>(0.0);
		Matrix<Double> b = new Matrix<Double>(0.0);
		
		Assert.assertTrue(s.size() == 0);
		
		s.push(a);
		s.push(b);
		Assert.assertTrue(s.size() == 2);
		Matrix<Double> c = s.top();
		Assert.assertTrue( c == b);
		Assert.assertTrue(s.size() == 2);
		
		c = s.pop();
		Assert.assertTrue( c == b);
		Assert.assertTrue(s.size() == 1);
		
		c = s.pop();
		Assert.assertTrue( c == a);
		Assert.assertTrue(s.size() == 0);
		
		// Exception ok
		//s.pop();
	}


}

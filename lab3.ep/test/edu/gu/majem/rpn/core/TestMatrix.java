package edu.gu.majem.rpn.core;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;

public class TestMatrix {

	@Test
	public void testSetGet() {
		Matrix<Double> m = new Matrix<Double>(0.0);

		m.set(0, 0, 1.0);
		m.set(0, 1, 0.0);
		m.set(1, 0, 0.0);
		m.set(1, 1, 1.0);

		Assert.assertTrue(m.getRows() == 2); 
		Assert.assertTrue(m.getCols() == 2);

		Assert.assertTrue(m.get(0, 0) == 1);
		Assert.assertTrue(m.get(0, 1) == 0);
		Assert.assertTrue(m.get(1, 0) == 0);
		Assert.assertTrue(m.get(1, 1) == 1);

	}

	@Test
	public void testClone() {
		Matrix<Double> m = new Matrix<Double>(0.0);

		m.set(0, 0, 1.0);
		m.set(0, 1, 0.0);
		m.set(1, 0, 0.0);
		m.set(1, 1, 1.0);
		
		Matrix<Double> n = (Matrix<Double>) m.clone();
		Assert.assertTrue(n.getRows() == 2);
		Assert.assertTrue(n.getCols() == 2);

		Assert.assertTrue(n.get(0, 0) == 1);
		Assert.assertTrue(n.get(0, 1) == 0);
		Assert.assertTrue(n.get(1, 0) == 0);
		Assert.assertTrue(n.get(1, 1) == 1);
	}

}

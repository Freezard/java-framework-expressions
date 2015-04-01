package edu.gu.majem.rpn.core;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.matrix.MatrixDoubleIO;

public class TestMatrixIO {

	public static String TEST_PATH = "test/edu/gu/hajo/rpn/core/";

	@Test
	public void testRead() throws FileNotFoundException {

		Matrix<Double> m = null;
		MatrixDoubleIO io = new MatrixDoubleIO(TEST_PATH);
		
		m = io.fileIn("id2x2.mtx");

		Assert.assertTrue(m.getRows() == 2);
		Assert.assertTrue(m.getCols() == 2);

		Assert.assertTrue(m.get(0, 0) == 1);
		Assert.assertTrue(m.get(0, 1) == 0);
		Assert.assertTrue(m.get(1, 0) == 0);
		Assert.assertTrue(m.get(1, 1) == 1);
		//System.out.println(m);
		
		m = io.fileIn("m2x3.mtx");
		Assert.assertTrue(m.getRows() == 2);
		Assert.assertTrue(m.getCols() == 3);
		Assert.assertTrue(m.get(0, 0) == 1);
		
		m = io.fileIn("m3x2.mtx");
		Assert.assertTrue(m.getRows() == 3);
		Assert.assertTrue(m.getCols() == 2);
		Assert.assertTrue(m.get(0, 0) == 1);
	}
	
	
}

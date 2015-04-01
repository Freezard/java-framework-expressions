package edu.gu.majem.rpn.core;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.matrix.MatrixDoubleTokens;

public class TestTokens {

	@Test
	public void testIsFile() {
		MatrixDoubleTokens c = new MatrixDoubleTokens();
		
		Assert.assertTrue(c.isFile("m2x3.mtx"));
		
		Assert.assertTrue(  ! c.isFile("m2x3mtx"));
	}

}

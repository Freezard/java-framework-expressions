package edu.gu.majem.rpn.core;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.matrix.MatrixDoubleOpsFactory;
import edu.gu.majem.matrix.MatrixDoubleTokens;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.core.Interpreter;
import edu.gu.majem.rpn.core.Parser;

public class TestInterpreterExceptions {

	Interpreter<Matrix<Double>> i;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		MatrixDoubleOpsFactory f = new MatrixDoubleOpsFactory();
		f.setPath("test/edu/gu/hajo/rpn/core/");
		ITokens t = new MatrixDoubleTokens();
		i = (Interpreter<Matrix<Double>>) Interpreter.getInstance(f, t);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInBadLeftStdin() {
		List<String> ts = Parser.parseLine("_ a < ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tesInBadLeftFile() {
		List<String> ts = Parser.parseLine("a.mtx a < ");
		i.eval(ts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOutBadLeftStdin() {
		List<String> ts = Parser.parseLine("_ a > ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tesOutBadLeftFile() {
		List<String> ts = Parser.parseLine("a.mtx a > ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadBinOpLeftStdin() {
		List<String> ts = Parser.parseLine("_ a + ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadBinOpRightStdin() {
		List<String> ts = Parser.parseLine("a _ + ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadBinOpLefFile() {
		List<String> ts = Parser.parseLine("a.mtx a + ");
		i.eval(ts);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadBinOpRightFile() {
		List<String> ts = Parser.parseLine("a a.mtx + ");
		i.eval(ts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadAssign() {
		List<String> ts = Parser.parseLine("a a + a =");
		i.eval(ts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testScalarOpRightNoNumber() {
		List<String> ts = Parser.parseLine("a _ ^");
		i.eval(ts);
	}
}

package edu.gu.majem.rpn.core;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.matrix.MatrixDoubleOpsFactory;
import edu.gu.majem.matrix.MatrixDoubleTokens;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.core.Interpreter;
import edu.gu.majem.rpn.core.Parser;

// Test matrices, never to be changed (or changed with care)!
public class TestInterpreter {

	Interpreter<Matrix<Double>> i;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException{	
		MatrixDoubleOpsFactory f = new MatrixDoubleOpsFactory();
		f.setPath("test/edu/gu/hajo/rpn/core/");
		ITokens t = new MatrixDoubleTokens();
		i = (Interpreter<Matrix<Double>>) Interpreter.getInstance(f, t);
	}
	
	
	@Test
	public void testInputSquare() {
		List<String> ts = Parser.parseLine("a id2x2.mtx <");
		Assert.assertTrue(ts != null && ts.size() > 0);
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 0);
	}

	@Test
	public void testInput() {
		List<String> ts = Parser.parseLine("a m2x3.mtx <");
		i.eval(ts);
		Matrix<Double> r  = i.getResult();
		Assert.assertTrue(r.getRows() == 2);
		Assert.assertTrue(r.getCols() == 3);
		Assert.assertTrue(r.get(0, 0) == 1); 
	}
	
	
	@Test
	public void testOutput() {
		List<String> ts = Parser.parseLine("a id2x2.mtx < _ >");
		
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 0);
	}
	
	@Test
	public void testInAndOutput() {
		List<String> ts = Parser.parseLine("a id2x2.mtx < _ >");
		
		i.eval(ts);
		Matrix<Double> r  = i.getResult();
		Assert.assertTrue(r.getRows() == 2);
		Assert.assertTrue(r.getCols() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		
		ts = Parser.parseLine("a id2x2.mtx < a id2x2.mtx < + _ >");
		i.eval(ts);
		r  = i.getResult();
		Assert.assertTrue(r.getRows() == 2);
		Assert.assertTrue(r.getCols() == 2);
		Assert.assertTrue(r.get(0, 0) == 2); 		
	}
	
	@Test
	public void testAdd() {
		List<String> ts = Parser.parseLine("a singular.mtx < a +");
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		Assert.assertTrue(r.getCols() == 1 && r.getRows() == 1);
		Assert.assertTrue(r.get(0, 0) == 2);
		
		ts = Parser.parseLine("a id2x2.mtx < a +");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 2);
		Assert.assertTrue(r.get(0, 1) == 0);
		
		ts = Parser.parseLine("a id2x2.mtx < a a + +");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 3);
		Assert.assertTrue(r.get(0, 1) == 0);
		
		ts = Parser.parseLine("a id2x2.mtx < a + a +");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 3);
		Assert.assertTrue(r.get(0, 1) == 0);
		
		ts = Parser.parseLine("a id2x2.mtx < a + a a + +");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 4);
		Assert.assertTrue(r.get(0, 1) == 0);
		
		ts = Parser.parseLine("a id2x2.mtx < a a a + + +");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 4);
		Assert.assertTrue(r.get(0, 1) == 0);
	}
	
	@Test
	public void testMul() {
		List<String> ts = Parser.parseLine("a singular.mtx < a *");
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		Assert.assertTrue(r.getCols() == 1 && r.getRows() == 1);
		Assert.assertTrue(r.get(0, 0) == 1);
		
		ts = Parser.parseLine("a m2x3.mtx < b m3x2.mtx < *");
		i.eval(ts);
		r = i.getResult();
		System.out.println(r);
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 2);
		Assert.assertTrue(r.get(0, 1) == 0);
		Assert.assertTrue(r.get(1, 0) == 0);
		Assert.assertTrue(r.get(1, 1) == 1);
	}
	
	@Test
	public void testAssign() {
		List<String> ts = Parser.parseLine("a b id2x2.mtx < =");
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 0);
		
		ts = Parser.parseLine("a b c id2x2.mtx < = =");
		i.eval(ts);
		r = i.getResult();
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 0);	
	}
	
	@Test
	public void testTransp() {
		List<String> ts = Parser.parseLine("a singular.mtx < !");
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		Assert.assertTrue(r.getCols() == 1 && r.getRows() == 1);
		Assert.assertTrue(r.get(0, 0) == 1);
		
		ts = Parser.parseLine("a m3x2v2.mtx < !");
		i.eval(ts);
		r = i.getResult();
		System.out.println(r);
		Assert.assertTrue(r.getCols() == 3 && r.getRows() == 2);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 3);
	}
	
	@Test
	public void testExp() {
		List<String> ts = Parser.parseLine("a singular.mtx < 3 ^");
		i.eval(ts);
		Matrix<Double> r = i.getResult();
		Assert.assertTrue(r.getCols() == 1 && r.getRows() == 1);
		Assert.assertTrue(r.get(0, 0) == 1);
		
		ts = Parser.parseLine("a m3x2v2.mtx < 3 ^");
		i.eval(ts);
		r = i.getResult();
		System.out.println(r);
		Assert.assertTrue(r.getCols() == 2 && r.getRows() == 3);
		Assert.assertTrue(r.get(0, 0) == 1);
		Assert.assertTrue(r.get(0, 1) == 8);
		Assert.assertTrue(r.get(1, 0) == 27);
		Assert.assertTrue(r.get(1, 1) == 64);
		Assert.assertTrue(r.get(2, 0) == 125);
		Assert.assertTrue(r.get(2, 1) == 216);
	}
}

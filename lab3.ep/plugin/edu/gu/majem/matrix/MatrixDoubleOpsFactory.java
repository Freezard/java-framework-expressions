package edu.gu.majem.matrix;

import edu.gu.majem.matrix.op.AddDoubleMatrix;
import edu.gu.majem.matrix.op.AssignDoubleMatrix;
import edu.gu.majem.matrix.op.ExpDoubleMatrix;
import edu.gu.majem.matrix.op.MulDoubleMatrix;
import edu.gu.majem.matrix.op.SubtrDoubleMatrix;
import edu.gu.majem.matrix.op.TranspDoubleMatrix;
import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.IOpsFactory;
import edu.gu.majem.rpn.IScalarOp;
import edu.gu.majem.rpn.IUnOp;
import edu.gu.majem.rpn.core.Input;
import edu.gu.majem.rpn.core.Output;

/**
 * Handle all operators and mapping of
 * @author hajo
 *
 */
public class MatrixDoubleOpsFactory implements IOpsFactory<Matrix<Double>> {

	public static final String IO_OPS = "<>";
	public static final String BIN_OPS = IO_OPS + "+-*="; 
	public static final String UN_OPS = "!";
	public static final String SCALAR_OPS = "^";
	
	private final MatrixDoubleIO io = new MatrixDoubleIO();
	private final AddDoubleMatrix add = new AddDoubleMatrix();
	private final SubtrDoubleMatrix subtr = new SubtrDoubleMatrix();
	private final MulDoubleMatrix mul = new MulDoubleMatrix();
	private final AssignDoubleMatrix assign = new AssignDoubleMatrix();
	private final TranspDoubleMatrix transp = new TranspDoubleMatrix();
	private final ExpDoubleMatrix exp = new ExpDoubleMatrix();
	private final Input<Matrix<Double>> input = new Input<Matrix<Double>>(io);
	private final Output<Matrix<Double>> output = new Output<Matrix<Double>>(io);
	
	public void setPath(String path) {
		io.setPath(path);
	}

	public String getPath() {
		return io.getPath();
	}
	
	@Override
	public IBinOp<Matrix<Double>, Matrix<Double>, Matrix<Double>> getBinOp (
			String token) {
		if (token.equals("<"))
			return input;
		else if (token.equals(">"))
			return output;
		else if (token.equals("+"))
			return add;
		else if (token.equals("-"))
			return subtr;
		else if (token.equals("*"))
			return mul;
		else if (token.equals("="))
			return assign;
		else
			return null;
	}
	
	@Override
	public IUnOp<Matrix<Double>, Matrix<Double>> getUnOp(String token) {
		if (token.equals("!"))
			return transp;
		else
			return null;
	}
	
	@Override
	public IScalarOp<Matrix<Double>, Matrix<Double> , Matrix<Double>> getScalarOp(String token) {
		if (token.equals("^"))
			return exp;
		else
			return null;
	}
	
	@Override
	public Matrix<Double> getEmpty() {
		return new Matrix<Double>(0.0);
	}

	@Override
	public boolean isBinOp(String s) {
		return s.length() == 1 && BIN_OPS.indexOf(s) >= 0;
	}

	@Override
	public boolean isUnOp(String s) {
		return s.length() == 1 && UN_OPS.indexOf(s) >= 0;
	}
	
	@Override
	public boolean isIOOp(String s) {
		return s.length() == 1 && IO_OPS.indexOf(s) >= 0;
	}

	@Override
	public boolean isScalarOp(String s) {
		return s.length() == 1 && SCALAR_OPS.indexOf(s) >= 0;
	}
}

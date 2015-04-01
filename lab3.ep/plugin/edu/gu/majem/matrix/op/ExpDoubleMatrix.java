package edu.gu.majem.matrix.op;

import java.util.Map;
import java.util.Map.Entry;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.IScalarOp;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.core.Interpreter;

/**
 * Scalar exponential of a matrix.
 * @author hajo
 *
 */
public class ExpDoubleMatrix implements
		IScalarOp<Matrix<Double>, Matrix<Double>, Matrix<Double>> {

	@Override
	public Matrix<Double> execute(Matrix<Double> left, Matrix<Double> right) {
		@SuppressWarnings("unchecked")
		Interpreter<Matrix<Double>> i = (Interpreter<Matrix<Double>>) Interpreter.getInstance();
		ITokens tokens = i.getTokens();
		
		String leftName = i.getKey(left);
		String rightName = i.getKey(right);

		// Must be a left value
		// I.e. in memory (not on stack) and a variable
		if (leftName != null && !tokens.isVariable(leftName))
			i.exception("Bad left value for input " + leftName);
		else if (rightName == null || !tokens.isNumber(rightName))
			i.exception("Bad right value for input " + rightName);
		
		Matrix<Double> tmp = new Matrix<Double>(0.0);
		Map<Integer, Double> columns;
		int k = new Integer(rightName).intValue();
		
		for (Integer row : left.getRowData()) {
			columns = left.getColData(row);
			for (Entry<Integer, Double> entry : columns.entrySet())
				tmp.set(row, entry.getKey(), Math.pow(entry.getValue(), k));
		}

		return tmp;
	}
}

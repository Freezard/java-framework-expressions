package edu.gu.majem.matrix.op;

import java.util.Map;
import java.util.Map.Entry;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.core.Interpreter;

/**
 * Multiplication of matrices.
 * @author hajo
 *
 */
public class MulDoubleMatrix implements
		IBinOp<Matrix<Double>, Matrix<Double>, Matrix<Double>> {

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
		else if (rightName != null && !tokens.isVariable(rightName))
			i.exception("Bad right value for input " + rightName);
		else if (left.getCols() != right.getRows())
			i.exception("Matrix A's column length doesn't match with B's row length");
		
		Matrix<Double> tmp = new Matrix<Double>(0.0);
		Map<Integer, Double> columns;
		
		for (Integer row : left.getRowData()) {
			columns = left.getColData(row);
			Double product = 0.0;
			for (int col = 0; col < right.getCols(); col++) {
				for (Entry<Integer, Double> entry : columns.entrySet()) {
					product += entry.getValue() * right.get(entry.getKey(), col);
				}
			tmp.set(row, col, product);
			product = 0.0;
			}			
		}

		return tmp;
	}
}

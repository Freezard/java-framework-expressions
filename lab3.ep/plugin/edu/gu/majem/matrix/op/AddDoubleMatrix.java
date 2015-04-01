package edu.gu.majem.matrix.op;

import java.util.Map;
import java.util.Map.Entry;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.IBinOp;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.core.Interpreter;

/**
 * Addition of matrices.
 * @author hajo
 *
 */
public class AddDoubleMatrix implements
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
		else if (left.getRows() != right.getRows() || left.getCols() != right.getCols())
			i.exception("Matrices not of same size");
		
		Matrix<Double> tmp = new Matrix<Double>(0.0);
		Map<Integer, Double> columns;
		
		for (Integer row : left.getRowData()) {
			columns = left.getColData(row);
			for (Entry<Integer, Double> entry : columns.entrySet())
				tmp.set(row, entry.getKey(), entry.getValue() + right.get(row, entry.getKey()));
		}
		
		for (Integer row : right.getRowData()) {
			columns = right.getColData(row);
			for (Entry<Integer, Double> entry : columns.entrySet())				
				if (left.get(row, entry.getKey()) == left.getZeroVal())
					tmp.set(row, entry.getKey(), entry.getValue());
		}

		return tmp;
	}
}

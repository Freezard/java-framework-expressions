package edu.gu.majem.matrix.op;

import java.util.Map;
import java.util.Map.Entry;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.ITokens;
import edu.gu.majem.rpn.IUnOp;
import edu.gu.majem.rpn.core.Interpreter;

/**
 * The transpose of a matrix.
 * @author hajo
 *
 */
public class TranspDoubleMatrix implements
		IUnOp<Matrix<Double>, Matrix<Double>> {

	@Override
	public Matrix<Double> execute(Matrix<Double> mat) {
		@SuppressWarnings("unchecked")
		Interpreter<Matrix<Double>> i = (Interpreter<Matrix<Double>>) Interpreter.getInstance();
		ITokens tokens = i.getTokens();
		
		String name = i.getKey(mat);

		// Must be a left value
		// I.e. in memory (not on stack) and a variable
		if (name != null && !tokens.isVariable(name))
			i.exception("Bad left value for input " + name);
		
		Matrix<Double> tmp = new Matrix<Double>(0.0);
		Map<Integer, Double> columns;
		
		for (Integer row : mat.getRowData()) {
			columns = mat.getColData(row);
			for (Entry<Integer, Double> entry : columns.entrySet())
				tmp.set(entry.getKey(), row, entry.getValue());
		}

		return tmp;
	}
}

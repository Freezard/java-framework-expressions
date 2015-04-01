package edu.gu.majem.matrix.op;

import edu.gu.majem.matrix.Matrix;
import edu.gu.majem.rpn.core.AbstractAssign;

/**
 * Assignment of matrices.
 * @author hajo
 *
 */
public class AssignDoubleMatrix extends AbstractAssign<Matrix<Double>> {

	@Override
	public Matrix<Double> getCopy(Matrix<Double> orig) {
		return orig.clone();
	}
}

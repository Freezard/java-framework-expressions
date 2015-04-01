package edu.gu.majem.matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import edu.gu.majem.rpn.IInOut;


/**
 * Utility to read and write matrices 
 * 
 * NOTHING TO DO HERE
 * 
 * @author hajo
 *
 * TODO @param <T>
 */
public class MatrixDoubleIO implements IInOut<Matrix<Double>> {

	private final static String DEFAULT_PATH = "prg/";
	private String path = DEFAULT_PATH;
	private final static String TERMINATOR = ";";
	private final static String PROMPT = "[ ";
	private final static String SEPARATOR = " ";

	public MatrixDoubleIO() {
	}
	
	public MatrixDoubleIO(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return path;
	}
	
	@Override
	public Matrix<Double> stdin() throws IOException {
		Matrix<Double> m = new Matrix<Double>(0.0);
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String line; 
		int i = 0;
		while (true) {
			System.out.print(PROMPT);
			line = stdin.readLine();
			// TODO empty lines
			if (line == null || line.matches(TERMINATOR)) {
				break;
			}
			String[] values = line.split(SEPARATOR);
			for (int j = 0; j < values.length; j++) {
				m.set(i, j, Double.parseDouble(values[j]));
			}
			i++;
		}
		return m;
	}

	@Override
	public Matrix<Double> fileIn(String filename) throws FileNotFoundException {
		Reader reader = null;
		reader = new BufferedReader(new FileReader(path + filename));
		Matrix<Double> m = new Matrix<Double>(0.0);
		Scanner sc = new Scanner(reader);
		try {
			String row = null;
			int i = 0;
			while (sc.hasNextLine()) {
				row = sc.nextLine();
				if (row.length() > 0 && row.charAt(0) != '#') {
					String[] elems = row.split(" ");
					// Skip empty and comments
					int j = 0;
					for (String e : elems) {
						// Don't need to set 0.0 (implicit)
						if (!e.equals("0")) { 
							m.set(i, j, Double.parseDouble(e));
						}
						j++;
					}
					i++;
				}
			}
		} finally {
			sc.close();
		}
		return m;
	}

	
	@Override
	public void stdout(Matrix<Double> m) {
		System.out.println(m);
	}

	
	@Override
	public void fileOut(String filename, Matrix<Double> left) {
		// TODO Auto-generated method stub

	}
}

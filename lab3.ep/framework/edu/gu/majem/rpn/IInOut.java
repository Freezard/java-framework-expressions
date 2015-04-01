package edu.gu.majem.rpn;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Plug-in must supply this, used by framework Input and Output classes
 * @author hajo
 *
 */
public interface IInOut<T> {

	// Read from stdin
	public abstract T stdin() throws IOException;

	// Read from datafile
	public abstract T fileIn(String filename)
			throws FileNotFoundException;

	// Write to stdout
	public abstract void stdout(T t);

	// Write to data file
	public abstract void fileOut(String filename, T t);
}
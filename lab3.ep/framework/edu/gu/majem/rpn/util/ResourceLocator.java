package edu.gu.majem.rpn.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Utility
 * 
 * NOTHING TO DO HERE
 * 
 * @author hajo
 *
 */
public class ResourceLocator {

	public static final String DEFAULT_PATH = "prg/";
	private static String path = DEFAULT_PATH;
	
	public static Reader getReader(String filename)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(path + filename));
	}

	public static void setPath(String path) {
		ResourceLocator.path = path;
	}

	public static String getPath() {
		return ResourceLocator.path;
	}
}

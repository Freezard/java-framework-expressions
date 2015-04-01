package edu.gu.majem.rpn.core;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Parse programs or lines as text.
 * 
 * NOTHING TO DO HERE
 * 
 * @author hajo
 *
 */
public final class Parser {

	public final static String TOKEN_SEPARTORS = " \t\n";
	public final static char COMMENT = '#';
	
	public static List<String> parseProgram(Reader reader) {
		List<String> tokenList = new ArrayList<String>();
		Scanner sc = new Scanner(reader);
		try {
			String line = null;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				// Skip empty and comments
				if (line.length() > 0 && line.charAt(0) != COMMENT) {
					tokenList.addAll(parseLine(line));
				}
			}
		} finally {
			sc.close();
		}
		return tokenList;
	}

	public static List<String> parseLine(String line) {
		List<String> tokenList = new ArrayList<String>();
		StringTokenizer t = new StringTokenizer(line, TOKEN_SEPARTORS);
		while (t.hasMoreTokens()) {
			String token = t.nextToken();
			tokenList.add(token);
		}
		return tokenList;
	}

	private Parser() {
	}
}

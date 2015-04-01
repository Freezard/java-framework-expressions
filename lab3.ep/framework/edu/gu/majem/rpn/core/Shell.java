package edu.gu.majem.rpn.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import edu.gu.majem.rpn.util.ResourceLocator;

/**
 * Command shell for interactive use
 * 
 * NOTHING TO DO HERE
 * 
 * @author hajo
 *
 */
public class Shell {

	private Interpreter<?> i;
	
	public Shell(Interpreter<?> i) {
		this.i = i;
	}

	public void run() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String line;
		List<String> tokenList;
		while (true) {
			try {
				System.out.print("> ");
				line = stdin.readLine();
				if (line.matches("(bye|quit|end)")) {
					break;
				} else if (line.matches("exec")) { // TODO sloppy pattern
					// TODO Possible run more programs
					String program = line.split(" ")[1];
					Reader r = ResourceLocator.getReader(program);
					tokenList = Parser.parseProgram(r);
				} else {
					tokenList = Parser.parseLine(line);
				}
				i.eval(tokenList);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Teminated");
	}
}

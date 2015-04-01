package edu.gu.majem.rpn.core;

import edu.gu.majem.rpn.ITokens;

/**
 * Default implementation of ITokens. Possible to subclass in plug-in 
 * @author hajo
 *
 */
public abstract class AbstractTokens implements ITokens {

	String variablePattern;
	String numberPattern;
	String filePattern;
	String stdioPattern;

	public AbstractTokens(String variablePattern, String numberPattern,
			String filePattern , String stdioPattern) {
		super();
		this.variablePattern = variablePattern;
		this.numberPattern = numberPattern;
		this.filePattern = filePattern;
		this.stdioPattern = stdioPattern;
	}

	public AbstractTokens() {
		// TODO check file pattern (not you, Joachim should)
		this("[a-zA-Z][a-zA-Z0-9]*", "[0-9][0-9]*", 
				"[a-zA-Z][a-zA-Z0-9]*\\.[a-zA-Z][a-zA-Z0-9]*", "_" );		
	}

	@Override
	public boolean isVariable(String s) {
		return s.matches(variablePattern);
	}

	@Override
	public boolean isNumber(String s) {
		return s.matches(numberPattern);
	}

	@Override
	public boolean isFile(String s) {
		return s.matches(filePattern);
	}

	@Override
	public boolean isStdio(String s) {
		return s.matches(stdioPattern);
	}

	@Override
	public boolean isOperand(String s) {
		return isStdio(s) || isFile(s) || isNumber(s) || isVariable(s);
	}
}

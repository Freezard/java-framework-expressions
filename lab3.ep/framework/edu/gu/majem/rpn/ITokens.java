package edu.gu.majem.rpn;

/**
 * The plug-in must supply this. Used by interpreter and more 
 * to classify tokens
 * @author hajo
 *
 */
public interface ITokens {

	public abstract boolean isVariable(String s);

	public abstract boolean isNumber(String s);

	public abstract boolean isFile(String s);

	public abstract boolean isStdio(String s);

	public abstract boolean isOperand(String s);
}
package edu.gu.majem.rpn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import edu.gu.majem.rpn.core.Interpreter;
import edu.gu.majem.rpn.core.Parser;
import edu.gu.majem.rpn.core.Shell;
import edu.gu.majem.rpn.util.ResourceLocator;

/**
 * Application entry point
 * 
 * This is a framework for evaluating expressions using
 * generic operands (i.e. any (but a single) type) in post fix notation.
 * 
 * To use this the implementor needs to supply:
 * - The type to use in the expressions.
 * - Factory for operators (implementing IOpsFactory): The factory defines 
 *   the operators as tokens and supply implementations.
 * - A class defining operand tokens (implementing ITokens).
 * 
 * The fully qualified class names for the factory and the tokens classes
 * must be supplied as args[0] and args[1]. As args[2] an optional
 * filename for a "program" could be supplied (a program is a collection
 * of expressions). If not, an interactive shell will be used.
 * 
 * @author hajo
 *
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		
		String factoryClassName = args[0];
		String tokensClassName = args[1];
	
	    ClassLoader classLoader = ClassLoader.getSystemClassLoader();

	    IOpsFactory<?> fac = (IOpsFactory<?>)
	    	classLoader.loadClass(factoryClassName).newInstance();
	    ITokens tokens = (ITokens)
	    	classLoader.loadClass(tokensClassName).newInstance();
			
		Interpreter<?> ip = Interpreter.getInstance(fac, tokens);
		try {
			if (args.length == 0) {
				new Shell(ip).run();
			} else {
					Reader in = ResourceLocator.getReader(args[2]);
					ip.eval(Parser.parseProgram(in));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

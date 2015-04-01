package edu.gu.majem.rpn.core;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.gu.majem.rpn.core.Parser;
import edu.gu.majem.rpn.util.ResourceLocator;


public class TestParser {
	
	
	@Test
	public void testParseString(){
		List<String> tokens = Parser.parseLine("a one.mtx < a _ >");
		
		Assert.assertTrue(tokens.size() == 6);
	}
	
	
	@Test
	public void testParseFile(){
		Reader in = null;
		try {
			in = ResourceLocator.getReader("aritm.mc");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> tokens = Parser.parseProgram(in);
		Assert.assertTrue(tokens.size() > 0);
		// TODO 
		for( String t: tokens){
			System.out.println(t + " ");
		}
	}
	
}

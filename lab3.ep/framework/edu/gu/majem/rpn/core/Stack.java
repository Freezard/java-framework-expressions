package edu.gu.majem.rpn.core;

import java.util.ArrayList;
import java.util.List;
/**
 * Stack used for the RPN calculations (private class)
 * @author hajo
 *
 * NOTHING TO DO HERE
 *
 */
class Stack<T> {
	
	List<T> stack = new ArrayList<T>();
	
	public void push( T m ){
		stack.add(m);
	}
	
	public T pop(){
		int lastElem = stack.size()-1;
		// Will throw if size = 0
		T m = stack.get(lastElem);
		stack.remove(lastElem);
		return m;	
	}
	
	public T top(){
		return stack.get(stack.size()-1);
	}
	
	public boolean empty(){
		return stack.size() == 0;
	}

	public void reset(){
		stack.clear();
	}
	
	public int size(){
		return stack.size();
	}
}

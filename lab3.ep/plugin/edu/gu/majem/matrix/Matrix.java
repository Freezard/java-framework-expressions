package edu.gu.majem.matrix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * The type used for the Matrix version of the framework
 * @author hajo
 * 
 * T must be numeric
 */
public class Matrix<T> implements Cloneable{

	private int currentRows = 0;
	private int currentCols = 0;	
	public Map<Integer, Map<Integer, T>> rows;
	private T zeroValue;

	public Matrix(T zeroValue) {
		this.zeroValue = zeroValue;
		rows = new HashMap<Integer, Map<Integer, T>>();
	}
	
	public T get(int i, int j) {
		Map<Integer, T> cols = rows.get(i);
		
		if (cols != null) {
			T value = cols.get(j);
			if (value != null)
				return value;
		}
		
		return zeroValue;
	}

	public void set(int i, int j, T value) {
		if (value.equals(zeroValue))
			remove(i, j);
		else {
			Map<Integer, T> cols = rows.get(i);
			if (cols == null) {
				cols = new HashMap<Integer, T>();
				rows.put(i, cols);
				if (i >= currentRows)
					currentRows = i + 1;
			}
			cols.put(j, value);
			if (j >= currentCols)
				currentCols = j + 1;
		}
	}
	
	public void remove(int i, int j) {
		
	}

	@Override
	public Matrix<T> clone() {
		Matrix<T> copy = new Matrix<T>(zeroValue);
		copy.currentRows = currentRows;
		copy.currentCols = currentCols;
		
		Set<Entry<Integer, Map<Integer, T>>> entries = rows.entrySet();
		Iterator<Entry<Integer, Map<Integer, T>>> it = entries.iterator();
		
		while (it.hasNext()) {
			Entry<Integer, Map<Integer, T>> entry = it.next();
			Map<Integer, T> cols = new HashMap<Integer, T>();
			cols.putAll(entry.getValue());
			copy.rows.put(entry.getKey(), cols);
		}
		
		return copy;
	}

	public int getRows() {
		return currentRows;
	}

	public int getCols() {
		return currentCols;
	}
	
	public Set<Integer> getRowData() {
		return rows.keySet();
	}
	
	public Map<Integer, T> getColData(int i) {
		return rows.get(i);
	}
	
	public T getZeroVal() {
		return zeroValue; 
	}
	
	// Should let user decide format	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < currentRows; i++) {
			for (int j = 0; j < currentCols; j++) {
				T t = get(i, j);
				if (t != getZeroVal()) {
					b.append(t.toString());
				} else {
					b.append(getZeroVal().toString());
				}
				b.append(", ");
			}
			b.append("\n"); 
		}
		return b.toString();
	}
}

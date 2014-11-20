package org.varel.calculator.MathCalculator;

import org.varel.calculator.V;

import java.util.ArrayList;
import java.util.List;

public class Convert {
	
	private List<Double> Operand = new ArrayList<Double>();
	
	public Convert() {
		Operand.add(0.0);
	}
	
	public String add(double newOperand) {
		Operand.add(newOperand);
      return V.STACK_TEMPLATE + (Operand.size() - 1);
	}

	public void set(int key, double value) {
		Operand.set(key, value);
	}

	public boolean set(String key, double value) {
		boolean result = false;
		key = key.substring(2, key.length());
		if(Integer.parseInt(key) <= Operand.size() - 1) {
			Operand.set(Integer.parseInt(key), value);
			result = true;
		}
		return result;
	}

	public double get(String key) {
		key = key.substring(V.STACK_TEMPLATE.length(), key.length());
      return Operand.get(Integer.parseInt(key));
	}

}

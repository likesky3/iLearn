package org.work.beautyofp;

public class E00_01_BigIntOperation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String add(String op1, String op2) {
		String sum = "";

		//ensure op1 is the bigger one
		if (op1.length() < op2.length()) {
			String tmp = op2;
			op2 = op1;
			op1 = op2;
		}

		int max = op1.length();
		int min = op2.length();
		int[] res = new int[max + 1];

		int[] a = new int[max];
		int[] b = new int[min];
		for (int i = 0; i < max; i++)
			a[i] = op1.charAt(i) - '0';
		for (int i = 0; i < min; i++)
			b[i] = op2.charAt(i) - '0';

		int i = max - 1, j = min - 1;
		for(; i >= 0 && j >= 0; i--, j--){
			res[i + 1] = a[i] + b[i];
		}
//		while()
		
		//deal with carry
		int carry = 0;
		
		return sum;
	}

}

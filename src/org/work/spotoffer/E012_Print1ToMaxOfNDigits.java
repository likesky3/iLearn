package org.work.spotoffer;

import java.math.BigInteger;

public class E012_Print1ToMaxOfNDigits {

	public static void main(String[] args) {
		E012_Print1ToMaxOfNDigits obj = new E012_Print1ToMaxOfNDigits();
		obj.print1ToMaxOfNDigits(2);
	}

	public void print1ToMaxOfNDigits(int n) {
		if (n <= 0)
			return;
		count = 0;
		char[] num = new char[n];
		for (int i = 1; i <= n; i++)
			buildNDigits(num, i, 0);
	}

	private int count = 0;

	public void buildNDigits(char[] num, int n, int k) {
		if (k < n) {
			// recursively build the n-digit number
			for (int i = 0; i < 10; i++) {
				num[k] = (char) (i + '0');
				buildNDigits(num, n, k + 1);
			}
		}else{
			//print out the number with n digits
			printNDigits(num, n);
		}
	}

	public void printNDigits(char[] num, int n) {
		if (count == 20) {
			System.out.print('\n');
			count = 0;
		} else
			count++;

		if (num[0] != '0')
			System.out.print(num[0]);
		else
			return;

		for (int i = 1; i < n; i++)
			System.out.print(num[i]);
		System.out.print('\t');
	}

}

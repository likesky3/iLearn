package org.work.beautyofp;

public class E02_09_Fibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E02_09_Fibonacci obj = new E02_09_Fibonacci();
		for(int i = 0; i < 11; i++)
			System.out.println(obj.calcFibonacci(i));
	}
	
	public int calcFibonacci(int n){
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		int i = 2;
		int[] f = {0, 1, 1};
		while(i <= n){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			f[i % 3] = f[(i - 1) % 3] + f[(i - 2) % 3];
			i++;
		}
		return f[n % 3];
	}

}

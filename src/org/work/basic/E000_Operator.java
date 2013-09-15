package org.work.basic;

public class E000_Operator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//understanding of 优先级 & 结合性
		int[] a = {0, 1, 2, 3};
		int k = 3;
		while(k >= 1){
			
			a[k--] = a[k];
		}
		for(int n: a)
			System.out.println(n);
	}

}

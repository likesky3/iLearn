package org.work.basic;

public class Operator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Operator obj = new Operator();
//		obj.f1();
		obj.f2();
	}
	
	//
	public void f1(){
		int i = 0;
		i += i++;
		System.out.println(i);
		
		i = 0;
		i = i++ + i;
		System.out.println(i);
		
		i = 0;
		i += ++i;
		System.out.println(i);
	}
	
	public void f2(){
		//[] left to right,  precedence: -- > =, a[k] = a[k - 1] 
		int[] a = {0, 1, 2, 3};
		int k = 3;
		while(k >= 1){
			a[k--] = a[k];
		}
		for(int n: a)
			System.out.println(n);
	}

}

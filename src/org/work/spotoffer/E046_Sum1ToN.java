package org.work.spotoffer;

public class E046_Sum1ToN {

	public static void main(String[] args) {
			int n = 10;
			Tmp.reset();
			Tmp[] a = new Tmp[n];
			int sum = Tmp.getSum();
			System.out.println(sum);
	}

}

class Tmp{
	private static int n;
	private static int sum;
	public Tmp(){
		n++;
		sum += n;
	}
	
	public static void reset(){
		n = 0;
		sum = 0;
	}
	
	public static int getSum(){
		return sum;
	}
}


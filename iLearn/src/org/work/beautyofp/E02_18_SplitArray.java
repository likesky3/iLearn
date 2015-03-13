package org.work.beautyofp;

public class E02_18_SplitArray {

	public static void main(String[] args) {
		int[] a = {0, 1, 2, 3, 6};
		E02_18_SplitArray obj = new E02_18_SplitArray();
		obj.splitArray(a);
	}
	
	public void splitArray(int[] a){
		int offset = forcePositive(a);
		System.out.println("offset: " + offset);
		
		int n2 = a.length - 1;
		int n = n2 / 2;
		
		//calculate sum of the array
		int sum = 0;
		int s;
		for(int item: a)
			sum += item;
		
		boolean[][] dp = new boolean[n + 1][(sum / 2) + 1];
		dp[0][0] = true;
		for(int k1 = 1; k1 <= n2; k1++ ){
			for(int k2 = Math.min(k1, n); k2 >= 1; k2--){
				for(s = 1; s <= sum / 2; s++){
					if(s >= a[k1] && dp[k2 - 1][s - a[k1]]){
						dp[k2][s] = true;
						System.out.print("[" + k2 + "," + s + "]");
					}
				}
			}
		}
		
		
		for(s = sum / 2; s >= 1 && !dp[n][s]; s--)
			;
		
		if(offset > 0){
			s -= offset * n;
			sum -= offset * n2;
		}
		System.out.println("s: " + s);
		System.out.println("the difference between two sub arrays is : " + (sum - 2 * s));
	}
	
	private int forcePositive(int[] a){
		int min = a[1];
		
		//get minimum value of array a
		for(int i = 2; i < a.length; i++ ){
			if(a[i ] < min)
				min = a[i];
		}
		
		int offset = 0;
		//force all elements positive
		if(min <= 0){
			offset = -min + 1;
			for(int i = 1; i < a.length; i++)
				a[i] += offset;
		}
		return offset;
	}
}

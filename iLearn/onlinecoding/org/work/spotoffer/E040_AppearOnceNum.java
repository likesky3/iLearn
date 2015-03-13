package org.work.spotoffer;

/**
 * 第三步做法巧妙。我的思路：用两个指针将数组按照同judge与操作后的结果划分为两个部分，
 * 然后在两部分中用^求出只出现一次的数字
 * 扩展：字符串中只出现一次的字符（其余均出现偶数次）
 * */
public class E040_AppearOnceNum {

	public static void main(String[] args) {
		int[] a = {1, 1, 2, 4, 5, 5, 4, 3, -1, 3};
		int[] res = findAppearOnceNum(a);
		System.out.println(res[0] + " " + res[1]);
	}
	
	//s1: xor all
	//s2: find the position of the  right most 1 in xor result
	//s3: use this bit to recognize the appear once number
	public static int[] findAppearOnceNum(int[] a){
		int[] res = new int[2];
		if(a == null || a.length < 2){
			res[0] = Integer.MAX_VALUE;
			res[1] = Integer.MAX_VALUE;
		}
		//s1
		int xor = 0;
		for(int i = 0; i < a.length; i++){
			xor ^= a[i];
		}
		//s2
		int judge = 1;
		while((xor & 1 )!= 1){
			xor >>=1;
			judge <<= 1;
		}
		//s3，巧妙！
		for(int i = 0; i < a.length; i++){
			if((judge & a[i] )> 0)
				res[0] ^= a[i];
			else {
				res[1] ^= a[i];
			}
		}
		return res;	
	}

}

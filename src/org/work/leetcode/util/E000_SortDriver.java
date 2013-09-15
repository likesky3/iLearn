package org.work.leetcode.util;

import java.util.Random;

public class E000_SortDriver {
	public static void main(String[] args){
//		Random rand = new Random();
//		int length = 100;
//		int[] num = new int[length];
//		for(int i = 0; i < length; i++){
//			num[i] = rand.nextInt();
//		}
		 int[] num = {9,8,7,3,6,5,5,4,3,2,1,0,0};
		
		E002_MergeSort obj = new E002_MergeSort();
		int[] tmp = new int[num.length];
		obj.mergeSort(num, 0, num.length, tmp);
		
		for(int n: num)
			 System.out.println(n);
	}
}

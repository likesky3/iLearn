package org.work.leetcode;

public class E091_NextPermutation {

	public static void main(String[] args) {
		E091_NextPermutation obj = new E091_NextPermutation();
		int[] num = new int[]{51, 31, 41, 35, 34,28,9};
		obj.nextPermutation(num);
		for(int n : num){
			System.out.print(n);
			System.out.print(", ");;
		}
	}
	/*
	 idea: find the swap pair and swap them, then sort the right side in ascending order
	 1. swap pair is described below in the code
	 2. need to take care of the edge case: when num is the max
	 * */
	public void nextPermutation(int[] num) {
		//check if it reach the max of the num[]
		if(isMax(num)){
			//edge case, when num[] is the max
			reverse(num, 0, num.length - 1);
			return;
		}
		
		//find the first number which is smaller than its right side sibling from right to left
		int i = num.length - 1;
		int j = i;
		for(; i >= 1; i--){
			if(num[i] > num[i - 1]) // num[i - 1] is the smaller number
				break;
		}
		
		//search for the lowest number which is greater  than num[i - 1] on the right side and swap them
		for(; j >= 0; j--){
			if(num[j] > num[i - 1])
				break;
		}
		
		//swap num[i - 1] & num[j]
		swap(num, i - 1, j);
		
		//minimize the right side from right most to i
		reverse(num, i, num.length - 1);
	}
	
	private boolean isMax(int[] num){
		for(int i = 1; i < num.length; i++){
			if(num[i - 1] < num[i])
				return false;
		}
		return true;
	}
	
	private void reverse(int[] num, int start, int end){
		int left = start; 
		int right = end;
		while(left < right){
			swap(num, left, right);
			left++;
			right--;
		}
	}
	
	private void swap(int[] num, int i, int j){
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}

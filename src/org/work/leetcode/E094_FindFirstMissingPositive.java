package org.work.leetcode;

public class E094_FindFirstMissingPositive {
	public int firstMissingPositive(int[] A) {
		final int impossible = A.length + 1;
		//1 first run, turn non positive numbers into n+1
		for(int i = 0; i < A.length; i++){
			if(A[i] <= 0)
				A[i] = impossible;
		}
		
		//2 second run, if k is in the array, flip A[k - 1] to negative 
		for(int i = 0; i < A.length; i++){
			int val = Math.abs(A[i]); //important!!!
			if(val <= A.length && A[val - 1] > 0 )
				A[val - 1] = - A[val - 1];
		}
		
		//3 third run,  find out the first positive index p in A, then p+1 is missing
		for(int i = 0; i < A.length; i++){
			if(A[i] > 0)
				return i + 1;
		}
		
		return impossible;
	}
}

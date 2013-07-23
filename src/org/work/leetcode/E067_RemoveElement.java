package org.work.leetcode;


public class E067_RemoveElement {
	//time O(N), space O(1)
	public int removeElement(int[] A, int elem) {
		int newEnd = 0;
		for(int i = 0; i < A.length; i++){
			if(A[i] != elem){
				if(newEnd < i)
					A[newEnd] = A[i];
				newEnd++;
			}
		}
		return newEnd;
	}
}

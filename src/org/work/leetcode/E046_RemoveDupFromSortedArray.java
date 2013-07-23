package org.work.leetcode;

public class E046_RemoveDupFromSortedArray {
	public int removeDuplicates(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(A.length < 2) //these 2 lines can not be removed.
            return A.length;
		int actual = 1;
		for(int i = 1; i < A.length; i++){
			if(A[i] != A[i-1]){
				A[actual++] = A[i];
			}
		}
		return actual;
    }
}

package org.work.basic.sort_search;

public class BinarySearch {
	
	/**recursive version*/
	public int binarySearch(int[] A, int left, int right, int target){
		if(left > right)
			return -1;
		int mid = (left + right) >> 1;
		if(target == A[mid])
			return mid;
		else if(target > A[mid])
			return binarySearch(A, mid + 1, right, target);
		else
			return binarySearch(A, left, mid - 1, target);
	}

}

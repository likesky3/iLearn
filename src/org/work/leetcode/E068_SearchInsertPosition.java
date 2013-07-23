package org.work.leetcode;

public class E068_SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
		return binarySearch(A, 0, A.length - 1, target);
	}

	public int binarySearch(int[] A, int left, int right, int target) {
		if (left > right) //[1,3] 0
			return left;
		else if (left == right) {
			if (target == A[left])
				return left;
			else if (target > A[left])
				return left + 1;
			else
				return left;
		}

		int mid = (left + right) >> 1;

		if (target == A[mid])
			return mid;
		else if (target > A[mid])
			return binarySearch(A, mid + 1, right, target);
		else {
			return binarySearch(A, left, mid - 1, target);
		}
	}

}

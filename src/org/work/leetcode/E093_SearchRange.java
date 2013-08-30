package org.work.leetcode;

public class E093_SearchRange {

	public static void main(String[] args) {
		E093_SearchRange obj = new E093_SearchRange();
		int[] res = obj.searchRange2(new int[]{8}, 8 );
		int start = res[0];
		int end = res[1];
		System.out.println(start + " " + end);
	}
	
	//version 1
	public int[] searchRange(int[] A, int target) {
		int left = 0, right = A.length - 1;
		int mid;
		int start = -1, end = -1;
		while(left <= right){
			mid = (left + right) / 2;
			if(target == A[mid]){
				start = mid;
				end = mid;
				while(start >= 0 && A[start] == target)
					start--;
				start++;
				while(end < A.length && A[end] == target)
					end++;
				end--;
				break;
			}else if(target > A[mid]){
				left = mid + 1;
			}else
				right = mid - 1;
		}
		
		int [] result = new int[]{start, end};
		return result;
	}
	
	//version 2, more efficient
	public int[] searchRange2(int[] A, int target) {
		int[] res = new int[2];
		res[0] = findPos(A, target, 0, A.length - 1, true);
		res[1] = findPos(A, target, 0, A.length - 1, false);
		return res;
	}
	
	private int findPos(int[] A, int target, int left, int right, boolean findLeft){
		if(left > right)
			return -1;
		int mid = (left + right) / 2;
		int pos;
		if(A[mid] == target){
			pos = findLeft ? findPos(A, target, left, mid - 1, findLeft) : findPos(A, target, mid + 1, right, findLeft);
			return pos == -1 ? mid : pos;
		}else if(A[mid] > target)
			return findPos(A, target, left, mid - 1, findLeft);
		else
			return findPos(A, target, mid + 1, right, findLeft);
		
	}

}

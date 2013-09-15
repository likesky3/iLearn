package org.work.leetcode;

public class E059_SearchinRotatedSortedArrayII {
	public static void main(String[] args) {

		int[] A = new int[]{1,3};
		E059_SearchinRotatedSortedArrayII obj = new E059_SearchinRotatedSortedArrayII();
		boolean res = obj.search(A, 3);
		System.out.println(res);
		
//		int mini = obj.searchMin(A);
//		System.out.println(A[mini]);
	}
	
	public boolean search(int[] A, int target) {
		int left = 0, right = A.length - 1;
		int mid = (left + right) / 2;
		
		int pos = -1;
		
		//case below, we have to check one by one
		if(A[left] == A[mid] && A[left] == A[right]){
			for(int i = 0; i < A.length; i++){
				if(A[i] == target){
					pos = i;
					break;
				}
			}
			return pos == -1 ? false : true;
		}
		
		
		while(left <= right){
			mid = (left + right) / 2;
			if(target == A [mid]){
				pos = mid;
				break;
			}
			//left part is in order, 
			//specially notice! 
			//if choose comparison of target with A[mid], logic will be more complex
			else if(A[left] <= A[mid]){
				if(A[mid] > target && target >= A[left])
					right = mid - 1;
				else {
					left = mid + 1;
				}
				
			}else {
				if(A[mid] < target && target <= A[right])
					left = mid + 1;
				else {
					right = mid - 1;
				}
			}
		}
		return pos == -1 ? false : true;
	}
	
	public boolean search2(int[] A, int target) {
		int pos = -1;
		
		//case A = {1,3,1,1,1};
		int end = A.length - 1;
		if(A[0] == A[end / 2] && A[0] == A[end]){
			for(int i = 0; i < A.length; i++){
				if(A[i] == target){
					pos = i;
					break;
				}
			}
			return pos == -1 ? false : true;
		}
		
		int mini = searchMin(A);
		int max = mini == 0 ? A.length - 1 : mini - 1;
		if(target < A[mini] || target > A[max])
			return false;
		
		if(A[0] <= target && target <= A[max])
			pos = binarySearch(A, 0, max, target);
		else if(A[mini] <= target && target <= A[A.length - 1])
			pos = binarySearch(A, mini, A.length - 1, target);
		
		return pos == -1 ? false : true;
	}
	
	public int binarySearch(int[] A, int beg, int end, int target){
		while(beg <= end){//wrong if beg < end
			int mid = (beg + end) / 2;
			if(target == A[mid])
				return mid;
			else if(target < A[mid])
				end = mid - 1;
			else
				beg = mid + 1;
		}
		return -1;
	}
	//search the minimum number in the array, return its index.
	//not rotated, return 0.(the first is the minimum)
	//if rotated, elements in the first in-order part are always larger than that in the second part.
	//so the minimum is in the latter part.
	public int searchMin(int[] A){
		if(A == null)
			return -1;
		int left = 0; //@ end, points to the last number in the first part, it's also the maximum of the array
		int right = A.length - 1; //@end, points to the first number in the second part, also the minimum one
		int mid = left; //initialize in this way to support case when no rotation at all
		
		while(A[left] >= A[right]){
			if(right - left == 1)
				return right;
			
			mid = (left + right) / 2;
			
			if(A[left] == A[mid] && A[mid] == A[right]){
				mid = minInOrder(A);
				break;
			}
				
			if(A[left] <= A[mid]) //mid is in the first part
				left = mid;
			else
				right = mid;
		}		
		return mid;
	}
	
	private int minInOrder(int[] A){
		int min = 0;
		for(int i = 1; i < A.length; i++){
			if(A[i] < A[min])
				min = i;
		}
		return min;
	}
}

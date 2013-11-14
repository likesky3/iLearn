package org.work.spotoffer;

public class E038_GetOccursInOrderArray {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 3, 3, 7, 9};
		E038_GetOccursInOrderArray obj = new E038_GetOccursInOrderArray();
		System.out.println(obj.getOccurs(a, 3));
		System.out.println(obj.getOccurs(a, 5));
	}
	
	public int getOccurs(int[] a, int k){
		if(a == null || a.length == 0)
			return 0;
		int end = a.length - 1;
		int iLeft = binarySearch(a, k, 0, end, -1);
		if(iLeft >= 0){
			int iRight = binarySearch(a, k, 0, end, 1);
			return iRight - iLeft + 1;
		}else {
			return 0;
		}
	}
	
	public int binarySearch(int[] a, int k, int start, int end, int flag){
		if(start > end)
			return -1;
		if(start == end)
			return a[start] == k ? start : -1;
		
		int mid = (end - start >> 1) + start;
		if(flag == -1){
			if( k <= a[mid])
				return binarySearch(a, k, start, mid, flag);
			else
				return binarySearch(a, k, mid + 1, end, flag);
		}else if(flag == 1){
			if(k >= a[mid])
				return binarySearch(a, k, mid, end, flag);
			else {
				return binarySearch(a, k, start, mid - 1, flag);
			}
		}
		return -1;
	}

}

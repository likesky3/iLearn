package org.work.leetcode;

public class E062_MedianOf2SortedArrays {
	public static void main(String[] args) {
		E062_MedianOf2SortedArrays obj = new E062_MedianOf2SortedArrays();
		int[] A = new int[0];
		int[] B = new int[]{2,3};
		double res = obj.findMedianSortedArrays(A, B);
		System.out.println(res);
	}
	
	//version 1, use find kth
	public double findMedianSortedArrays(int A[], int B[]) {
		int total = A.length + B.length;
		if((total & 0x1) == 1)
			return findkth(A, 0, A.length - 1, B, 0, B.length - 1, (total + 1) >> 1);
		else
			return (findkth(A, 0, A.length - 1, B, 0, B.length - 1, total >> 1)
					+ findkth(A, 0, A.length - 1, B, 0, B.length - 1, (total >> 1) + 1)) / 2.0;
	}
	
	private int findkth(int[] A, int abeg, int aend, int[] B, int bbeg, int bend, int k){
		int m = aend - abeg + 1;
		int n = bend - bbeg + 1;
		//always assume m <= n
		if(m > n)
			return findkth(B, bbeg, bend, A, abeg, aend, k);
		
		if(m == 0)
			return B[bbeg + k - 1];
		if(k == 1)
			return A[abeg] <= B[bbeg] ? A[abeg] : B[bbeg];
			
		int pa = (k >> 1) > m ? m : (k >> 1);
		int pb = k - pa;
		if(A[abeg + pa - 1] < B[bbeg + pb - 1])
			return findkth(A, abeg + pa, aend, B, bbeg,bend, k - pa);
		else if(A[abeg + pa - 1] > B[bbeg + pb - 1])
			return findkth(A, abeg, aend, B, bbeg + pb,bend, k - pb);
		else
			return A[abeg + pa - 1];
	}
}

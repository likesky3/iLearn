package org.work.leetcode;

public class E075_SortColors {

	// naively one
	public void sortColors(int[] A) {
		// Start typing your Java solution below
		// DO NOT write main() function
		int c1 = 0, c2 = 0, c3 = 0;
		for (int color : A) {
			if (color == 0)
				c1++;
			else if (color == 1)
				c2++;
			else
				c3++;
		}

		int i = 0;
		for (int c = 1; c <= c1; c++) {
			A[i++] = 0;
		}

		for (int c = 1; c <= c2; c++) {
			A[i++] = 1;
		}

		for (int c = 1; c <= c3; c++) {
			A[i++] = 2;
		}

	}

	// put 2 to the end of array, put 0 to the front part of array
	public void sortColors2(int[] A) {
		// Start typing your Java solution below
		// DO NOT write main() function
		int front = 0, end = A.length - 1;
		for (int i = 0; i <= end; i++) {
			if (A[i] == 0) {
				if(i == front){
					front++;
					continue;
				}
				A[i] = A[front];
				A[front] = 0;
				front++;
			} else if (A[i] == 2) {
				if(i >= end)
					continue;
				
				while(A[end] == 2 && end > i)
					end--;
				if(i == end)
					break;
				
				if(A[end] == 0){
					A[i] = A[front];
					A[front] = 0;
					front++;
				}else
					A[i] = A[end];
				A[end] = 2;
				end--;
			}

		}

	}

}

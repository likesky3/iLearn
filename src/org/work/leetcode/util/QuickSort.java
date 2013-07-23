/**
 * QuickSort.java
 * 
 * Copyright (c) 2011 State Street Bank and Trust Corp. 225 Franklin Street, Boston, MA
 * 02110, U.S.A. All rights reserved.
 * 
 * QuickSort.java is the copyrighted, proprietary property of State Street Bank and Trust
 * Company and its subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 * 
 * Date Programmer Notes ------------ -------------------- ----------------------------
 * May 21, 2013 a524690 Init
 * 
 */
package org.work.leetcode.util;

/**
 * @author a524690
 * 
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] a = { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1 };
		doSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("\nDone");

	}

	public static void doSort(Integer[] array) {

		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(Integer[] array, int start, int end) {
		// System.out.println("------------round" + round++);
		// System.out.println("[start, end]:" + start + " " + end);
		int length = end - start + 1;
		if (length <= 10) {
			InsertSort.doSort(array);
			return;
		}

		int pivot = getPivot(array, start, end);
		int i = start;
		int j = end - 1;
		int tmp = 0;
		while (i < j) {
			while (array[i] < pivot && i < j)
				i++;
			while (array[j] > pivot && i < j)
				j--;

			if (i < j) {
				tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			}
		}
		// put pivot to its correct position
		tmp = array[end - 1];
		array[end - 1] = array[i];
		array[i] = tmp;

		quickSort(array, start, i - 1);
		quickSort(array, i + 1, end);
		// System.out.println("[i, j]:" + i + " " + j);
	}

	private static int getPivot(Integer[] array, int start, int end) {
		int mid = (start + end) / 2;
		int tmp = 0;

		if (array[start] > array[mid]) {
			tmp = array[mid];
			array[mid] = array[start];
			array[start] = tmp;
		}

		if (array[mid] > array[end]) {
			tmp = array[mid];
			array[mid] = array[end];
			array[end] = tmp;
		}

		// hide pivot in position [end-1]
		tmp = array[mid];
		array[mid] = array[end - 1];
		array[end - 1] = tmp;

		return tmp;
	}

}

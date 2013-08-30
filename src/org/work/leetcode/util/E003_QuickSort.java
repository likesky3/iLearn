package org.work.leetcode.util;

public class E003_QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1 };
		doSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("\nDone");

	}

	public static void doSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int start, int end) {
		int length = end - start + 1;
		if (length <= 10) {
			E001_InsertSort.insertSort(array);
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
				swap(array, i, j);
			}
		}
		// put pivot to its correct position
		swap(array, i, end - 1);

		quickSort(array, start, i - 1);
		quickSort(array, i + 1, end);
	}

	private static int getPivot(int[] array, int start, int end) {
		int mid = (start + end) / 2;
		int tmp = 0;

		if (array[start] > array[mid]) {
			swap(array, start, end);
		}
		if (array[mid] > array[end]) {
			swap(array, mid, end);
		}
		// hide pivot in position [end-1]
		swap(array, mid, end - 1);
		return tmp;
	}
	
	private static void swap(int[] array, int i, int j){
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

}

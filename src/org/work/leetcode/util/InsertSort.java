/**
 * InsertSort.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * InsertSort.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 21, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode.util;

/**
 * @author a524690
 * 
 */
public class InsertSort {
	public static void main(String[] args) {
		// int[] array = {9,8,7,6,5,4,3,2,1,0};
		Integer[] array = { 1, 1, 1 };
		InsertSort.doSort(array);
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	public static void doSort(Integer[] array) {
		if (array.length == 0 || 1 == array.length)
			return;

		for (int i = 1; i < array.length; i++) {
			int currElem = array[i];
			int j;
			for (j = i - 1; j >= 0 && currElem < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = currElem;
		}
	}

}

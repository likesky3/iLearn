package org.work.basic.sort_search;

public class DutchNationalFlag {

	public static void main(String[] args) {
		int[] a = {2, 0, 1, 1, 0, 0, 2, 1};
		DutchNationalFlag.dnf(a);
		for(int num : a)
			System.out.print(num + "\t");
	}
	
	public static void dnf(int[] a){
		int low = 0, mid = 0, high = a.length - 1;
		
		while(mid < high){
			switch (a[mid]) {
			case 0:
				swap(a, low++, mid++);
				break;
			case 1:
				mid++;
				break;
			case 2:
				swap(a, mid, high--);
				break;
			default:
				break;
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
/**
 * 1 if the items being sorted are just[0..2], we can just count each number and overwrite the array
 * 2 the problem is closely related to partition operation of quick sort, the attribute need not be 
 * a color, but can be "greater than the medium" or "leading digit is zero"...
 * 
 * */

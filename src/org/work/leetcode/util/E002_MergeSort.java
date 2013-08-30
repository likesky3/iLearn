package org.work.leetcode.util;

public class E002_MergeSort {

	//left inclusive, right exclusive
	public void mergeSort(int[] num, int left, int right, int[] tmp){
		if(left + 1< right){//process when at least 2 numbers
			int mid = (left + right) / 2;
			mergeSort(num, left, mid , tmp);
			mergeSort(num, mid, right, tmp);
			merge(num, left, mid, right, tmp);
		}
	}
	
	private void merge(int[] num, int left, int mid, int right, int[] tmp){
		int i = left;
		int j = mid;
		int k = 0;
		while(i < mid && j < right){
			if(num[i] <= num[j]) //<=, steady sort; only <, unsteady
				tmp[k++] = num[i++];
			else
				tmp[k++] = num[j++];
		}
		while(i < mid)
			tmp[k++] = num[i++];
		while(j < right)
			tmp[k++] = num[j++];
		
		//copy tmp to num;
		k -= 1;
		while(k >= 0){
			num[left + k] = tmp[k];
			k--;
		}
	}
	
}

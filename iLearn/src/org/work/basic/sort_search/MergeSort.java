package org.work.basic.sort_search;

public class MergeSort {

	//both left & right are inclusive
	public void mergeSort(int[] num, int left, int right, int[] tmp){
		if(left < right){//process when at least 2 numbers!!!
//			int mid = (left + right) / 2; 
			int mid = left + (right - left >> 1); //to avoid overflow
			mergeSort(num, left, mid , tmp);
			mergeSort(num, mid + 1, right, tmp);
			merge(num, left, mid, right, tmp);
		}
	}
	
	private void merge(int[] num, int left, int mid, int right, int[] tmp){
		int i = left; //left sub array
		int j = mid + 1; //right sub array
		int k = 0;
		while(i <= mid && j <= right){
			if(num[i] <= num[j]) //<=, steady sort; only <, unsteady
				tmp[k++] = num[i++];
			else
				tmp[k++] = num[j++];
		}
		while(i <= mid)
			tmp[k++] = num[i++];
		while(j <= right)
			tmp[k++] = num[j++];
		
		//copy tmp to num;
		k--;
		while(k >= 0){
			num[left + k] = tmp[k];
			k--;
		}
	}
	
	
	
}

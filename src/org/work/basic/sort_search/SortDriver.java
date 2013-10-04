package org.work.basic.sort_search;

import java.util.Random;

public class SortDriver {
	public static void main(String[] args){
		/**construct random array*/
//		Random rand = new Random();
//		int length = 100;
//		int[] num = new int[length];
//		for(int i = 0; i < length; i++){
//			num[i] = rand.nextInt();
//		}
		 int[] num = {9,8,7,3,6,5,5,4,3,2,1,0,0};
		
		 /**1. selection sort*/
		 SelectionSort selectSorter = new SelectionSort();
		 selectSorter.selectSort(num);
		 
		 /**2. bubble sort*/
//		 BubbleSort obj = new BubbleSort();
//		 obj.bubblesort1(num);
//		 obj.bubblesort2(num);
//		 obj.bubblesort3(num);
		 
		 /**3. merge sort*/
		 MergeSort obj = new MergeSort();
		 int[] tmp = new int[num.length];
		 obj.mergeSort(num, 0, num.length - 1,tmp);
		
		 /**5. heap sort*/
//		 HeapSort heapSorter = new HeapSort();
//		 heapSorter.buildHeap(num);
//		 heapSorter.heapSort(num);
		 
		 /**print sorted array*/
		for(int n: num)
			 System.out.println(n);
	}
	
	public  void quicksort(int[] a, int start, int end){
		//0 or 1 number
		if(end <= start) return;
		//2 number
//		if(end - start == 1 && a[start] > a[end]){
//			swap(a, start, end);
//			return;
//		}
		if((end - start )< 20){
			insertSort(a, start,end);
			return;
		}
	    int pivot = getPivot(a, start, end);
	    int i = start, j = end - 1;
	    while(i < j){
	        while(i < j && a[++i] < pivot);
	        while(i < j && a[--j] > pivot);
	        if(i < j)
	            swap(a, i, j);
	    }
	    swap(a, i, end -1);
	    quicksort(a, start, i - 1);
	    quicksort(a, i + 1, end);
	}

	public int getPivot(int[] a, int left, int right){
		int mid = left + (right - left) >> 1;
		if(a[left] > a[mid])
			swap(a, left, mid);
		if(a[mid] > a[right])
			swap(a, mid, right);
		if(a[left] > a[mid])
			swap(a, left, mid);
		swap(a, mid, right - 1);
		return a[mid];
	}

	public void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public void insertSort(int[] a, int start, int end){
	    if(a == null || (end == start))
	        return;
	    for(int i = start + 1; i <= end; i++){
	        int tmp = a[i];
	        int j = i - 1;
	        for(; j >= 0 && a[j] > tmp; j--){
	            a[j + 1] = a[j];
	        }
	        a[j + 1] = tmp;
	    }
	}

	
}

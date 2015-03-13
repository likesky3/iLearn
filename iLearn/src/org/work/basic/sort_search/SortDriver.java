package org.work.basic.sort_search;

import java.util.Arrays;
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
		 int[] a = {572,815,387,418,434,530,376,190,196,74,830,561,973,771,640,37,539,369,327,51,623,575,988,44,659,48,22,776,487,873,486,169,499,82,128,31,386,691,553,848,968,874,692,404,463,285,745,631,304,271,40,921,733,56,883,517,99,580,55,81,232,971,561,683,806,994,823,219,315,564,997,976,158,208,851,206,101,989,542,985,940,116,153,47,806,944,337,903,712,138,236,777,630,912,22,140,525,270,997,763,812,597,806,423,869,926,344,494,858,519,389,627,517,964,74,432,730,843,673,985,819,397,607,34,948,648,43,212,950,235,995,76,439,614,203,313,180,760,210,813,920,229,615,730,359,863,678,43,293,978,305,106,797,769,3,700,945,135,430,965,762,479,152,121,935,809,101,271,428,608,8,983,758,662,755,190,632,792,789,174,869,622,885,626,310,128,233,82,223,339,771,741,227,131,85,51,361,343,641,568,922,145,256,177,329,959,991,293,850,858,76,291,134,254,956,971,718,391,336,899,206,642,254,851,274,239,538,418,21,232,706,275,615,568,714,234,567,994,368,54,744,498,380,594,415,286,260,582,522,795,261,437,292,887,405,293,946,678,686,682,501,238,245,380,218,591,722,519,770,359,340,215,151,368,356,795,91,250,413,970,37,941,356,648,594,513,484,364,484,909,292,501,59,982,686,827,461,60,557,178,952,218,634,785,251,290,156,300,711,322,570,820,191,755,429,950,18,917,905,905,126,790,638,94,857,235,889,611,605,203,859,749,874,530,727,764,197,537,951,919,24,341,334,505,796,619,492,295,380,128,533,600,160,51,249,5,837,905,747,505,82,158,687,507,339,575,206,28,29,91,459,118,284,995,544,3,154,89,840,364,682,700,143,173,216,290,733,525,399,574,693,500,189,590,529,972,378,299,461,866,326,43,711,460,426,947,391,536,26,579,304,852,158,621,683,901,237,22,225,59,52,798,262,754,649,504,861,472,480,570,347,891,956,347,31,784,581,668,127,628,962,698,191,313,714,893 };
		
		 /**1. selection sort*/
//		 SelectionSort selectSorter = new SelectionSort();
//		 selectSorter.selectSort(num);
		 
		 /**2. bubble sort*/
//		 BubbleSort obj = new BubbleSort();
//		 obj.bubblesort1(num);
//		 obj.bubblesort2(num);
//		 obj.bubblesort3(num);
		 
		 /**3. merge sort*/
//		 MergeSort obj = new MergeSort();
//		 int[] tmp = new int[num.length];
//		 obj.mergeSort(num, 0, num.length - 1,tmp);
		
		 /**4 quick sort*/
		 int[] b = Arrays.copyOf(a, a.length);
		 QuickSort.doSort(a);
		 Arrays.sort(b);
		 for(int i = 0; i < a.length; i++){
			 if(a[i] != b[i])
				 System.out.println("nonono");
		 }
		 System.out.println("Yep!");
		 
		 /**5. heap sort*/
//		 HeapSort heapSorter = new HeapSort();
//		 heapSorter.buildHeap(num);
//		 heapSorter.heapSort(num);
		 
		
		 
		 /**print sorted array*/
//		for(int n: num)
//			 System.out.println(n);
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

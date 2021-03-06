package org.work.basic.sort_search;

public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] a = {572,815,387,418,434,530,376,190,196,74,830,561,973,771,640,37,539,369,327,51,623,575,988,44,659,48,22,776,487,873,486,169,499,82,128,31,386,691,553,848,968,874,692,404,463,285,745,631,304,271,40,921,733,56,883,517,99,580,55,81,232,971,561,683,806,994,823,219,315,564,997,976,158,208,851,206,101,989,542,985,940,116,153,47,806,944,337,903,712,138,236,777,630,912,22,140,525,270,997,763,812,597,806,423,869,926,344,494,858,519,389,627,517,964,74,432,730,843,673,985,819,397,607,34,948,648,43,212,950,235,995,76,439,614,203,313,180,760,210,813,920,229,615,730,359,863,678,43,293,978,305,106,797,769,3,700,945,135,430,965,762,479,152,121,935,809,101,271,428,608,8,983,758,662,755,190,632,792,789,174,869,622,885,626,310,128,233,82,223,339,771,741,227,131,85,51,361,343,641,568,922,145,256,177,329,959,991,293,850,858,76,291,134,254,956,971,718,391,336,899,206,642,254,851,274,239,538,418,21,232,706,275,615,568,714,234,567,994,368,54,744,498,380,594,415,286,260,582,522,795,261,437,292,887,405,293,946,678,686,682,501,238,245,380,218,591,722,519,770,359,340,215,151,368,356,795,91,250,413,970,37,941,356,648,594,513,484,364,484,909,292,501,59,982,686,827,461,60,557,178,952,218,634,785,251,290,156,300,711,322,570,820,191,755,429,950,18,917,905,905,126,790,638,94,857,235,889,611,605,203,859,749,874,530,727,764,197,537,951,919,24,341,334,505,796,619,492,295,380,128,533,600,160,51,249,5,837,905,747,505,82,158,687,507,339,575,206,28,29,91,459,118,284,995,544,3,154,89,840,364,682,700,143,173,216,290,733,525,399,574,693,500,189,590,529,972,378,299,461,866,326,43,711,460,426,947,391,536,26,579,304,852,158,621,683,901,237,22,225,59,52,798,262,754,649,504,861,472,480,570,347,891,956,347,31,784,581,668,127,628,962,698,191,313,714,893 };
//		int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int[] a = {4,5,2,6,8,10,1,7,3,9};
		
		quickSelect(a, 0, a.length - 1, 6);
		System.out.println(a[5]);
		
//		doSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
			if(i %20 == 0)
				System.out.println();
		}
		System.out.println("\nDone");
		
//		int[] a = {4,5,2,6,8,10,1,7,3,9};

	}

	public static void doSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int start, int end) {
		int length = end - start + 1;
		//0 or 1 number
//		if(end <= start) return;
////		2 number
//		if(end - start == 1 && array[start] > array[end]){
//			swap(array, start, end);
//			return;
//		}
		if (length <= 20) {
			//InsertSort.insertSort(array); //wrong!!!
			InsertSort.insertSort(array, start, end);
			return;
		}
		
		getPivot(array, start, end);
		int pivot = array[end - 1];

		/* 注释的代码遇到a[i] 和a[j]相同的情况会陷入死循环，因为交换后i,j 值始终不变
		int i = start + 1;
		int j = end - 2;
		while (i < j) {
			while (array[i] < pivot && i < j)
				i++;
			while (array[j] > pivot && i < j)
				j--;
			if (i < j) {
				swap(array, i, j);
			}
		}*/
		
		/** this implementation leads biased partition when all elements are same
		 * inspired by Dutch National Flag problem
		 * [0..i - 1] < pivot, [j+1, n - 1] >= pivot, [i .. j] unknown
		 * int i = start + 1;
		 * int j = end - 2;
		 * while(i < j){ 
		 * 		if(a[i] < pivot)
		 * 			i++;
		 * 		else{
		 * 			swap(a, i, j);
		 * 			j--;		
		 * 		}
		 * }
		 * */
		
		//这个实现可以避免划分不均匀
		int i = start;
		int j = end - 1;
		while (i < j) {
			while (array[++i] < pivot );
			while (array[--j] > pivot);
			if (i < j) 
				swap(array, i, j);
		}
		
		// put pivot to its correct position
		swap(array, i, end - 1);

		quickSort(array, start, i - 1);
		quickSort(array, i + 1, end);
	}

	private static void getPivot(int[] array, int start, int end) {
		if(end - start <2)
			return;
		int mid = start + (end  - start >> 1);

		if (array[start] > array[mid]) {
			swap(array, start, mid);
		}
		if (array[start] > array[end]) {
			swap(array, start, end);
		}
		if (array[mid] > array[end]) {
			swap(array, mid, end);
		}
		// hide pivot in position [end-1]
		swap(array, mid, end - 1);
	}
	
	private static void swap(int[] array, int i, int j){
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	//  3-way quick sort
	private static void quickSort2(int[]a, int start, int end){
		
	}
	private static void quickSelect(int[] array, int start, int end,  int k) {
		int length = end - start + 1;
		
		//should put in driver function
//		if(k > length){
//			System.out.println("illegal input");
//			return;
//		}
		
		//0 or 1 number
		if(end <= start) return;
		//2 number
		if(end - start == 1 && array[start] > array[end]){
			swap(array, start, end);
			return;
		}
		
//		if (length <= 20) {
//			InsertSort.insertSort(array, start, end);
//			return;
//		}
		
		getPivot(array, start, end);
		int pivot = array[end - 1];
		
		int i = start;
		int j = end - 1;
		while (i < j) {
			while (array[++i] < pivot );
			while (array[--j] > pivot);
			if (i < j) 
				swap(array, i, j);
		}
		swap(array, i, end - 1);

		if(i > k - 1)
			quickSelect(array, start, i - 1, k);
		else if(i < k - 1)
			quickSelect(array, i + 1, end, k);
	}

}

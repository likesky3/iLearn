package org.work.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class E061_2Sum {

	public static void main(String[] args) {
		E061_2Sum obj = new E061_2Sum();
//		int[] numbers = { 572,815,387,418,434,530,376,190,196,74,830,561,973,771,640,37,539,369,327,51,623,575,988,44,659,48,22,776,487,873,486,169,499,82,128,31,386,691,553,848,968,874,692,404,463,285,745,631,304,271,40,921,733,56,883,517,99,580,55,81,232,971,561,683,806,994,823,219,315,564,997,976,158,208,851,206,101,989,542,985,940,116,153,47,806,944,337,903,712,138,236,777,630,912,22,140,525,270,997,763,812,597,806,423,869,926,344,494,858,519,389,627,517,964,74,432,730,843,673,985,819,397,607,34,948,648,43,212,950,235,995,76,439,614,203,313,180,760,210,813,920,229,615,730,359,863,678,43,293,978,305,106,797,769,3,700,945,135,430,965,762,479,152,121,935,809,101,271,428,608,8,983,758,662,755,190,632,792,789,174,869,622,885,626,310,128,233,82,223,339,771,741,227,131,85,51,361,343,641,568,922,145,256,177,329,959,991,293,850,858,76,291,134,254,956,971,718,391,336,899,206,642,254,851,274,239,538,418,21,232,706,275,615,568,714,234,567,994,368,54,744,498,380,594,415,286,260,582,522,795,261,437,292,887,405,293,946,678,686,682,501,238,245,380,218,591,722,519,770,359,340,215,151,368,356,795,91,250,413,970,37,941,356,648,594,513,484,364,484,909,292,501,59,982,686,827,461,60,557,178,952,218,634,785,251,290,156,300,711,322,570,820,191,755,429,950,18,917,905,905,126,790,638,94,857,235,889,611,605,203,859,749,874,530,727,764,197,537,951,919,24,341,334,505,796,619,492,295,380,128,533,600,160,51,249,5,837,905,747,505,82,158,687,507,339,575,206,28,29,91,459,118,284,995,544,3,154,89,840,364,682,700,143,173,216,290,733,525,399,574,693,500,189,590,529,972,378,299,461,866,326,43,711,460,426,947,391,536,26,579,304,852,158,621,683,901,237,22,225,59,52,798,262,754,649,504,861,472,480,570,347,891,956,347,31,784,581,668,127,628,962,698,191,313,714,893 };
		int[] numbers = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		obj.twoSum3(numbers, 101);
	}

	// method 1: O(N * logN) + O(N) + O(N),  space:O(N) 
	public int[] twoSum(int[] numbers, int target) {
		int[] bak = new int[numbers.length];
		for (int p = 0; p < numbers.length; p++)
			bak[p] = numbers[p];

		// step1: sort input array O(N * logN)
		Arrays.sort(numbers);

		// step2: 2 pointers traverse O(N)
		int i = 0, j = numbers.length - 1;
		int sum = 0;
		while (i < j) {
			sum = numbers[i] + numbers[j];
			if (sum == target)
				break;
			else if (sum < target)
				i++;
			else
				j--;
		}

		int[] res = new int[2];
		int c = 0;
		// step 3: find the origin index O(N)
		for (int k = 0; k < bak.length; k++) {
			if (bak[k] == numbers[i]) {
				res[c] = k;
				c++;
			} else if (bak[k] == numbers[j]) {
				res[c] = k;
				c++;
			}
			if (c == 2)
				break;
		}

		// System.out.println(res[0] + " " + res[1]);
		return res;

	}

	// method 2: use hashmap, O(N); space : O(N)
	// result index starts from 1
	public int[] twoSum2(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] res = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				res[0] = map.get(numbers[i]);
				res[1] = i + 1;
			} else {
				map.put(target - numbers[i], i + 1);
			}
		}
		return res;
	}

	// method 3: slower than method 1, 2O(N * logN) + O(N)
	public int[] twoSum3(int[] numbers, int target) {
		int[] res = new int[2];
		int[] index = new int[numbers.length];
		int len = numbers.length;
		for (int i = 0; i < len; i++)
			index[i] = i + 1;

		// step 1, sort
		quickSort(numbers, index, 0, len - 1);
		for (int n : numbers)
			System.out.print(n + " ");
		System.out.println();
		for (int n : index)
			System.out.print(n + " ");

		// step 2, traverse
		for (int i = 0, j = len - 1; i < j;) {
			int sum = numbers[i] + numbers[j];
			if (sum == target) {
				if(index[i] > index[j]){
					res[0] = index[j];
					res[1] = index[i];
				}else{
					res[0] = index[i];
					res[1] = index[j];
				}
				break;
			} else if (sum < target){
				i++;
			}
			else {
				j--;
			}
		}

		return res;
	}

	private void insertSort(int[] a, int[] b) {
		if (a == null || a.length < 2)
			return;

		for (int i = 1; i < a.length; i++) {
			int tmpa = a[i];
			int tmpb = b[i];

			int j = i - 1;
			for (; j >= 0 && a[j] > tmpa; j--) {
				a[j + 1] = a[j];
				b[j + 1] = b[j];
			}
			a[j + 1] = tmpa;
			b[j + 1] = tmpb;
		}
	}

	private void quickSort(int[] a, int[] b, int start, int end) {
		
		int len = end - start + 1;
		if (len <= 10) {
			insertSort(a, b);
			return;
		}
		
		System.out.println(start + " " + end);

		getPivot(a, b, start, end);
		int pivota = a[end - 1];
		int pivotb = b[end - 1];

		/* 注释的代码遇到a[i] 和a[j]相同的情况会陷入死循环，因为交换后i,j 值始终不变
		int i = start + 1;
		int j = end - 2;
		while (i < j) {
			while (a[i] < pivota && i < j)
				i++;
			while (a[j] > pivota && i < j)
				j--;
			if (i < j) {
				swap(a, i, j);
				swap(b, i, j);
			}
		}*/
		
		int i = start;
		int j = end - 1;
		while (i < j) {
			while (a[++i] < pivota && i < j);
			while (a[--j] > pivota && i < j);
			if (i < j) {
				swap(a, i, j);
				swap(b, i, j);
			}
		}
		
		swap(a, i, end - 1);
		swap(b, i, end - 1);

		quickSort(a, b, start, i - 1);
		quickSort(a, b, i + 1, end);
	}

	private void getPivot(int[] a, int[] b, int start, int end) {
		int mid = (start + end) / 2;
		if (a[0] > a[mid]) {
			swap(a, 0, mid);
			swap(b, 0, mid);
		}
		if (a[0] > a[end]) {
			swap(a, 0, end);
			swap(b, 0, end);
		}
		if (a[mid] > a[end]) {
			swap(a, mid, end);
			swap(b, mid, end);
		}

		// hide pivot
		swap(a, mid, end - 1);
		swap(b, mid, end - 1);
	}

	private void swap(int[] a, int i, int j) {
//		System.out.println(i + " " + j);
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}

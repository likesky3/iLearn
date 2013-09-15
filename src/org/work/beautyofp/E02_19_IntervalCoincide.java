package org.work.beautyofp;


public class E02_19_IntervalCoincide {

	public static void main(String[] args) {
		int[] a = { 5,3 };
		int[] b = { 10,4};
		E02_19_IntervalCoincide obj = new E02_19_IntervalCoincide();
//		obj.quicksort(a, b, 0, a.length - 1);
//		for (int n : a)
//			System.out.println(n);
//		System.out.println("--------");
//		for (int n : b)
//			System.out.println(n);
		boolean res = obj.checkIntervalCoincide(a, b, 5, 11);
		System.out.println(res);
	}

	// 自己想到的数据结构是ArrayList<int[]> target, int[] source，int[0]表示xi, int[1]表示yi
	// 操作这种数据结构不方便
	// good idea：用两个数组分别表示区间头xi和区间尾yi
	public boolean checkIntervalCoincide(int[] targetx, int[] targety, int x,
			int y) {
		// step 1: sort target inteval
		quicksort(targetx, targety, 0, targetx.length - 1);

		// step 2: do union
		int[] c = new int[targetx.length];
		int[] d = new int[targety.length];
		int unionLength = union(targetx, targety, c, d);
		
		// step 3: search
		return lookUp(c, d, 0, unionLength - 1, x, y);
	}

	private final int cutoff = 10;

	// sort a[], b[] just follow suit
	public void quicksort(int[] a, int[] b, int left, int right) {
		if (left + cutoff < right) {
			// 1, select pivot
			int pivot = selectPivot(a, b, left, right);

			// 2, divide: partition
			int i = left, j = right - 2;
			while (i < j) {
				while (a[i] < pivot && i < j)
					i++;
				while (a[j] > pivot && i < j)
					j--;
				if (i < j) {
					swap(a, i, j);
					swap(b, i, j);
				}
			}
			swap(a, i, right - 1);
			swap(b, i, right - 1);

			// 3, conqure: sort subarray
			quicksort(a, b, left, i - 1);
			quicksort(a, b, i + 1, right);
		} else {// small array, insert sort is more efficient
			insertSort(a, b, left, right);
		}
	}

	private int selectPivot(int[] a, int[] b, int start, int end) {
		int mid = (start + end) / 2;
		if (a[start] > a[mid]) {
			swap(a, start, mid);
			swap(b, start, mid);
		}
		if (a[mid] > a[end]) {
			swap(a, mid, end);
			swap(b, mid, end);
		}
		// hide pivot
		swap(a, mid, end - 1);
		swap(b, mid, end - 1);
		return a[end - 1];
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public void insertSort(int[] a, int[] b, int start, int end) {
		if(start >= end)
			return;
		
		for (int i = start + 1; i <= end; i++) {
			int acurr = a[i];
			int bcurr = b[i];
			int j = i;
			for (; j > 0 && a[j - 1] > acurr; j--) {
				a[j] = a[j - 1];
				b[j] = b[j - 1];
			}
			a[j] = acurr;
			b[j] = bcurr;
		}
	}
	
	//c[i], d[i]构成合并后的区间
	public int union(int[] a, int[] b, int[] c, int[] d){
		int k = 0;
		c[k] = a[0];
		for(int i = 1; i < a.length; i++){
			if(b[i - 1] >= a[i]	)
				continue;
			d[k++] = b[i - 1];
			c[k] = a[i];
		}
		d[k] = b[b.length - 1];
		return k + 1;
	}
	
	public boolean lookUp(int[] c, int[]d, int begin, int end, int x, int y){
		if(begin == end && begin == 0){//合并后只有一个区间
			if(x >= c[0] && y <= d[0])
				return true;
			else
				return false;
		}
		
		while(begin <= end){
			int mid = (begin + end) / 2;
			if(c[mid] < x)
				begin = mid + 1;
			else if(c[mid] > x)
				end = mid - 1;
			else{
				end = mid;
				break;
			}
		}
		
		if(c[end] <= x &&  y <= d[end])
			return true;
		return false;
	}
}

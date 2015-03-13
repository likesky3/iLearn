package org.work.basic.sort_search;

/**
 * 遍历未排序数组，选出最小的放在未排序最前面，然后从下一个元素开始下一轮遍历*/
public class SelectionSort {
	public void selectSort(int[] a){
		int min;
		for(int i = 0; i < a.length; i++){
			min = i;
			for(int j = i + 1; j < a.length; j++){
				if(a[j] < a[min])
					min = j;
			}
			if(min != i)
				SortUtil.swap(a, i, min);
		}
	}
}

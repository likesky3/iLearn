package org.work.basic.sort_search;

public class BubbleSort {
	//no optimization
	public void bubblesort1(int[] a){
		for(int end = a.length; end > 1; end--){
			for(int j = 1; j < end; j++){
				if(a[j - 1] > a[j])
					swap(a, j - 1, j);
			}
		}
	}
	
	//optimization 1, if no exchange in one iteration, then over
	public void bubblesort2(int[] a){
		int end = a.length;
		boolean swapFlag = true;
		while(swapFlag){
			swapFlag = false;
			for(int i = 1; i < end; i++){
				if(a[i - 1] > a[i]){
					swap(a, i -1, i);
					swapFlag = true;
				}
			}
			end--;
		}
	}
	
	//optimization 2, use a variable to keep the last position which swap happened
	public void bubblesort3(int[] a){
		int lastSwapPos = a.length;
		while(lastSwapPos > 0){
			int end = lastSwapPos;
			lastSwapPos = 0;
			for(int i = 1; i < end; i++){
				if(a[i - 1] > a[i]){
					swap(a, i - 1, i);
					lastSwapPos = i;
				}
			}
		}
	}
	
	public void swap(int[] a, int i, int j){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}

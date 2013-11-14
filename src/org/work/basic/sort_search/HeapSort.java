package org.work.basic.sort_search;

public class HeapSort {
	/**recursive version*/
	public void buildHeap(int[] a){
		int startIndex = getParentIndex(a.length - 1);
		//bottom up的方式，保证i处调节好后，其后面元素都具有最大堆的性质
		for(int i = startIndex; i >= 0; i--)
			maxHeapify(a, a.length, i);
	}
	
	public void maxHeapify(int[] a, int size, int index){
		int left = getLeftChildIndex(index);
		int right = left + 1;
		int largest = index;
		
		if(left < size && a[left] > a[largest])
			largest = left;
		if(right < size && a[right] > a[largest])
			largest = right;
		if(largest != index){//递归出口，叶子节点或者以index为根的树堆序正确
			SortUtil.swap(a, index, largest);
			maxHeapify(a, size, largest);
		}
			
	}
	
	public void heapSort(int[] a){
		for(int i = a.length - 1; i > 0; i--){
			SortUtil.swap(a, 0, i);
			maxHeapify(a, i, 0);
		}
	}
	
	private int getLeftChildIndex(int current){
		return (current << 1) + 1; 
	}
	
	private int getRightChildIndex(int current){
		return (current << 1) + 2;
	}
	
	private int getParentIndex(int current){
		return current - 1 >> 1;
	}
	
	/**iterative version*/
}

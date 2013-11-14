package org.work.basic.tree;

import java.util.Comparator;

import org.work.basic.sort_search.SortUtil;


public class MyPriorityQueue<T> {
	Comparator<T> comparator;
	public MyPriorityQueue(Comparator<T> comp){
		this.comparator = comp;
	}
	public static void main(String[] args) {
		
	}

	/**recursive version*/
	public void buildHeap(T[] a){
		int startIndex = getParentIndex(a.length - 1);
		//bottom up的方式，保证i处调节好后，其后面元素都具有最大堆的性质
		for(int i = startIndex; i >= 0; i--)
			heapify(a, a.length, i);
	}
	
	public void heapify(T[] a, int size, int index){
		int left = getLeftChildIndex(index);
		int right = left + 1;
		int largest = index;
		
		if(left < size && comparator.compare(a[left], a[largest]) >= 0)
			largest = left;
		if(right < size && comparator.compare(a[right], a[largest]) >= 0)
			largest = right;
		if(largest != index){//递归出口，叶子节点或者以index为根的树堆序正确
			swap(a, index, largest);
			heapify(a, size, largest);
		}
			
	}
	
	public void heapSort(T[] a){
		for(int i = a.length - 1; i > 0; i--){
			swap(a, 0, i);
			heapify(a, i, 0);
		}
	}
	
	private int getLeftChildIndex(int current){
		return (current << 1) + 1; 
	}
	
//	private int getRightChildIndex(int current){
//		return (current << 1) + 2;
//	}
	
	private int getParentIndex(int current){
		return current - 1 >> 1;
	}
	
	private void swap(T[] a, int i, int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}

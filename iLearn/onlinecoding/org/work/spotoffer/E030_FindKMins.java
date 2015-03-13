package org.work.spotoffer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class E030_FindKMins {
	public static void main(String[] args) {
		int[] a = {4,5,2,6,8,10,1,7,3,9};
		finkKMins(a, 3);
	}
	
	public static int[] finkKMins(int[] a, int k){
		if(a == null || k > a.length)
			return a;
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new MaxHeapComparator());
		for(int i = 0; i < k; i++)
			maxHeap.add(a[i]);
		
		for(int i = k; i < a.length; i++){
			if(a[i] < maxHeap.peek()){
				maxHeap.remove();
				maxHeap.add(a[i]);
			}
		}
		int [] res = new int[k];
		for(int i = k - 1; i >= 0; i--){
			res[i] = maxHeap.poll();
			System.out.println(res[i]);
		}
		return res;
	}

}

class MaxHeapComparator implements Comparator<Integer>{
	public int compare(Integer o1, Integer o2){
		if(o2 > o1)
			return 1;
		else if(o2 < o1)
			return -1;
		else 
			return 0;
	}
}

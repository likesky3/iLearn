package org.work.basic.array;

import java.util.ArrayList;
import java.util.Arrays;

public class Intervals {

	public static void main(String[] args) {
		Interval2 a = new Interval2(12, 22);
		Interval2 b = new Interval2(2, 13);
		ArrayList<Interval2> res = mergeIntervals(new Interval2[]{a, b});
		for(Interval2 item: res){
			System.out.print(item.x + " ");
			System.out.println(item.y);
		}
	}
	
	public static ArrayList<Interval2> mergeIntervals(Interval2[] A){
		ArrayList<Interval2> res = new ArrayList<Interval2>();
		if(A == null)
			return res;
		if(A.length == 1){
			res.add(A[0]);
			return res;
		}
			
		//sort the intervals
		Arrays.sort(A, new Comparator());
		
		//merge
		for(int i = 1; i < A.length; i++){
			if(A[i -1].y < A[i].x){
				res.add(A[i - 1]);
				res.add(A[i]);
			}else{
				merge(A[i - 1], A[i]);
				res.add(A[i]);
			}
		}
		return res;
	}
	//merge a to b
	public static void merge(Interval2 a, Interval2 b){
		if(a.y >= b.y){
			b.x = a.x;
			b.y = a.y;
			return;
		}
		if(a.y >= b.x)
			b.x = a.x;
	}
	
}

class Comparator implements java.util.Comparator<Interval2>{

	public int compare(Interval2 o1, Interval2 o2) {
		return o1.x - o2.x;
	}
	
}
class Interval2{
	public int x;
	public int y;
	public Interval2(int a, int b){
		x = a;
		y = b;
	}
}
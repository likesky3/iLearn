package org.work.leetcode;

import java.util.ArrayList;

public class E070_TrappingRainWater {
	public static void main(String[] args){
		E070_TrappingRainWater obj = new E070_TrappingRainWater();
		int[] A = new int[]{4,2,0,3,2,5};
		System.out.println(obj.trap2(A));
	}
	
	//failed at [4,2,0,3,2,5]
	public int trap(int[] A) {
		int len = A.length;
		if(len < 3)
			return 0;
		
		//get indexes of  all the peeks
		ArrayList<Integer> peeks = new ArrayList<Integer>();
		if(A[0] >= A[1])
			peeks.add(0);
		for(int i = 1; i < len - 1; i++){
			if(A[i] > A[i - 1] && A[i] >= A[i + 1])
				peeks.add(i);
		}
		if(A[len - 1] > A[len - 2])
			peeks.add(len - 1);
		
		if(peeks.size() < 2)
			return 0;

		//accumulate water between each pair of neighbor peeks
		int p = -1, q = 0;
		int min = 0, first = 0, sec = 0;
		int sum = 0;
		for(int i = 1; i < peeks.size(); i++){
			p = q;
			q = i;
			first = peeks.get(p);
			sec = peeks.get(q);
			min = A[first] < A[sec] ? A[first] : A[sec];
			while(A[first] >= A[sec] || A[first + 1] == A[first])
				first++;
			
			int area1 = (sec - first) * min;
			int area2 = 0;
			while(first < sec)
				area2 += A[first++];
			sum += area1 - area2;
		}
		return sum;
	}
	
	//others
	//water[i] = min(left, right) - A[i]
	public int trap2(int[] A) {
		if(A.length < 3)
			return 0;
		
		int sum = 0;
		
		int[] left = new int[A.length];
		left[0] = A[0];
		for(int i = 1; i < A.length; i++){
			left[i] = left[i - 1] > A[i] ? left[i - 1] : A[i];
		}
		
		int right = 0;
		for(int i = A.length - 1; i >= 0; i--){
			right = right > A[i] ? right : A[i];
			sum += (left[i] > right ? right : left[i]) - A[i];
		}
		
		return sum;
	}
}

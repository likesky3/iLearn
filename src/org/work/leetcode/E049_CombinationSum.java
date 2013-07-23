package org.work.leetcode;

import java.util.ArrayList;

//complete knapsack problem, DP
//imporved understanding of ArrayList
public class E049_CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		// Start typing your Java solution below
		// DO NOT write main() function
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (candidates.length == 0)
			return res;
		
		//sort candidates array
		Integer[] cc2= new Integer[candidates.length];
		for(int i = 0; i < candidates.length; i++)
			cc2[i] = candidates[i];
		doSort(cc2);
		
		if(cc2[0] > target)
			return res;
		
		ArrayList<ArrayList<ArrayList<Integer>>> storage = new 
				ArrayList<ArrayList<ArrayList<Integer>>>(target - cc2[0] + 1);
		//initial storage ArrayList
		for(int i = 0; i < target - cc2[0] + 1; i++)
			storage.add(new ArrayList<ArrayList<Integer>>());
		
		for (int i = 0; i < cc2.length; i++) {
			for (int v = cc2[i]; v <= target; v++) {
				if (v == cc2[i]) {
					ArrayList<Integer> elem = new ArrayList<Integer>();
					elem.add(cc2[i]);
					storage.get(v - cc2[0]).add(elem);
				} else if (v - cc2[i] >= cc2[0]) {
					for (ArrayList<Integer> item : storage.get(v - cc2[i] - cc2[0])) {
						ArrayList<Integer> newItem = new ArrayList<Integer>(
								item);
						newItem.add(cc2[i]);
						storage.get(v - cc2[0]).add(newItem);
					}
				}
			}
		}

		return storage.get(target - cc2[0]);
	}
	
	public static void doSort(Integer[] array) {
		if (array.length == 0 || 1 == array.length)
			return;

		for (int i = 1; i < array.length; i++) {
			int currElem = array[i];
			int j;
			for (j = i - 1; j >= 0 && currElem < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = currElem;
		}
	}
	
	public static void main(String[] args){
		E049_CombinationSum obj = new E049_CombinationSum();
		int[] c = {7,3,5,2};
		System.out.println(obj.combinationSum(c, 7));
	}
}

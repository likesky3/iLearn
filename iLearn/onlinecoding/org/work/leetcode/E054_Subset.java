package org.work.leetcode;

import java.util.ArrayList;

public class E054_Subset {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		S = doSort(S);
		dfs(0, S.length, S, a, 0);
		return res;
	}
	
	private ArrayList<ArrayList<Integer>> res;
	private void dfs(int dep, int maxDep, int[] S, ArrayList<Integer> a, int start){
		if(!res.contains(a))
			res.add(a);
		if(dep == maxDep)
			return;
		for(int i = start; i < S.length; i++){
			ArrayList<Integer> b = new ArrayList<Integer>(a);
			b.add(S[i]);
			dfs(dep + 1, maxDep, S, b, i + 1);
		}
	}
	
	private int[] doSort(int[] array) {
		if (array.length == 0 || 1 == array.length)
			return array;

		for (int i = 1; i < array.length; i++) {
			int currElem = array[i];
			int j;
			for (j = i - 1; j >= 0 && currElem < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = currElem;
		}
		return array;
	}

}

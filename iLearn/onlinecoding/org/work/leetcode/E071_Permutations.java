package org.work.leetcode;

import java.util.ArrayList;

public class E071_Permutations {
	
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		ArrayList<Integer> remains = new ArrayList<Integer>();
		for(int i : num)
			remains.add(i);
		dfs(0, num.length, remains, item, res);
		return res;
	}

	private void dfs(int dep, int maxDep, ArrayList<Integer> remains,
			ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
		if(dep == maxDep){
			res.add(item);
		}
		for(int i = 0; i < remains.size(); i++){
			ArrayList<Integer> newRemains = new ArrayList<Integer>(remains);
			ArrayList<Integer> newItem = new ArrayList<Integer>(item);
			newItem.add(remains.get(i));
			newRemains.remove(i);
			dfs(dep + 1, maxDep, newRemains, newItem,res);
		}
		
	}
}

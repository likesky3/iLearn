package org.work.leetcode;

import java.util.ArrayList;

public class E072_PermutationsII {
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		
		doSort(num);//to deal with duplication
		
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
			//the following two line is to deal with duplication
			if(i > 0 && remains.get(i) == remains.get(i - 1)) //assume remains list is in non-descending order
				continue;
			ArrayList<Integer> newRemains = new ArrayList<Integer>(remains);
			ArrayList<Integer> newItem = new ArrayList<Integer>(item);
			newItem.add(remains.get(i));
			newRemains.remove(i);
			dfs(dep + 1, maxDep, newRemains, newItem,res);
		}
		
	}
	
	public void doSort(int[] array) {
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
}

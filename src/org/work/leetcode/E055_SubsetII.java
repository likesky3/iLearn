package org.work.leetcode;

import java.util.ArrayList;

public class E055_SubsetII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
//		ArrayList<Integer> item = new ArrayList<Integer>();
//		Collections.addAll(item, 1,2,3);
//		res.add(item);
//		ArrayList<Integer> item2 = new ArrayList<Integer>(item);
//		if(!res.contains(item2))
//			res.add(item2);
//		System.out.println(res);
		
		E055_SubsetII obj = new E055_SubsetII();
		int[] num = {3,2,1};
		System.out.println(obj.doSort(num).toString());
	}
	
	//binary encoding, check and add into the result set.
	//i.e. if num.length = 3, then at most there are 2^3 = 8 subsets
	//101 represents a subset which contains num[0] && num[2]
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> empty = new ArrayList<Integer>();
		res.add(empty);
		if(num.length == 0)
			return res;
		
		int k = (int) (Math.pow(2, num.length));
		
		for(int i = 1; i < k; i++){
			ArrayList<Integer> candidate = new ArrayList<Integer>();
			int rem = -1;
			int quotient = i;
			int pos = 0;
			//bit operation is faster than * && /
			while(quotient > 0){
				rem = quotient & 1;
				if(rem == 1)
					candidate.add(num[pos]);
				quotient = quotient >> 1;
				pos++;
			}
			doSort(candidate);
			if(!res.contains(candidate)){
				res.add(candidate);
			}
		}
		return res;
    }
	
	public void doSort(ArrayList<Integer> array) {
		if (array.size() == 0 || 1 == array.size())
			return;

		for (int i = 1; i < array.size(); i++) {
			int currElem = array.get(i);
			int j;
			for (j = i - 1; j >= 0 && currElem < array.get(j); j--) {
				array.set(j + 1, array.get(j));
			}
			array.set(j + 1, currElem);
		}
	}
	
	//Version 2, thought better, sort once, sort num[], but in leetcode, no better
	public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> empty = new ArrayList<Integer>();
		res.add(empty);
		if(num.length == 0)
			return res;
		
		num = doSort(num);
		int k = (int) (Math.pow(2, num.length));
		
		for(int i = 1; i < k; i++){
			ArrayList<Integer> candidate = new ArrayList<Integer>();
			int rem = -1;
			int quotient = i;
			int pos = 0;
			//bit operation is faster than * && /
			while(quotient > 0){
				rem = quotient & 1;
				if(rem == 1)
					candidate.add(num[pos]);
				quotient = quotient >> 1;
				pos++;
			}
			if(!res.contains(candidate)){
				res.add(candidate);
			}
		}
		return res;
    }
	
	//version 3, others, DFS
	public ArrayList<ArrayList<Integer>> subsetsWithDup3(int[] S) {
		S = doSort(S);
		
		res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		canUse = new boolean[S.length]; //for version 4
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
	
	//version 4, others, DFS, faster than the above
	private boolean[] canUse;
	private void dfs2(int dep, int maxDep, int[] S, ArrayList<Integer> a, int start){
		if(!res.contains(a))
			res.add(a);
		if(dep == maxDep)
			return;
		for(int i = start; i < S.length; i++){
			if(i == 0){
				canUse[i] = false;
				ArrayList<Integer> b = new ArrayList<Integer>(a);
				b.add(S[i]);
				dfs(dep + 1, maxDep, S, b, i + 1);
				canUse[i] = true;
			}else{
				if(S[i] == S[i - 1] && canUse[i - 1])
					continue;
				canUse[i] = false;
				ArrayList<Integer> b = new ArrayList<Integer>(a);
				b.add(S[i]);
				dfs(dep + 1, maxDep, S, b, i + 1);
				canUse[i] = true;
			}
			
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

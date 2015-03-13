package org.work.spotoffer;

import java.util.ArrayList;
import java.util.TreeMap;

public class SubArrayEqualsK {

	public static void main(String[] args) {
		SubArrayEqualsK obj = new SubArrayEqualsK();
		int[] a = {-2, 3, 4, -1, 0, 6};
		obj.solve(a, 3);
	}
	
	public ArrayList<Integer[]> solve(int[] a, int k){
		ArrayList<Integer[]> res = new ArrayList<Integer[]>();
		if(a == null || a.length == 0)
			return res;

		int n = a.length;
		int[] s = new int[n];
		s[0] = a[0];
		//O(N)
		for(int i = 1; i < n; i++) 
			s[i] = s[i - 1] + a[i];
		
		//binary search tree, O(N logN)
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		map.put(s[0], 0);
		for(int j = 1; j < n; j++){
			int key = s[j] - k; 
			if(map.containsKey(key)){
				Integer[] pair = new Integer[2];
				pair[0] = map.get(key) + 1;
				pair[1] = j;
				res.add(pair);
				System.out.println(pair[0] + " " + pair[1]);
			}
			map.put(s[j], j);
		}
		
		return res;
	}

}

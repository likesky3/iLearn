package org.work.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class E084_4Sum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		E084_4Sum obj = new E084_4Sum();
//		int[] num = new int[]{-1,2,1,-2,3,0};
//		System.out.println(obj.fourSum(num, 0));
	}
	
	//want to use hash, not correct
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(num == null || num.length < 4)
			return res;
		
		Arrays.sort(num);
		
		//use hashmap to reduce 4 sum problem to 2 sum problem
		//hashmap: key - sum of pair, value - list of all the pairs whose sum is the key
		HashMap<Integer,ArrayList<ArrayList<Integer>>> map = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
		for(int i = 0; i < num.length - 1; i++){
			if(i > 0 && num[i] == num[i - 1])
				continue;
			for(int j = i + 1; j < num.length; j++){
				if(j > (i + 1) && num[j] == num[j - 1])
					continue;
				int pairSum = num[i] + num[j];
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(num[i]);
				list.add(num[j]);
				if(map.containsKey(pairSum))
					map.get(pairSum).add(list);
				else{
					ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();
					pairs.add(list);
					map.put(pairSum, pairs);
				}
			}
		}
		System.out.println(map);
		
		//solve 2sum problem
		int[] keys = new int[map.keySet().size()];
		int i = 0;
		for(int key : map.keySet()){
			keys[i++] = key; 
		}
		Arrays.sort(keys);
		int p = 0;
		int q = keys.length - 1;
		while(p < q){
			int sum = keys[p] + keys[q]; 
			if( sum > target){
				q--;
			}else if(sum < target){
				p++;
			}else {
				for(ArrayList<Integer> pair1 : map.get(keys[p])){
					for(ArrayList<Integer> pair2 : map.get(keys[q])){
						ArrayList<Integer> item = new ArrayList<Integer>();
						item.add(pair1.get(0));
						item.add(pair1.get(1));
						item.add(pair2.get(0));
						item.add(pair2.get(1));
						res.add(item);
					}
				}
				p++;
				q--;
				
			}
		}
		return res;
	}
	
	public ArrayList<ArrayList<Integer>> threeSum(int[] num, int target) {
	    ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();

	    for (int i = 0; i < num.length - 2; i++) {
	        if (i > 0 && num[i] == num[i - 1]) continue;
	        int j = i + 1;
	        int k = num.length - 1;
	        while (j < k) {
	            int three_sum = num[i] + num[j] + num[k];
	            if (three_sum > target) {
	                k--;
	            } else if (three_sum < target) {
	                j++;
	            } else {
	                Integer[] tmp = { num[i], num[j], num[k] };
	                boolean exist = false;
	                for (ArrayList<Integer> triplet : resultList) {
	                    if (triplet.get(0) == num[i] &&
	                            triplet.get(1) == num[j] &&
	                            triplet.get(2) == num[k]) {
	                        exist = true;
	                        break;
	                    }
	                }
	                if (!exist) {
	                    resultList.add(new ArrayList<Integer>(Arrays.asList(tmp)));
	                }                   
	                j++;
	                k--;
	            }
	        }
	    }

	    return resultList;
	}
	
	//version 2, which can pass large test
	public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
	    Arrays.sort(num);
	    ArrayList<ArrayList<Integer>> sum_result = new ArrayList<ArrayList<Integer>>();
	    for (int a = 0; a < num.length - 3; a++) {
	        if (a > 0 && num[a] == num[a - 1]) continue;
	        int rest = target - num[a];
	        int[] tmp_num_ary = Arrays.copyOfRange(num, a + 1, num.length);
	        ArrayList<ArrayList<Integer>> three_sum_result = threeSum(
	                tmp_num_ary, rest);
	        for (ArrayList<Integer> arrayList : three_sum_result) {
	            arrayList.add(0, num[a]);
	        }
	        sum_result.addAll(three_sum_result);
	    }

	    return sum_result;
	}
}

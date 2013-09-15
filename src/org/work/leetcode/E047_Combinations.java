package org.work.leetcode;

import java.util.ArrayList;

//key idea: C(1,n,k) = C(2, n, k-1) + C(3, n, k-1) + ... + C(n-k+2, n, k-1)
/**comments from others
 * Recursion Logic.
 *  
 * Let input = {1,2,3,4,5}, k = {3}
 *
 *  Result = Append '1' to all Combainations of k-1 = 2 numbers from set {2,3,4,5}  (Which will be { {2,3},{2,4},{2,5},{3,4},{3,5},{3,6},{4,5}})
 *                 + Append '2' to all Combinations of k-1 = 2 numbers from set {3,4,5} (Which will be { {3,4}, {3,5}, {3,6}, {4,5} }) 
 *                 + Append '3' to all Combinations of k-1 = 2 numbers from set {4,5} (Which will be {4,5}) 
 *                 // No need to consider '4' here because there will not be no combinations of k-1=2 numbers from set {5}
 * To find the results for k -1 , we just recurse.  
 * */
public class E047_Combinations {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return process(1, n, k);
    }
    
	//all different combinations of k(k = counter) numbers out of [start, start+1, ..., end]  
    private ArrayList<ArrayList<Integer>> process(int start, int end, int counter){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        //terminal condition for recursion
        if(counter == 0){
            res.add(new ArrayList<Integer>());
            return res;
        }
        if(end - start + 1 <= counter){//case n <= k, return all n elements
            ArrayList<Integer> item = new ArrayList<Integer>();
            for(int i = start; i <= end; i++){
                item.add(i);
            }
            res.add(item);
            return res;
        }
        
        for(int first = start; first <= end - counter + 1; first++){
            for(ArrayList<Integer> subItem : process(first + 1, end, counter - 1)){
            	//to get a non-decreasing order in item, we should add first, then each num in subItem
                ArrayList<Integer> item = new ArrayList<Integer>();
                item.add(first);
                for(Integer num : subItem)
                    item.add(num);
                res.add(item);
            }
        }
        return res;
    }
    
    //version 2, clearer and more efficient than version 1
    private int maxLevel;
    private int bound;
    private ArrayList<ArrayList<Integer>> result;
    private Integer[] comb;
    public ArrayList<ArrayList<Integer>> combine2(int n, int k) {
    	result = new ArrayList<ArrayList<Integer>>();
    	maxLevel = k;
    	bound = n;
    	ArrayList<Integer> item = new ArrayList<Integer>();
    	dfs(0, k, 1, item);
    	return result;
    }
    private void dfs(int level, int c, int start, ArrayList<Integer> item){
    	if(level == maxLevel){
    		result.add(item);
    		return;
    	}
    	
    	for(int i = start; i <= bound - c + 1; i++){
    		ArrayList<Integer> newItem = new ArrayList<Integer>(item);
    		newItem.add(i);
    		dfs(level + 1, c - 1, i + 1, newItem);
    	}
    }
    
    //version 3, use a global int[] as a combination
    public ArrayList<ArrayList<Integer>> combine3(int n, int k) {
    	result = new ArrayList<ArrayList<Integer>>();
    	maxLevel = k;
    	bound = n;
    	comb = new Integer[k];
    	
    	dfs(0, k, 1);
    	return result;
    }
    private void dfs(int level, int c, int start){
    	if(level == maxLevel){
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		for(int i : comb)
    			list.add(i);
    		result.add(list);
    		return;
    	}
    	
    	for(int i = start; i <= bound - c + 1; i++){
    		comb[level] = i;
    		dfs(level + 1, c - 1, i + 1);
    	}
    }
    
    public static void main(String[] args){
    	E047_Combinations obj = new E047_Combinations();
    	obj.combine3(1, 1);
    }
}

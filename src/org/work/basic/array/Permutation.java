package org.work.basic.array;

import java.util.ArrayList;

public class Permutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Permutation obj = new Permutation();
		int[] num = {0, 1,2,3};
		System.out.println(obj.permute(num));
	}
	
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return result;
        return recur(num, 0, num.length - 1);
    }
    
    private ArrayList<ArrayList<Integer>> recur(int[] num, int start, int end){
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(start == end){
            ArrayList<Integer> item = new ArrayList<Integer>(num.length);
            item.add(num[start]);
            result.add(item);
        }else{
            int tmp = num[start];
            for(int i = start; i <= end; i++){
                //swap
                num[start] = num[i];
                num[i] = tmp;
                
                
                //recursively permutate the subarray
                for(ArrayList<Integer> sub : recur(num, start + 1, end)){
                    sub.add( num[start]);                    
                    result.add(sub);
                }
                
                //restore
                num[i] = num[start];
                num[start] = tmp;
            }
        }
        
        return result;
    }
}

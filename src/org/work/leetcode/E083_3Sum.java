package org.work.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class E083_3Sum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E083_3Sum obj = new E083_3Sum();
		int[] num = { -1, 0, 1, 2, -1, -4 };
		obj.threeSum3(num);
	}

	// failed @ large
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		// s1. sort
		Arrays.sort(num);

		for (int k = 0; k < num.length - 1; k++) {
			int target = -num[k];
			// s2. two sum solution
			for (int i = k + 1, j = num.length - 1; i < j;) {
				int sum = num[i] + num[j];
				if (sum == target) {
					ArrayList<Integer> item = new ArrayList<Integer>();
					item.add(num[k]);
					item.add(num[i]);
					item.add(num[j]);

					if (!res.contains(item)) {
						res.add(item);
						 System.out.println(item);
					}
					i++;
					j--;
				} else if (sum < target) {
					i++;
				} else {
					j--;
				}

			}
		}
		return res;
	}

	// 他山之石，优化优化再优化, O(N * logN) + O(N ^2)
	public ArrayList<ArrayList<Integer>> threeSum3(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num.length < 3)
            return res;
        
        Arrays.sort(num);
        for(int k = 0; k < (num.length - 2) && num[k] <= 0; k++){
            //remove duplicates
            if(k > 0 && num[k] == num[k - 1]){
                continue;
            }
            
            //2 sum solution in sorted array
            int target = -num[k];
            for(int i = k + 1, j = num.length - 1; i < j;){
                int sum2 = num[i] + num[j];
                if(sum2 == target){
                    ArrayList<Integer> comb = new ArrayList<Integer>();
                    comb.add(num[k]);
                    comb.add(num[i]);
                    comb.add(num[j]);
                    res.add(comb);
                    while(++i < j && num[i] == num[i - 1]);
                        
                    while(--j > i && num[j] == num[j + 1]);
                        
                }else if(sum2 > target){
                                   
                    while(--j > i && num[j] == num[j + 1]);
                        
                }else{
                    while(++i < j && num[i] == num[i - 1]);
                }
            }
        }
        
        return res;
    }
}

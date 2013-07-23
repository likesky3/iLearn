package org.work.leetcode;

import java.util.ArrayList;

public class E043_GrayCode {
	public ArrayList<Integer> grayCode(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> curr = new ArrayList<Integer>();
        if(n < 0)
            return curr;
        
        if(n == 0){
            curr.add(0);
            return curr;
        }
        
        //res[n] = res[n -1] + (each num in mirror(res[n-1]) + 1 << (n-1))
        ArrayList<Integer> prev = new ArrayList<Integer>();
        prev.add(0);
        for(int len = 1; len <= n; len++){
            curr = new ArrayList<Integer>(prev);
            for(int i = prev.size() - 1; i >= 0; i--){
                curr.add(prev.get(i) + (1 << (len - 1)));//Note!  + is prior than <<
            }
            prev = curr;
        }
        
        return curr;
        
    }
}

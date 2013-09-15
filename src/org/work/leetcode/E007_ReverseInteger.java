package org.work.leetcode;

public class E007_ReverseInteger {
	 public int reverse(int x) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        int sign = 1;
	        if(x < 0)
	        {
	            sign = -1;
	            x = -x;
	        }
	        
	        if(x < 10)
	            return sign == 1 ? x : -x;
	        
	        int r = 0;
	        while(x > 0)
	        {
	            r = r * 10 + x %10;
	            x = x / 10;
	        }
	        
	        return sign == 1? r : -r;
	    }
}

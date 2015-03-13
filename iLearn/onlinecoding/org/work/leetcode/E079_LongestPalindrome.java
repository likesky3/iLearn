package org.work.leetcode;

public class E079_LongestPalindrome {
	//brute force solution, O(N ^3),C(N, 2) substrings
	public String longestPalindrome(String s) {
    	for(int len = s.length(); len > 0; len--){
			for(int start = 0; start <= (s.length() - len); start++){
				String subStr = s.substring(start, start + len);
				if(isPalin(subStr))
					return subStr;
			}
		}
		return null;
	}
	
	//O(N)
	private boolean isPalin(String s){
		int i = 0, j = s.length() - 1;
		while(i <= j){
			if(s.charAt(i) != s.charAt(j))
				return false;
			i++; 
			j--;
		}
		return true;
	}
}

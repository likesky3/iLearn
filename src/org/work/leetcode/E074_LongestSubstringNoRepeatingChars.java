package org.work.leetcode;


public class E074_LongestSubstringNoRepeatingChars {
	public static void main(String[] args){
		E074_LongestSubstringNoRepeatingChars obj = new E074_LongestSubstringNoRepeatingChars();
		System.out.println(obj.lengthOfLongestSubstring2("abcddcbae"));
	}
	public int lengthOfLongestSubstring(String s) {
		String substr = null;
		int maxSubLen = 0;
		int beg = 0; //start index of a substring
		int i = 0; //index of the first occurrence in substring of a char
		for(int end = 1; end <= s.length(); end++){
			substr = s.substring(beg, end);
			if(maxSubLen < substr.length())
				maxSubLen = substr.length();
			
			if(end == s.length())
				break;
			i = substr.indexOf(s.charAt(end));
			if(i != -1)
				beg += ++i;
			
		}
		
		return maxSubLen;
	}
	
	//version 2, use canUse[256]map, much faster
	public int lengthOfLongestSubstring2(String s) {
		int maxSubLen = 0;
		int beg = 0; //start index of a substring
		int i = 0; //index of the first occurrence in substring of a char
		
		int count = 0;
		boolean[] canUse = new boolean[256];//use this flag map to speed up
		for(int ii = 0; ii < 256; ii++)
			canUse[ii] = true;
		
		for(int end = 0; end < s.length(); end++){
			if(canUse[s.charAt(end)]){
				count++;
				canUse[s.charAt(end)] = false;
			}else {
				System.out.print("--" + end + " ");
				System.out.println(count);
				maxSubLen = count > maxSubLen ? count : maxSubLen;
				i = s.indexOf(s.charAt(end), beg);
				beg = ++i;
				count = end - beg + 1;
				
				//reset all chars before beg canUse to true
				for(int j = 0; j < beg; j++)
					canUse[s.charAt(j)] = true;
				//reset all chars from beg to end to false
				for(int j = beg; j <= end; j++)
					canUse[s.charAt(j)] = false;
			}
		}
		maxSubLen = count > maxSubLen ? count : maxSubLen;
		return maxSubLen;
	}
}

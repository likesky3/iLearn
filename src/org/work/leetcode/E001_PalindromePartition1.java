package org.work.leetcode;

import java.util.ArrayList;


public class E001_PalindromePartition1 {
	
	static boolean isPalindromePartition(String str)
	{
		int left = 0;
		int right = str.length() - 1;
		
		for(;left <= right; left++, right--)
			if(str.charAt(left) != str.charAt(right))
				return false;
		return true;
	}


	static ArrayList<ArrayList<String>> doPalindromePartition(String str, int start, int endSentinel,final ArrayList<String> palinList, ArrayList<ArrayList<String>> resultList)
	{
		int sub1End;
		String substr1;
		ArrayList<String> copyList = new ArrayList<String>();

		//recursion, DFS, palinList stores the partition of str[0 .. start]
		for(sub1End = start + 1; sub1End <= endSentinel; sub1End++)
		{
			copyList.clear(); //清除上一次划分的影响
			copyList.addAll(palinList); 
			
			substr1 = str.substring(start, sub1End);
			if(isPalindromePartition(substr1))
			{
				copyList.add(substr1);
				if(sub1End < endSentinel)
				{
					doPalindromePartition(str, sub1End, endSentinel,copyList, resultList);
				}
				else {
					resultList.add(copyList);
					
				}
			}
		}
		
		return resultList;
	}
	
	static ArrayList<ArrayList<String>> doPalindromePartition2(String str, int start, int endSentinel, ArrayList<String> palinList, ArrayList<ArrayList<String>> resultList)
	{
		int sub1End;
		String substr1;

		//recursion, DFS, palinList stores the partition of str[0 .. start]
		for(sub1End = start + 1; sub1End <= endSentinel; sub1End++)
		{
			
			substr1 = str.substring(start, sub1End);
			if(isPalindromePartition(substr1))
			{
				palinList.add(substr1);
				if(sub1End < endSentinel)
				{
					doPalindromePartition(str, sub1End, endSentinel,palinList, resultList);
				}
				else {
					//special attention to the life cycle of palinList.
					//resultList.add(palinList); will lead empty result.
					resultList.add(new ArrayList<String>(palinList)); 
					
				}
				palinList.remove(palinList.size() - 1); //remove substr1 in this iteration
			}
		}
		
		return resultList; //if resultList is set to be a global variable, we can avoid return it in each call
	}
	
	public static ArrayList<ArrayList<String>> partition(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
		ArrayList<String> palinList = new ArrayList<String>();
		ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
		return doPalindromePartition(s, 0, s.length(),palinList, resultList);
        
    }
	
	public static void main(String[] args)
	{
		String string = "aab";
		System.out.println(partition(string));
		
	}

}

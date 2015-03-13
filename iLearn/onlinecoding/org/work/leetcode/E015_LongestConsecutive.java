/**
 * LongestConsecutive.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * LongestConsecutive.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 15, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

import java.util.HashSet;


/**
 * @author a524690
 *
 */
public class E015_LongestConsecutive
{
   public static void main(String [] args)
   {
       int[] num = {1,100,5,3,2,20,4};
       E015_LongestConsecutive obj = new E015_LongestConsecutive();
       System.out.println(obj.getLongestConsecutive(num));
   }
  
   
/*   public int getLongestConsecutive(int[] num)
   {
       HashSet<Integer> hs = new HashSet<Integer>();
       for(int v:num)
	   hs.add(v);
       	
       int count = 1;
       for(int v:num)
       {
	   if(hs.contains(v))
	       count = Math.max(count, findConsecutives(hs, v, false) + findConsecutives(hs, v + 1, true));
       }
       return count;
   }
   
   private int findConsecutives(HashSet<Integer> hs, int value, boolean isAscending)
   {
       int count = 0;
       while(hs.contains(value))
       {
	   hs.remove(value);
	   count++;
	   if(isAscending)
	       value++;
	   else {
	    value--;
	}
       }
       return count;
   }*/
 
   //Version 2, second time, hashset is more efficient(light weight) than hashmap
   public int getLongestConsecutive(int[] num)
   {
	   if(num.length == 0)
		   return 0;
	   if(num.length == 1)
		   return 1;
	   int max = 1;
	   int temp= 1;
	   int backward = 0;
	   int forward = 0;
	   //step 1 put the array elements into hashmap
//	   HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//	   for(int i : num) {
//		map.put(i,i);
//	   }
	   HashSet<Integer> map = new HashSet<Integer>();
	   for(int i : num) {
		map.add(i);
	   }
	   
	   //step 2: get longest consecutive sequence
	   for(int i : num) {
		   if(map.contains(i))
		   {
			   temp = 1;
			   forward = i + 1;
			   backward = i - 1;
			   while(map.contains(forward))
			   {
				   temp++;
				   map.remove(forward);
				   forward++;
			   }
			   while(map.contains(backward))
			   {
				   temp++;
				   map.remove(backward);
				   backward--;
			   }
			   if (temp > max)
				   max = temp;
			   map.remove(i);
		   }
	   }
	   return max;
   }
}

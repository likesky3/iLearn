package org.work.leetcode;

//permutation problem
public class E057_ScrambleString {
	
	public static void main(String[] args){
		E057_ScrambleString obj = new E057_ScrambleString();
		System.out.println(obj.isScramble("abc", "cba"));
		//
	}
	
	public boolean isScramble_0(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		if(s1.equals(s2))
			return true;
		
		int i1 = 0, i2 = 0;
		//find the first char in s2 which differs char with the same index in s1
		while(i2 < s2.length()){
			if(s1.charAt(i1) != s2.charAt(i2))
				break;
			i1++;
			i2++;
		}
		
		int p1 = s1.indexOf(s2.charAt(i2++),i1); //index in s1 of the first char of the swapped node's right child
		if(p1 == -1)
			return false;
		int p2 = p1 + 1;//index in s1 behind the last char of the swapped node's right child
		while(i2 < s2.length() && p2 < s1.length()){
			if(s1.charAt(p2) != s2.charAt(i2))
				break;
			p2++;
			i2++;
		}
		
		String sub = s1.substring(0, p1);
		int q1 = sub.indexOf(s2.charAt(i2++));
		if(q1 == -1)
			return false;
		int q2 = q1 + 1;
		while(i2 < s2.length() && q2 < s1.length()){
			if(s1.charAt(q2) != s2.charAt(i2))
				break;
			q2++;
			i2++;
		}
		
		while(i2 < s2.length() && p2 < s1.length()){
			if(s1.charAt(p2++) != s2.charAt(i2++))
				return false;
		}
		return true;
    }
	
	public boolean isScramble(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		if(s1.equals(s2))
			return true;
		StringBuffer sb = new StringBuffer(s1);
		for(int i = 0; i < s2.length(); i++){
			int j = sb.indexOf(s2.substring(i, i+1));
			if(j == -1)
				return false;
			sb.deleteCharAt(j);
		}
		return true;
	}
}

//1, not fully understand the requirement of the exercise, you can swap as many times as you will
//
package org.work.leetcode;

public class E081_RegularExpMatching {
	public static void main(String[] args){
		E081_RegularExpMatching obj = new E081_RegularExpMatching();
		boolean res = obj.isMatch("ab", ".*c");
	}
	
	public boolean isMatch(String s, String p) {
		if(p == null || p.isEmpty())
			return (s == null || s.isEmpty());
		
		if(p.length() == 1)
			return s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
		
		//next character is not *, must match current character
		if(p.charAt(1) != '*'){
			if(s.isEmpty())
				return false;
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1)); 
		}
		
		//next character is *
		while(!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') ){
			if(isMatch(s, p.substring(2))) return true;
			s = s.substring(1);
		}
		
		//e.g. s = "", p = "b*"
		return isMatch(s, p.substring(2));
	}
}

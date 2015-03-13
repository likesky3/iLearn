package org.work.leetcode;

/**
 * http://discuss.leetcode.com/questions/262/scramble-string
 * Given a string A and a string B, if B is a scramble string of A, 
 * then there must be a way for us to divide B into two parts B1, B2 and A into A1, A2, 
 * where B1 contains exactly the same characters (ignore order) as A1 or A2. 
 * Otherwise, B won't be a scramble string of A.
 * */
public class E057_ScrambleString {
	
	public static void main(String[] args){
		E057_ScrambleString obj = new E057_ScrambleString();
		System.out.println(obj.isScramble("abc", "cba"));
	}
	
	/**
	 * 3 dimensional dynamic programming: 
	 * f(i, j, n) ||= ((f(i, j, m) && f(i + m, j + m, n - m)) || (f(i, j + n - m, m) && f(i + m, j, n - m))
	 *  for 1 < m < n where f(i, j, n) is true 
	 *  iff substring starts at s1[i] and substring starts at s2[j] both with length n are scrambled
	 * */
	boolean isScramble(String s1, String s2) {
	    if(s1.equals(s2))
	        return true;
	    boolean[][][] scrambled = new boolean[s1.length()][s2.length()][s1.length() + 1];
	    for(int i = 0; i < s1.length(); i++)
	        for(int j = 0; j < s2.length(); j++){
	            scrambled[i][j][0] = true; 
	            scrambled[i][j][1] = s1.charAt(i) == s2.charAt(j);
	        }

	    for(int i = s1.length() - 1; i >= 0 ; i--)
	        for(int j = s2.length() - 1; j >= 0; j--)
	            for(int n = 2; n <= Math.min(s1.length() - i, s2.length() - j); n ++)
	                for(int m = 1; m < n; m++){
	                    scrambled[i][j][n] |= scrambled[i][j][m] && scrambled[i + m][j + m][n - m] ||
	                            scrambled[i][j + n - m][m] && scrambled[i + m][j][n - m];
	                    if(scrambled[i][j][n])  break;
	                }
	    return scrambled[0][0][s1.length()]; 
	}
	//wrong, not all permutation are correct, e.g. (abcd, bdac) = false
	public boolean isScramble0(String s1, String s2) {
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


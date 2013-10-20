package org.work.leetcode;

/**
 * isPrefix(i,j) indicates s1.substr(0, i - 1)+s2.substr(0,j - 1)==s3.substr(0,i+j-1);
isPrefix(i,j) = isPrefix(i-1,j)&&s1[i-1]==s3[i+j-1] || isPrefix(i,j-1)&&s2[j-1]==s3[i+j-1]
Here, only an array is used to simplify the space overhead (O(min(len1, len2)))
 * */
public class E048_InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1 == null && s2 == null)
        	return s3 == null;
        else if(s1 == null ||s1.isEmpty())
        	return s2.equals(s3);
        else if(s2 == null || s2.isEmpty())
        	return s1.equals(s3);
        else if(s1.isEmpty() && s2.isEmpty())
        	return s3.isEmpty();
        
		if(s1.length() + s2.length() != s3.length())
			return false;
		
		//f[i][j] == isInterleave(s1[1..i], s2[1..j],s3[1..(i + j)]
		//we can induce the value of f[i][j] with f[i-1][j] && f[i][j-1],
		//in other words, classified according to the last char is obtained from s1 or s2
		boolean[][] f = new boolean[s1.length() + 1][s2.length() + 1];
		f[0][0] = true;
		for(int i = 1; i <= s1.length(); i++)
			f[i][0] = f[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
		for(int j = 1; j <= s2.length(); j++)
			f[0][j] = f[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
		
		for(int i = 1; i <= s1.length(); i++)
			for(int j = 1; j <= s2.length(); j++){
				f[i][j] = (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || 
						(f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
			}
		
		return f[s1.length()][s2.length()];
	}
	
	public static void main(String[] args){
		E048_InterleavingString obj = new E048_InterleavingString();
		System.out.println(obj.isInterleave(null, "", ""));
	}
}

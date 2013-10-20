package org.work.leetcode;


public class E002_PalindromePartition2 {
	static int c = 0;
	
	public static void main(String[] args) {
		String s = "ababababababababababababcbabababababababababababa";
		System.out.println(minCut(s));
	}
	
	//易于理解的代码，但会超时
	public static int minCut(String s) {
        if(s == null || s.length() < 2)
            return 0;
        int n = s.length();
        int[][] c = new int[n][n]; //c[i][j] = minimum cuts needed for a palindrome partition of s[i..j]
        boolean[][] p = new boolean[n][n];//p[i][j] = true if s[i][j] is a palindrome, else false
        int cuts = 0;
        int i = 0, j = 0, k = 0;
        
        //every substring of length 1 is a palindrome, need 0 cut
        for(i = 0; i < n; i++){
            p[i][i] = true;
            c[i][i] = 0;
        }
        //2 chars substring，only need to compare the two chars to decide if it is a palindrome
        for(i = 0; i < n - 1;i++){
            j = i + 1;
            p[i][j] = s.charAt(i) == s.charAt(j);
            c[i][j] = p[i][j] ? 0 : 1;
        }
        //substring length > 2，calculate min cuts for substrings of length from short to long
        //build the solution in bottom up manner by calculating minimum cuts of substring of length starting from 3 to n
        for(int len = 3; len < n; len++){
            //for each length(len), set different possible starting index
            for(i = 0; i < n - len + 1; i++){
                j = i + len - 1;//j is the end index of current substring
                p[i][j] = (s.charAt(i) == s.charAt(j)) && p[i + 1][j - 1];//compare the two corner chars & check innner substring
                if(p[i][j])//0 cut for palindrome
                    c[i][j] = 0;
                else{//make a cut at every possible location starting from i to j - 1, get the minimum one
                    for(k = i; k < j; k++){
                        cuts = c[i][k] + c[k + 1][j] + 1;
                        if(cuts < c[i][j])
                            c[i][j] = cuts;
                    }
                }
            }
        }
    
        return c[0][n - 1];
    }
	
	//More efficient version! 前一种方法是更通用的思路，计算出了更多的信息，任意i，j直接的最小划分数, O(N^3)
	//后一种方法只计算了s[i, n - 1]的最小划分数，复杂度O（N^2）
	//只在s[i..j]是回文串时更新dp[i],即s[i..length - 1]的最小划分值
	//只枚举了和i形成回文串的j，正确性证明如下：
	/**
	 * s[i..k]非回文，s[i..j]是回文。记[i..k]间的第一个划分位置为m，则[i..m]是一个回文串，
	 * cuts[i..m] +  cuts[m+1, j]  <= cuts[i, j]
	 * so cuts[i..m] +  cuts[m+1, j] + cuts[j+1, n -1] <= cuts[i, j]+ cuts[j+1, n -1] 
	 * 而按照算法枚举[i..m]会先于[i..k]，所以只需要枚举比较同 i 形成回文串的 j 
	 * */
	public int minCut2(String s) {
	    int length = s.length();
	    int[] dp = new int[length + 1];
	    boolean[][] parlin = new boolean[length][length];

	    for (int i = length; i >= 0; i--) {
	        dp[i] = length - i;
	    }

	    for (int i = length - 1; i >= 0; i--) {
	        for (int j = i; j < length; j++) {//在 j 处划分，若s[i .. j]是回文，则更新dp[i]
	            if (s.charAt(i) == s.charAt(j) && (j - i < 2 || parlin[i + 1][j - 1])) {//这个判断语句写得非常巧妙！！！
	                parlin[i][j] = true;
	                dp[i] = Math.min(dp[i], dp[j + 1] + 1);
	            }
	        }
	    }

	    return dp[0] - 1;
	  }
	

}

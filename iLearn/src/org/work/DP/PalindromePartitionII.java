package org.work.DP;

public class PalindromePartitionII {
    // ³¬Ê±°æ
    public int minCut1(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        for (int i = 0; i < n - 1; i++)
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? true : false;
        int j = 0;
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : false;
            }
        }
        return getMinCut(s, 0, dp);
    }
    
    private int getMinCut (String s, int start, boolean[][] dp) {
        int n = s.length();
        if (start == n || dp[start][n - 1])
            return 0;
        
        int min = n - start;
        for (int i = start; i < n - 1; i++) {
            if (dp[start][i]) {
                min = Math.min(min, getMinCut(s, i + 1, dp) + 1);
            }
        }
        return min;
    }
}

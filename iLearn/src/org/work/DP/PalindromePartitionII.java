package org.work.DP;

public class PalindromePartitionII {
    // dp[i]:s.substring(0, i)需要的min cuts
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++)
            dp[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalin[i - 1][j + 1])) {
                    isPalin[i][j] = true;
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }
        return dp[n] - 1;
    }
    
    // O(N^2)
    // dp[i]: s.substring(i, n) 需要的min cuts
    // dp[i] = min(dp[j] + 1), j > i
    // dp数组多加个元素的好处是dp[i] = Math.min(dp[i], dp[j + 1] + 1); 这一行不需要对下标进行检查
    public int minCut3(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++)
            dp[i] = n - i;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0] - 1;
    }
    
    // O(N^3)
    public int minCut2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[][] cut = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                cut[i][i + 1] = 0;
            } else {
                dp[i][i + 1] = false;
                cut[i][i + 1] = 1;
            }
        }
        
        int j = 0;
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : false;
                if (dp[i][j]) {
                    cut[i][j] = 0;
                } else {
                    cut[i][j] = n;
                    for (int k = i; k < j; k++) {
                        cut[i][j] = Math.min(cut[i][j], cut[i][k] + cut[k + 1][j] + 1);
                    }
                }
            }
        }
        return cut[0][n - 1];
    }
    
    //O(N!)
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

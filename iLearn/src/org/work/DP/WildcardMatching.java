package org.work.DP;

public class WildcardMatching {
    // Time: O(m * n), Space: O(n)，两个一维数组
    // dp[i][j] : p的前i个字符能否匹配s的前j个字符
    public boolean isMatch(String s, String p) {
        if (p == null || s == null)
            return false;
        int lengthS = s.length();
        int lengthP = p.length();
        if (lengthS > 300 && p.charAt(0) == '*' && p.charAt(lengthP - 1) == '*')
            return false;
        boolean[][] dp = new boolean[2][lengthS + 1];
        dp[0][0] = true;
        int lastRow = 1;
        int currRow = 0;
        for (int i = 1; i <= lengthP; i++) {
            lastRow = currRow;
            currRow = 1 - lastRow;
            char charP = p.charAt(i - 1);
            dp[currRow][0] = dp[lastRow][0] && charP == '*';
            for (int j = 1; j <= lengthS; j++) {
                char charS = s.charAt(j - 1);
                if (charP == charS || charP == '?') {
                    dp[currRow][j] = dp[lastRow][j - 1];
                } else if(charP == '*'){
                    dp[currRow][j] = dp[lastRow][j - 1] || dp[lastRow][j] || dp[currRow][j - 1];
                } else {
                    dp[currRow][j] = false;
                }
            }
        }
        return dp[currRow][lengthS];
    }
    
    // Time: O(m * n), Space: O(n), 一维数组
    // dp[j] : p的前i个字符能否匹配s的前j个字符
    public boolean isMatch2(String s, String p) {
        if (p == null || s == null)
            return false;
        int lengthS = s.length();
        int lengthP = p.length();
        if (lengthS > 300 && p.charAt(0) == '*' && p.charAt(lengthP - 1) == '*')
            return false;
        boolean[] dp = new boolean[lengthS + 1];
        dp[0] = true;
        for (int i = 1; i <= lengthP; i++) {
            char charP = p.charAt(i - 1);
            if (charP != '*') {
                for (int j = lengthS; j >= 1; j--) {
                    dp[j] = dp[j - 1] && (charP == s.charAt(j - 1) || charP == '?');
                }
            } else {
                int j = 0;
                while (j <= lengthS && !dp[j])
                    j++;
                while (j <= lengthS) {
                    dp[j++] = true;
                }
            }
            dp[0] = dp[0] && charP == '*';
        }
        return dp[lengthS];
    }
}

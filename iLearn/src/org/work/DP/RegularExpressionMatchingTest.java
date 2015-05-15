package org.work.DP;

import junit.framework.Assert;

import org.junit.Test;

public class RegularExpressionMatchingTest {
    @Test
    public void testIsMatch() {
        RegularExpressionMatching obj = new RegularExpressionMatching();
//        Assert.assertEquals(true, obj.isMatch("", "c*"));
        Assert.assertEquals(true, obj.isMatch("baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*")); // leetcode timeout
//        Assert.assertEquals(true, obj.isMatch("abc", "a*b*c*"));
//        Assert.assertEquals(true, obj.isMatch("abcd", "d*"));
    }
}

class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int lengthS = s.length();
        int lengthP = p.length();
        boolean[][] dp = new boolean[lengthP + 1][lengthS + 1];
        dp[0][0] = true;
        for (int i = 1; i <= lengthP ; i++) {
            char currP = p.charAt(i - 1);
            if (i >= 2) {
                dp[i][0] = dp[i - 2][0] && currP == '*'; 
            } else {
                dp[i][0] = false;
            }
            System.out.print(dp[i][0] + " ");
            for (int j = 1; j <= lengthS; j++) {
                if (currP != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (currP == s.charAt(j - 1) || currP == '.');
                } else {
                    dp[i][j] = dp[i - 1][j];//not use *
                    if (!dp[i][j] && i >= 2) {
                        char lastP = p.charAt(i - 2);
                        dp[i][j] = dp[i][j - 1] && (lastP == '.' || lastP == s.charAt(j - 1));
                    }
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[lengthP][lengthS];
    }
}
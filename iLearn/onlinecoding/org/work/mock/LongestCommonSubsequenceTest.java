package org.work.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class LongestCommonSubsequence {
    public static void main(String[] args) {
    }
    
    // method 1: 借助EditDistance思路，不允许替换，设删除代价为1， 插入代价为0，则求出的编辑距离是将s转为t过程中需要的删除次数，
    // 最长公共子序列长度=（s的长度 - 删除次数）
    public int getLengthOfLongestCommonSubsequence(String s, String t) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        int deletionCost = 1;
        int insertionCost = 0;
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        dp[0][0] = 0;
        for (int j = 1; j <= lengthT; j++) {
            dp[0][j] = dp[0][j - 1] + insertionCost;
        }
        for (int i = 1; i <= lengthS; i++) {
            dp[i][0] = dp[i - 1][0] + deletionCost;
            for (int j = 1; j <= lengthT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + insertionCost, dp[i - 1][j] + deletionCost);
                }
            }
        }
        return s.length() - dp[lengthS][lengthT];
    }
    
    public int getLengthOfLongestCommonSubsequence2(String s, String t) {
        if (s == null || t== null || s.length() == 0 || t.length() == 0)
            return 0;
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        for (int j = 0; j <= lengthT; j++)
            dp[0][j] = 0;
        for (int i = 1; i <= lengthS; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= lengthT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[lengthS][lengthT];
        
    }
    
    public String getLongestCommonSubsequence(String s, String t) {
        if (s == null || t== null || s.length() == 0 || t.length() == 0)
            return "";
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        for (int j = 0; j <= lengthT; j++)
            dp[0][j] = 0;
        for (int i = 1; i <= lengthS; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= lengthT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        //backtracing
        int i = lengthS;
        int j = lengthT;
        StringBuilder lcs = new StringBuilder();
        while(i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                lcs.append(s.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } 
        }
        return lcs.reverse().toString();
    }
    
}
public class LongestCommonSubsequenceTest {

    @Test
    public void testGetLengthOfLongestCommonSubsequence() {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence(null, null));
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence("", ""));
        assertEquals(4, obj.getLengthOfLongestCommonSubsequence("leetcode", "codejam"));
        assertEquals(4, obj.getLengthOfLongestCommonSubsequence("abcd", "1a2b3c4d"));
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence("aaaaaaaaaaaaaaaaaaaaaaaaaa", "b"));
    }

    @Test
    public void testGetLengthOfLongestCommonSubsequence2() {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence2(null, null));
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence2("", ""));
        assertEquals(4, obj.getLengthOfLongestCommonSubsequence2("leetcode", "codejam"));
        assertEquals(4, obj.getLengthOfLongestCommonSubsequence2("abcd", "1a2b3c4d"));
        assertEquals(0, obj.getLengthOfLongestCommonSubsequence2("aaaaaaaaaaaaaaaaaaaaaaaaaa", "b"));
    }
    
    @Test
    public void testGetOfLongestCommonSubsequence() {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        assertEquals("", obj.getLongestCommonSubsequence(null, null));
        assertEquals("", obj.getLongestCommonSubsequence("", ""));
        assertEquals("code", obj.getLongestCommonSubsequence("leetcode", "codejam"));
        assertEquals("abcd", obj.getLongestCommonSubsequence("abcd", "1a2b3c4d"));
        assertEquals("", obj.getLongestCommonSubsequence("aaaaaaaaaaaaaaaaaaaaaaaaaa", "b"));
        assertEquals("abcdef", obj.getLongestCommonSubsequence("abcdef", "xxabxxtcdsdfhef"));
    }
}

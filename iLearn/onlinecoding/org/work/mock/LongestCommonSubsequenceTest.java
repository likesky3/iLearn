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

}

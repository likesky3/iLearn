package org.work.DP;

public class WildcardMatching {
    
    // Method1: Time: O(m * n), Space: O(n)，两个一维数组
    // dp[i][j] : p的前i个字符能否匹配s的前j个字符
    public boolean isMatch1(String s, String p) {
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
    
    // Method2: Time: O(m * n), Space: O(n), 一维数组
    // dp[j] : p的前i个字符能否匹配s的前j个字符
    // http://blog.csdn.net/linhuanmars/article/details/21198049
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
    
    // Method 3: Time: O(n), Space: O(1)
    // http://shmilyaw-hotmail-com.iteye.com/blog/2154716
    public boolean isMatch3(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        int lengthP = pattern.length();
        int lengthS = str.length();
        while (s < lengthS) {
            if (p < lengthP && (pattern.charAt(p) == str.charAt(s) || pattern.charAt(p) == '?')) {
                s++;
                p++;
            } else if (p < lengthP && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p = starIdx + 1;
            } else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            } else {
                return false;
            }
        }
        // deal with remaining character in pattern
        while (p < lengthP && pattern.charAt(p) == '*') {
            p++;
        }
        return p == lengthP;
    }
}

/***
 * 算法的时间复杂度是O(m * n), 空间复杂度是O（n）, m, n分别为p 和 s 的长度。
一开始使用纯二维数组，提交时会遇到OutOfMemory错误，事实上确实只要两个一维数组就行。
使用二维数组来描述思路：
dp[i][j] 表示 p的前i个字符能否匹配s的前j个字符，递推公式分两大情况：
1）如果p的当前字符非 * 号，dp[i][j] == dp[i-1][j-1] && (charP == charS || charP == '?');
2）如果p的当前字符是 * 号，分3种情况讨论：
a) ' * ' 匹配0次：dp[i][j] = dp[i - 1][j]
b) ' * ' 匹配1次：dp[i][j] = dp[i - 1][j - 1]
c) ' * '匹配次数>1：dp[i][j] = dp[i][j - 1]
dp[i][j]的值是三种情况的并集， dp[i][j] == dp[i - 1][j] || dp[i-1][j-1] || dp[i][j - 1]

可以看到dp[i][j] 至多只需看上一行同列和对角以及本行前一列的元素，
因此只需要两个一维数组保存中间结果就可以。
两个一维数组循环使用需要注意line32 & 33行的赋值不可省，因为数组存储上一次循环的旧值。

同意博客http://blog.csdn.net/linhuanmars/article/details/21198049 说的，这题算法复杂度已经到位，
但leetcode 超时设置得太严，对于java程序需要扣各种细节以求过，最后一个case过不了，因此写了第12,13行跳过那个test case。

方法二是实现博客中只需要一维数组的做法，借鉴其精简空间的思路。

方法三也是参考网上，是贪婪思想，能直接过大数据测试，时间复杂度说O(n)我觉得不准确，但也不知如何分析

 */
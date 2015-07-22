package org.work.DP;

public class LonestPalindromicSubstring {
    public static void main(String[] args) {
        LonestPalindromicSubstring obj = new LonestPalindromicSubstring();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String s = "aaab";
        long start = System.currentTimeMillis();
        obj.longestPalindrome1(s);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        obj.longestPalindrome2(s);
        System.out.println(System.currentTimeMillis() - start);
//        start = System.currentTimeMillis();
//        obj.longestPalindrome3(s);
//        System.out.println(System.currentTimeMillis() - start);
        
        start = System.currentTimeMillis();
        obj.longestPalindrome4(s);
        System.out.println(System.currentTimeMillis() - start);
    }
    
    public String longestPalindrome1(String s) {
        if (s == null || s.equals(""))
            return "";
        if (s.length() == 1)
            return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int cc = 2 * n - 1;
        String ans = s.substring(0, 1);
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = true;
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (ans.length() == 1 && dp[i][i + 1])
                ans = s.substring(i, i + 2);
        }
        
        for (int l = 3; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                int j = i + l - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                cc++;
                if (ans.length() < l && dp[i][j])
                    ans = s.substring(i, i + l);
            }
        }
        System.out.println("cost=" + cc);
        return ans;
    }
    
    public String longestPalindrome2(String s) {
        if (s == null || s.equals(""))
            return "";
        int n = s.length();
        String ans = "";
        int maxLen = 0;
        boolean[][] dp = new boolean[n][n];
        int cc = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                cc++;
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        System.out.println("cost2=" + cc);
        return ans;
    }
    
    public String longestPalindrome3(String s) {
        if (s == null || s.equals(""))
            return "";
        int n = s.length();
        for (int len = n; len >= 1; len--) {
            for (int i = 0; i + len <= n; i++) {
                String sub = s.substring(i, i + len);
                if (isPalin(sub)) {
                    return sub;
                }
            }
        }
        return "";
    }
    private boolean isPalin(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    
    public String longestPalindrome4(String s) {
        if (s == null || s.equals(""))
            return "";
        int n = s.length();
        int maxLen = 1;
        int tmpLen = 0;
        String ans = s.substring(0, 1);
        for (int i = 0; i < n - 1; i++) {
            tmpLen = getPalin(s, i - 1, i + 1);
            if (tmpLen > maxLen) {
                int start = i - tmpLen / 2;
                ans = s.substring(start, start + tmpLen); 
                maxLen = tmpLen;
            }
            tmpLen = getPalin(s, i, i + 1);
            if (tmpLen > maxLen) {
                int start = i - tmpLen / 2 + 1;
                ans = s.substring(start, start + tmpLen);
                maxLen = tmpLen;
            }
        }
        return ans;
    }
    
    private int getPalin(String s, int left, int right) {
        int n = s.length();
        int len = right - left - 1;
        while (left >= 0 && right < n) {
            if (s.charAt(left--) == s.charAt(right++)) {
                len += 2;
            } else {
                return len;
            }
        }
        return len;
    }
    
}

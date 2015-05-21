package org.work.DP;

import java.util.ArrayList;

public class PalindromePartition {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        if (s == null || s.length() == 0) {
            ret.add(new ArrayList<String>());
            return ret;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //substrings which length == 1
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        // substrings which length == 2
        for (int i = 0; i < n - 1; i++)
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? true : false;
        // substrings which length >= 3;
        int j = 0;
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : false;
            }
        }
        return doPartition(s, 0, dp);
    }
    
    private ArrayList<ArrayList<String>> doPartition(String s, int start, boolean[][] dp) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        if (start == s.length()) {
            ret.add(new ArrayList<String>());
            return ret;
        }
        int n = s.length();
        for (int i = start; i < n; i++) {
            if (dp[start][i]) {
                ArrayList<ArrayList<String>> rightPart = doPartition(s, i + 1, dp);
                for (ArrayList<String> item : rightPart) {
                    item.add(0, s.substring(start, i + 1));
                    ret.add(item);
                }
            }
        }
        return ret;
    }
}

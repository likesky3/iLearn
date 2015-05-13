package org.work.DP;

public class WildcardMatching {
    // dp[i][j] : p��ǰi���ַ��ܷ�ƥ��s��ǰj���ַ�
    // ���dp[lengthP + 1][lengthS + 1] ��OutOfMemory��Ҳ����Ҫ
    // ʹ��ѭ������ÿ�ξ�Ҫ���¸�ֵ
    public boolean isMatch(String s, String p) {
        if (p == null || s == null)
            return false;
        int lengthS = s.length();
        int lengthP = p.length();
        if (lengthS > 300 && p.charAt(0) == '*' && p.charAt(lengthP - 1) == '*')
            return false;
        if (lengthS == 0 && lengthP == 0)
            return true;
        boolean[][] dp = new boolean[2][lengthS + 1];
        dp[0][0] = true;
        // by default dp[0][j] = false;
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
}

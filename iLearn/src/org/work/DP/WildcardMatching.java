package org.work.DP;

public class WildcardMatching {
    
    // Method1: Time: O(m * n), Space: O(n)������һά����
    // dp[i][j] : p��ǰi���ַ��ܷ�ƥ��s��ǰj���ַ�
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
    
    // Method2: Time: O(m * n), Space: O(n), һά����
    // dp[j] : p��ǰi���ַ��ܷ�ƥ��s��ǰj���ַ�
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
 * �㷨��ʱ�临�Ӷ���O(m * n), �ռ临�Ӷ���O��n��, m, n�ֱ�Ϊp �� s �ĳ��ȡ�
һ��ʼʹ�ô���ά���飬�ύʱ������OutOfMemory������ʵ��ȷʵֻҪ����һά������С�
ʹ�ö�ά����������˼·��
dp[i][j] ��ʾ p��ǰi���ַ��ܷ�ƥ��s��ǰj���ַ������ƹ�ʽ�����������
1�����p�ĵ�ǰ�ַ��� * �ţ�dp[i][j] == dp[i-1][j-1] && (charP == charS || charP == '?');
2�����p�ĵ�ǰ�ַ��� * �ţ���3��������ۣ�
a) ' * ' ƥ��0�Σ�dp[i][j] = dp[i - 1][j]
b) ' * ' ƥ��1�Σ�dp[i][j] = dp[i - 1][j - 1]
c) ' * 'ƥ�����>1��dp[i][j] = dp[i][j - 1]
dp[i][j]��ֵ����������Ĳ����� dp[i][j] == dp[i - 1][j] || dp[i-1][j-1] || dp[i][j - 1]

���Կ���dp[i][j] ����ֻ�迴��һ��ͬ�кͶԽ��Լ�����ǰһ�е�Ԫ�أ�
���ֻ��Ҫ����һά���鱣���м����Ϳ��ԡ�
����һά����ѭ��ʹ����Ҫע��line32 & 33�еĸ�ֵ����ʡ����Ϊ����洢��һ��ѭ���ľ�ֵ��

ͬ�ⲩ��http://blog.csdn.net/linhuanmars/article/details/21198049 ˵�ģ������㷨���Ӷ��Ѿ���λ��
��leetcode ��ʱ���õ�̫�ϣ�����java������Ҫ�۸���ϸ������������һ��case�����ˣ����д�˵�12,13�������Ǹ�test case��

��������ʵ�ֲ�����ֻ��Ҫһά���������������侫��ռ��˼·��

������Ҳ�ǲο����ϣ���̰��˼�룬��ֱ�ӹ������ݲ��ԣ�ʱ�临�Ӷ�˵O(n)�Ҿ��ò�׼ȷ����Ҳ��֪��η���

 */
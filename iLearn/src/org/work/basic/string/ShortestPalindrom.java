package org.work.basic.string;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Given a string S, you are allowed to convert it to a palindrome 
 * by adding characters in front of it. 
 * Find and return the shortest palindrome you can find 
 * by performing this transformation.
 * 特别注意是题目限定在字符串前面加额外字符使之成为回文
 * 因此按照题目限定 abcgg 对应的最短回文是ggabcgg, 而不是abcggcba
 * */
public class ShortestPalindrom {

//    public static void main(String[] args) {
//        ShortestPalindrom obj = new ShortestPalindrom();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 30000; i++) {
//            sb.append('a');
//        }
//        String s = sb.toString();
//        long start = System.currentTimeMillis();
//        obj.shortestPalindrome(s);
//        System.out.println(System.currentTimeMillis() - start);
//        
//        start = System.currentTimeMillis();
//        obj.shortestPalindrome1(s);
//        System.out.println(System.currentTimeMillis() - start);
//        
//        start = System.currentTimeMillis();
//        obj.shortestPalindrome2(s);
//        System.out.println(System.currentTimeMillis() - start);
//    }
    public void test(StringBuilder sb) {
        sb.append("abc");
    }
    @Test
    public void testShortestPalindrome() {
        ShortestPalindrom obj = new ShortestPalindrom();
        Assert.assertEquals("ggbcabcgg", obj.shortestPalindrome("abcgg"));
        Assert.assertEquals("aba", obj.shortestPalindrome("ba"));
        Assert.assertEquals("abcdadcba", obj.shortestPalindrome("adcba"));
        Assert.assertEquals("cccaccc", obj.shortestPalindrome("cccaccc"));
//        Assert.assertEquals("adcbabcda", obj.shortestPalindrome("adcba"));
//        Assert.assertEquals("bab", obj.shortestPalindrome("ba"));
        Assert.assertEquals("dcabbacd", obj.shortestPalindrome("abbacd"));
        Assert.assertEquals("caac", obj.shortestPalindrome("aac"));
        Assert.assertEquals("cac", obj.shortestPalindrome("cac"));
    }
    
    public String shortestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;
        int center = (s.length() - 1) / 2;
        String result = null;
        for (int i = center; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                if ((result = buildPalin(s, i, i + 1)) != null)
                    return result;
            }
            if ((result = buildPalin(s, i, i)) != null)
                return result;
        }
        return null;
    }
    private String buildPalin(String s, int left, int right) {
        int n = s.length();
        while (left >= 0 && right < n) {
            if (s.charAt(left) != s.charAt(right))
                break;
            left--;
            right++;
        }
        if (left >= 0)
            return null;
        StringBuilder extra = new StringBuilder(s.substring(right));
        return extra.reverse().append(s).toString();
    }
    
    public String shortestPalindrome3(String s) {
        if (s == null || s.length() < 2)
            return s;
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String bestAns = "";
        
        int right = n / 2;
        int left = right - 1;
        String ans;
        while (true) {
            // round 1: 从中心开始，左右开弓，找到的首个以当前字符为中心且边界之前左右都相等即是这种找法下最短回文串中心点
            if (left >= 0) {
                ans = wrap(s, left, left, n, minLen);
                if (ans != null && ans.length() < minLen) {
                    minLen = ans.length();
                    bestAns = ans;
                    if (minLen == n)
                        return bestAns;
                }
                if (left + 1 < n) {
                    ans = wrap(s, left, left + 1, n, minLen);
                    if (ans != null && ans.length() < minLen) {
                        minLen = ans.length();
                        bestAns = ans;
                        if (minLen == n)
                            return bestAns;
                    }
                }
                left--;
            }
            if (right < n) {
                ans = wrap(s, right, right, n, minLen);
                if (ans != null && ans.length() < minLen) {
                    minLen = ans.length();
                    bestAns = ans;
                    if (minLen == n)
                        return bestAns;
                }
                if (right + 1 < n) {
                    ans = wrap(s, right, right + 1, n, minLen);
                    if (ans != null && ans.length() < minLen) {
                        minLen = ans.length();
                        bestAns = ans;
                        if (minLen == n)
                            return bestAns;
                    }
                }
                right++;
            }
            if (left < 0 && right >= n)
                break;
        }
        return bestAns;
    }
    
    public String shortestPalindrome2(String s) {
        if (s == null || s.length() < 2)
            return s;
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String bestAns = "";
        for (int i = 0; i < n - 1; i++) {
            // round 1: 从中心开始，左右开弓，找到的首个以当前字符为中心且边界之前左右都相等即是这种找法下最短回文串中心点
            String ans = wrap(s, i, i, n, minLen);
            if (ans != null && ans.length() < minLen) {
                minLen = ans.length();
                bestAns = ans;
            }
            // round 2: 以相邻两个字符为中心向外辐射查找最短回文串
            ans = wrap(s, i, i + 1, n, minLen);
            if (ans != null && ans.length() < minLen) {
                minLen = ans.length();
                bestAns = ans;
            }
        }
        return bestAns;
    }
    
    public String wrap (String s, int left, int right, int n, int minLen) { 
        int len = getPalin(s, left, right, n);
        if (len == n) {
            return s;
        } else if (len > 0 && len < minLen) {
            int leftLen = left != right ? (left + 1) : left;
            int rightLen = left != right ? (n - left - 1) : (n - right - 1);
            return buildPalin(s, leftLen, rightLen, n);
        }
        return null;
    }
    
    // 方向和leetcode test case不一致
    public String shortestPalindrome1(String s) {
        if (s == null || s.length() < 2)
            return s;
        int n = s.length();
        // round 1: 从中心开始，左右开弓，找到的首个以当前字符为中心且边界之前左右都相等即是这种找法下最短回文串中心点
        int left = n / 2, right = left + 1;
        int minLen = -1;
        String ans = null;
        while (left >= 0 && right < n) {
            minLen = getPalin(s, left, left, n);
            if (minLen == n)
                return s;
            if (minLen > 0) {
                ans = buildPalin(s, left, n - left - 1, n);
                break;
            } else {
                minLen = getPalin(s, right, right, n);
                if (minLen > 0) {
                    ans = buildPalin(s, right, n - right - 1, n);
                    break;
                }
            }
            left--;
            right++;
        }
        if (ans == null && left >= 0) {
            // minLen = getPalin(s, left, left, n);
            ans = buildPalin(s, left, n - left - 1, n);
        }
        
        // round 2: 以相邻两个字符为中心向外辐射查找最短回文串
        int mid = n / 2;
        for (int i = 0; i < n - 1; i++) {
            int len = getPalin(s, i, i + 1, n);
            if (len == n)
                return s;
            if (len > 0 && len < minLen) {
                int leftLen = i + 1, rightLen = n - i - 1;
                ans = buildPalin(s, leftLen, rightLen, n);
            }
        }
        return ans;
    }
    // 以left, right为中心向外辐射，某一方向能到达边界则可以以(left, right)为中心创建一个回文串
    private int getPalin(String s, int left, int right, int n) {
        int i = left, j = right;
        while (i >= 0 && j < n) {
            if (s.charAt(i--) != s.charAt(j++))
                return -1;
        }
        if (i > 0) {
            return left == right ? (2 * left + 1) : (2 * left + 2);
        } else {
            return left == right ? (2 * (n - right) - 1) : (2 * (n - right));
        }
    }
    private String buildPalin(String s, int leftLen, int rightLen, int n) {
        String ans = null;
        if (leftLen >= rightLen) {// left part is longer
            StringBuilder extraLeft = new StringBuilder(s.substring(0, leftLen - rightLen));
            StringBuilder tmp = new StringBuilder(s);
            tmp.append(extraLeft.reverse().toString());
            ans = tmp.toString();
        } else {
            StringBuilder extraRight = new StringBuilder(s.substring(n - (rightLen - leftLen), n));
            StringBuilder tmp = new StringBuilder(extraRight.reverse().toString());
            tmp.append(s);
            ans = tmp.toString();
        }
        return ans;
    }
    
}

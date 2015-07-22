package org.work.twoPointers;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.Assert;


public class LongestSubstringWithAtMost2DistinctChars {
    @Test
    public void testSolution() {
        Solution obj = new Solution();
        Assert.assertEquals(4, obj.longest2("abaa"));
        Assert.assertEquals(4, obj.longest2("abaccc"));
        Assert.assertEquals(4, obj.longestK("abaa", 2));
        Assert.assertEquals(4, obj.longestK("abaccc", 2));
        Assert.assertEquals(6, obj.longestK("abacccd", 3));
    }
}

class Solution {
    public int longest2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int start = 0;
        int N = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(2);
        map.put(s.charAt(0), 0);
        int max = 1;
        for (int i = 1; i < N; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c) && map.size() == 2) {
                // update left bound of sliding window
                int mostLeftEndPos = i - 1;
                char toBeRemoved = ' ';
                for (Character key : map.keySet()) {
                    if (map.get(key) < mostLeftEndPos) {
                        mostLeftEndPos = map.get(key);
                        toBeRemoved = key;
                    }
                }
                start = mostLeftEndPos + 1;
                map.remove(toBeRemoved);
            }
            map.put(c, i);
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
    //k 比较大时遍历map就比较低效，不如从start开始寻找第一个最后位置跑出窗口的字符
    public int longestK(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;
        int N = s.length();
        int start = 0;
        int max = 0;
        
        // c: letter; value: the number of occurrences.  
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
                while (map.size() > k) {
                    char startChar = s.charAt(start++);
                    int count = map.get(startChar);
                    if (count > 1) {
                        map.put(c, count - 1);
                    } else {
                        map.remove(startChar);
                    }
                }
           }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}

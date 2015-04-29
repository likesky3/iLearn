package org.work.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// This problem has a more popular name: Longest Increasing Subsequence(LIS)
class MaximumMonotoneSubsequence {
    // O(N^2)
    public int getMaxmumMonotoneSubsequence(String s) {
        if (s == null || s.equals(""))
            return 0;
        int lengthS = s.length();
        int[] dp = new int[lengthS];
        dp[0] = 1;
        int maxLIS = dp[0];
        for (int i = 1; i < lengthS; i++) {
            char currChar = s.charAt(i);
            int currVal = 1;
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                currVal = currChar >= s.charAt(j) ? dp[j] + 1 : 1;
                dp[i] = Math.max(currVal, dp[i]);
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }
        return maxLIS;
    }
    // O(N^2), 比方法1少些计算，但复杂度数量级没有变化
    public int getMaxmumMonotoneSubsequence2(String s) {
        if (s == null || s.equals(""))
            return 0;
        int lengthS = s.length();
        int[] dp = new int[lengthS]; // dp[i]表示以s[i]为结尾的LIS的长度
        dp[0] = 1;
        int[] maxV = new int[lengthS + 1]; // maxV[i]表示长度为i的LIS的最大元素的最小值
        maxV[0] = Integer.MIN_VALUE;
        maxV[1] = s.charAt(0);
        int maxLIS = 1;
        for (int i = 1; i < lengthS; i++) {
            char currChar = s.charAt(i);
            dp[i] = 1;
            int j = maxLIS;
            for (; j >= 1; j--) { //找到最大元素最小值不大于currChar的LIS的长度
                if (currChar >= maxV[j]) {
                    dp[i] = j + 1;
                    break;
                }
            }
            if (dp[i] > maxLIS) { // 更新全局LIS最大值
                maxLIS = dp[i];
                maxV[maxLIS] = currChar;
                
            } else if (currChar < maxV[dp[i]]) { // 更新长度为dp[i]的LIS的maxV
                maxV[dp[i]] = currChar;
            }
        }
        return maxLIS;
    }
    // O(NlogN)
    public int getMaxmumMonotoneSubsequence3(String s) {
        if (s == null || s.equals(""))
            return 0;
        int lengthS = s.length();
        int[] dp = new int[lengthS + 1]; //dp[i]表示长度为i的LIS最大元素的最小值
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= lengthS; i++ ) {
            dp[i] = Integer.MAX_VALUE; 
            dp[upperBound(dp, i + 1, s.charAt(i - 1))] = s.charAt(i - 1); // logN
        }
        return lowerBound(dp, dp.length, Integer.MAX_VALUE) - 1;
    }
    // 返回一个非递减序列中第一个大于等于key值的位置
    public int lowerBound(int[] nonDecreasingArray, int size ,int key) {
        int first = 0, mid;
        int len = size, half;
        while (len > 0) {
            half = len >> 1;
            mid = first + half;
            if (nonDecreasingArray[mid] < key) { // search in right half
                first = mid + 1;
                len = len - half - 1;
            } else { // search in left half
                len = half;
            }
        }
        return first;
    }
    // 返回一个非递减序列中第一个大于key值的位置
    public int upperBound(int[] nonDecreasingArray, int size, int key) {
        int first = 0, mid;
        int len = size, half;
        while(len > 0) {
            half = len >> 1;
            mid = first + half;
            if (nonDecreasingArray[mid] <= key) {
                first = mid + 1;
                len = len - half - 1;
            } else {
                len = half;
            }
        }
        return first;
    }
}
public class MaximumMonotoneSubsequenceTest {
    @Test
    public void testGetMaxmumMonotoneSubsequence() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
//        assertEquals(0,obj.getMaxmumMonotoneSubsequence(""));
        assertEquals(0,obj.getMaxmumMonotoneSubsequence(null));
        assertEquals(0,obj.getMaxmumMonotoneSubsequence(""));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence("amnopbcq"));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence("amnopbcdef"));
        assertEquals(4,obj.getMaxmumMonotoneSubsequence("amnbn"));
        assertEquals(3,obj.getMaxmumMonotoneSubsequence("bcda"));
    }
    
    @Test
    public void testGetMaxmumMonotoneSubsequence2() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
        assertEquals(0,obj.getMaxmumMonotoneSubsequence2(null));
        assertEquals(0,obj.getMaxmumMonotoneSubsequence2(""));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence2("amnopbcq"));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence2("amnopbcdef"));
        assertEquals(4,obj.getMaxmumMonotoneSubsequence2("amnbn"));
        assertEquals(3,obj.getMaxmumMonotoneSubsequence2("bcda"));
    }
    
    @Test
    public void testGetMaxmumMonotoneSubsequence3() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
        assertEquals(0,obj.getMaxmumMonotoneSubsequence3(null));
        assertEquals(0,obj.getMaxmumMonotoneSubsequence3(""));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence3("amnopbcq"));
        assertEquals(6,obj.getMaxmumMonotoneSubsequence3("amnopbcdef"));
        assertEquals(4,obj.getMaxmumMonotoneSubsequence3("amnbn"));
        assertEquals(3,obj.getMaxmumMonotoneSubsequence3("bcda"));
    }
    
    @Test
    public void testLowerBound() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
        int[] array = {0, 1};
        assertEquals(1, obj.lowerBound(array, array.length, 1));
        int[] array2 = {1,2,2,4};
        assertEquals(3, obj.lowerBound(array2, array2.length, 3)); //好好琢磨
        assertEquals(1, obj.lowerBound(array2, array2.length, 2));
    }
    
    @Test
    public void testUpperBound() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
        int[] array = {0, 1};
        assertEquals(2, obj.upperBound(array, array.length, 1));
        int[] array2 = {1,2,2,4};
        assertEquals(3, obj.upperBound(array2, array2.length, 3)); //好好琢磨
        assertEquals(3, obj.upperBound(array2, array2.length, 2));
    }
}

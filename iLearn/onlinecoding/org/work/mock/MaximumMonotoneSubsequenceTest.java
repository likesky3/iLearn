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
    // O(N^2), �ȷ���1��Щ���㣬�����Ӷ�������û�б仯
    public int getMaxmumMonotoneSubsequence2(String s) {
        if (s == null || s.equals(""))
            return 0;
        int lengthS = s.length();
        int[] dp = new int[lengthS]; // dp[i]��ʾ��s[i]Ϊ��β��LIS�ĳ���
        dp[0] = 1;
        int[] maxV = new int[lengthS + 1]; // maxV[i]��ʾ����Ϊi��LIS�����Ԫ�ص���Сֵ
        maxV[0] = Integer.MIN_VALUE;
        maxV[1] = s.charAt(0);
        int maxLIS = 1;
        for (int i = 1; i < lengthS; i++) {
            char currChar = s.charAt(i);
            dp[i] = 1;
            int j = maxLIS;
            for (; j >= 1; j--) { //�ҵ����Ԫ����Сֵ������currChar��LIS�ĳ���
                if (currChar >= maxV[j]) {
                    dp[i] = j + 1;
                    break;
                }
            }
            if (dp[i] > maxLIS) { // ����ȫ��LIS���ֵ
                maxLIS = dp[i];
                maxV[maxLIS] = currChar;
                
            } else if (currChar < maxV[dp[i]]) { // ���³���Ϊdp[i]��LIS��maxV
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
        int[] dp = new int[lengthS + 1]; //dp[i]��ʾ����Ϊi��LIS���Ԫ�ص���Сֵ
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= lengthS; i++ ) {
            dp[i] = Integer.MAX_VALUE; 
            dp[upperBound(dp, i + 1, s.charAt(i - 1))] = s.charAt(i - 1); // logN
        }
        return lowerBound(dp, dp.length, Integer.MAX_VALUE) - 1;
    }
    // ����һ���ǵݼ������е�һ�����ڵ���keyֵ��λ��
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
    // ����һ���ǵݼ������е�һ������keyֵ��λ��
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
        assertEquals(3, obj.lowerBound(array2, array2.length, 3)); //�ú���ĥ
        assertEquals(1, obj.lowerBound(array2, array2.length, 2));
    }
    
    @Test
    public void testUpperBound() {
        MaximumMonotoneSubsequence obj = new MaximumMonotoneSubsequence();
        int[] array = {0, 1};
        assertEquals(2, obj.upperBound(array, array.length, 1));
        int[] array2 = {1,2,2,4};
        assertEquals(3, obj.upperBound(array2, array2.length, 3)); //�ú���ĥ
        assertEquals(3, obj.upperBound(array2, array2.length, 2));
    }
}

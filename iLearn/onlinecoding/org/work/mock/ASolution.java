package org.work.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Coin {
    int coin;
    int count;
}

public class ASolution {

    public static void main(String[] args) {
        // int[] dp = new int[0];
        // System.out.println(dp.length);
        // int[] orgin = new int[2];
        // orgin[0] = 1;
        // orgin[1] = 2;
        // int[] copy = orgin.clone();
        // copy[0] = 2;
        // print(orgin);
        // print(copy);

    }

    public static void print(int[] array) {
        for (int o : array)
            System.out.print(o + " ");
        System.out.println();
    }

    // Possible coin: 1
    int findMinimumCoinNum(int sum) {
        if (sum <= 0)
            return 0;
        return sum;
    }

    // Possible coin: 1, 3
    int findMinimumCoinNum2(int sum) {
        if (sum <= 0)
            return 0;
        if (sum < 3)
            return sum;
        int[] dp = new int[sum + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= sum; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 3]) + 1;
        }
        return dp[sum];
    }

    // 贪婪法
    int findMinimumCoinNum3(int sum) {
        if (sum <= 0)
            return 0;
        int ret = sum / 3;
        ret += sum % 3;
        return ret;
    }

    // Possible coin: 1, 3, 5
    int findMinimumCoinNum4(int sum) {
        if (sum <= 0)
            return 0;
        int[] dp = new int[sum + 1];
        dp[0] = 0;
        for (int i = 1; i <= sum; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i >= 3)
                dp[i] = Math.min(dp[i], dp[i - 3] + 1);
            if (i >= 5)
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
        }
        return dp[sum];
    }

    Coin[] findMinimumCoinNum7(int sum, int[] possibleCoins) {
        if (sum <= 0)
            return null;
        int n = possibleCoins.length;
        int[][] data = new int[sum + 1][possibleCoins.length];
        int[] dp = new int[sum + 1];
        dp[0] = 0;
        for (int i = 1; i <= sum; i++) {
            data[i] = new int[possibleCoins.length];
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int last = i - possibleCoins[j];
                if (i >= possibleCoins[j] && dp[i] > dp[last] + 1) {
                    dp[i] = dp[last] + 1;
                    // data[i] = data[last]; //should not change data[last]
                    // for (int k = 0; k < n; k++)
                    // data[i][k] = data[last][k];
                    data[i] = data[last].clone();
                    data[i][j] += 1;
                }
            }
        }
     
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (data[sum][i] > 0)
                size++;
        }
        if (size == 0)
            return null;
        Coin[] result = new Coin[size];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (data[sum][i] != 0) {
                Coin c = new Coin();
                c.coin = possibleCoins[i];
                c.count = data[sum][i];
                result[j++] = c;
            }
        }
        return result;
    }

    // possible coins: 3, 4, 5, sum 8, minimum types = 1
    int findMinimumCoinTypes(int sum, int[] possibleCoins) {
        for (int tries = 1; tries < possibleCoins.length; tries++) {// O(N)*T(recur())
            if (recur(sum, possibleCoins, 0, tries))
                return tries;
        }
        return -1;
    }

    // recur（）调用深度最大是N， 一次调用最多会递归调用N * sum次recur()自身
    // T(recur()) = (sum* N) ^N
    boolean recur(int sum, int[] possibleCoins, int start, int tries) {
        if (sum == 0)
            return true;
        if (tries == 0)
            return false;
        for (int i = start; i < possibleCoins.length; i++) {
            if (sum >= possibleCoins[i]) {
                int max = sum / possibleCoins[i];
                for (int j = 1; j <= max; j++) {
                    if (recur(sum - j * possibleCoins[i], possibleCoins, i + 1, tries - 1))
                        return true;
                }
            }
        }
        return false;
    }

    // int findMinimumCoinNum(int sum, int[] possibleCoins);
    // dp[i] |= dp[i-possibleCoins[j]]
    // O(sum * N)
    boolean isComposible(int sum, int[] possibleCoins) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= sum; i++) {
            for (int j = 0; j < possibleCoins.length; j++) {
                dp[i] = dp[i - possibleCoins[j]];
                if (dp[i])
                    break;
            }
        }
        return dp[sum];
    }

    // 1, 3, 5 k=1, k=2, k=3
    boolean isComposibleByKTypes(int sum, int[] possibleCoins, int k) {
        for (int i = 0; i <= possibleCoins.length - k; i++) {
            ArrayList<ArrayList<Integer>> kGroups = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> coins = new ArrayList<Integer>();
            enumCoins(possibleCoins, i, kGroups, coins, k);
            // build(possibleCoins, i, sub, 0);
            for (ArrayList<Integer> kGroup : kGroups) {
                int[] sub = new int[kGroup.size()];
                for (int j = 0; j < sub.length; j++)
                    sub[j] = kGroup.get(j);
                if (isComposible(sum, sub))
                    return true;
            }
        }
        return false;
    }

    // void enumCoins(int[] possibleCoins, int start,
    // ArrayList<ArrayList<Integer>> res, int k) {
    void enumCoins(int[] possibleCoins, int start, ArrayList<ArrayList<Integer>> res,
            ArrayList<Integer> coins, int k) {
        if (k == 0) {
            ArrayList<Integer> copy = (ArrayList<Integer>) coins.clone();
            res.add(copy);
            return;
        }
        int end = possibleCoins.length - k;
        for (int i = start; i <= end; i++) {// O(N)
            coins.add(possibleCoins[i]);
            enumCoins(possibleCoins, i + 1, res, coins, k - 1);
            coins.remove(coins.size() - 1);
        }
    }

    void build(int[] possibleCoins, int start, int[] result, int pos) {
        if (pos == result.length)
            return;
        int end = possibleCoins.length - result.length + pos;
        for (int i = start; i <= end; i++) {
            result[pos] = possibleCoins[i];
            build(possibleCoins, i + 1, result, pos + 1);
        }
    }

    // O(sum * N * 2^N)
    int findMinimumCoinType2(int sum, int[] possibleCoins) {
        for (int i = 1; i <= possibleCoins.length; i++) { // binary search O(2^N
                                                          // * sum * log(N))
            if (isComposibleByKTypes(sum, possibleCoins, i))
                return i;
        }
        return 0;
    }

}

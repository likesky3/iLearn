package org.work.DP;

public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);
        int[] dp = new int[n - 1];
        int[] dp2 = new int[n - 1];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        dp2[0] = nums[n - 1];
        dp2[1] = Math.max(dp2[0], nums[0]);
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        for (int i = 1; i < n - 2; i++) {
            dp2[i + 1] = Math.max(dp2[i - 1] + nums[i], dp2[i]);
        }
        return Math.max(dp[n - 2], dp2[n - 2]);
    }
}

package org.work.math;

public class NextPermutation {

    public static void main(String[] args) {

    }
    
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int N = nums.length;
        int i = N - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i == 0) {
            reverse(nums, 0, N - 1);
            return;
        }
        int j = i + 1;
        while (j < N - 1 && nums[j + 1] > nums[i])
            j++;
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
        reverse(nums, i + 1, N - 1);
    }
    public void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int tmp = nums[j];
            nums[j--] = nums[i];
            nums[i++] = tmp;
        }
    }

}

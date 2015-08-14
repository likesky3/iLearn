package org.work.twoPointers;



public class TwoSumIISorted {

    public static void main(String[] args) {
        int[] nums1 = {0, 0, 1, 2};
        TwoSumIISorted obj = new TwoSumIISorted();
//        int[] result = obj.twoSum(nums1, 0);
//        System.out.println(result[0] + " " + result[1]);
//        result = obj.twoSum(nums1, 1);
//        System.out.println(result[0] + " " + result[1]);
//        result = obj.twoSum(nums1, 2);
//        System.out.println(result[0] + " " + result[1]);
//        result = obj.twoSum(nums1, 3);
//        System.out.println(result[0] + " " + result[1]);
        int[] nums = {5, 1, 3};
        System.out.println(obj.search(nums, 5));
    }
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                result[0] = i;
                result[1] = j;
                break;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
    
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (right - left > 1) {
            mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                if (target < nums[left]) left = mid + 1;
                else if (target < nums[mid]) right = mid - 1;
                else if (target > nums[mid]) left = mid + 1;
                else return mid;
            } else {
                if (target >= nums[left]) right = mid - 1;
                else if (target > nums[mid]) left = mid + 1; 
                else if (target < nums[mid]) right = mid - 1;
                else return mid;
            }
        }
        return nums[left] == target ? left : (nums[right] == target ? right : -1);
    }
    
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (right - left > 1) {
            mid = left + (right - left) / 2;
            if (nums[right] <= nums[left] && nums[left] <= nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] < nums[right] ? nums[left] : nums[right];
    }
}

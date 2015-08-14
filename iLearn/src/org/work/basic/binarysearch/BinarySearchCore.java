package org.work.basic.binarysearch;

import junit.framework.Assert;
import org.junit.Test;

public class BinarySearchCore {

    @Test 
    public void testBinarySearch() {
        int[] nums = {0,1,1,1,2};
        Assert.assertEquals(2, binarySearch(nums, 1));
        Assert.assertEquals(1, binarySearchFirst(nums, 1));
        Assert.assertEquals(3, binarySearchLast(nums, 1));
        int[] nums1 = {1};
        Assert.assertEquals(0, binarySearch(nums1, 1));
        Assert.assertEquals(0, binarySearchFirst(nums1, 1));
        Assert.assertEquals(0, binarySearchLast(nums1, 1));
        int[] nums2 = {1,1};
        Assert.assertEquals(0, binarySearch(nums2, 1));
        Assert.assertEquals(0, binarySearchFirst(nums2, 1));
        Assert.assertEquals(1, binarySearchLast(nums2, 1));
        
    }
    // 查找target在nums[]中的下标，若有重复，返回任意一个即可，若找不到返回 -1
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid])
                right = mid - 1;
            else 
                left = mid + 1;
        }
        return -1;
    }
    // 查找target在nums[]中第一次出现的下标，若找不到返回-1
    public int binarySearchFirst(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left < right) { // 此处不能有等号，否则可能在right=mid处死循环，考虑case：[1]， 1
            mid = left + ((right - left) >> 1);
            if (target > nums[mid])
                left = mid + 1;
            else
                right = mid;
        }
        if (nums[left] == target)
            return left;
        return -1;
    }
    // 查找target在nums[]中最后一次出现的下标，若找不到返回-1
    public int binarySearchLast(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) { // 循环只处理元素个数大于两个的情况，两个以下元素时可能在left=mid处死循环(两个元素时left==mid)，考虑case:[1,1],1
            mid = left + ((right - left) >> 1);
            if (target < nums[mid])
                right = mid - 1;
            else
                left = mid;
        }
        if (nums[right] == target)
            return right;
        else if(nums[left] == target)
            return left;
        return -1;
    }
}

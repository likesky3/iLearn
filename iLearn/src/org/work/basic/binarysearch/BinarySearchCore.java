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
    // ����target��nums[]�е��±꣬�����ظ�����������һ�����ɣ����Ҳ������� -1
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
    // ����target��nums[]�е�һ�γ��ֵ��±꣬���Ҳ�������-1
    public int binarySearchFirst(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left < right) { // �˴������еȺţ����������right=mid����ѭ��������case��[1]�� 1
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
    // ����target��nums[]�����һ�γ��ֵ��±꣬���Ҳ�������-1
    public int binarySearchLast(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) { // ѭ��ֻ����Ԫ�ظ��������������������������Ԫ��ʱ������left=mid����ѭ��(����Ԫ��ʱleft==mid)������case:[1,1],1
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

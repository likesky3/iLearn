package org.work.leetcode;

//二分查找本质就是逐步缩小比较范围，最后缩小到一个数上
public class E102_SearchForARange {
	public int[] searchRange(int[] A, int target) {
        int[] res = {-1, -1};
        if(A == null || A.length == 0)
            return res;
        
        //Deferred detection of equality，减少判断次数
        //search lower bound，搜目标数在数组中出现的最小下标。相等时继续搜左半段并包含相等的那个元素
        int left = 0, right = A.length;
        while(left < right){//at least 2 numbers, loop ends when left == right
            int mid = left + (right - left >> 1);
            if(A[mid] < target)
                left = mid + 1;
            else
                right = mid;//right = mid - 1, will overflow when right - left == 1
        }
        //after loop ends, left == right 
        //if left == A.length, indicates that target > A[n - 1]
        //A[left] <= target <= A[right] 
        if(left == A.length || A[left] != target)
            return res;
        res[0] = left;
        
        //search high bound，搜数组中目标数的最大下标。相等时继续搜右半段，不包含相等的元素
        right = A.length;
        while(left < right){
            int mid = left + (right - left >> 1);
            if(A[mid] > target)
                right = mid;
            else
                left = mid + 1;//不能写为left = mid, 否则会死循环，例子【8,9】，target = 8，因为当right 和left相差1时，left == mid
        }
        res[1] = right - 1;
        
        return res;
    }
	
	//version 2, right最大值为A.length - 1
	public int[] searchRange2(int[] A, int target) {
        int[] res = {-1, -1};
        if(A == null || A.length == 0)
            return res;
        //search lower bound
        int left = 0, right = A.length - 1;
        while(left < right){//at least 2 numbers, loop ends when left == right
            int mid = left + (right - left >> 1);
            if(A[mid] < target)
                left = mid + 1;
            else
                right = mid;//right = mid - 1, will overflow when right - left == 1
        }
        if(A[left] != target)
            return res;
        res[0] = left;
        
        //search high bound
        right = A.length - 1;
        while(left < right){
            int mid = left + (right - left >> 1);
            if(A[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }
        if(A[left] == target)
            res[1] = left;
        else
            res[1] = left - 1;
        
        return res;
    }
}

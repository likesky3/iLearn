package org.work.basic.sort_search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsDuplicateIII {
 // Method2: »¬¶¯´°¿Ú + TreeSet
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 1 || t < 0)
            return false;
        SortedSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            SortedSet<Long> subset = set.subSet((long)nums[i] - t, (long)nums[i] + t + 1);
            if (!subset.isEmpty())
                return true;
            if (i >= k)
                set.remove((long)nums[i - k]);
            set.add((long)nums[i]);
        }
        return false;
    }
    
    // Method 1
    class WrappedNum{
        int value;
        int idx;
        public WrappedNum(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    class WrappedNumComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            int value1 = ((WrappedNum)o1).value;
            int value2 = ((WrappedNum)o2).value;
            if (value1 > 0 && value2 > 0 || (value1 < 0 && value2 < 0))
                return value1 - value2;
            else if (value1 > 0)
                return 1;
            else
                return -1;
        }
        
    }
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2)
            return false;
        WrappedNum[] numsWrapped = new WrappedNum[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsWrapped[i] = new WrappedNum(nums[i], i);
        }
        Arrays.sort(numsWrapped, new WrappedNumComparator());
        for (int i = 1; i < nums.length; i++) {
            int currValue = numsWrapped[i].value;
            for (int j = i - 1; j >= 0 && (currValue - numsWrapped[j].value) >= 0 && (currValue - numsWrapped[j].value) <= t; j--) {
                if ((Math.abs(numsWrapped[i].idx - numsWrapped[j].idx)) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}

package org.work.basic.array;

import java.util.ArrayList;
import java.util.List;

public class MajorityNumberII {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MajorityNumberII obj = new MajorityNumberII();
        int[] nums = {1,1,1,3,3,2,2,2};
        List<Integer> ret = obj.majorityElement(nums);
        for (int i : ret) {
            System.out.println(i);
        }
        StringBuilder sb = new StringBuilder();
    }

    public List<Integer> majorityElement(int[] nums) {
        // Moore voting
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return result;
        int N = nums.length;
        int cand1 = nums[0], counter1 = 1;
        int i = 1;
        while (i < N) {
            if (cand1 == nums[i]) {
                counter1++;
                i++;
            } else {
                break;
            }
        }
        if (i == N) {
            result.add(cand1);
            return result;
        }
        int cand2 = nums[i++], counter2 = 1;
        while (i < N) {
            if (nums[i] == cand1) {
                cand1 = nums[i];
                counter1++;
            } else if (nums[i] == cand2) {
                cand2 = nums[i];
                counter2++;
            } else {
                if (counter1 == 0) {
                    cand1 = nums[i];
                    counter1++;
                } else if (counter2 == 0) {
                    cand2 = nums[i];
                    counter2++;
                } else {
                    counter1--;
                    counter2--;
                }
            }
            i++;
        }
        
        int c1 = 0, c2 = 0;
        for (int j = 0; j < N; j++) {
            if (cand1 == nums[j])
                c1++;
            else if (cand2 == nums[j])
                c2++;
        }
        if (c1 > N / 3) 
            result.add(cand1);
        if (c2 > N / 3) 
            result.add(cand2);
        return result;
    }
}

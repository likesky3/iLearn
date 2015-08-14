package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        Permutation obj = new Permutation();
        int[] nums = {3,3,0,0,2,3,2};
        List<List<Integer>> result = obj.permuteUnique(nums);
        System.out.println(result.size());
        System.out.println(counter);
    }
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return result;
        Arrays.sort(nums);
        recur(nums, new boolean[nums.length], new ArrayList<Integer>(), result);
        return result;
    }
    public void recur(int[] nums, boolean[] used, List<Integer> item, List<List<Integer>> result) {
        if (item.size() == nums.length) {
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1])
                continue;
            if (!used[i]) {
                item.add(nums[i]);
                used[i] = true;
                recur(nums, used, item, result);
                used[i] = false;
                item.remove(item.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0)
            return result;
        Arrays.sort(num);
        return permutate(num, 0);
    }
    static int counter = 0;
    private List<List<Integer>> permutate(int[] num, int start){
        counter++;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(start == num.length - 1){
            List<Integer> item = new ArrayList<Integer>();
            item.add(num[start]);
            result.add(item);
            return result;
        }
        
        int origin = num[start];
        for(int i = start; i < num.length; i++){
            if(i != start  && num[i] == num[i - 1])
                continue;
            num[start] = num[i];
            num[i] = origin;
            
            int[] sub = Arrays.copyOf(num, num.length);
            Arrays.sort(sub, start + 1, num.length);
            
            List<List<Integer>> subResult = permutate(sub, start + 1);
            for(List<Integer> item : subResult){
                item.add(0, num[start]);
                result.add(item);
            }
            num[i] = num[start];
            num[start] = origin;
        }
        return result;
    }
}

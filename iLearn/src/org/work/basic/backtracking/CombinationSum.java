package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {1, 1};
        CombinationSum obj = new CombinationSum();
        List<List<Integer>> result = obj.combinationSum(candidates, 1);
        System.out.println(result.size());
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null) return result;
        Arrays.sort(candidates);
        recur(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }
    @SuppressWarnings("unchecked")
    public void recur(int[] candidates, int target, int start, ArrayList<Integer> oneAns, List<List<Integer>> result) {
        if (target <= 0) {
            if (target == 0)
                result.add((ArrayList<Integer>)oneAns.clone());
            return;
        }
        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) continue;
            oneAns.add(candidates[i]);
            recur(candidates, target - candidates[i], i, oneAns, result);
            oneAns.remove(oneAns.size() - 1);
        }
    }
}

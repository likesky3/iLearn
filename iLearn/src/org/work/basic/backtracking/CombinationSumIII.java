package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    // accept version
    public List<List<Integer>> combinationSum3_OK(int k, int n) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        recur_OK(1, k, n, result, item);
        return result;
    }
    
    private void recur_OK(int start, int k, int n, List<List<Integer>> result, ArrayList<Integer> item) {
        if (k == 0) {
            if (n == 0)
                result.add((List<Integer>) item.clone());
            return;
        }
        for (int i = start; i <= 9; i++) {
            item.add(i);
            recur_OK(i + 1, k - 1, n - i, result, item);
            item.remove(item.size() - 1);
        }
    }
    
    // compiler error in leetcode
    public ArrayList<ArrayList<Integer>> combinationSum3(int k, int n) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        recur(1, k, n, result, item);
        return result;
    }
    
    @SuppressWarnings("unchecked")
    private void recur(int start, int k, int n, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> item) {
        if (k == 0) {
            if (n == 0)
                result.add((ArrayList<Integer>) item.clone());
            return;
        }
        for (int i = start; i <= 9; i++) {
            item.add(i);
            recur(i + 1, k - 1, n - i, result, item);
            item.remove(item.size() - 1);
        }
    }
}

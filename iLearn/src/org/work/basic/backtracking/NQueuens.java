package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueuens {

    public static void main(String[] args) {
        NQueuens obj = new NQueuens();
        List<List<String>> result = obj.solveNQueens(9);
        System.out.println(result.size());
        
        List<List<String>> result2 = obj.solveNQueens1(9);
        System.out.println(result2.size());
//        for (List<String> solution : result) {
//            for (String str : solution) {
//                System.out.println(str);
//            }
//            System.out.println("==========");
//        }
    }
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<List<String>>();
        if(n <= 0)
            return res;
            
        queenAt = new int[n];
        occupiedCol = new boolean[n];
        solve(0, n);
        return res;
    }

    public void solve(int row, int n){
        if(row == n){
            List<String> solution = new ArrayList<String>(n);
            StringBuilder line;
            for(int i = 0; i < n; i++){
                line = new StringBuilder();
                int j = 0;
                for(; j < queenAt[i]; j++)
                    line.append('.');
                line.append('Q');
                j++;
                for(; j < n; j++)
                    line.append('.');
                solution.add(line.toString());
            }
            res.add(solution);
            return;
        }
        
        for(int j = 0; j < n; j++){
            if(!occupiedCol[j] && verifyDiagnol(row, j)){
                occupiedCol[j] = true;
                queenAt[row] = j;
                solve(row + 1, n);
                occupiedCol[j] = false;
            }
        }
    }
    
    public boolean verifyDiagnol(int x, int y){
        for(int i = 0; i < x; i++){
            if(x - i == Math.abs(y - queenAt[i]))
                return false;
        }
        return true;
    }
    private int[] queenAt;
    private boolean[] occupiedCol;
    private List<List<String>> res;
    
    
    public List<List<String>> solveNQueens1(int n) {
        int[] rows = new int[n]; // rows[i] = j, means board[i][j] = Q
        int[] cols = new int[n]; // cols[j] = i, means board[i][j] = Q
        for (int i = 0; i < n; i++) {
            rows[i] = -1;
            cols[i] = -1;
        }
        List<List<String>> result = new ArrayList<List<String>>();
        recur(n, 0, rows, cols, result);
        return result;
    }
    
    public void recur(int n, int r, int[] rows, int[] cols, List<List<String>> result) {
        if (r == n) {
            List<String> oneSolution = new ArrayList<String>();
            StringBuilder rowstr = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                rowstr.delete(0, n);
                for (int j = 0; j < n; j++) {
                    if (rows[i] != j)
                        rowstr.append('.');
                    else
                        rowstr.append('Q');
                }
                oneSolution.add(rowstr.toString());
            }
            result.add(oneSolution);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (cols[j] == -1 && !(r > 0 && ((j > 0 && cols[j - 1] == r - 1) || (j < n - 1 && cols[j + 1] == r - 1)))) {
                rows[r] = j;
                cols[j] = r;
                recur(n, r + 1, rows, cols, result);
                rows[r] = -1;
                cols[j] = -1;
            }
        }
    }

}


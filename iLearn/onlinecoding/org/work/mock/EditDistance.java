package org.work.mock;

import java.util.ArrayList;

public class EditDistance {
    class Operation {
        int pos;
        char op;
        char val;
        public Operation(int pos, char op) {
            this.pos = pos;
            this.op = op;
        }
        public Operation(int pos, char op, char val) {
            this.pos = pos;
            this.op = op;
            this.val = val;
        }
    }
    public int getMinEditDistance(String s, String t, int insertionCost, int deletionCost, int substitutionCost) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        calculateEditDistance(s, t, insertionCost, deletionCost, substitutionCost, dp);
        return dp[lengthS][lengthT];
    }
    
    public int getMinEditDistance(String s, String t, int deletionCost, 
            int insertionCost, int substitutionCost, ArrayList<Operation> operations) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        calculateEditDistance(s, t, insertionCost, deletionCost, substitutionCost, dp);
        
        operations.clear();
        int i = lengthS;
        int j = lengthT;
        while (i > 0 && j >0) {
            int currS = i - 1;
            int currT = j - 1;
            if (s.charAt(currS) == t.charAt(currT)) {
                i--;
                j--;
            } else {
                if (dp[i][j] == addNoOverflow(dp[i - 1][j - 1], substitutionCost)) {
                    operations.add(new Operation(currS, 'S', t.charAt(currT)));
                    i--;
                    j--;
                } else if (dp[i][j] == addNoOverflow(dp[i][j - 1], insertionCost)) {
                    operations.add(new Operation(currS + 1, 'I', t.charAt(currT)));
                    j--;
                } else if (dp[i][j] == addNoOverflow(dp[i - 1][j], deletionCost)) {
                    operations.add(new Operation(currS, 'D'));
                    i--;
                }
            }
        }
        while (i > 0) {
            operations.add(new Operation(i - 1, 'D'));
            i--;
        }
        while (j > 0) {
            operations.add(new Operation(0, 'I', t.charAt(j - 1)));
            j--;
        }
        return dp[lengthS][lengthT];
    }
    
    public boolean runEditOperation(String s, String t, int insertionCost, int deletionCost, int substitutionCost) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        ArrayList<Operation> operations = new ArrayList<Operation>();
        getMinEditDistance(s, t, deletionCost, insertionCost, substitutionCost, operations);
        StringBuilder sb = new StringBuilder(s);
        for (Operation operation : operations) {
            switch (operation.op) {
            case 'D':
                sb.deleteCharAt(operation.pos);
                break;
            case 'I':
                sb.insert(operation.pos, operation.val);
                break;
            case 'S':
                sb.setCharAt(operation.pos, operation.val);
                break;

            default:
                break;
            }
        }
        return sb.toString().equals(t);
    }
    
 // O(|s| * |t| ^2)
    public int matchWithMinEditDistance(String s, String t, int insertionCost, int deletionCost, int substitutionCost) {
        int min = Integer.MAX_VALUE;
        int lengthT = t.length();
        for (int j = 0; j <= lengthT; j++) {
            min = Math.min(min, getMinEditDistanceOfPrefix(s, t.substring(j), 
                    deletionCost, insertionCost, substitutionCost));
        }
        return min;
    }
    
    public int getMinEditDistanceOfPrefix(String s, String t, int insertionCost, int deletionCost, int substitutionCost) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        calculateEditDistance(s, t, insertionCost, deletionCost, substitutionCost, dp);
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= lengthT; j++) {
            min = Math.min(min, dp[lengthS][j]);
        }
        return min;
    }
    // O(|s| * |t|)
    public int matchWithMinEditDistance2(String s, String t, int insertionCost, int deletionCost, int substitutionCost) {
        if (s == null)
            s = "";
        if (t == null)
            t = "";
        int lengthS = s.length();
        int lengthT = t.length();
        int[][] dp = new int[lengthS + 1][lengthT + 1];
        for (int j = 0; j <= lengthT; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= lengthS; i++) {
            dp[i][0] = addNoOverflow(dp[i - 1][0], deletionCost);
            for (int j = 1; j <= lengthT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(Integer.MAX_VALUE, addNoOverflow(dp[i - 1][j - 1], substitutionCost));
                    min = Math.min(min, addNoOverflow(dp[i - 1][j], deletionCost));
                    min = Math.min(min, addNoOverflow(dp[i][j - 1], insertionCost));
                    dp[i][j] = min;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= lengthT; j++) {
            min = Math.min(min, dp[lengthS][j]);
        }
        return min;
    }
    
    private void calculateEditDistance(String s, String t, int insertionCost, int deletionCost, int substitutionCost, int[][] dp) {
        int lengthS = s.length();
        int lengthT = t.length();
        dp[0][0] = 0;
        for (int j = 1; j <= lengthT; j++) {
            dp[0][j] = addNoOverflow(dp[0][j - 1], insertionCost);
        }
        for (int i = 1; i <= lengthS; i++) {
            dp[i][0] = addNoOverflow(dp[i - 1][0], deletionCost);
            for (int j = 1; j <= lengthT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(Integer.MAX_VALUE, addNoOverflow(dp[i - 1][j - 1], substitutionCost));
                    min = Math.min(min, addNoOverflow(dp[i - 1][j], deletionCost));
                    min = Math.min(min, addNoOverflow(dp[i][j - 1], insertionCost));
                    dp[i][j] = min;
                }
            }
        }
    }
    private int addNoOverflow(int a, int b) {
        return a < Integer.MAX_VALUE - b ? (a + b) : Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        int ret = obj.getMinEditDistance("d", "", 1, 0, 1);
        System.out.println(ret);
    }
}

package org.work.DP;

import java.util.LinkedList;

public class MaximumRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] h = new int[cols];
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                h[j] = matrix[i][j] == '1' ? h[j] + 1 : 0;
            }
            int j = 0;
            stack.clear();
            while (j < cols || !stack.isEmpty()) {
                if (stack.isEmpty() || (j < cols && h[j] >= h[stack.peek()])) {
                    stack.push(j++);
                } else {
                    int currH = h[stack.pop()];
                    while (!stack.isEmpty() && currH == h[stack.peek()]) {
                        stack.pop();
                    }
                    max = Math.max(max, currH * (stack.isEmpty() ? j : (j - stack.peek() - 1)));
                }
            }
        }
        return max;
    }
}

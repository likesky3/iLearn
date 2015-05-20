package org.work.leetcode;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Test;

public class MaximumRectangleTest {
    @Test
    public void testMaximumRectangle() {
        MaximumRectangle obj = new MaximumRectangle();
        char[][] matrix = new char[1][1];
        matrix[0][0] = 1;
        Assert.assertEquals(1, obj.maximalRectangle(matrix));
    }
}

class MaximumRectangle {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        
        //calculate height[][]
        int[][] h = new int[m][n];
        for(int c = 0; c < n; c++)
            h[0][c] = matrix[0][c] == '1' ? 1 : 0;
        for(int r = 1; r < m; r++){
            for(int c = 0; c < n; c++){
                h[r][c] = matrix[r][c] == '1' ? (h[r-1][c] + 1):0;
            }
        }
        
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        //calculate max rectangle area of each line
        for(int r = 0; r < m; r++){
            int c = 0;
            while(c < n || !stack.isEmpty()){
                if(stack.isEmpty() || c < n && h[r][c] >= h[r][stack.peek()])
                    stack.push(c++);
                else{
                    int currH = h[r][stack.pop()];
                    while(!stack.isEmpty() && currH == h[r][stack.peek()])
                        stack.pop();
                    max = Math.max(max, currH * (stack.isEmpty() ? c : (c - stack.peek() - 1)));
                }
            }
        }
        return max;
    }
    
    public int maximalRectangle1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] h = new int[cols];
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                h[j] = matrix[i][j] == 1 ? h[j] + 1 : 0;
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

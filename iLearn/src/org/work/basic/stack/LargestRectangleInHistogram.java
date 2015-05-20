package org.work.basic.stack;

import java.util.Arrays;
import java.util.LinkedList;

public class LargestRectangleInHistogram {
    // method 1
    public int largestRectangleArea1(int[] height) {
        if (height == null)
            return 0;
        int n = height.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && height[j] >= height[i]) {
                j--;
            }
            int left = i - j;
            j = i + 1;
            while (j < n && height[j] >= height[i]) {
                j++;
            }
            int right = j - i;
            max = Math.max(max, (left + right - 1) * height[i]);
        }
        return max;
    }

    // method 2
    public int largestRectangleArea2(int[] height) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, h.length);
        int i = 0;
        int max = 0;
        while (i < h.length) {
            if (stack.isEmpty() || h[i] >= h[stack.peek()]) {
                stack.push(i++);
            } else {
                int currH = h[stack.pop()];
                max = Math.max(max, currH * (stack.isEmpty() ? i : (i - stack.peek() - 1)));
            }
        }
        return max;
    }

    // method 3
    public int largestRectangleArea3(int[] height) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        int max = 0;
        int i = 0;
        
        while(i < height.length || !stack.isEmpty()){
            if(stack.isEmpty() || i < height.length && height[i] >= height[stack.peek()]){
                stack.push(i++);
            }else{
                int currH = height[stack.pop()];
                while(!stack.isEmpty() && height[stack.peek()] == currH)
                    stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, currH * (i - left - 1));
            }
        }
        
        return max;
    }
}

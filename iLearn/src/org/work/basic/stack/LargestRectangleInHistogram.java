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
    
    // method 4
    public int largestRectangleArea4(int[] height) {
        
        if (height == null || height.length == 0)
            return 0;
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] <height[stack.peek()]) {
                    int curr = stack.pop();
                    while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                        stack.pop();
                    }
                    int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                    max = Math.max(max, height[curr] * width);
                }
                stack.push(i);
            }
        }
        System.out.println("part1=" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        int rightBound = height.length;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                stack.pop();
            }
            int width = stack.isEmpty() ? rightBound : (rightBound - stack.peek() - 1);
            max = Math.max(max, height[curr] * width);
        }
        System.out.println("part2=" + (System.currentTimeMillis() - start));
        
        return max;
    }
    
 // method 5
    public int largestRectangleArea5(int[] height) {
        
        if (height == null || height.length == 0)
            return 0;
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        long start = System.currentTimeMillis();
        int i = 0;
        while(i < height.length) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int curr = stack.pop();
                while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                    stack.pop();
                }
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                max = Math.max(max, height[curr] * width);
            }
        }
        System.out.println("part1=" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        int rightBound = height.length;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
//            long start2 = System.currentTimeMillis();
            while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                stack.pop();
            }
//            System.out.println("part2.1=" + (System.currentTimeMillis() - start2));
            int width = stack.isEmpty() ? rightBound : (rightBound - stack.peek() - 1);
            max = Math.max(max, height[curr] * width);
        }
        System.out.println("part2=" + (System.currentTimeMillis() - start));
        
        return max;
    }
    
    public int largestRectangleArea6(int[] h) {
        if (h == null || h.length == 0)
            return 0;
        int max = 0;
        int[] height = new int[h.length + 1];
        for (int i = 0; i < h.length; i++) {
            height[i] = h[i];
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] <height[stack.peek()]) {
                    int curr = stack.pop();
                    while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                        stack.pop();
                    }
                    int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                    max = Math.max(max, height[curr] * width);
                }
                stack.push(i);
            }
        }
        return max;
    }
    
    public int largestRectangleArea7(int[] h) {
        if (h == null || h.length == 0)
            return 0;
        int max = 0;
        int[] height = new int[h.length + 1];
        for (int i = 0; i < h.length; i++) {
            height[i] = h[i];
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        int n = height.length;
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] <height[stack.peek()]) {
                    int curr = stack.pop();
                    while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                        stack.pop();
                    }
                    int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                    max = Math.max(max, height[curr] * width);
                }
                stack.push(i);
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        LargestRectangleInHistogram obj = new LargestRectangleInHistogram();
        int[] height = new int[300000];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 300000; i++)
            height[i] = 1;
        System.out.println("cost1=" + (System.currentTimeMillis() - start));
        System.out.println();
        
//        System.out.println(obj.largestRectangleArea1(height));
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea2(height));
        System.out.println("cost2=" + (System.currentTimeMillis() - start));
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea3(height));
        System.out.println("cost3=" + (System.currentTimeMillis() - start));
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea4(height));
        System.out.println("cost4=" + (System.currentTimeMillis() - start));
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea5(height));
        System.out.println("cost5=" + (System.currentTimeMillis() - start));
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea6(height));
        System.out.println("cost6=" + (System.currentTimeMillis() - start));
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println(obj.largestRectangleArea7(height));
        System.out.println("cost7=" + (System.currentTimeMillis() - start));
        
    }
}

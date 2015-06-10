package org.work.basic.stack;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Test;

public class Calculator {
    public int calculate(String s) {
        if (s == null || s.equals(""))
            return 0;
        LinkedList<String> stack1 = new LinkedList<String>();
        LinkedList<Character> stack2 = new LinkedList<Character>();
        int n = s.length();
        int i = n - 1;
        while (i >= 0) {
            StringBuilder num = new StringBuilder();
            char c = s.charAt(i);
            while (c >= '0' && c <= '9') {
                num.append(c);
                i--;
                if (i >= 0)
                    c = s.charAt(i);
                else
                    break;
            }
            if (num.length() > 0)
                stack1.push(num.reverse().toString());
            if (i < 0)
                break;
                
            c = s.charAt(i);
            if (c == ')') {
                stack1.push(")");
            } else if (c == '(') {
                while (!stack1.peek().equals(")")) {
                    sum(stack1, stack2);
                }
                stack1.pop();
            } else if (c == '+' || c == '-') {
                stack2.push(c);
            }
            i--;
        }
        while (!stack2.isEmpty()) {
            sum(stack1, stack2);
        }
        return !stack1.isEmpty() ? Integer.valueOf(stack1.pop()) : 0;
    }
    private void sum(LinkedList<String> stack1, LinkedList<Character> stack2) {
        int num1 = Integer.valueOf(stack1.pop());
        if (stack1.peek().equals(")")) {
            stack1.pop();
            stack1.push(String.valueOf(num1));
            stack1.push(")");
            return;
        }
        int num2 = Integer.valueOf(stack1.pop());
        char op = stack2.pop();
        if (op == '+')
            stack1.push(String.valueOf(num1 + num2));
        else
            stack1.push(String.valueOf(num1 - num2));
    }
    
    @Test 
    public void testCalculator() {
        Calculator obj = new Calculator();
        Assert.assertEquals(3, obj.calculate(" 2-1 + 2 "));
        Assert.assertEquals(2147483647, obj.calculate("2147483647"));
        
    }
}

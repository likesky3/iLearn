package org.work.basic.stack;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Test;

public class ReversePolishNotation {

    @Test 
    public void testAnswer(){
        ReversePolishNotation obj = new ReversePolishNotation();
        Assert.assertEquals("23*4+", obj.answer("2*3+4"));
        Assert.assertEquals("243**93*5++", obj.answer("2*4*3+9*3+5"));
    }
	public static String answer(String str) { 

        // Your code goes here.
        if (str == null || str.length() < 2)
            return str;
        LinkedList<Character> stack1 = new LinkedList<Character>();
        LinkedList<Character> stack2 = new LinkedList<Character>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9')
                stack1.push(c);
            else if (c == '*')
                stack2.push(c);
            else {
                while (!stack2.isEmpty() && stack2.peek() == '*') {
                    stack1.push(stack2.pop());
                }
                stack2.push(c);
            }
        }
        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
        StringBuilder result = new StringBuilder();
        while (!stack1.isEmpty()) {
            result.append(stack1.pop());
        }
        return result.reverse().toString();
    } 
	
	

}

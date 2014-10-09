package org.work.basic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

public class Test {

	public void print(int[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// HashMap<String, String> map = new HashMap<>();
		// map.put("1", "2");
		// if (map.get("0").equals("0"))
		// System.out.println("you");

		int aint = '}';
		byte abyte = (byte) aint;
		char[] achar = new char[2];
		achar[0] = (char) abyte;
		achar[1] = '}';
		byte[] bytebuffer;
		byte[] bytebuffer2;
		try {
			bytebuffer = new String(achar).getBytes();
			for (int i = 0; i < bytebuffer.length; i++)
				System.out.println(bytebuffer[i]);
			System.out.println("-----");
			bytebuffer2 = "12".getBytes("UTF-16LE");
			for (int i = 0; i < bytebuffer2.length; i++)
				System.out.println(bytebuffer2[i]);
			System.out.println("-----");
			String astring = new String(bytebuffer);
			System.out.println(aint + " " + abyte + " " + astring + "; " + "}");
			StringBuilder stringBuilder = new StringBuilder(astring);
			stringBuilder.deleteCharAt(astring.length() - 1);
			System.out.println(stringBuilder.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int longestValidParentheses(String s) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (s == null || s.length() == 0)
			return 0;
		int last = -1, max = 0;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				stack.push(i);
			else {
				if (!stack.isEmpty())
					last = i;
				else {
					stack.pop();
					if (!stack.isEmpty())
						max = Math.max(max, i - stack.peek());
					else
						max = Math.max(max, i - last);
				}
			}
		}
		return max;
	}

}

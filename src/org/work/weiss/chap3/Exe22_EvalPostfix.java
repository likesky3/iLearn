package org.work.weiss.chap3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Exe22_EvalPostfix {

	public static void main(String[] args) {
		String[] infix = {"1 + 2 + 3", "1 + 2 * 3 * 4", "1 * 2 * 3 + 4", "12 / 2 / 3 * ( 1 + 1 ) - 1 - 1 + 2 ^ 2 ^ 3"};
//		String[] items = postfix.split(" ");
//		for (String item : items)
//			System.out.print(item + "\t");
		Exe22_EvalPostfix instance = new Exe22_EvalPostfix();
/*		for (int i = 0; i < infix.length; i++) {
			String postfix = instance.infix2postfix(infix[i]);
			System.out.println("infix " + i + ": " + infix[i]);
			System.out.println("postfix " + i + ": " + postfix);
			System.out.println("result = " + instance.evalPostfix(postfix));
			System.out.println();*/
//		}
		System.out.println(instance.evalPostfix());
	}
	
	/**
	 * @mainIdea
	 * if operand, push into stack; if operator, pop two operands and calculate, then push the result into stack
	 * @input: postfix expression
	 * @output: result
	 * @postiveCase:"1 + 2 + 3" = 6; "1 + 2 * 3 * 4" =25; "1 * 2 * 3 + 4"=10;"12 / 2 / 3 * ( 1 + 1 ) - 1 - 1 + 2 ^ 2 ^ 3"=258
	 * @negativeCase: "", null, "1 2", "1 + "
	 * */
	public int evalPostfix(String postfix) {
		LinkedList<Integer> stack = new LinkedList<>();
		String[] ops = postfix.split(" ");
		HashMap<String, Character> map = new HashMap<String, Character>();
		map.put("+", '+');
		map.put("-", '-');
		map.put("*", '*');
		map.put("/", '/');
		map.put("^", '^');
		int num1 = 0, num2 = 0;
		for (String op : ops) {
			if (!map.containsKey(op))
				stack.push(Integer.valueOf(op));
			else {
				if (!stack.isEmpty())
					num2 = stack.poll();
				else {
					System.out.println(">< invalid expression 1");
					System.exit(1);
				}
				if (!stack.isEmpty())
					num1 = stack.poll();
				else {
					System.out.println(">< invalid expression 2");
					System.exit(1);
				}
//				System.out.println("num1 op num2: " + num1 + " " + map.get(op) + " " + num2);
				switch (map.get(op)) {
				case '+':
					stack.push(num1 + num2);break;
				case '-':
					stack.push(num1 - num2);break;
				case '*':
					stack.push(num1 * num2);break;
				case '/':
					stack.push(num1 / num2);break;
				case '^':
					stack.push((int) Math.pow(num1, num2));break;
				default:
					break;
				}
			}
		}
		
		int ret = 0;
		if (!stack.isEmpty()) {
			ret = stack.poll();
		}
		if (stack.isEmpty())
			return ret;
		else {
			System.out.println("stack.size = " + stack.size() + " stack.top = " + stack.poll());
			System.out.println(">< invalid expression 3");
			return Integer.MAX_VALUE;
		}
			
	}
	
	/**
	 * 21:01 - 
	 * main idea: 
	 * 0) current operator has higher priority than top of stack, push it into stack, otherwise, pop till a lower operator 
	 * 1) - & / : the previous has higher priority : 2-2-3 -> 22-3-
	 * 2) ^ : the latter has higher priority: 2 ^ 2 ^ 3 -> 223^^
	 * 3) + & * : either is ok, here same as - & /
	 * 4) ( : highest when read, lowest when it is in stack; ):lowest when read, never in stack
	 * input: operator & operand separated by blank space
	 * output: postfix of the input
	 * test: pos = "12 / 2 / 3 * ( 1 + 1 ) - 1 - 1 + 2 ^ 2 ^ 3"; neg = "", "1 + ", "1+1"
	 * */
//	enum BasicOperator4 {
//		ADD("+"),
//		MINUS("-"),
//		MULIPLY("*"),
//		DIVIDE("/"),
//		;
//		String value;
//		BasicOperator4(String op){
//			this.value = op;
//		}
//	};
	public String infix2postfix(String infix) {
		if (infix == null || infix.length() == 0)
			return "empty input";
		LinkedList<String> stack = new LinkedList<>();
		String[] items = infix.split(" ");
		StringBuilder postfix = new StringBuilder();
		HashMap<String, Integer> map = new HashMap();
		map.put("(", 0);
		map.put("+",1);
		map.put("-", 1);
		map.put("*",2);
		map.put("/",2);
		map.put("^", 2);
		for (int i = 0; i < items.length; i++) {
			String currOp = items[i];
			 if (currOp.equals("^") || currOp.equals("(")) {
				 stack.push(items[i]);
			 } else if (currOp.equals(")")) {
				 String top;
				 while (!stack.isEmpty()) {
					 top = stack.peek();
					 if (top.equals("(")) {
						 stack.pop();
						 break;
					 }
					 postfix.append(stack.pop()).append(' ');
				 }
			 } else if (map.containsKey(currOp)) {
				 String top;
				 while (!stack.isEmpty()) {
					 top = stack.peek();
//					 System.out.println("currop " + currOp + " " + map.get(currOp) + " top " + top + " " + map.get(top));
					 if (map.get(currOp) > map.get(top)) {
						 stack.push(currOp);
						 break;
					 } 
					 postfix.append(stack.pop()).append(' ');
				 }
				 if (stack.isEmpty()) //@@ Do you know why need this ?
					 stack.push(currOp); 
			 } else {
				 postfix.append(currOp).append(' ');
			 }
		}
		//append the rest in the stack
		while(!stack.isEmpty()) 
			postfix.append(stack.pop()).append(' ');
		return postfix.toString();
	}
	
	/**author's solution*/
	public double evalPostfix() {
		Stack<Double> s = new Stack<>();
		String token;
		Double a, b, result = 0.0;
		boolean isNumber;
		
		Scanner sc = new Scanner(System.in);
		token = sc.next();
		while (token.charAt(0) != '=') {
			try {
				isNumber = true;
				result = Double.parseDouble(token);
			} catch (Exception e) {
				isNumber = false;
			}
			
			if (isNumber)
				s.push(result);
			else {
				switch (token.charAt(0)) {
				case '+': a = s.pop(); b= s.pop();
				s.push(a + b); break;
				case '-': a = s.pop(); b= s.pop();
				s.push(a - b); break;
				case '*': a = s.pop(); b= s.pop();
				s.push(a * b); break;
				case '/': a = s.pop(); b= s.pop();
				s.push(a / b); break;
				case '^': a = s.pop(); b= s.pop();
				s.push(Math.exp(a * Math.log(b))); break;
				}
			}
			token = sc.next();
		}
		return s.peek();
	}
	
}

package org.work.basic;


import org.work.leetcode.*;

public class ExceptionDemo {
	public static void main(String[] args){
		
		ExceptionDemo obj = new ExceptionDemo();
		//obj.f();
		obj.g();		
	}
	
	public void f(){
		TreeNode node = null;
		try {
			System.out.println(node.toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Caught it!");
			e.printStackTrace(System.out);
		}
	}
	
	public void g(){
		double c = 1.0 / 0;
		System.out.println(c);
	}
}

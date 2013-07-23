package org.work.basic.exception;

public class E00_RunTimeException {
	static void f(){
		throw new RuntimeException("from f()");
	}
	
	static void g(){
		f();
	}
	
	public static void main(String[] args){
		g();
	}
}

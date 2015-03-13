package org.work.basic;

public class KnowStatic {
	public static int i = 1;

	public static KnowStatic t0 = new KnowStatic("t0");

	static {
		System.out.println("static statement");
	}
	public static int k = 2;

	

	public KnowStatic(String s) {
		System.out.println("i: " + (++i) + " " + s + " k: " + k);
	}

	public static int print(String str) {
		System.out.println("print()");
		return i;
	}
	
	{
		System.out.println("normal statement.");
		KnowStatic.print("hello");
	}

	public static void main(String[] args) {
//		KnowStatic t1 = new KnowStatic(" main1");
//		KnowStatic t2 = new KnowStatic(" main2");
		System.out.println(KnowStatic.k);
	}
}

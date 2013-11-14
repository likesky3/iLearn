package org.work.basic;

class Parent {
	Parent() {
		System.out.println("1");
	}

	Parent(int x) {
		System.out.println("2");
	}
}

public class Child extends Parent {
	Child(int x) {
		super();
		System.out.println("3");
	}

	Child(int x, int y) {
		this(x);
		System.out.println("4");
	}

	public static void main(String[] args) {
		new Child(5, 10);
	}
}

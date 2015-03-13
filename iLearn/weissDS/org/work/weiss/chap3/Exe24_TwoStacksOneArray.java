package org.work.weiss.chap3;

public class Exe24_TwoStacksOneArray<T> {

	public static void main(String[] args) {
		Exe24_TwoStacksOneArray<Integer> stack2 = new Exe24_TwoStacksOneArray<>(4);
		System.out.println(stack2.pop1() + "\t" + stack2.pop2());
		stack2.push1(1);
		stack2.push2(2);
		stack2.push1(3);
		stack2.push2(4);
		stack2.push1(5);
		stack2.push2(6);
		System.out.println(stack2.pop1());
		System.out.println(stack2.pop1());
		System.out.println(stack2.pop1());
		System.out.println(stack2.pop2());
		System.out.println(stack2.pop2());
		System.out.println(stack2.pop2());
	}
	
	private Object[] array;
	private static final int DEFAULT_CAPACITY = 10;
	private int stack1Pos, stack2Pos;
	
	public Exe24_TwoStacksOneArray() {
		this(Exe24_TwoStacksOneArray.DEFAULT_CAPACITY);
	}
	
	public Exe24_TwoStacksOneArray(int capacity) {
		array = new Object[capacity];
		stack1Pos = -1;
		stack2Pos = capacity;
	}
	
	public void push1(T value) {
		if (stack1Pos < (stack2Pos - 1))
			array[++stack1Pos] = value;
		else
			System.out.println("stack1 overflow");
	}
	
	public void push2(T value) {
		if (stack2Pos > (stack1Pos + 1))
			array[--stack2Pos] = value;
		else
			System.out.println("stack2 overflow");
	}
	
	public T pop1() {
		if (stack1Pos >= 0) //@@
			return (T)array[stack1Pos--];
		else
			return null;
	}
	
	public T pop2() {
		if (stack2Pos < array.length)
			return (T)array[stack2Pos++];//@@
		else
			return null;
	}
}

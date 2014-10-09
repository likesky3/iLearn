package org.work.weiss.chap3;


public class Exe25_FindMinStack<T extends Comparable<? super T>> {
	private Object[] array;
	private Object[] min;
	private static final int DEFAULT_CAPACITY = 10;
	private int cursor = -1;
	private int minCursor = -1;
	
	public Exe25_FindMinStack() {
		this(Exe25_FindMinStack.DEFAULT_CAPACITY);
	}
	
	public Exe25_FindMinStack(int capacity) {
		array = new Object[capacity];
		min = new Object[capacity];
	}
	
	public void push(T value) {
		if (cursor < array.length) {
			array[++cursor] = value;
			if ((minCursor >= 0 && value.compareTo((T)min[minCursor]) < 0) || minCursor < 0) {
				min[++minCursor] = value;
			}
		} else
			System.out.println("stack overflow");
	}
	
	public T pop() {
		if (cursor >= 0) {
			T ret = (T) array[cursor--];
			if (ret.compareTo((T) min[minCursor]) == 0)
				minCursor--;
			return ret;
		} else {
			System.out.println("stack is empty");
			return null;
		}
	}
	
	public T findMin() {
		return (T) min[minCursor];
	}
	
	public static void main(String[] args) {
		Exe25_FindMinStack<Integer> stack = new Exe25_FindMinStack<>(3);
		stack.push(-3);
		System.out.println(stack.findMin());
		stack.push(2);
		stack.push(1);
		System.out.println(stack.findMin());
		stack.pop();
		stack.pop();
		System.out.println(stack.findMin());
	}
}

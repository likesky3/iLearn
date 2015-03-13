package org.work.weiss.chap3;

public class Exe31_Stack_SinglyList<T> {

	public static void main(String[] args) {
		Exe31_Stack_SinglyList<Integer> ins = new Exe31_Stack_SinglyList<>();
		System.out.println(ins.top());
		ins.push(1);
		ins.push(2);
		System.out.println(ins.top());
		ins.pop();
		System.out.println(ins.top());
	}
	
	public void push(T value) {
		head = new Node<T>(value, head);
	}
	
	public T top() {
		return head != null ? head.data : null;
	}
	
	public void pop() {
		if (head != null)
			head = head.next;
	}
	
	@SuppressWarnings("hiding")
	private class Node<T> {
		public Node(T value, Node<T> p) {
			data = value;
			next = p;
		}
		
		private Node<T> next;
		private T data;
	}
	
	private Node<T> head = null;
}

package org.work.weiss.chap3;


public class Exe32_Queue_SinglyList<T> {

	public static void main(String[] args) {
		Exe32_Queue_SinglyList<Integer> ins = new Exe32_Queue_SinglyList<>();
		System.out.println(ins.front());
		ins.enque(1);
		ins.enque(2);
		System.out.println(ins.front());
		ins.deque();
		System.out.println(ins.front());
	}
	
	public void enque(T value) {
		if (head == null) {
			head = new Node<T>(value);
			tail = head;
		} else {
			tail.next = new Node<T>(value);
			tail = tail.next;
		}
	}
	
	public void deque() {
		if (head != null)
			head = head.next;
	}
	
	public T front() {
		return head != null ? head.data : null;
	}
	@SuppressWarnings("hiding")
	private class Node<T> {
		public Node(T value) {
			this(value, null);
		}
		public Node(T value, Node<T> p) {
			data = value;
			next = p;
		}
		
		private Node<T> next;
		private T data;
	}
	
	private Node<T> head = null;
	private Node<T> tail = null;
}

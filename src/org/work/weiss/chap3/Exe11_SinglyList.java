package org.work.weiss.chap3;

import java.util.NoSuchElementException;

public class Exe11_SinglyList<T> {

	public static void main(String[] args) {
		Exe11_SinglyList<Integer> list = new Exe11_SinglyList<>();
		list.add(1);
		list.add(1);
		list.print();
		list.add(2);
		list.remove(2);
		System.out.println(list.contains(2));
		System.out.println(list.contains(1));
	}
	
	private static class Node<T> {
		public Node(T val, Node<T> next) {
			this.value = val;
			this.next = next;
		}
		public T value;
		public Node<T> next;
	}
	
	private int size;
	private Node<T> head;
	
	//constructor
	public Exe11_SinglyList() {
		doClear();
	}
	
	public void clear() {
		doClear();
	}
	
	private void doClear() {
		size = 0;
		head = new Node(null, null);
	}
	
	public int size() {
		return size;
	}
	
	public void print() {
		Node<T> curr = head.next;
		while(curr != null) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
		System.out.println();
	}
	
	public boolean contains(T item) { 
		Node<T> curr = head.next;
		while(curr != null) {
			if (curr.value.equals(item))
				return true;
			curr = curr.next;
		}
		return false;
	}
	
	public void add(T item) {
		Node<T> curr = head;
		while(curr.next != null) {
			curr = curr.next;
			if (curr.value.equals(item))
				return;
		}
		curr.next = new Node(item, curr.next);
		size++;
	}
	
	public void remove(T item) {
		Node<T> curr = head.next;
		Node<T> prev = head;
		while(curr != null) {
			if (curr.value.equals(item)) {
				prev.next = curr.next;
				curr = null;
				size--;
				return;
			}
			prev = curr;
			curr = curr.next;
		}
		throw new NoSuchElementException();
	}
	
}

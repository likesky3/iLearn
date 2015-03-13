package org.work.weiss.chap3;

import java.util.NoSuchElementException;

public class Exe11_SinglyList<T> {

	public static void main(String[] args) {
		Exe11_SinglyList<Integer> list = new Exe11_SinglyList<>();
//		list.add(1);
//		list.add(1);
//		list.print();
//		list.add(2);
//		list.remove(2);
//		System.out.println(list.contains(2));
//		System.out.println(list.contains(1));
//		list.reverse();
//		list.print();
//		list.add(-1);
//		list.reverse();
//		list.print();
		for (int i = 0; i < 5; i++)
			list.add(i);
		System.out.println(list.find(6));
		System.out.println(list.find(3));
		list.print();
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
	
	//pos: 1, 1-2, 1-2-3; neg: empty
	public void reverse() {
		if (head.next == null) {
			System.out.println("empty list");
			return;
		}
		Node<T> firstHalf = head.next;
		Node<T> secHalf = firstHalf.next;
		Node<T> succNode;
		firstHalf.next = null;
		while (secHalf != null) {
			succNode = secHalf.next;
			secHalf.next = firstHalf;
			firstHalf = secHalf;
			secHalf = succNode;
		}
		head.next = firstHalf;
	}
	
	public boolean find(T value) {
		Node<T> prev = head;
		while (prev != null) {
			if (prev.next != null && value.equals(prev.next.value)) {
				Node<T> target = prev.next;
				prev.next = target.next;
				target.next = head.next;
				head.next = target;
				return true;
			} else if (prev.next == null) {
				return false;
			} else {
				prev = prev.next;
			}
		}
		
		return false;
	}
	
}

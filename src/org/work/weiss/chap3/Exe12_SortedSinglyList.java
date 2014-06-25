package org.work.weiss.chap3;


public class Exe12_SortedSinglyList<T extends Comparable<? super T>> {

	public static void main(String[] args) {
		Exe12_SortedSinglyList<Integer> list = new Exe12_SortedSinglyList<>();
		list.add(1);
		list.add(1);
		list.print();
		list.add(-2);
		list.remove(2);
		System.out.println(list.contains(2));
		System.out.println(list.contains(1));
		list.add(3);
		list.add(0);
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
	public Exe12_SortedSinglyList() {
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
		Node<T> prev = head;
		while(curr.next != null) {
			prev = curr;
			curr = curr.next;
			if (curr.value.compareTo(item) == 0)
				return;
			else if (curr.value.compareTo(item) > 0) {
				prev.next = new Node(item, curr);
				size++;
				return;
			} 
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
//		throw new NoSuchElementException();
		System.out.println("no such element");
	}

}

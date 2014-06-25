package org.work.weiss.chap3;

import java.util.ConcurrentModificationException;
import java.util.Currency;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("===stdlist");
		LinkedList<Integer> stdlist = new LinkedList<>();
		for (int i = 0; i < 5; i++)
			stdlist.add(i);
		java.util.ListIterator<Integer> stdIter = stdlist.listIterator();
		System.out.println("print use previous");
		while (stdIter.hasPrevious())
			System.out.print(stdIter.previous() + " ");
		System.out.println();
		
		System.out.println("after add a item");
		stdIter.add(6);
		for(int x : stdlist)
			System.out.print(x + " ");
		System.out.println();
		
		System.out.println("print after remove");
		if (stdIter.hasNext())
			stdIter.next();
		stdIter.remove();	
		for(int x : stdlist)
			System.out.print(x + " ");
		System.out.println();
		
		if (stdIter.hasNext())
			stdIter.next();
		stdIter.set(100);
		System.out.println("print use next");
		while(stdIter.hasNext())
			System.out.print(stdIter.next() + " ");
		System.out.println();
		
		System.out.println("print use previous after use next");
		while (stdIter.hasPrevious())
			System.out.print(stdIter.previous() + " ");
		System.out.println();
		
		System.out.println("====mylinkedlist");
		MyLinkedList<Integer> list = new MyLinkedList<>();
		for (int i = 0; i < 5; i++)
			list.addLast(i);
		ListIterator<Integer> iter = list.listIterator();
		System.out.println("print use previous");
		while (iter.hasPrevious())
			System.out.print(iter.previous() + " ");
		System.out.println();
		
		System.out.println("after add a item");
		iter.add(6);
		for(int x : list)
			System.out.print(x + " ");
		System.out.println();
		
		System.out.println("print after remove");
		if (iter.hasNext())
			iter.next();
		iter.remove();	
		for(int x : list)
			System.out.print(x + " ");
		System.out.println();
		
		if (iter.hasNext())
			iter.next();
		iter.set(100);
		System.out.println("print use next");
		while(iter.hasNext())
			System.out.print(iter.next() + " ");
		System.out.println();
		
		System.out.println("print use previous after use next");
		while (iter.hasPrevious())
			System.out.print(iter.previous() + " ");
		System.out.println();
	}
	
	private static class Node<T> {
		T value;
		Node<T> next;
		Node<T> prev;
		public Node(T value, Node<T> prev, Node<T> next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	private int modCount = 0;
	
	public MyLinkedList() {
		doClear();
	}
	
	public void clear() {
		doClear();
	}
	
	private void doClear() {
		size = 0;
		head = new Node<T>(null, null, null);
		tail = new Node<T>(null, head, null);
		head.next = tail;
		modCount++;
	}
	
	public int size() {
		return size;
	}
	public void addFirst(T x) {
		Node<T> newNode = new Node<>(x, head, head.next);
		head.next.prev = newNode;
		head.next = newNode;
		size++;
		modCount++;
	}
	
	public void addLast(T x) {
		Node<T> newNode = new Node<>(x, tail.prev, tail);
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		modCount++;
	}
	
	private T remove(Node<T> p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		T removedValue = p.value;
		p = null;
		size--;
		modCount++;
		return removedValue;
	}
	
	public T remove(int index) {
		return remove(node(index, 0, size - 1));
	}
	
	//remove the first x in list, easy to modify the code to remove all x in the list
	public T remove(T x) {
		Node<T> curr = head.next;
		while(curr != tail) {
			if (curr.value == x) {
				return remove(curr);
			}
		}
		return null;
	}
	public void removeAll(Iterable<? extends T> items) {
//		for (T x : items)
//			remove(x);
		Iterator<? extends T> iter2 = items.iterator();
		while (iter2.hasNext()) {
			T elem2 = iter2.next();
			Iterator<T> iter = iterator();
			while (iter.hasNext()) {
				T elem = iter.next();
				if (elem.equals(elem2))
					iter.remove();
			}
		}
	}
	
	public T get(int index) {
		return node(index, 0, size - 1).value;
	}
	
	public void set(int index, T newValue) {
		Node<T> target = node(index, 0, size - 1);
		target.value = newValue;
	}
	
	private Node<T> node(int index, int lower, int upper) {
		if (index < lower || index > upper)
			throw new IndexOutOfBoundsException();
		Node<T> tmp = null;
		if (index < (size >> 1)) {
			tmp = head.next;
			for (int i = 0; i < index; i++) {
				tmp = tmp.next;
			}
			
		} else {
			tmp = tail.prev;
			for (int i = size - 1; i > index; i--) {
				tmp = tmp.prev;
			}
		}
		return tmp;
	}
	
	public void splice(Iterator<T> itr, MyLinkedList<? extends T> lst) {
		Node<T> firstNode = head.next;
		Node<T> firstLst = (Node<T>) lst.get(0);
		head.next = firstLst;
		firstLst.prev = head;
		Node<T> lastLst = (Node<T>)lst.get(lst.size() - 1); //O(N)
		lastLst.next = firstNode;
		firstNode.prev = lastLst;
		lst = null;
	}
	@Override
	public Iterator<T> iterator() {
//		return new LinkedListIterator();
		return new ListIter();
	}
	
	public ListIterator<T> listIterator() {
		return new ListIter();
	}
	
	//need practice,注意LinkedListIterator 不要写成LinkedListIterator<T>,
	//不然curr = head.next需要类型转换而且不能调用MyLinkedList.this.remove(curr.prev);
	//可以认为实现泛型接口的类就是一个泛型类
	private class LinkedListIterator implements Iterator<T> {
		private Node<T> curr = head.next;
		private Node<T> lastReturned = null;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		@Override
		public boolean hasNext() {
			return curr != tail;
		}

		@Override
		public T next() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			T next = curr.value;
			curr = curr.next;
			okToRemove = true;
			return next;
		}
		
		public void remove() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();
			
			MyLinkedList.this.remove(curr.prev);
			expectedModCount++;
			okToRemove = false;
			
		}
		
	}
	
	private class ListIter implements ListIterator<T> {
		
		private Node<T> curr = head.next;
		private Node<T> lastReturned = null;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		@Override
		public boolean hasNext() {
			return curr != tail;
		}

		@Override
		public T next() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if(!hasNext())
				throw new NoSuchElementException();
			lastReturned = curr;
			T next = curr.value;
			curr = curr.next;
			okToRemove = true;
			return next;
		}
		
		public void remove() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();
			
			MyLinkedList.this.remove(curr.prev);
			expectedModCount++;
			okToRemove = false;
			lastReturned = null;
		}

		@Override
		public boolean hasPrevious() {
			return curr.prev != head;
		}

		@Override
		public T previous() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!hasPrevious())
				throw new NoSuchElementException();
			lastReturned = curr.prev;
			T prev = curr.prev.value;
			curr = curr.prev;
			return prev;
		}

		@Override
		public void add(T x) {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			Node<T> newNode = new Node(x, curr.prev, curr);
			curr.prev.next = newNode;
			curr.prev = newNode;
			lastReturned = null;
		}

		@Override
		public void set(T newVal) {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (lastReturned != null)
				lastReturned.value = newVal;
			else {
				throw new IllegalStateException();
			}
		}
	}
	
	public void print() {
		ListIterator<T> iter = listIterator();
		while(iter.hasNext())
			System.out.print(iter.next() + "\t");
		System.out.println();
	}
	
	public void printReverse() {
		ListIterator<T> iter = listIterator();
		while(iter.hasPrevious())
			System.out.print(iter.previous() + "\t");
		System.out.println();
	}

}


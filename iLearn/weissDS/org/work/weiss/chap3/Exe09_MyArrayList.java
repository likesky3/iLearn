package org.work.weiss.chap3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

public class Exe09_MyArrayList<T> implements Iterable<T>{

	public static void main(String[] args) {
	    
		ArrayList<Integer> stdArrayList = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			stdArrayList.add(i);
		Iterator<Integer> iter = stdArrayList.iterator();
		if (iter.hasNext())
			System.out.println(iter.next());
//		stdArrayList.add(5);
		while(iter.hasNext())
			System.out.print(iter.next() + "\t");
		System.out.println("\n\n");
		Exe09_MyArrayList<Integer> arrayList = new Exe09_MyArrayList<>();
//		arrayList.remove(1);
		arrayList.add(1);
//		arrayList.add(3,2);
		System.out.println(arrayList.get(0));
		arrayList.set(0, -1);
		System.out.println(arrayList.get(0));
		Exe09_MyArrayList<Integer> arrayList2 = new Exe09_MyArrayList<>();
		arrayList2.add(10);
		arrayList2.add(11);
		arrayList.addAll(arrayList2);
//		for (int num : arrayList)
//			System.out.print(num + "\t");
		
		
		ListIterator<Integer> listIterator = arrayList.listIterator();
		arrayList.print();
		listIterator.add(100);
		arrayList.print();
		listIterator.set(50);
		listIterator.next();
//		arrayList.add(333);
		listIterator.set(40);
		arrayList.print();
		
		Iterator<Integer> reverseIter = arrayList.reverseIter();
		while(reverseIter.hasNext())
			System.out.print(reverseIter.next() + "\t");
		System.out.println();
	}

	private static final int DEFAULT_CAPACITY = 10;
//	private int size;
//	private T[] theItems;
	protected int size;
	protected T[] theItems;
	
	public Exe09_MyArrayList() {
		doClear();
	}
	
	public void clear() {
		doClear();
	}
	
	private void doClear() {
		this.size = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	public void ensureCapacity(int minCapacity) {
		if (minCapacity < this.size)
			return;
		T[] oldItems = theItems;
		theItems = (T[]) new Object[minCapacity];
		for (int i = 0; i < size; i++)
			theItems[i] = oldItems[i];
	}
	
	public int size() {
		return this.size;
	}
	public boolean isEmpty() {
		return this.size == 0;
	}
	public void trimToSize() {
		ensureCapacity(this.size);
	}
//	private void rangeCheck(int idx) {
//		if (idx < 0 || idx >= size)
//			throw new ArrayIndexOutOfBoundsException();
//	}
	//remember to check bounds
	public T get(int idx) {
		if (idx < 0 || idx >= size)
			throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}
	//why return old?
	public T set(int idx, T newVal) {
		if (idx < 0 || idx >= size)
			throw new ArrayIndexOutOfBoundsException();
		T oldVal = theItems[idx];
		theItems[idx] = newVal;
		return oldVal;
	}
	public boolean add(T x) {
		add(size(), x);
		return true;
	}
	public void add(int idx, T x) {
		if (idx < 0 || idx > size)
			throw new ArrayIndexOutOfBoundsException();
		if (theItems.length == size) //list is full
			ensureCapacity(size << 1 + 1);
		for (int i = size; i > idx; i--)
			theItems[i] = theItems[i - 1];
		theItems[idx] = x;
		size++;
	}
	public void addAll(Iterable<? extends T> items) {
		Iterator<? extends T> iter = items.iterator();
		while (iter.hasNext())
			add(iter.next());
	}
	public T remove(int idx) {
		if (idx < 0 || idx >= size)
			throw new ArrayIndexOutOfBoundsException();
		T old = theItems[idx];
		for (int i = idx; i < (size - 1); i++)
			theItems[i] = theItems[i + 1];
		size--;
		return old;
	}
	@Override
	public Iterator<T> iterator() {
//		return new ArrayListIterator();
		return new ListIter();
	}
	
	public ListIterator<T> listIterator() {
		return new ListIter();
	}
	private class ArrayListIterator implements Iterator<T> {
		
		private int curr = 0;
		
		@Override
		public boolean hasNext() {
			return curr < size();
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return theItems[curr++];
		}
		
		public void remove() {
			Exe09_MyArrayList.this.remove(--curr);
		}
		
	}
	
	private class ListIter implements ListIterator<T> {
		
		private int curr = 0;
		
		@Override
		public boolean hasNext() {
			return curr < size();
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return theItems[curr++];
		}
		
		public void remove() {
			Exe09_MyArrayList.this.remove(--curr);
		}
		
		@Override
		public boolean hasPrevious() {
			return curr > 0;
		}

		@Override
		public T previous() {
			if (hasPrevious())
				return theItems[--curr];
			return null;
		}

		@Override
		public void add(T x) {
			Exe09_MyArrayList.this.add(curr, x);
		}

		@Override
		public void set(T newVal) {
			if (hasPrevious())
				theItems[curr - 1] = newVal;
			else
				theItems[size - 1] = newVal;
		}
	}
	
	public Iterator<T> reverseIter() {
		return new ReverseIterator();
	}
	private class ReverseIterator implements Iterator<T> {
		
		private int curr = size - 1;
		@Override
		public boolean hasNext() {
			return curr >= 0;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return theItems[curr--];
		}
	}
	
	public void print() {
		ListIterator<T> listIterator = listIterator();
		while(listIterator.hasNext())
			System.out.print(listIterator.next() + "\t");
		System.out.println();
	}
	
	public void printReverse() {
		ListIterator<T> listIterator = listIterator();
		while(listIterator.hasPrevious())
			System.out.print(listIterator.previous() + "\t");
		System.out.println();
	}

}

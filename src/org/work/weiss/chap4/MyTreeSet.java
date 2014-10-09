package org.work.weiss.chap4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @ why static class BinaryNode
 * @ why need call next() before remove() in iterator
 */
public class MyTreeSet<AnyType extends Comparable<? super AnyType>> {

	public static void main(String[] args) throws UnderFlowException {
		// std TreeSet
//		TreeSet<Integer> stdSet = new TreeSet<>();
		MyTreeSet<Integer> mySet = new MyTreeSet<>();
		mySet.add(5);
		mySet.add(3);
		mySet.add(8);
		mySet.add(6);
		mySet.add(4);
		/*mySet.printTree();
		System.out.println();
		
		mySet.remove(5);
		mySet.printTree();
		System.out.println();
		
		System.out.println(mySet.findMin());
		System.out.println(mySet.findMax());
		System.out.println();*/
		
		Iterator<Integer> setIterator = mySet.iterator();
		while(setIterator.hasNext()) {
			System.out.println(setIterator.next());
//			setIterator.remove();
		}
		System.out.println(mySet.isEmpty());
		System.out.println(mySet.contains(8));
	}
	
	private BinaryNode<AnyType> root;
	private int modCount = 0;
	
	private static class BinaryNode<AnyType> {
		BinaryNode(AnyType value) {
			this(value, null, null, null);
		}
		BinaryNode(AnyType value, BinaryNode<AnyType> left,
				BinaryNode<AnyType> right, BinaryNode<AnyType> parent) {
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		AnyType value;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		BinaryNode<AnyType> parent;
	}
	
	public Iterator<AnyType> iterator() {
		return new MyTreeSetIterator();
	}
	
	private class MyTreeSetIterator implements Iterator<AnyType> {
		
		@Override
		public boolean hasNext() {
			return !atEnd;
		}

		//find the next larger one
		@Override
		public AnyType next() {
//			System.out.println(expectedModCount + " " + modCount);
			if (expectedModCount != modCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			AnyType nextItem = current.value;
			previous = current;
			if (current.right != null) {
//				current = findMin(current); //bug 6
				current = findMin(current.right); 
			} else {//find the first ancestor which it is left of
				BinaryNode<AnyType> child = current;
				current = current.parent;
				while (current != null && current.left != child) {
					child = current;
					current = current.parent;
				}
				if (current == null)
					atEnd = true;
			}
			okToRemove = true;
			return nextItem;
		}
		
		public void remove() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();
			MyTreeSet.this.remove(previous.value);
			okToRemove = false;
			expectedModCount = modCount;
		}
		
		private BinaryNode<AnyType> current = findMin(root);
		private int expectedModCount = modCount;
		private boolean atEnd = false;
		private BinaryNode<AnyType> previous;
		private boolean okToRemove = false;
	}
	
	public MyTreeSet() {
		root = null;
	}
	
	public void printTree() {
		printTree(root);
	}
	public void makeEmpty() {
		root = null;
		modCount = 0;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void add(AnyType item) {
		root = this.add(item, root, null);
	}
	
	public void remove(AnyType item){
		root = this.remove(item, root);
	}
	
	public boolean contains(AnyType item) {
		return contains(item, root);
	}
	
	public AnyType findMin() throws UnderFlowException {
		if (root == null)
			throw new UnderFlowException();
		else
			return findMin(root).value;
	}
	
	public AnyType findMax() throws UnderFlowException {
		if (root == null)
			throw new UnderFlowException();
		else
			return findMax(root).value;
	}
	private void printTree(BinaryNode<AnyType> t) {
		if (t == null)
			return;
		printTree(t.left);
		System.out.println(t.value);
		printTree(t.right);
	}
	private BinaryNode<AnyType> add(AnyType item, BinaryNode<AnyType>t, BinaryNode<AnyType>pt) {
		if (t== null) {
			modCount++;
			return new BinaryNode<AnyType>(item, null, null, pt);
		}
		int compareResult = item.compareTo(t.value);
		if (compareResult < 0) {
//			add(item, t.left, t); //bug1
			t.left = add(item, t.left, t);
		} else if (compareResult > 0) {
//			add(item, t.right, t); //bug2
			t.right = add(item, t.right, t);
		} else {
			;//duplicate
		}
		return t;
	}
	private BinaryNode<AnyType> remove(AnyType item, BinaryNode<AnyType>t) {
		if (t == null)
			return t;
		int compareResult = item.compareTo(t.value);
		if (compareResult < 0) {
//			remove(item, t.left); //bug3
			t.left = remove(item, t.left);
		} else if (compareResult > 0) {
//			remove(item, t.right); //bug4
			t.right = remove(item, t.right);
		} else if (t.left != null && t.right != null) {
			t.value = findMin(t.right).value;
			t.right = remove(t.value, t.right);
		} else {
			modCount++;
			BinaryNode<AnyType> oneChild = (t.left != null) ? t.left : t.right;
			if (oneChild != null) //fixed bug5
				oneChild.parent = t.parent;
			t = oneChild;
		}
		return t;
	}
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;
		int compareResult = x.compareTo(t.value);
		if (compareResult == 0)
			return true;
		else if (compareResult < 0)
			return contains(x, t.left);
		else 
			return contains(x, t.right);
	}
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t== null)
			return null;
		if (t.left == null)
			return t;
		else
			return findMin(t.left);
	}
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		if (t.right == null)
			return t;
		else
			return findMax(t.right);
	}
	
}

class UnderFlowException extends Exception {
	
}

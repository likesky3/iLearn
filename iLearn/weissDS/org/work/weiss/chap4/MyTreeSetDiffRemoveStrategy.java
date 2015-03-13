package org.work.weiss.chap4;

import java.util.BitSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * @ why static class BinaryNode: inner class won't use outer class 
 * @ why need call next() before remove() in iterator: 
 * the main advantage of the Iterator's remove method is that the Collection's remove method must first find the item to remove.
 * An example that we will see is remove every other item in the collection
 */
public class MyTreeSetDiffRemoveStrategy<AnyType extends Comparable<? super AnyType>> {

	public static void main(String[] args) throws UnderFlowException {
		MyTreeSetDiffRemoveStrategy<Integer> mySet = new MyTreeSetDiffRemoveStrategy<>();
		createASet(mySet,100);
		int[] timer = new int[4];
		int i = 1;
		for (int k = 0; k < 1; k++) {
			for (i = 3; i >= 0; i--) 
			{
//			for (i = 0; i < 4; i++) {
				long start = System.currentTimeMillis();
				int size = mySet.size;
				verifyRemoveStrategy(mySet, size, size * size, i);
				timer[i] += System.currentTimeMillis() - start;
				System.out.println("size=" + size);
			}
		}
		
		for (i = 0; i < 4; i++) {
			System.out.println("strategy " + i + ": " + timer[i]/10);
		}
		
	}
	
	private static BitSet bitSet;
	
	public static void verifyRemoveStrategy(MyTreeSetDiffRemoveStrategy<Integer> set, int size, int times, int strategy) {
		int M = size << 1;
		int nextValue;
		for (int i = 0; i < times; i++) {
			do {
				nextValue = random.nextInt(M);
			} while (bitSet.get(nextValue));
			set.add(nextValue);
			bitSet.set(nextValue);
//			System.out.println("insert: " + nextValue);
//			set.printTreePreOrder(set.root);
//			System.out.println();
//			set.printTreeInorder(set.root);
//			System.out.println();
			
			do {
				nextValue = random.nextInt(M);
			} while (!bitSet.get(nextValue));
			set.remove(nextValue, strategy);
			bitSet.set(nextValue, false);
//			System.out.println("remove: " + nextValue);
//			set.printTreePreOrder(set.root);
//			System.out.println();
//			set.printTreeInorder(set.root);
//			System.out.println();
//			System.out.println("size=" + set.size);
//			System.out.println();
		}
	}
	
	public void remove(AnyType item, int strategy) {
		root = this.remove(item, root, strategy);
	}
	
private BinaryNode<AnyType> remove(AnyType item, BinaryNode<AnyType> t, int strategy) {
		if (t == null)
			return t;
		int compareResult = item.compareTo(t.value);
		if (compareResult < 0) {
			// remove(item, t.left); //bug3
			t.left = remove(item, t.left, strategy);
		} else if (compareResult > 0) {
			// remove(item, t.right); //bug4
			t.right = remove(item, t.right, strategy);
		} else if (t.left != null && t.right != null) {
//			System.out.println("enter ");
			//without break in case 1,2,3 leads to dead loop
			switch (strategy) {
			case 0:
				t = removeStrategyLeft(t, strategy);break;
			case 1:
				t = removeStrategyRight(t, strategy);break;
			case 2:
				t = removeStrategy2(t, strategy);break;
			case 3:
				t = removeStrategy3(t, strategy);break;
			default:
				break;
			}
		} else {
			size--;
			modCount++;
			t.prior.succ = t.succ;
			t.succ.prior = t.prior;
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}
	
	private BinaryNode<AnyType> removeStrategyLeft(BinaryNode<AnyType> t, int strategy) {
		if (t== null)
			return null;
		if (t.left != null && t.right != null) {
			t.value = findMax(t.left).value;
			t.left = remove(t.value, t.left, strategy);
		} else {
			size--;
			modCount++;
			t.prior.succ = t.succ;
			t.succ.prior = t.prior;
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}
	
	private BinaryNode<AnyType> removeStrategyRight(BinaryNode<AnyType> t, int strategy) {
		if (t== null)
			return null;
		if (t.left != null && t.right != null) {
			t.value = findMin(t.right).value;
			t.right = remove(t.value, t.right, strategy);
		} else {
			size--;
			modCount++;
			t.prior.succ = t.succ;
			t.succ.prior = t.prior;
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}
	
	private static boolean isLeftTurn = true;
	private BinaryNode<AnyType> removeStrategy2(BinaryNode<AnyType> t, int strategy) {
		if (t== null)
			return null;
		if (isLeftTurn) {
			t = removeStrategyLeft(t, strategy);
		}
		else {
			t = removeStrategyRight(t, strategy);
		}
		isLeftTurn = !isLeftTurn;
		return t;
	}
	
	private static Random random = new Random();
	private BinaryNode<AnyType> removeStrategy3(BinaryNode<AnyType> t, int strategy) {
//		System.out.println("enter 3");
		if (t== null)
			return null;
		int judge = random.nextInt(2);
		if (judge == 0) {
			t = removeStrategyLeft(t, strategy);
		} else {
			t = removeStrategyRight(t, strategy);
		}
		return t;
	}
	
	public static void createASet(MyTreeSetDiffRemoveStrategy<Integer> set, int size) {
		bitSet = new BitSet(size);
		int nextValue;
		for (int i = 0; i < size; i++) {
			do {
				nextValue = random.nextInt(size);
			} while (bitSet.get(nextValue));
			set.add(nextValue);
			bitSet.set(nextValue);
//			System.out.print(nextValue + "\t");
		}
//		System.out.println();
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
		if (t == null)
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
	
	public void printTreeInorder(BinaryNode<AnyType> root) {
		if (root == null)
			return;
		printTreeInorder(root.left);
		System.out.print(root.value + "\t");
		printTreeInorder(root.right);
	}
	
	public void printTreePreOrder(BinaryNode<AnyType> root) {
		if (root == null)
			return;
		System.out.print(root.value + "\t");
		printTreePreOrder(root.left);
		printTreePreOrder(root.right);
	}

	private BinaryNode<AnyType> root;
	private BinaryNode<AnyType> header = new BinaryNode(-1);
	private BinaryNode<AnyType> tail = new BinaryNode(-1);
	private int modCount = 0;

	private static class BinaryNode<AnyType> {
		BinaryNode(AnyType value) {
			this(value, null, null, null, null, null);
		}

		BinaryNode(AnyType value, BinaryNode<AnyType> left,
				BinaryNode<AnyType> right, BinaryNode<AnyType> parent,
				BinaryNode<AnyType> prior, BinaryNode<AnyType> succ) {
			this.value = value;
			this.left = left;
			this.right = right;
			this.prior = prior;
			this.succ = succ;
		}

		AnyType value;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		BinaryNode<AnyType> prior;
		BinaryNode<AnyType> succ;
	}

	public Iterator<AnyType> iterator() {
		return new MyTreeSet2Iterator();
	}

	private class MyTreeSet2Iterator implements Iterator<AnyType> {

		@Override
		public boolean hasNext() {
			return !atEnd;
		}

		// find the next larger one
		@Override
		public AnyType next() {
			// System.out.println(expectedModCount + " " + modCount);
			if (expectedModCount != modCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			AnyType nextItem = current.value;
			previous = current;
			current = current.succ;
//			if (current.right != null) {
//				// current = findMin(current); //bug 6
//				current = findMin(current.right);
//			} else {// find the first ancestor which it is left of
//				BinaryNode<AnyType> child = current;
//				current = current.parent;
//				while (current != null && current.left != child) {
//					child = current;
//					current = current.parent;
//				}
//				if (current == null)
//					atEnd = true;
//			}
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();
			MyTreeSetDiffRemoveStrategy.this.remove(previous.value, 1);
			okToRemove = false;
			expectedModCount = modCount;
		}

		private BinaryNode<AnyType> current = findMin(root);
		private int expectedModCount = modCount;
		private boolean atEnd = false;
		private BinaryNode<AnyType> previous;
		private boolean okToRemove = false;
	}

	public MyTreeSetDiffRemoveStrategy() {
		root = null;
		header.succ = tail;
		tail.prior = header;
	}

	public void printTree() {
		printTree(root);
	}
	
	public void printTreeList() {
		BinaryNode<AnyType> curr = header.succ;
		while (curr != tail) {
			System.out.print(curr.value + "\t");
			curr = curr.succ;
		}
		System.out.println();
	}
	public void makeEmpty() {
		root = null;
		modCount = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}
	
	private int size = 0;
	public int size() {
		return this.size;
	}

	public void add(AnyType item) {
		root = this.add(item, root, null, header, tail);
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

	private BinaryNode<AnyType> add(AnyType item, BinaryNode<AnyType> t,
			BinaryNode<AnyType> pt, BinaryNode<AnyType> prev, BinaryNode<AnyType> next) {
		if (t == null) {
			size++;
			modCount++;
			BinaryNode<AnyType> newNode = new BinaryNode<AnyType>(item, null,
					null, pt, prev, next);
			if (prev != null) {
				prev.succ = newNode;
			}
			if (next != null) {
				next.prior = newNode;
			}
			return newNode;
		}
		int compareResult = item.compareTo(t.value);
		if (compareResult < 0) {
			// add(item, t.left, t); //bug1
			t.left = add(item, t.left, t, t.prior, t);
		} else if (compareResult > 0) {
			// add(item, t.right, t); //bug2
			t.right = add(item, t.right, t, t, t.succ);
		} else {
			;// duplicate
		}
		return t;
	}

	

}


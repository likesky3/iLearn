package org.work.basic.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeSet;

public class HashSet_TreeSet {
	public static void main(String[] args) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();

		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int num = rand.nextInt();
			System.out.print(num + " ");
			hashSet.add(num);
			treeSet.add(num);
			linkedHashSet.add(num);
		}
		System.out.println();

		Integer[] a = new Integer[hashSet.size()];
		hashSet.toArray(a);
		Integer[] b = new Integer[treeSet.size()];
		treeSet.toArray(b);
		Integer[] c = new Integer[linkedHashSet.size()];
		linkedHashSet.toArray(c);

		for (int i : a)
			System.out.print(i + " ");
		System.out.println();
		for (int j : b)
			System.out.print(j + " ");
		System.out.println();
		for (int i : c)
			System.out.print(i + " ");
		System.out.println();
		
	}
}

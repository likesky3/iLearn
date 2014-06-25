package org.work.weiss.chap3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Exe02_Josephus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exe02_Josephus.solveJosephus(0, 5);
		System.out.println();
		Exe02_Josephus.solve2(0, 5);
		System.out.println("==========");
		Exe02_Josephus.solveJosephus(1, 5);
		Exe02_Josephus.solve2(1, 5);
		System.out.println("=========");
		Exe02_Josephus.solveJosephus(2, 10);
		Exe02_Josephus.solve2(2, 10);
	}
	
	//O(N^2), 09:30 - 10:30
	//main idea: locate the to-be deleted element, remove it, its index becom the beginer index, 
	//loop till the list only has one element
	//pos: (0,5),(1, 5) neg:(-1, 2), (0, -1)
	public static void solveJosephus (int M, int N) {
		if (M < 0 || N <= 0)
			return;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) 
			list.add(i + 1);
		
		int beg = 0;
		int target;
		while (list.size() > 1) {
			target = (beg + M) % list.size();
			System.out.print(list.get(target) + "\t");
			list.remove(target); //使用了solve2中的取最近一半技巧
			beg = target;
		}
		System.out.println();
		System.out.println("Winner is " + list.get(0));
	}
	
	public static void solve2(int m, int n) {
		ArrayList<Integer> L = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			L.add(i);
		ListIterator<Integer> iter = L.listIterator();
		int item = 0;
		int numLeft = n;
		
		for (int i = 0; i < n; i++) {
			int mPrime = m % numLeft;
			if (mPrime <= numLeft / 2) {
				if (iter.hasNext())
					item = iter.next();
				for (int j = 0; j < mPrime; j++) {
					if (!iter.hasNext())
						iter = L.listIterator();
					item = iter.next();
				}
			} else {
				for (int j = 0; j < numLeft - mPrime; j++) {
					if (!iter.hasPrevious())
						iter = L.listIterator(L.size());
					item = iter.previous();
				}
			}
			System.out.print("Removed " + item + " ");
			iter.remove();
			if (!iter.hasNext())
				iter = L.listIterator();
			System.out.println();
			for (Integer x : L)
				System.out.print(x + " ");
			System.out.println();
			numLeft--;
		}
		System.out.println();
	}
	

}

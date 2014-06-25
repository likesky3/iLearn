package org.work.weiss.chap3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Exe01 {
	public static void main(String[] args) {
		ArrayList<Integer> AL = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			AL.add(i);
		ArrayList<Integer> AP = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			AP.add(i + 1);
		Exe01.printLots(AL, AP);
		
		System.out.println();
		LinkedList<Integer> LL = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			LL.add(i);
		LinkedList<Integer> LP = new LinkedList<>();
		for (int i = 0; i < 5; i++)
			LP.add(i + 1);
		Exe01.printLots(LL, LP);
	}
	
	public static void printLots(List<Integer> L, List<Integer> P) {
//		for (int item : L)
//			System.out.print(item + " ");
		for (int i : P) {
//			L.remove(i);
			System.out.print(L.get(i) + " ");
		}
//		System.out.println("\n===========\n");
//		for (int item : L)
//			System.out.print(item + " ");
	}
}

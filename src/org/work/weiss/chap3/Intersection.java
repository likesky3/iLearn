package org.work.weiss.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Intersection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> aList = Arrays.asList(1,3,5,6,10,13,15);
		List<Integer> bList = Arrays.asList(2,3,6,7,9,13);
//		List<Integer> result = Intersection.getIntersection(aList, bList);
		List<Integer> result = Intersection.getUnion(aList, bList);
		for (int item : result)
			System.out.print(item + " ");
		System.out.println();
	}
	
	public static List<Integer> getIntersection(List<Integer> aList, List<Integer> bList) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (aList == null && bList == null)
			return result;
		if (aList == null)
			return bList;
		if (bList == null)
			return aList;
		if (aList.size() == 0 && bList.size() == 0)
			return result;
		
		int alen = aList.size();
		int blen = bList.size();
		int ai = 0, bi = 0;
//		System.out.println(alen + " " + blen);
		while(ai < alen && bi < blen ) {
			int acurr = aList.get(ai);
			int bcurr = bList.get(bi);
//			System.out.println("acurr=" + acurr + ", bcurr=" + bcurr);
			if (acurr == bcurr) {
				result.add(acurr);
				ai++;
				bi++;
			}
			else if (acurr < bcurr)
				ai++;
			else
				bi++;
//			System.out.println(ai + " " + bi);
		}
		return result;
	}
	
	public static List<Integer> getUnion (List<Integer> aList, List<Integer> bList) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (aList == null && bList == null)
			return result;
		if (aList == null)
			return bList;
		if (bList == null)
			return aList;
		if (aList.size() == 0 && bList.size() == 0)
			return result;
		
		int alen = aList.size();
		int blen = bList.size();
		int ai = 0, bi = 0;
		while(ai < alen && bi < blen) {
			int acurr = aList.get(ai);
			int bcurr = bList.get(bi);
			if (acurr == bcurr) {
				result.add(acurr);
				ai++;
				bi++;
			} else if (acurr < bcurr) {
				result.add(acurr);
				ai++;
			} else {
				result.add(bcurr);
				bi++;
			}
		}
		if (ai < alen) {
			for (int i = ai; i < alen; i++)
				result.add(aList.get(ai));
		}
		if (bi < blen) {
			for (int i = bi; i < blen; i++)
				result.add(bList.get(bi));
		}
		return result;
	}
	
	public static <T extends Comparable<? super T>> void Union(List<T> L1, List<T> L2, List<T> result) {
		ListIterator<T> iter1 = L1.listIterator();
		ListIterator<T> iter2 = L2.listIterator();
	}
	
	
	

}

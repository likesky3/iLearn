package org.work.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

//08.24 1 hour
public class E302_OccurMostNums {

	public static void main(String[] args) {
		E302_OccurMostNums obj = new E302_OccurMostNums();
		int[] res = obj.selectOccurMostNum(new int[] { 0, 1, 4, 4, 4, 3, 2, 2,
				2 });
		for (int i : res)
			System.out.print(i);
	}

	/*
	 * input: {0, 1, 4, 4, 4, 3,2,2,2} output: {2, 4}
	 */
	public int[] selectOccurMostNum(int[] num) {
		if (num == null || num.length == 1)
			return num;

		ArrayList<Integer> ret = new ArrayList<Integer>();

		// s1: sort input array num[]
		insertSort(num);
		// Arrays.sort(num);

		// s2: counter occurrence of each number in num[]
		// key: counter, value:corresponding numbers
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int counter = 1;
		int max = 1;
		for (int i = 1; i < num.length; i++) {
			if (num[i] == num[i - 1])
				counter++;
			if(num[i] != num[i - 1] || i == (num.length - 1)) {
				if (!map.containsKey(counter)) {
					ArrayList<Integer> values = new ArrayList<Integer>();
					values.add(num[i - 1]);
					map.put(counter, values);
				} else {
					map.get(counter).add(num[i - 1]);
				}
				if (counter > max)
					max = counter;

				counter = 1; // reset counter
			}
		}

		// s3: select occur most numbers indicated by max
		ret = map.get(max);
		int[] result = new int[ret.size()];
		for (int i = 0; i < ret.size(); i++) {
			result[i] = ret.get(i);
		}
		// Integer[] result = (Integer[]) ret.toArray(new Integer[ret.size()]);
		return result;
	}

	private void insertSort(int[] num) {
		if (num == null || num.length < 2)
			return;

		for (int i = 1; i < num.length; i++) {
			int currElem = num[i];
			int j = i - 1;
			for (; j >= 0 && num[j] > currElem; j--) {
				num[j + 1] = num[j];
			}
			num[j + 1] = currElem;
		}
	}
}

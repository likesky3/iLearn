package org.work.leetcode;

import java.util.Arrays;

public class E085_3SumClosest {

	public int threeSumClosest(int[] num, int target) {
		Arrays.sort(num);
		int res = 0;
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i < num.length - 2; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int p = i + 1;
			int q = num.length - 1;
			while (p < q) {
				int sum = num[i] + num[p] + num[q];
				int newDiff = sum - target;

				if (Math.abs(newDiff) < diff) {
					diff = Math.abs(newDiff);
					res = sum;
					if (newDiff < 0)
						p++;
					else if (newDiff > 0)
						q--;
					else
						break;
				} else if (newDiff > 0)
					q--;
				else {
					p++;
				}
			}
		}
		return res;
	}
}

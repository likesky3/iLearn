package org.work.leetcode;

import java.util.Arrays;

public class E085_3SumClosest {

	public int threeSumClosest(int[] num, int target) {
		// sort
		Arrays.sort(num);

		int res = 0;
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i < num.length - 2; i++) {
			// remove duplicates
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

	// version 2, much neater code
	public int threeSumClosest2(int[] num, int target) {
		Arrays.sort(num);

		int res = 0;
		int min = Integer.MAX_VALUE;
		for (int k = 0; k < (num.length - 2); k++) {
			if (k > 0 && num[k] == num[k - 1])
				continue;
			for (int i = k + 1, j = num.length - 1; i < j;) {
				int sum3 = num[k] + num[i] + num[j];
				int diff = sum3 - target;
				if (Math.abs(diff) < min) {
					min = Math.abs(diff);
					res = diff + target;
				}
				if (diff < 0) {
					while (++i < j && num[i] == num[i - 1])
						;
				} else if (diff > 0) {
					while (--j > i && num[j] == num[j + 1])
						;
				} else
					return target;
			}

		}
		return res;
	}
}

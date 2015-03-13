package org.work.spotoffer;

public class E34_UglyNumber {

	public int getUglyNumber(int k) {
		if (k <= 0)
			return 0;
		int[] uglys = new int[k];
		uglys[0] = 1;

		int m2 = 1, m3 = 1, m5 = 1;
		int p2 = 0, p3 = 0, p5 = 0;
		int next = 0;
		for (int i = 1; i < k; i++) {
			// find the next ugly
			m2 = 2 * uglys[p2];
			m3 = 3 * uglys[p3];
			m5 = 5 * uglys[p5];
			next = m2;
			if (m3 < next)
				next = m3;
			if (m5 < next)
				next = m5;
			uglys[i] = next;

			// update p2, p3, p5 accordingly
			if (m2 == next)
				p2++;
			if (m3 == next)
				p3++;
			if (m5 == next)
				p5++;

		}
		return uglys[k - 1];
	}
}

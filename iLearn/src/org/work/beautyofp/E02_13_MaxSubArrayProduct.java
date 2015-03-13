package org.work.beautyofp;

//O(N)
public class E02_13_MaxSubArrayProduct {
	public static void main(String[] args) {
		E02_13_MaxSubArrayProduct obj = new E02_13_MaxSubArrayProduct();
		int[] a = { -4, -5, -9, -2, -3, -7 };
		int res = obj.maxSubProduct(a);
		System.out.println(res);
	}
	
	public int maxSubProduct(int[] a) {
		// scan the array and book some information
		int zeroNum = 0;
		int negNum = 0;

		int minPos = Integer.MAX_VALUE;
		int minNeg = 0;
		int maxNeg = Integer.MIN_VALUE;

		for (int elem : a) {
			if (elem == 0)
				zeroNum++;
			else if (elem > 0 && elem < minPos)
				minPos = elem;
			else if (elem < 0) {
				negNum++;
				if (elem > maxNeg)
					maxNeg = elem;
				if (elem < minNeg)
					minNeg = elem;
			}
		}

		System.out.println("#0=" + zeroNum + "; ");
		System.out.println("minPos: " + minPos + ";");
		System.out.println("minNeg: " + minNeg + "; ");
		System.out.println("maxNeg:" + maxNeg + ";");
		// let p = a[0] * a[1] * ... * a[n - 1]
		boolean evenNegNum = (negNum & 1) == 1 ? false : true;
		// 1, p = 0
		// 1.2 more than one zero elements, return 0
		if (zeroNum > 1)
			return 0;
		// 1.1, one zero element, let the rest be q
		if (zeroNum == 1) {
			if (!evenNegNum)// q < 0, return 0
				return 0;
			else {// q > 0, return q
				return calProduct(a, 0);
			}
		}

		// 2, p > 0
		if (evenNegNum) {
			// 2.1: has positive, exclude the minimum
			if (negNum < a.length) 
				return calProduct(a, minPos);
			// 2.2: all negative, exclude the smallest negative number
			else
				return calProduct(a, minNeg);
		}

		// 3, p < 0: exclue the largest negative number
		return calProduct(a, maxNeg);
	}

	private int calProduct(int[] a, int exception) {
		System.out.println("excluded: " + exception);
		int res = 1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == exception)
				continue;
			res *= a[i];
		}
		return res;
	}
}

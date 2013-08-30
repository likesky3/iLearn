package org.work.leetcode;

//the zigzag shape is not N, but mirror of N(up down,then down up)
public class E077_ZigZagConvention {
	public static void main(String[] args) {
		E077_ZigZagConvention obj = new E077_ZigZagConvention();
		System.out
				.println(obj
						.convert2(
								"oiexpqhmebhhufehespkahcyngbhudzindgevprzqaikfotkiiwkpyjfgmoapnjetrjogcqweajjrevzntkervlzhaaznlfi",
								89));
	}

	// space: nRows * cols;
	public String convert(String s, int nRows) {

		if (nRows < 2)
			return s;
		StringBuffer newStr = new StringBuffer();
		int len = s.length();
		// step1:calculate the needed columns
		// failed at len = 5, nRows = 4
		// int cols = ((len / ((nRows << 1) - 2)) * (nRows - 1)) + 1; //!!!
		// operator << is inferior than -
		int halfZigs = 0;
		halfZigs += len / ((nRows << 1) - 2);
		int cols2 = 0;
		int remains = len % ((nRows << 1) - 2);
		if (remains > 0)
			cols2++;
		if (remains >= nRows) {
			remains -= nRows;
			cols2 += remains;
		}

		int cols = halfZigs * (nRows - 1) + cols2;

		char[][] zigzag = new char[nRows][cols];

		// step2:fill s into ziazag structure
		int i = 0;
		int col = 0;

		while (i < len) {
			for (int row = 0; row < nRows; row++) {
				if (i < len)
					zigzag[row][col] = s.charAt(i);
				else
					break;
				i++;

			}
			for (int row = nRows - 2; row > 0; row--) {
				if (i < len)
					zigzag[row][++col] = s.charAt(i);
				else
					break;
				i++;
			}
			col++;
			// i++;
		}

		// step3: form new string line by line
		for (int row = 0; row < nRows; row++) {
			for (col = 0; col < cols; col++) {
				if (zigzag[row][col] != 0)// without this statement, println
											// newStr appears correct, check its
											// length will spot the problem
					newStr.append(zigzag[row][col]);
			}
		}
		return newStr.toString();
	}

	// memory limit exceed ???
	public String convert2(String s, int nRows) {
		int zigsize = (nRows << 1) - 2;
		StringBuffer sb = new StringBuffer();
		int i = 0;
		int internalStep = 0;
		int ii = 0;
		for (int row = 0; row < nRows; row++) {
			i = row;

			while (i < s.length()) {
				sb.append(s.charAt(i));
				if (row > 0 && row < nRows - 1) {
					internalStep = zigsize - (row << 1);
					ii = i + internalStep;
					if (ii >= s.length())
						break;
					sb.append(s.charAt(ii));
				}
				i += zigsize;
			}
		}
		return sb.toString();
	}
}

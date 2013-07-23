package org.work.leetcode;

import java.util.ArrayList;

public class E016_Triangle {
	public static void main(String[] args) {

	}

	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		// Start typing your Java solution below
		// DO NOT write main() function

		if (triangle == null || triangle.size() == 0)
			return 0;

		if (triangle.size() == 1)
			return triangle.get(0).get(0);

		// image the triangle as a special tree which adjacent nodes have a
		// common child node
		// bottom up manner
		// in each layer, select the smaller child and add it to its parent node
		int size = triangle.size();
		for (int row = size - 1; row > 0; row--) {
			for (int col = 0; col < row; col++) {
				int left = triangle.get(row).get(col);
				int right = triangle.get(row).get(col + 1);
				int parent = triangle.get(row - 1).get(col);
				if (left > right)
					triangle.get(row - 1).set(col, parent + right);
				else
					triangle.get(row - 1).set(col, parent + left);
			}
		}

		return triangle.get(0).get(0);
	}
}

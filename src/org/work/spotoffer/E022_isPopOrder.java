package org.work.spotoffer;

import java.util.Scanner;
import java.util.LinkedList;

public class E022_isPopOrder {

	public static boolean isPopSequence(int[] in, int[] out) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int len = in.length;
		int i = 0, j = 0;
		while (j < len) {
			while (stack.isEmpty() || stack.peek() != out[j]) {
				if (i == len)// all the elements have been pushed in the stack
					return false;
				stack.push(in[i++]);
			}
			stack.pop();
			j++;
		}
		return true;
	}

	public static void main(String[] args) {
		int N;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			N = scanner.nextInt();
			int[] a = new int[N];
			int[] b = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = scanner.nextInt();
			}
			for (int i = 0; i < N; i++) {
				b[i] = scanner.nextInt();
			}
			if (isPopSequence(a, b)) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
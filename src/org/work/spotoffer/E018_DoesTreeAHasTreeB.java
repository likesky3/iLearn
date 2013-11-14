package org.work.spotoffer;

import java.util.Scanner;

public class E018_DoesTreeAHasTreeB {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			int n1 = cin.nextInt();
			int n2 = cin.nextInt();
			int[] tree1Val = new int[n1];
			int[] tree2Val = new int[n2];
			int[][] tree1Struct = new int[n1][3];
			int[][] tree2Struct = new int[n2][3];

			readTreeValue(n1, tree1Val, cin);
			readTreeStruct(n1, tree1Struct, cin);
			readTreeValue(n2, tree2Val, cin);
			readTreeStruct(n2, tree2Struct, cin);
			
			System.out.println();
			
//			printTreeVal(tree1Val);
//			printTreeStruct(tree1Struct);
//			printTreeVal(tree2Val);
//			printTreeStruct(tree2Struct);
			
			boolean res = doesTreeAHasTreeB(tree1Val, tree1Struct, 0, tree2Val, tree2Struct, 0);
			System.out.println(res ? "YES" : "NO");
		}
	}
	public static void printTreeVal(int[] treeVal){
		int n = treeVal.length;
		for(int i = 0; i < n; i++)
			System.out.print(treeVal[i] + "\t");
		System.out.println();
	}
	
	public static void printTreeStruct(int[][] treeStruct){
		int d1 = treeStruct.length;
		int d2 = treeStruct[0].length;
		for(int i = 0; i < d1; i++){
			for(int j = 0; j < d2; j++){
				System.out.print(treeStruct[i][j] + "\t");
			}
			System.out.println();
		}
				
	}
	public static void readTreeValue(int n, int[] treeValue, Scanner cin) {
		for (int i = 0; i < n; i++) {
			treeValue[i] = cin.nextInt();
		}
	}

	public static void readTreeStruct(int n, int[][] treeStruct, Scanner cin) {
		for (int i = 0; i < n; i++) {
			int childNum = cin.nextInt();
			treeStruct[i][0] = childNum;
			if (childNum == 0) {
				treeStruct[i][1] = -1;
				treeStruct[i][2] = -1;
			}else if(childNum == 1){
				treeStruct[i][1] = cin.nextInt() - 1;
				treeStruct[i][2] = -1;
			}
			else if(childNum == 2){
				treeStruct[i][1] = cin.nextInt() - 1;
				treeStruct[i][2] = cin.nextInt() - 1;
			}
		}
	}

	public static boolean doesTreeAHasTreeB(int[] tree1Val,
			int[][] tree1Struct, int i1, int[] tree2Val, int[][] tree2Strcut,
			int i2) {
		if (tree1Val == null || tree2Val == null)
			return false;

		boolean res = false;
		int left1 = tree1Struct[i1][1];
		int right1 = tree1Struct[i1][2];

		if (tree1Val[i1] == tree2Val[i2])
			res = verify(tree1Val, tree1Struct, i1, tree2Val, tree2Strcut, i2);
		if (!res && tree1Struct[i1][1] != -1)
			res = doesTreeAHasTreeB(tree1Val, tree1Struct, left1, tree2Val,
					tree2Strcut, i2);
		if (!res && tree1Struct[i1][2] != -1)
			res = doesTreeAHasTreeB(tree1Val, tree1Struct, right1, tree2Val,
					tree2Strcut, i2);
		return res;
	}

	public static boolean verify(int[] tree1Val, int[][] tree1Struct, int i1,
			int[] tree2Val, int[][] tree2Struct, int i2) {
		if (i2 == -1)
			return true;
		if (i1 == -1)
			return false;
		if (tree1Val[i1] != tree2Val[i2])
			return false;

		int left1 = tree1Struct[i1][1];
		int right1 = tree1Struct[i1][2];
		int left2 = tree2Struct[i2][1];
		int right2 = tree2Struct[i2][2];
		return verify(tree1Val, tree1Struct, left1, tree2Val, tree2Struct,
				left2)
				&& verify(tree1Val, tree1Struct, right1, tree2Val, tree2Struct,
						right2);
	}

}

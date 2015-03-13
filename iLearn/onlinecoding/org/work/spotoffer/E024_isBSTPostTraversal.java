package org.work.spotoffer;

public class E024_isBSTPostTraversal {

	//messy code
		public static boolean verify(int[] a, int left, int right){
			int total = right - left + 1;
			if(total < 3)
				return true;
			
			int root = a[right];
			
			//split left & right subtree
			int leftEnd = left - 1;
			while(a[leftEnd + 1] < root)
				leftEnd++;
			
			int rightStart = leftEnd < left ? left : (leftEnd + 1);
			
			//verify left & right subtree
			boolean leftIsBST = true, rightIsBST = true;
			if(leftEnd >= left){//if has left subtree
				leftIsBST = verify(a, left, leftEnd);
			}
			
			if(rightStart < right){//if has right subtree
				if(a[right - 1] < root)
					return false;
				rightIsBST = verify(a, rightStart, right - 1);
			}
			return leftIsBST && rightIsBST;
		}
		
		//ref others
		public static boolean verify2(int[] a, int left, int right){
			if(right - left < 2)
				return true;
			
			int root = a[right];
			
			//left subtree[left, i - 1]
			int i = left;
			while(a[i] < root){
				i++;
			}
			
			//check nodes in  right subtree
			int j = i;
			while(j < right){
				if(a[j++] < root) return false;
			}
			
			boolean tLeft = verify2(a, left, i - 1);
			boolean tRight = verify2(a, i, right - 1);
			return tLeft && tRight;
		}

}

package org.work.DP;

public class LIS {

	public static void main(String[] args) {
		int[] a = {1, -4, 5, -8, 10, 12};
//		int p = insert(a, 0, a.length - 1, 9);
		System.out.println(getLIS(a));
	}
	
	public static int getLIS(int[]a){
		int[] len = new int[a.length];
		int[] maxV = new int[a.length + 1];
		maxV[1] = a[0];
		int max = 1;
		//initial len[]
		for(int i = 0; i < a.length; i++){
			len[i] = 1;
		}
		for(int i = 1; i < a.length; i++){
			len[i] = insert(maxV, 1, max, a[i]);
			if(len[i] > max){
				max = len[i];
				maxV[max] = a[i];
			}else if(a[i] < maxV[len[i]])
				maxV[len[i]] = a[i];
			
		}
		return len[a.length - 1];
	}
	public static int insert(int[] a, int left, int right, int k){
		if(left > right)
			return -1;
		if(left == right)
			return k >= a[left] ? left + 1 : left;
		int mid = left + (right - left >> 1);
		if(a[mid] >= k)
			return insert(a, left, mid - 1, k);
		else
			return insert(a, mid + 1, right, k);
	}
}

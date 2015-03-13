package org.work.leetcode;

//08.24 08:02 - 08:55, 53
public class E301_ClosenessSort {

	public static void main(String[] args) {
		E301_ClosenessSort obj = new E301_ClosenessSort();
		int[] num = new int[] { -1,0,1,2,3,4,5,6,7,8,9,10 };
		obj.doClosenessSort(num, 5);
		for (int n : num)
			System.out.println(n);
	}

	/*
	 * input: num = {5, 3, 18, 1, 0} & value = 5 output: {5, 3, 1, 0, 18}
	 */
	public int[] doClosenessSort(int[] num, int value) {
		if(num == null || num.length < 2)
			return num;
		
		//s1, get deviate[]
		int[] deviate = new int[num.length];
		for(int i = 0; i < num.length; i++){
			deviate[i] = num[i] - value;
		}
		
		//s2, sort according to closeness
		variedInsertSort(num, deviate);
		return num;
	}

	private void variedInsertSort(int[] num, int[] deviate) {
		for (int i = 1; i < num.length; i++) {
			int currD = deviate[i];
			int absD = currD < 0 ? -currD : currD;
			int currNum = num[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				int tmp = deviate[j] < 0 ? -deviate[j] : deviate[j];
				if (tmp > absD || (deviate[j] == -currD && currD < 0)) {
					deviate[j + 1] = deviate[j];
					num[j + 1] = num[j];
				}else
					break;
			}
			deviate[j + 1] = currD;
			num[j + 1] = currNum;
//			System.out.println("-------- i:" + i);
//			for (int n : deviate)
//				System.out.println(n);
		}
	}
	//simple idea, sort according the abstract value and then swap each neighbor pair
}

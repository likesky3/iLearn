package org.work.leetcode;

public class E061_TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[]{5,4,3,2,1};
		int[] ind = new int[]{1,2,3,4,5};
		E061_TwoSum obj = new E061_TwoSum();
		obj.sort(num, ind);

	}
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		int[] index = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++){
			index[i] = i + 1;
		}
		sort(numbers, index);
		
		int i = 0, j = numbers.length - 1;
		while(i < j){
			int sum = numbers[i] + numbers[j];
			if(sum == target){
				if(index[i] > index[j]){
					res[0] = index[j];
					res[1] = index[i];
				}else {
					res[0] = index[i];
					res[1] = index[j];
				}
				break;
			}
			else if(sum > target)
				j--;
			else {
				i++;
			}
			
		}
		if(i == j){
			//indicates not found
			res[0] = -1;
			res[1] = -1;
		}
			
		return res;
				
	}
	public void sort(int[] num, int[] index) {
		for (int i = 1; i < num.length; i++) {
			int j = i;
			int tmp = num[i];
			int tmpIndex = index[i];
			for (; j > 0; j--) {
				if (tmp < num[j - 1]){
					num[j] = num[j - 1];
					index[j] = index[j - 1];
				}
				else {
					break;
				}
			}
			num[j] = tmp;
			index[j] = tmpIndex;
		}
	}
}

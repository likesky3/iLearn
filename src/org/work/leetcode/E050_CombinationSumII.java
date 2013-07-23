package org.work.leetcode;

import java.util.ArrayList;

//0-1 knapsack problem
//can you think of these test case:
//[1,1,2,5,6,7,10], target = 8
//[2,2,2], target = 4
//[1,1,1,3,5,3], target = 8
public class E050_CombinationSumII {

	 public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		 ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
			if (num.length == 0)
				return res;
			
			//sort num array
			Integer[] sortedNum= new Integer[num.length];
			for(int i = 0; i < num.length; i++)
				sortedNum[i] = num[i];
			doSort(sortedNum);
			
			if(sortedNum[0] > target)
				return res;
			
			ArrayList<ArrayList<ArrayList<Integer>>> storage = new 
					ArrayList<ArrayList<ArrayList<Integer>>>(target - sortedNum[0] + 1);
			//initial storage ArrayList
			for(int i = 0; i < target - sortedNum[0] + 1; i++)
				storage.add(new ArrayList<ArrayList<Integer>>());

			int bound = 0, accu = 0;
			for (int i = 0; i < sortedNum.length; i++) {
				accu += sortedNum[i];
				bound = (target > accu) ? accu : target; //especially useful when accu is much smaller than target
				for (int v = bound; v >= sortedNum[i]; v--) { //0-1 knapsack problem feature: v starts big, ends in small
					if (v == sortedNum[i]) {
						if( i > 0 && sortedNum[i] == sortedNum[i - 1])
							continue;
						ArrayList<Integer> elem = new ArrayList<Integer>();
						elem.add(sortedNum[i]);
						storage.get(v - sortedNum[0]).add(elem);
					} else if (v - sortedNum[i] >= sortedNum[0]) {
						for (ArrayList<Integer> item : storage.get(v - sortedNum[i] - sortedNum[0])) {
							ArrayList<Integer> newItem = new ArrayList<Integer>(
									item);
							newItem.add(sortedNum[i]);
							if(!storage.get(v-sortedNum[0]).contains(newItem)) //avoid duplicated items
								storage.get(v - sortedNum[0]).add(newItem);
						}
					}
				}
				
			}
			
			return storage.get(target - sortedNum[0]);
		}
		
		public static void doSort(Integer[] array) {
			if (array.length == 0 || 1 == array.length)
				return;

			for (int i = 1; i < array.length; i++) {
				int currElem = array[i];
				int j;
				for (j = i - 1; j >= 0 && currElem < array[j]; j--) {
					array[j + 1] = array[j];
				}
				array[j + 1] = currElem;
			}
		}
		
		public static void main(String[] args){
			E050_CombinationSumII obj = new E050_CombinationSumII();
			int[] c = {1,1,1,3,5,3};
			System.out.println(obj.combinationSum2(c, 8));
		}
}

package org.work.beautyofp;

import java.util.ArrayList;

//http://zhang-xzhi-xjtu.iteye.com/blog/478834
public class E02_21_ContinueIntSum {

	public static void main(String[] args) {
		E02_21_ContinueIntSum obj = new E02_21_ContinueIntSum();
		obj.givenSum2(9);
	}
	
	//@返回值数组中[0]元素是连续整数序列中的最小整数，[1]元素为连续整数中的最大整数
	//O(N)
	public ArrayList<int[]> givenSum(int given){
		ArrayList<int[]> res = new ArrayList<int[]>();
		int left = 1, right = 1;
		int sum = 0;
		
		for(; left < (given / 2 + 1); right++){
			sum += right;
			while(sum > given){
				sum -= left;
				left++;
			}
			if(sum == given && left != right){ //用left != right条件确保连续整数序列长度至少为2
				int[] solution = new int[2];
				solution[0] = left;
				solution[1] = right;
				res.add(solution);
				System.out.println(left + " " + right);
			}
		}
		if(res.isEmpty())
			System.out.println("no solution");
		return res;
	}
	
	//记问题的一个解为A=(a, i), a为连续整数中的最小整数，i为连续整数的长度
	//A = a + (a + 1) + ... + (a + i - 1) = a * i + (1 + 2 + ... + (i - 1))
	// 问题转换为求符合上述等式的a和i，
	//下面代码中sum = 1 + 2 + ... + (i - 1), a = tmp / i 
	//O(sqrt(N))
	public void givenSum2(int given){
		
		for(int i = 1, sum = 0; given > sum; i++){
			int tmp = given - sum;
//			System.out.println("---------------");
//			System.out.println("i: " + i);
//			System.out.println("sum: " + sum);
//			System.out.println("tmp: " + tmp);
			if(tmp % i == 0){
				System.out.println(tmp / i + " " + (tmp /i + i - 1));
			}
			sum += i;
		}
	}

}


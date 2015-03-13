package org.work.beautyofp;

//O(N)
public class E02_14_MaxSubarray {
	
	public static void main(String[] args) {
		E02_14_MaxSubarray obj = new E02_14_MaxSubarray();
		int[] a = {5,2,1,2};
//		obj.maxSubArray1(a);
		obj.maxSubArrayExt(a);
	}
	
	//version 0: only calculate maximum sum, space O(N)
		public int maxSubArray(int[] A) {
			int[] sum = new int[A.length];
			int max = A[0];
			sum[0] = A[0];
			
			for(int i = 1; i < A.length; i++){
				sum[i] = A[i];
				
				if(sum[i - 1] > 0){
					sum[i] += sum[i - 1] ;
				}
				if(sum[i] > max){
					max = sum[i];
				}
			}
			System.out.println(max);
			return max;
		}
		
	//version 1: only calculate maximum sum, space O(1)
	public int maxSubArray1(int[] A) {
		int lastSum = A[0];
		int sum = 0;
		int max = A[0];
		
		for(int i = 1; i < A.length; lastSum = sum, i++){
			sum = A[i];
			
			if(lastSum > 0){
				sum += lastSum;
			}
			if(sum > max){
				max = sum;
			}
		}
		System.out.println(max);
		return max;
	}
	//more neat code
	public int maxSubArray1_1(int[] A) {
		int sum = A[0];
		int max = A[0];
		
		for(int i = 1; i < A.length; i++){
			if(sum > 0){
				sum += A[i];
			}else
				sum = A[i];
			if(sum > max){
				max = sum;
			}
		}
		System.out.println(max);
		return max;
	}
	
	//version 2: book start & end index of max sub-array
		public int maxSubArray2(int[] A) {
			int lastSum = A[0];
			int sum = 0;
			int max = A[0];
			boolean restart = true;
			int start = 0, end = 0;
			
			for(int i = 1; i < A.length; lastSum = sum, i++){
				sum = A[i];
				
				if(lastSum > 0){
					sum += lastSum;
					restart = false;
				}
				if(sum > max){
					max = sum;
					end = i;
				}
				if(restart)
					start = i;
				
			}
			
			System.out.println("start: " + start + ", end: " + end);
			System.out.println("max:" + max); 
			return max;
		}
		
		public int maxSubArrayExt(int[] A){
			int n = A.length;
			int lastSum = A[0], sum = 0, max = A[0]; //for 求解没有跨过A[n - 1]到A[0]
			int end0 = 0, max0 = A[0], sum0 = A[0]; //用于求解从A[0]开始和最大的子数组，end0是这种数组的尾指针
			
			for(int i = 1; i < A.length; lastSum = sum, i++){
				sum = A[i];
				if(lastSum > 0){
					sum += lastSum;
				}
				if(sum > max){
					max = sum;
				}
				
				//求解从A[0]开始和最大的子数组
				sum0 += A[i];
				if(sum0 > max0){
					end0 = i;
					max0 = sum0;
				}
			}
			
			//求解以A[n - 1]结尾的和最大的子数组
			int startn = n - 1, sumN = A[n - 1], maxN = sumN ;
			for(int i = n -2; i >= 0; i-- ){
				sumN += A[i];
				if(sumN > maxN){
					startn = i;
					maxN = sumN;
				}
			}
			
			System.out.println("origin max: " + max);
			int sum2 = 0; //sum2 = A[0..end0] + A[startn .. n - 1];
			if(end0 < startn){
				sum2 = max0 + maxN;
				max = sum2 > max ? sum2 : max;//判断跨过A[n - 1]到A[0]的子数组和是否更大
			}
			System.out.println("max0: " + max0);
			System.out.println("maxN: " + maxN);
			System.out.println("sum2: " + sum2);
			System.out.println("finally sum: " + max);
			return max;
		}
}

package org.work.leetcode;

//http://www.cnblogs.com/daijinqiao/p/3352893.html
public class E102_SingleNumII {
	// 依次计数每个bit位上1的个数，若为3的倍数，说明目标数在该位上值为0，若次数mod3为1，说明目标数在该位值为1
	// O(32N)
	public int singleNumber(int[] A) {
		// if(A == null || A.length == 0)
		// return Integer.MIN_VALUE;
		if (A.length == 1)
			return A[0];

		int target = 0;
		int times = 0;// apperance times of 1 at bit k
		int oneAtBitK = 1;
		for (int k = 0; k < 32; k++) {
			times = 0;
			for (int i = 0; i < A.length; i++) {
				times += (A[i] >> k) & 1;// won't modify A[]
			}
			oneAtBitK <<= 1;
			if (times % 3 == 1)
				target += oneAtBitK;
		}
		return target;
	}

	// 非常巧妙的位运算，参考http://www.cnblogs.com/daijinqiao/p/3352893.html
	// 位运算均满足交换律和结合律，验证结果正确性时可以将相同的数字放在一起，目标是让三个相同的数进行一系列位运算后值为0
	// 注意到位运算时每一位都是独立的，所以目标可进一步简化为同一位置上若1出现3次则清零。
	// ones保存出现次数mod 3 为1的状态（1出现1次、3次后ones为1），twos保存出现次数为2的状态，则当ones & two
	// 为1时说明1出现了3次
	// (two, one) = 0次：(0,0)；1次：（0,1）；2次（1,0）；3次（1,1）
	public int singleNumber2(int[] A) {
		// if(A == null || A.length == 0)
		// return Integer.MIN_VALUE;
		if (A.length == 1)
			return A[0];

		int ones = 0, twos = 0, xthree = 0;
		for (int i = 0; i < A.length; i++) {
			twos |= (ones & A[i]);
			ones ^= A[i];

			// 当(twos,ones)=(1,1)时清零，为其它时保持原样
			xthree = ~(ones & twos);
			twos = xthree & twos;
			ones = xthree & ones;
		 } 
		return ones; 
	}

	//扩展题：1.整数x出现b次，其余均出现a次 2. 整数x出现b次，整数y出现c次，其余均出现a次
	public int appear1Others4(int[] A) {
		int one = 0, two = 0, four = 0, xfour = 0;
		for(int i = 0; i < A.length; i++){
			four |= one & two & A[i];
			two |= (one & A[i]);
			one ^= A[i];
			
			xfour = ~four;
			four = xfour & four;
			two = xfour & two;
			one = xfour & one;
		}
		return one;
	}
	
	public int appear2Others4(int[] A) {
		int one = 0, two = 0, four = 0, xfour = 0;
		for(int i = 0; i < A.length; i++){
			four |= one & two & A[i];
			two |= (one & A[i]);
			one ^= A[i];
			
			xfour = ~four;
			four = xfour & four;
			two = xfour & two;
			one = xfour & one;
		}
		return two;
	}
	
	public int appear3Others4(int[] A) {
		int one = 0, two = 0, four = 0, xfour = 0;
		for(int i = 0; i < A.length; i++){
			four |= one & two & A[i];
			two |= (one & A[i]);
			one ^= A[i];
			
			xfour = ~four;
			four = xfour & four;
			two = xfour & two;
			one = xfour & one;
		}
		return one & two;
	}
	
	public static void main(String[] args){
		E102_SingleNumII obj = new E102_SingleNumII();
		int[] A= { 3,3,4,4,5,5,5,5, 4, 4};
//		int res = obj.singleNumber2(A);
		int res = obj.appear2Others4(A);
		System.out.println(res);
	}
}

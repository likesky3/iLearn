package org.work.leetcode;

public class E089_Divide2Int {

	public static void main(String[] args) {
		E089_Divide2Int obj = new E089_Divide2Int();
		System.out.println(obj.divide2(-2147483648, -2147483648 ));
		
	}
	
	//version 1, 每次将被除数增加1倍，同时将count也增加一倍，如果超过了被除数，那么用被除数减去当前和再继续本操作。
	 public int divide(int dividend, int divisor) {
		 long  a = Math.abs((long) dividend); //both dividend & divisor should be cast to long type, or it will overflow when = -2147483648 
		 long b = Math.abs((long) divisor);
		 
		 long ret = 0;
		 while(a >= b){
			 long c = b;
			 for(int i = 0; a >= c;  i++, c <<= 1){
				 a -= c;
				 ret += 1 << i;
			 }
		 }
		 return( (dividend ^ divisor) >> 31) == 0 ? (int) ret : (int)-ret;
	 }
	 
	 //version 2, variation of version 1
	 public int divide2(int dividend, int divisor) {
		 long  a = Math.abs((long) dividend); //both dividend & divisor should be cast to long type, or it will overflow when = -2147483648 
		 long b = Math.abs((long) divisor);
		 
		 long ret = 0;
		 while(a >= b){
			 long c = b;
			 long counter = 1;
			while(a >= c){
				c <<= 1;
				 counter <<= 1;
			 }
			 counter >>= 1;
			 a -= c >> 1;
			ret += counter;
		 }
		 return( (dividend ^ divisor) >> 31) == 0 ? (int) ret : (int)-ret;
	 }
	 
	 // using Long division， not understand yet
	 public int divide3(int dividend, int divisor) {
		    boolean signA = dividend >= 0, signB = divisor > 0;

		    long num = Math.abs((long) dividend), div = Math.abs((long) divisor), res = 0, curr = 0;
		   
		    for (int idx = 31; idx >= 0; idx--) {
		        curr <<= 1;
		        curr |= ((num >> idx) & 1);

		        res = res << 1;
		        if (curr >= div) {
		            curr -= div;
		            res |= 1;
		        }
		    }

		    if (signA ^ signB)
		        return -(int) res;
		    return (int) res;
		}

}

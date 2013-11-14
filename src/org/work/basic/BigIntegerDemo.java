package org.work.basic;

import java.math.BigInteger;

public class BigIntegerDemo {

	public static void main(String[] args) {
		BigInteger a = BigInteger.ZERO;
		// BigInteger b = 0; //error, can not convert int to BigInteger

		// byte数组表示这个数的二进制补码
		byte[] b = { -1, 1 };
		a = new BigInteger(b);
		System.out.println(a);

		// magnitude是该大数的二进制表示（非补码）
		byte[] magnitude = { 1, 0 };
		a = new BigInteger(-1, magnitude);
		System.out.println(a);
	}

}

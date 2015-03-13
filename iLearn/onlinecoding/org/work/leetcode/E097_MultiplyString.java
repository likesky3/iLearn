package org.work.leetcode;

public class E097_MultiplyString {

	private static final int ascall = 48;

	/**
	 * 字符串乘法
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	//version 1
	public static String doMul(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0"))
            return "0";
		
		char[][] arrayTemp = new char[num2.length()][num1.length()
				+ num2.length()];
		initArrayZero(arrayTemp);
		for (int i = num2.length(), j = 0; i > 0; i--, j++) {
			copyArray(arrayTemp[i - 1], doMulti(num1, num2.charAt(i - 1)), j);
		}
		char[] reaultTemp = doAdd(arrayTemp);
		// 如果第一位是0，则去掉0
		return reaultTemp[0] == '0' ? String.valueOf(reaultTemp, 1,
				reaultTemp.length - 1) : String.valueOf(reaultTemp);
		// return String.valueOf(doAdd(arrayTemp));
	}

	/**
	 * 乘法
	 * 
	 * @param str1
	 *            字符串乘数
	 * @param multi2
	 *            一个字符的被乘数
	 * @return 乘法结果，用char数组返回
	 */
	public static char[] doMulti(String str1, char multi2) {
		int temp;
		int temp1;// 十位
		int temp2;// 个位
		char[][] arrayTemp = new char[str1.length()][str1.length() + 1];
		initArrayZero(arrayTemp);
		for (int i = str1.length(), j = str1.length() + 1; i > 0; i--, j--) {
			temp = (str1.charAt(i - 1) - ascall) * (multi2 - ascall);
			temp1 = temp / 10; //
			temp2 = Math.abs(temp % 10);
			arrayTemp[i - 1][j - 2] = String.valueOf(temp1).charAt(0);
			arrayTemp[i - 1][j - 1] = String.valueOf(temp2).charAt(0);
		}

		return doAdd(arrayTemp);
	}

	/**
	 * 初始化0数组
	 * 
	 * @param array
	 */
	private static void initArrayZero(char[][] array) {
		for (char[] aaa : array) {
			for (int i = 0; i < aaa.length; i++) {
				aaa[i] = '0';
			}
		}
	}

	/**
	 * 拷贝数组中字符至正确位
	 * 
	 * @param targetArray
	 * @param array
	 * @param index
	 *            数组偏移量
	 */
	private static void copyArray(char[] targetArray, char[] array, int index) {
		for (int i = array.length, j = targetArray.length; i > 0; i--, j--) {
			targetArray[j - 1 - index] = array[i - 1];
		}
	}

	/**
	 * 将数组中字符按位做加法运算
	 * 
	 * @param array
	 * @return
	 */
	private static char[] doAdd(char[][] array) {
		char[] add = new char[array[0].length];
		for (int i = 0; i < add.length; i++) {
			add[i] = '0';
		}

		int next = 0;// 进位
		for (int i = add.length; i > 0; i--) {
			int addTemp = 0;// 相加中间数
			for (char[] aaa : array) {
				addTemp += (aaa[i - 1] - ascall);
			}
			addTemp += next;
			int temp2 = Math.abs(addTemp % 10);
			add[i - 1] = String.valueOf(temp2).charAt(0);
			next = addTemp / 10;
		}
		return add;
	}

	public static void main(String[] args) {
		System.out.println(doMulti("123",'4'));
	}

	//version 2
	public String multiply(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0"))
            return "0"; 
		
		int n1 = num1.length();
		int n2 = num2.length();
		int n = n1 + n2;
		int[] tmp = new int[n];
		
		for(int i = n1 - 1; i >= 0; i--){
			for(int j = n2 - 1; j >= 0; j--){
				tmp[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
			}
		}
		
		int carry = 0;
		for(int i = n - 1; i >= 0; i--){
			tmp[i] += carry;
			carry = tmp[i] / 10;
			tmp[i] = tmp[i] % 10;
		}
		
		StringBuffer ret = new StringBuffer();
		boolean skipZero = true;
		for(int i = 0; i < n; i++){
			if(skipZero && tmp[i] == 0)
				continue;
			else{
				ret.append(tmp[i]);
				skipZero = false;
			}
		}
		
		if(ret.length() == 0)
			return "0";
		return ret.toString();
	}
}

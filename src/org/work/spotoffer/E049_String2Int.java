package org.work.spotoffer;

public class E049_String2Int {
	private final String ILLEGAL_INPUT = "Illegal input.";
	private final String OVERFLOW_INPUT = "Overflow input.";
	public static void main(String[] args) {
		E049_String2Int obj = new E049_String2Int();
		try {
			int res = obj.parseInt("2147483649");
			System.out.println(res);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(Integer.parseInt("00123451"));
	}
	
	public int parseInt(String str) throws Exception{
		if(str == null || str.isEmpty())
			throw new RuntimeException(ILLEGAL_INPUT);
//		if(str.length() > 11)
//			throw new RuntimeException(OVERFLOW_INPUT);
		
		boolean isPositive = true;
		Character first = str.charAt(0);
		if(first.equals('-') || first.equals('+')){
			isPositive = first.equals('-') ? false : true;
			str = str.substring(1);
			if(str.isEmpty())
				throw new RuntimeException(ILLEGAL_INPUT);
		}else if(!Character.isDigit(first))
			throw new RuntimeException(ILLEGAL_INPUT);
		
		//skip prefix 0
		int i = 0;
		for(;i < str.length(); i++){
			if(str.charAt(i) != '0')
				break;
		}
		if(i == str.length())
			return 0;
		else
			str = str.substring(i);
		//判断去除前导0后的串是否溢出
		if(str.length() >= 11)
			throw new RuntimeException(OVERFLOW_INPUT);
		
		//start parsing the rest
		i = str.length() - 1;
		int power = 1;
		int res = 0;
		Character curr = null;
		while(i >= 0){
			curr = str.charAt(i--);
			if(! Character.isDigit(curr))
				throw new RuntimeException(ILLEGAL_INPUT);
			res += -(curr - '0') * power;
			if(res > 0)
				throw new RuntimeException(OVERFLOW_INPUT);
			power *= 10;
		}
		if(isPositive && res == Integer.MIN_VALUE)
			throw new RuntimeException(OVERFLOW_INPUT);
		return isPositive ? -res : res;
	}

}

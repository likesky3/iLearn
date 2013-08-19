package org.work.leetcode;

public class E003_PlusOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E003_PlusOne obj = new E003_PlusOne();
		int[] res = obj.plusOne(new int[]{9});
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
		System.out.println(Integer.MAX_VALUE);
	}
	
	//fail @ digits > Integer.MAX_VALUE
	public int[] plusOne(int[] digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        //step 1, int[] -> number
        int num = 0;
        int newLen = 0;
        int tens = 1;
        for(int i = 0; i < digits.length; i++){
            num = num * 10 + digits[i];
            tens *= 10;
        }
        
        //step 2
        num++;
        newLen = num < tens ? digits.length : (digits.length + 1);
        
        //step 3, new number -> int[]
        int[] result = new int[newLen];
        for(int i = newLen - 1; i >= 0; i--){
        	result[i] = num % 10;
        	num /= 10;
        }
        return result;
    }
	
	public int[] plusOne2(int[] digits){
		for(int i = digits.length - 1; i >= 0; i--){
			if(digits[i] < 9){
				digits[i]++;
				break;
			}
			digits[i] = 0;
		}
		
		if(digits[0] == 0){
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			for(int i = 0; i < digits.length; i++)
				newDigits[i + 1] = digits[i];
			return newDigits;
		}
		
		return digits;
	}

}

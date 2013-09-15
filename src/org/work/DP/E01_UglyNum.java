package org.work.DP;

public class E01_UglyNum{
    public int getUglyNum(int n){
        int[] uglys = new int[n];
        uglys[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int next_multiple_of_2 = 2;
        int next_multiple_of_3 = 3;
        int next_multiple_of_5 = 5;
        int nextUglyNo;
        int i = 0;
        while( i < n){
            nextUglyNo = getMin(next_multiple_of_2, next_multiple_of_3, next_multiple_of_5);
            uglys[i] = nextUglyNo;
            if(nextUglyNo == next_multiple_of_2){
                next_multiple_of_2 = uglys[i2] * 2;
                i2++;
            }    
            if(nextUglyNo == next_multiple_of_3){
                next_multiple_of_3 = uglys[i3] * 3;
                i3++;
            }    
            if(nextUglyNo == next_multiple_of_5){
                next_multiple_of_5 = uglys[i5] * 5;
                i5++;
            }
            System.out.println(nextUglyNo);
            
            i++;
        }
        return uglys[149];
    }
    
    private int getMin(int a, int b, int c){
    	if(a < b){
    		if(a < c)
    			return a;
    		else
    			return c;
    	}else{
    		if(b < c)
    			return b;
    		else
    			return c;
    	}
    	
    }
    
    public static void main(String[] args){
    	E01_UglyNum obj = new E01_UglyNum();
       obj.getUglyNum(150);
    }
}


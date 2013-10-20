package org.work.basic;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

enum Season {
	SPRING, SUMMER, FALL, WINTER
}

public class Test {

	public static void main(String[] args) throws IOException{
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		ArrayList<Integer> b = a;
		b.remove(0);
		System.out.println(a.size());
		System.out.println(b.size());
		
	}
	
	public void f(Integer n){
		n++;
	}


}

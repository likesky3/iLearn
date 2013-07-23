package org.work.basic;

import java.util.ArrayList;


enum Season{SPRING, SUMMER, FALL, WINTER}

public class Test {
	
	public static void main(String[] args){

		ArrayList<Integer> aa = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> aaa = new ArrayList<ArrayList<Integer>>(3);
		
		System.out.println(aaa.size());
		
		for(int i = 0; i < 3; i++){
			ArrayList<Integer> item = new ArrayList<Integer>();
			aaa.add(item);
		}
		System.out.println(aa);
		System.out.println(aaa);
		
		
	}
	
}

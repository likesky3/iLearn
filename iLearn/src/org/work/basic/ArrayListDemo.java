package org.work.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArrayListDemo {
	public static void main(String[] args){
		ArrayListDemo demo = new ArrayListDemo();
//		int[] S = {1, 2};
//		System.out.println(demo.subsets(S));
		StringBuffer str = new StringBuffer("abc");
		
		HashMap<Integer, StringBuffer> map = new HashMap<Integer, StringBuffer>();
		map.put(1, str);
		HashMap<Integer,StringBuffer> map2 = new HashMap<Integer, StringBuffer>(map);
		map2.get(1).setCharAt(0, 'e');
		System.out.println(map.get(1));
	}
	
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> empty = new ArrayList<Integer>();
        result.add(empty);
        if(S == null || S.length == 0)
            return result;
        
        Arrays.sort(S);
        
        for(int i = 0; i < S.length; i++){
             int preSize = result.size();
             //copy previous subsets
             result.addAll(( ArrayList<ArrayList<Integer>>) result.clone());
             for(int j = preSize; j < result.size(); j++)
                 result.get(j).add(S[i]);
           
        }
        return result;
    }
}

package org.work.spotoffer;

import java.util.LinkedList;

public class E045_GetLastNumber {

	public static void main(String[] args) {

	}
	
	public int getLastNum(int n, int m){
		if(n <= 0 || m <= 0)
			return -1;
		LinkedList<Integer> a = new LinkedList<Integer>();
		for(int i = 0; i < n; i++)
			a.add(i);
		int start = 0;
		int len = a.size();
		while(len > 1){
			int iToBeDel = ((m - 1) % len + start) % len;
			a.remove(iToBeDel);
			start = iToBeDel;
		}
		return a.get(0);
	}

}

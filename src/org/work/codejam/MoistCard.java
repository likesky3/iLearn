package org.work.codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MoistCard {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		MoistCard obj = new MoistCard();
//		boolean res = obj.isSmaller(" agb", "Agb");
//		System.out.println(res);
		obj.getExchangeNum();
	}
	
	public void getExchangeNum() throws IOException{
		 BufferedReader in = new BufferedReader(new FileReader("C-small-2-attempt0.in"));
	     PrintWriter out = new PrintWriter(new FileWriter("C-small-2-attempt0.out"));
	     
	     int N = Integer.parseInt(in.readLine());
	     int k = 1;
	     
	     int num = 0;
	     int exchangeNum;
	     while(k <= N){
	    	 num = Integer.parseInt(in.readLine());
	    	 String[] cards = new String[num];
	    	 
	    	 for(int i = 0; i < num; i++){
	    		 cards[i] = in.readLine();
	    	 }
	    	 exchangeNum = insertSortString(cards);
	    	 out.println("Case #" + k + ": " + exchangeNum);
	    	 k++;
	     }
	     in.close();
	     out.close();
	}
	
	public boolean isSmaller(String a, String b){
		int i = 0, j = 0;
		while(i < a.length() && j < b.length()){
			if(a.charAt(i) < b.charAt(j)){
				return true;
			}else if(a.charAt(i) > b.charAt(j)){
				return false;
			}else{
				i++; j++;
			}
				
		}
		
		if(i < a.length())
			return false;
		else
			return true;
	}
	
	public int insertSortString(String[] a){
		int num = 0;
		for(int i = 1; i < a.length; i++){
			String tmp = a[i];
			int j = i - 1;
			for(; j >= 0 && isSmaller(a[j + 1], a[j]); j--){
				num++;
				a[j + 1] = a[j];
			}
			a[j + 1] = tmp;
		}
		return num;
	}
}

package org.work.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Template {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedReader in = new BufferedReader(new FileReader("a.in"));
	     PrintWriter out = new PrintWriter(new FileWriter("a.out"));
	     
	     int N = Integer.parseInt(in.readLine());
	     int k = 1;
	     
	     int num = 0;
	     while(k <= N){
	    	 num = Integer.parseInt(in.readLine());
	    	 
	    	 out.println("Case #" + k + ": ");
	    	 k++;
	     }
	     in.close();
	     out.close();
	}

}

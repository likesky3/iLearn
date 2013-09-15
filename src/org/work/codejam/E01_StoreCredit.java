package org.work.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class E01_StoreCredit {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		E01_StoreCredit obj = new E01_StoreCredit();
		obj.storeCredit();
	}
	
	public void storeCredit() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("A-large-practice.in"));
        PrintWriter out = new PrintWriter(new FileWriter("A-large-practice.out"));
        
        int N = Integer.parseInt(in.readLine());
        int k = 1;
        int credit;
        int numItems;
        while(k <= N){
            credit = Integer.parseInt(in.readLine());
            numItems = Integer.parseInt(in.readLine());
            String[] itemsStr = in.readLine().split(" ");
            int[] items = new int[numItems];
            
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i < numItems; i++){
                items[i] = Integer.parseInt(itemsStr[i]);
                if(!map.containsKey(items[i])){
                    map.put(credit - items[i], i);
                }else{
                    int i1 = map.get(items[i]) + 1;
                    out.println("Case #" + k + ": " + i1 + " " + (i + 1));
                }
            
            }
            k++;
        }
        
        
        in.close();
        out.close();
    }

}

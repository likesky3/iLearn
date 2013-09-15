package org.work.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class BadHorse {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("a.in"));
		PrintWriter out = new PrintWriter(new FileWriter("a.out"));

		int N = Integer.parseInt(in.readLine());
		int k = 0;

		int num = 0;
		String[] pair = new String[2];
		HashMap<String, Integer> group1 = new HashMap<String, Integer>();
		HashMap<String, Integer> group2 = new HashMap<String, Integer>();
		while (k < N) {
			group1.clear();
			group2.clear();
			k++;
			
			String line = in.readLine();
//			System.out.println(line);
			num = Integer.parseInt( line);
			if (num == 1) {
				in.readLine();
				out.println("Case #" + k + ": " + "Yes");
				continue;
			}
			int i = 0;
			for (; i < num; i++) {
				line = in.readLine();
				pair = line.split(" ");
//				pair = in.readLine().split(" ");
				if (k == 1) {
					group1.put(pair[0], 1);
					group2.put(pair[1], 1);
					continue;
				}
				if (!group2.containsKey(pair[0])){
					//p0 -> group1
					group1.put(pair[0], 1);
					if(!group1.containsKey(pair[1]))
						group2.put(pair[1], 1);
					else{
						out.println("Case #" + k + ": " + "No");
						break;
					}
				}else{
					if(!group2.containsKey(pair[1]))
						group1.put(pair[1], 1);
					else{
						out.println("Case #" + k + ": " + "No");
						break;
					}
						
				}

			}
			if(i == num)
				out.println("Case #" + k + ": " + "Yes");
			i++;
			while(i < num){
				line = in.readLine();
//				System.out.println(line);
				i++;
			}
//			System.out.println("---");
			
		}
		in.close();
		out.close();
	}

}

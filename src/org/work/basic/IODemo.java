package org.work.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class IODemo {
	public void readFromFile(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = in.readLine()) != null)
			System.out.println(line);
		in.close();
	}

	public void writeToFile(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		String line;
		while ((line = in.readLine()) != null)
			out.println(line);
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		IODemo obj = new IODemo();
//		obj.writeToFile("test.txt");

		// read file
//		obj.readFromFile("test.txt");
		Scanner cin = new Scanner(System.in);
		int a, b;
		while(cin.hasNext()){
			a = cin.nextInt();
			b = cin.nextInt();
			System.out.println(a + b);
		}
	}
	
	
}

package org.work.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class E001_IOoperation {
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
		E001_IOoperation obj = new E001_IOoperation();
		obj.writeToFile("test.txt");

		// read file
		obj.readFromFile("test.txt");
	}
}

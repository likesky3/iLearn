package org.work.basic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
		// obj.writeToFile("test.txt");

		// read file
		// obj.readFromFile("test.txt");
//		Scanner cin = new Scanner(System.in);
//		int a, b;
//		while (cin.hasNext()) {
//			a = cin.nextInt();
//			b = cin.nextInt();
//			System.out.println(a + b);
//		}
		
		byteArrayInputStreamDemo();
	}

	public static void byteArrayInputStreamDemo() throws IOException {
		byte c[] = { 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		InputStream in = new ByteArrayInputStream(c);
		System.out.println("Line 1 : " + in.markSupported());
		in.mark(0);
		System.out.println("Line 2 : " + in.read());
		byte b[] = new byte[20];
		in.read(b, 0, 3);
		System.out.print("Line 3 : ");
		for (int i = 0; i < 3; i++) {
			System.out.print(b[i] + " ");
		}
		in.reset();
		System.out.println(in.read(b, 0, 13) + " " + in.available());
	}

}

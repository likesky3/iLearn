package org.work.weiss.chap4;

import java.io.File;

public class FileList {

	public static void main(String[] args) {
		FileList fileList = new FileList();
		fileList.list(new File("D:\\我的文档\\routine"));
	}
	
	public void list(File f) {
//		list(f, 0);
		list2(f, 0);
	}
	
	private void list(File f, int depth) {
		if (f == null || f.listFiles() == null)
			return;
		for (File file : f.listFiles())
			printInfo(file, depth);
	}
	
	private void printInfo(File f, int depth) {
		for (int i = 0; i < depth; i++)
			System.out.print("	");
		if (f.isDirectory()) {
			System.out.println(f.getName());
			list(f, depth + 1);
		} else {
			System.out.println(f.getName() + " " + f.length());
		}
	}
	
	//print current file info, if it is a directory, recursively print the sub directory
	private void list2(File f, int depth) {
		printName(f, depth);
		if (f.isDirectory()) {
			for (File file : f.listFiles())
				list2(file, depth + 1);
		}
	}
	
	private void printName(File f, int depth) {
		for (int i = 0; i < depth; i++)
			System.out.print("  ");
		if (f.isDirectory()) {
			System.out.println("Dir: " + f.getName());
		} else {
			System.out.println(f.getName() + " " + f.length());
		}
	}
	
}

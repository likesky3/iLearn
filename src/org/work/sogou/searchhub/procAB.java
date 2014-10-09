package org.work.sogou.searchhub;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;

public class procAB {
	public static void main(String[] args) {
		BufferedReader readerA = null;
		BufferedReader readerB = null;
		try {
			readerA = new BufferedReader(new InputStreamReader(new FileInputStream("D:/a.id"), "gbk"));
			readerB = new BufferedReader(new InputStreamReader(new FileInputStream("D:/b.data"), "gbk"));
			OutputStream output = new FileOutputStream("D:/result");
			
			HashSet<String> idset = new HashSet<>();
			String line = null;
			while ((line = readerA.readLine()) != null) {
				idset.add(line);
			}
			
			String idInData;
			int pos1, pos2;
			StringBuffer outBuffer = new StringBuffer();
			while ((line = readerB.readLine()) != null) {
				pos1 = line.indexOf("session:") + 8;
				pos2 = line.indexOf(", uuid");
				idInData = line.substring(pos1, pos2);
				if (idset.contains(idInData)) {
					outBuffer.append(idInData).append("|");
					idset.remove(idInData);
				}
				outBuffer.append(line.substring(line.indexOf("queryString: ") + 13, line.indexOf(", site")));
				outBuffer.append("\n");
			}
			 PrintStream ps = new PrintStream(output);   
		     ps.print(outBuffer.toString());
				
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}


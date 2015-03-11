package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.LinkedList;

public class IPConversionUtil {
	public static void main(String[] args) {
		IPConversionUtil obj = new IPConversionUtil();
		obj.convertIP();
	}
	
	public void convertIP() {
		// antispider ip转换工具
//		PrintWriter out = null;
//		File f = new File("D:/ip_res");
//		if (!f.exists())
//			try {
//				f.createNewFile();
//			} catch (Exception e) {
//				return;
//			}
//		try {
//			out = new PrintWriter(f, "gbk");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		BufferedReader reader = null;
		try {
			//TODO
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/ip_res"), "gbk"));

			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				 builder.append(line);
//				System.out.println(line);

				String ip = line;
				String ips[] = ip.split("\\.");
//				 System.out.println(ips.length);
				String res = "";
//				long ten = 0;
				for (int i = 0; i < 4; i++) {
					Integer a = Integer.valueOf(ips[i]);
					String b = Integer.toHexString(a.intValue());
					if (b.length() == 1)
						b = "0" + b;
					res = b + res;
//					ten = ten * 256 + a.intValue();
				}
				System.out.println(res.toUpperCase());
//				out.println(res.toUpperCase());
			}
//			 System.out.println(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}
//		out.close();
	}
}

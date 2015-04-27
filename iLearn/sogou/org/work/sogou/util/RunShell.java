package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.work.design_pattern.future.Host;

public class RunShell {
	public static void main(String[] args) {
		try {
			// String shpath="/home/felven/word2vec/demo-classes.sh";
			Process ps = Runtime.getRuntime().exec("sogou-host");
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					ps.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getHost() {
		String[] hosts = {"resin10.web.zw",
				"resinhub10.web.zw",
				"rsync.resin10.web.zw",
				"rsync.resinhub10.web.zw",
				"rsync.searchhub10.web.zw"};
		for (String host : hosts) {
			if (host.startsWith("resinhub"))
				System.out.println(host);
		}
	}
}

package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {

	public static void main(String[] args) {

//		ExecutorService exec = Executors.newCachedThreadPool();
//		for (int i = 0; i < 1; i++)
//			exec.execute(new LifeOff());
//		exec.shutdown();
		
		try {
			System.out.println("----------------------------------");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/jsonData")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				JSONObject jo = new JSONObject(line);
			}
			System.out.println("++++++++++++++++++++++++++++++++++\n\n");
		} catch (JSONException | IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		}
	}

}

class LifeOff implements Runnable {
	public void run() {
		while (true) {
			try {
				System.out.println("----------------------------------");
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/jsonData")));
				String line = null;
				while ((line = reader.readLine()) != null) {
					JSONObject jo = new JSONObject(line);
				}
				System.out.println("++++++++++++++++++++++++++++++++++\n\n");
			} catch (JSONException | IOException e) {
				 System.out.println(e.getMessage());
				 e.printStackTrace();
			}
			break;
		}
	}
}

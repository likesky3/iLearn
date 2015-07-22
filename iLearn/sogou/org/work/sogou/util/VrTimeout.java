package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class VrTimeout {

    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/uuid1time1cost.vr"), "gbk"));
            HashMap<String, String> uuidTimeCostVr = new HashMap<String, String>();
            String line = null;
            System.out.println("begin");
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] items = line.split("\t");
                uuidTimeCostVr.put(items[2], items[1] + "\t" + items[0]);
            }
            reader.close();
            System.out.println("======================");
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/uuid1time.vr"), "gbk"));
            HashMap<String, String> uuidTimeVr = new HashMap<String, String>();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] items = line.split("\t");
                uuidTimeVr.put(items[1], items[0]);
            }
            reader.close();
            System.out.println("======================");
            HashMap<String, String> uuidTimeSH = new HashMap<String, String>();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/uuid1time.sh"), "gbk"));
            while ((line = reader.readLine()) != null) {
                String[] items = line.split("\t");
                String time = items[0].substring(1, items[0].length() - 1);
                uuidTimeSH.put(items[1], time);
            }
            System.out.println(uuidTimeSH.size() +" " +  uuidTimeVr.size());
            int i = 0;
            for (String uuid : uuidTimeVr.keySet()) {
                String vrtime = uuidTimeVr.get(uuid);
                String shtime = uuidTimeSH.get(uuid);
                if (uuidTimeVr.get(uuid).compareTo(uuidTimeSH.get(uuid)) > 0) {
//                    System.out.println("vr > sh: " + uuid);
//                    System.out.println(uuid + " " + uuidTimeCostVr.get(uuid) + " " + uuidTimeSH.get(uuid));
                    i++;
                } else {
                    System.out.println("vr < sh=" + uuid + " " + uuidTimeCostVr.get(uuid) + " " + uuidTimeSH.get(uuid));
                }
            }
            System.out.println("vr > sh=" + i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

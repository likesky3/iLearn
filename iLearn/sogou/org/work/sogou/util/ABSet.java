package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class ABSet {

    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Administrator/Downloads/trace1.val"), "gbk"));
            HashSet<Long> set1 = new HashSet<Long>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                set1.add(Long.parseLong(line));
            }
            reader.close();
            HashSet<Long> set2 = new HashSet<Long>();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Administrator/Downloads/trace3.val"), "gbk"));
            while ((line = reader.readLine()) != null) {
                set2.add(Long.parseLong(line));
            }
            for(long num : set1) {
                if (!set2.contains(num))
                    System.out.println(num);
            }
            
        }catch (Exception e) {
            
        }

    }

}

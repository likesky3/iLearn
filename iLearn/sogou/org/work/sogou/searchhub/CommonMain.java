package org.work.sogou.searchhub;

import java.util.HashSet;



public class CommonMain {

    public static void main(String[] args) {
        // get input from file
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/hostmap"), "gbk"));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                line = line.trim();
//                String[] tokens = StringUtils.split(line, '\t');
//                if (tokens.length != 2)
//                    continue;
//                System.out.println(tokens[0] + " " + tokens[1]);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//         String a = "10.11.213.11";
//         a = a.replace('.', '_');
//         System.out.println(a);
         
         
//         while(true) {
//        	 try {
//        		 throw new Exception();
//        	 } catch(Exception e) {
//        		 e.printStackTrace();
//        	 }
//         }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 5; i++)
            set.add(i);
        while (true) {
            for (int i : set)
                System.out.print(i + " ");
            System.out.println("\n\n");
        }
    }
    
    private void f() throws Exception {
    	throw new Exception();
    }
    
}

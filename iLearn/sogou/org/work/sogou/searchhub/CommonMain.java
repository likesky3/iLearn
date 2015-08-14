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
        CommonMain obj = new CommonMain();
        obj.comparePerformance();
    }
    
    private void f() throws Exception {
    	throw new Exception();
    }
    
    public void comparePerformance() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int id = getLdaArray()[i];
        }
        System.out.println("before optimize=" + (System.currentTimeMillis() - start));
        
        start = System.currentTimeMillis();
        int[] localArray = getLdaArray();
        for (int i = 0; i < 10000; i++) {
            int id = localArray[i];
        }
        System.out.println("after optimize=" + (System.currentTimeMillis() - start));
    }
    int[] lda = new int[10000];
    public int[] getLdaArray() {
        return lda;
    }
}

package org.work.sogou.searchhub;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHubMainTest {
    public static final HashMap<String, String> map = new HashMap<>();
    static {
        map.put("webcache", "web");
    }

    static public enum ResinReqType {
        Normal, Retry(), RetryShadow, Forward
    }

    private static final String DUMP_FILE = "D:/dumpfile";
    private static Pattern leftTupuTypePattern = Pattern.compile("<doc>\\s*<item type=\"([^15]|[0-9]{2,})\"");

    public static void main(String[] args) {
        SearchHubMainTest obj = new SearchHubMainTest();
        String cc = "11";
        System.out.println(cc);
        cc = cc + null;
        System.out.println(cc);
        
//        obj.testPattern();
    }
    
    public void testPattern() {
        String xmldoc = "<doc><item type=\"13\" entitytype=\"#|99\" pvtype=\"4_103_1\"><display type=\"7\">";
        Matcher matcher = leftTupuTypePattern.matcher(xmldoc);
        if (matcher.find()) {
            System.out.println(matcher.group());
            int start = xmldoc.indexOf("pvtype=\"") + "pvtype=\"".length();
            int end = xmldoc.indexOf("\"", start);
            System.out.println(xmldoc.substring(start));
            System.out.println(xmldoc.substring(end));
            System.out.println(start + "," + end + "; " + xmldoc.substring(start, end));
        } else {
            System.out.println("not find");
        }
    }
    
    public void testIteratorHasNext() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = -3; i < 4; i++) {
            list.add(i);
        }
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    
    public void testIterator() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = -3; i < 4; i++) {
            list.add(i);
        }
        Iterator<Integer> iter = list.iterator();
        int min = Integer.MAX_VALUE;
        while (iter.hasNext()) {
            int curr = iter.next();
            if (curr < 0) {
                iter.remove();
            } else if (curr < min) {
                min = curr;
            }
        }
        if (min != Integer.MAX_VALUE) 
            list.remove(min);
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
    static ArrayList<Integer> VRabtestGroupRange = new ArrayList<Integer>();
    {
        VRabtestGroupRange.add(5);
        VRabtestGroupRange.add(15);
        VRabtestGroupRange.add(25);
        VRabtestGroupRange.add(50);
        VRabtestGroupRange.add(75);
        VRabtestGroupRange.add(100);
    }

    public int calculateVRabtest(String suid) {
        int vrabtest = 0;
        if (suid == null || suid.equals(""))
            return vrabtest;
        int mod = Integer.parseInt(suid.substring(0, 6), 16) % 100;
        if (mod < VRabtestGroupRange.get(0)) {
            vrabtest = 1;
        } else if (mod < VRabtestGroupRange.get(1)) {
            vrabtest = 2;
        } else if (mod < VRabtestGroupRange.get(2)) {
            vrabtest = 3;
        } else if (mod < VRabtestGroupRange.get(3)) {
            vrabtest = 4;
        } else if (mod < VRabtestGroupRange.get(4)) {
            vrabtest = 5;
        } else if (mod < VRabtestGroupRange.get(5)) {
            vrabtest = 6;
        } else {
            vrabtest = 0;
        }
        return vrabtest;
    }

    public void testURLEncoder() {
        try {
//            String input = "ｄ５ｄｂ７７ｄ７５９３４２７９６ａ４ａｂ９２０８０ｄｃｅ６５ｅ５ＶＷｘ００";
            String input = "魅男gv资源";
            String output = URLEncoder.encode(input, "utf-16le");
             System.out.println(output);
//            System.out.println(URLDecoder.decode("%45%9b%37%75%67%00%76%00%44%8d%90%6e", "utf-16le"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testDumpFile() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(DUMP_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printer = new PrintStream(outputStream);
        printer.print("123");
        printer.close();
        System.out.println("printer == null: " + printer == null);
    }

    public void test12() {
        System.out.println(this.hashCode());
    }

    public void test11() {
        HashMap<String, String> params = new HashMap<>();
        System.out.println(params.get("key") == null);
    }

    public void test10() {
        // test arrayList
        ArrayList<Integer> list = new ArrayList(10);
        for (int i = 0; i < 3; i++) {
            list.add(null);
        }
        list.set(0, 0);
        list.set(1, 1);
        list.remove(list.size() - 1);
        list.add(0, -1);
        System.out.println(list.size());
        for (Integer num : list)
            System.out.println(num);
        // list.set(11, 11);

    }

    public boolean test9() {
        int[] a = new int[1];
        try {
            System.out.println(a[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("out");
        return false;
    }

    // @@ ArrayList.set()
    public void test8() {
        ArrayList<String> list = new ArrayList<>(10);

        // without the following 2 lines, runtime will throw exception
        // for (int i = 0; i < 10; i++)
        // list.add(null);
        String a = "3";
        String b = "2";
        String c = "1";
        list.set(1, c);
        list.set(2, b);
        list.set(3, a);
        for (String str : list)
            System.out.println(str);
    }

    public void test7() {
        String hex = "DE33300DBABEBED70388011ABB33973C";
        BigInteger value = new BigInteger(hex, 16);
        int intval = value.intValue();
        System.out.println(intval);
        System.out.println(intval & 1);
    }

    public void test6() {
        int a = 2 + +2; // amazing
        System.out.println(a);
    }



    // test if System.arraycopy is shallow copy
    public void test4() {
        ArrayList<DocTermInfo> origin = new ArrayList<>();
        DocTermInfo docInfo = new DocTermInfo();
        docInfo.cc = "origin";
        origin.add(docInfo);

        ArrayList<DocTermInfo> copy = new ArrayList<>();
        copy.addAll(origin);
        copy.get(0).cc = "copy";
        System.out.println("origin: " + origin.get(0).cc);
        System.out.println("copy: " + copy.get(0).cc);
    }

    public void test3() {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                DocTermInfo source = new DocTermInfo();
                String a;
                for (TermInfo term : source.title_terms) {
                    // System.err.println(term.term);
                    // System.out.println(term.term);
                    // System.out.println(term.term);
                    a = term.term;
                    a = term.term;
                    a = term.term;
                }
                System.out.println("ugly:" + (System.currentTimeMillis() - start));
            }
        });
        thread1.setName("ugly thread");
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                DocTermInfo source = new DocTermInfo();
                String a;
                for (TermInfo term : source.title_terms) {
                    String termStr = term.term;
                    // System.out.println(termStr);
                    a = termStr;
                    a = termStr;
                    a = termStr;
                }
                System.out.println("pretty:" + (System.currentTimeMillis() - start));
            }
        });
        thread2.setName("pretty thread");
        thread2.start();

    }

    public void test2() {
        String two = "25:50";
        String one = "25";
        System.out.println(two.split(":")[0] + " " + two.split(":")[1]);
        System.out.println(one.split(":")[0]);
    }

    public void test1() {
        System.out.println("start");
        // style1 of use
        Thread thread1 = new Thread(new Runnable() {

            NLPBaseInfo nlpinfo = new NLPBaseInfo();

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (int ii = 0; ii < 10; ii++) {
                    for (int i = 0; i < nlpinfo.getldaNum(); i++) {
                        System.out.println(nlpinfo.getLdaIdArray()[i]);
                        System.out.println(nlpinfo.getLdaScoreArray()[i]);
                        // int id = nlpinfo.getLdaIdArray()[i];
                        // double score = nlpinfo.getLdaScoreArray()[i];
                    }
                }
                System.out.println("ugly:" + (System.currentTimeMillis() - start));
            }
        });
        thread1.setName("ugly thread");
        // thread1.start();

        // style2 of use
        Thread thread2 = new Thread(new Runnable() {

            NLPBaseInfo nlpinfo = new NLPBaseInfo();
            int ldaNum = nlpinfo.getldaNum();
            int[] ldaId = nlpinfo.getLdaIdArray();
            double[] ldaScore = nlpinfo.getLdaScoreArray();

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (int ii = 0; ii < 10; ii++) {
                    // while(true) {
                    for (int i = 0; i < ldaNum; i++) {
                        System.out.println(ldaId[i]);
                        System.out.println(ldaScore[i]);
                        // int id = ldaId[i];
                        // double score = ldaScore[i];
                    }
                }
                System.out.println("pretty:" + (System.currentTimeMillis() - start));
            }
        });
        thread2.setName("pretty thread");
        thread2.start();

        // style3 of use
        Thread thread3 = new Thread(new Runnable() {

            NLPBaseInfo nlpinfo = new NLPBaseInfo();
            int ldaNum = nlpinfo.getldaNum();
            int[] ldaId = nlpinfo.getLdaIdArray();
            double[] ldaScore = nlpinfo.getLdaScoreArray();

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                int id;
                double score;
                for (int ii = 0; ii < 20000000; ii++) {
                    // while(true) {
                    for (int i = 0; i < ldaNum; i++) {
                        // System.out.println(ldaId[i]);
                        // System.out.println(ldaScore[i]);
                        id = ldaId[i];
                        score = ldaScore[i];
                    }
                }
                // System.out.println("pretty2:" + (System.currentTimeMillis() -
                // start));
            }
        });
        thread3.setName("pretty thread2");
        thread3.start();
    }
}

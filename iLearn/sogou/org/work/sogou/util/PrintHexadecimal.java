package org.work.sogou.util;

public class PrintHexadecimal {
    public static void main(String[] args) {
        String a = "hello";
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            tmp.append(Integer.toHexString((int)a.charAt(i)));
        }
        System.out.println(tmp.toString());
    }
}

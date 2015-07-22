package org.work.sogou.util;

import java.io.UnsupportedEncodingException;

public class EncodeDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        EncodeDecode obj = new EncodeDecode();
        obj.diffCharSetLength();
    }
    
    public void diffCharSetLength() throws UnsupportedEncodingException {
        String a = "abc";
        String b = "周琳琳d";
        
        System.out.println(a.getBytes("UTF-16LE").length);
        System.out.println(b.getBytes("UTF-16LE").length);
        System.out.println();
        
        System.out.println(a.getBytes("UTF-8").length);
        System.out.println(b.getBytes("UTF-8").length);
        System.out.println();
        
        System.out.println(a.getBytes("GBK").length);
        System.out.println(b.getBytes("GBK").length);
    }
    
    public void stringToHex () {
      String s = "太原“警察踩头发”案今日９时开庭审理";
      StringBuilder hex = new StringBuilder();
      StringBuilder utf8 = new StringBuilder();
      
      int n = s.length();
      for (int i = 0; i < n; i++) {
          StringBuilder singleChar = new StringBuilder();
          singleChar.append(Integer.toHexString((int)s.charAt(i)));
          if (singleChar.length() == 1)
              singleChar.insert(0, '0');
          singleChar.append(' ');
          hex.append(singleChar);
      }
      
      
      System.out.println("-------------");
      System.out.println(s);
      System.out.println(hex.toString());
      System.out.println(utf8.toString());
    }
    

}

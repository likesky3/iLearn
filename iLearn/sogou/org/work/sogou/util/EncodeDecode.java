package org.work.sogou.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class EncodeDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {
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

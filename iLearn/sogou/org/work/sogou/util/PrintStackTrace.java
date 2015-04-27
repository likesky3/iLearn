package org.work.sogou.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintStackTrace {

    public static void main(String[] args) {
        Exception e = new Exception();
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        System.out.println("*****="+sw.getBuffer().toString());

    }

}

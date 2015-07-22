package org.work.basic.string;

import java.util.Arrays;

public class AddBinary {

    public static void main(String[] args) {
//        AddBinary obj = new AddBinary();
//        System.out.println(obj.addBinary("1010", "1011"));
        String[] a = "1.1".split(".");
        for (String s : a) {
            System.out.println(s);
        }
    }

    public String addBinary(String a, String b) {
        if (a == null && b == null) return null;
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;
        
        int n1 = a.length();
        int n2 = b.length();
        int i = 0, j = 0;
        StringBuilder sum = new StringBuilder();
        int carry = 0, curr = 0;
        while (i < n1 && j < n2) {
            curr = a.charAt(i++) - '0' + b.charAt(j++) - '0' + carry;
            carry = curr >> 1;
            sum.append(curr == 2 ? 0 : curr);
        }
        while (i < n1) {
            curr = a.charAt(i++) - '0' + carry;
            carry = curr >> 1;
            sum.append(curr == 2 ? 0 : curr);
        }
        while (j < n2) {
            curr = b.charAt(j++) - '0' + carry;
            carry = curr >> 1;
            sum.append(curr == 2 ? 0 : curr);
        }
        if (carry > 0)
            sum.append(carry);
        return sum.reverse().toString();
    }
    
}

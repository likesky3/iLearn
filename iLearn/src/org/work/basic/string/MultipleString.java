package org.work.basic.string;

public class MultipleString {

    public static void main(String[] args) {
        MultipleString obj = new MultipleString();
        long start = System.currentTimeMillis();
//        System.out.println();
        obj.multiply("2795820576851", "95369034579");
        System.out.println(System.currentTimeMillis() - start);
        
        start = System.currentTimeMillis();
//        System.out.println(obj.multiply1("2795820576851", "95369034579"));
        obj.multiply1("2795820576851", "95369034579");
        System.out.println(System.currentTimeMillis() - start);
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0)
            return null;
        if (num1.length() < num2.length())
            return multiply(num2, num1);
        if (num2.equals("0")) return "0";
        if (num2.equals("1")) return num1;
        int[] result = new int[num1.length() + num2.length() - 1];
        
        for (int i = num2.length() - 1; i >= 0; i--) {
            int digit1 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0; j--) {
                result[i + j] += digit1 * (num1.charAt(j) - '0');
            }
        }
        int carry = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] += carry;
            carry = result[i] / 10;
            result[i] %= 10;
        }
        StringBuilder val = new StringBuilder();
        if (carry > 0)
            val.append(carry);
        for (int i = 0; i < result.length; i++)
            val.append(result[i]);
        return val.toString();
    }
    
 // method 1 : 
    public String multiply1(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0)
            return null;
        String result = "";
        for (int i = 0; i < num2.length(); i++) {
            result = add(result + '0', multiOneDigit(num1, num2.charAt(i) - '0'));
        }
        return result;
    }
    private String add(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String tmp = num2;
            num2 = num1;
            num1 = tmp;
        }
        StringBuilder sum = new StringBuilder();
        int carry = 0, val = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        while (j >= 0) {
            val = (num1.charAt(i) - '0') + (num2.charAt(j) - '0');
            sum.append(val - 10); // val % 10
            carry = val >= 10 ? 1 : 0; // val / 10
            i--; j--;
        }
        while (i >= 0) {
            val = (num1.charAt(i--) - '0') + carry;
            sum.append(val - 10);
            carry = val >= 10 ? 1 : 0;
        }
        if (carry > 0) sum.append(carry);
        return sum.reverse().toString();
    }
    private String multiOneDigit(String num1, int num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0, val = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            val = (num1.charAt(i) - '0') * num2 + carry;
            result.append(val % 10);
            carry = val / 10;
        }
        if (carry > 0)
            result.append(carry);
        return result.reverse().toString();
    }
}

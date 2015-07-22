package org.work.basic.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {

    public static void main(String[] args) {
        RestoreIPAddress obj = new RestoreIPAddress();
        List<String> result = obj.restoreIpAddresses("0000");
        for (String ip: result) {
            System.out.println(ip);
        }
        
        // Expected: ["0.10.0.10","0.100.1.0"]
        result = obj.restoreIpAddresses("010010");
        for (String ip: result) {
            System.out.println(ip);
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() < 4) 
            return result;
        dfs(s, 0, 0, new StringBuilder(), result);
        return result;
    }
    private void dfs(String s, int start, int segNum, StringBuilder ip, List<String> result) {
        if (segNum == 4) {
            if (start == s.length()) {
                ip.deleteCharAt(ip.length() - 1);
                result.add(ip.toString());
            }
            return;
        }
        int end = Math.min(start + 3, s.length());
        for (int i = start + 1; i <= end; i++) {
            String seg = s.substring(start, i);
            if ((seg.charAt(0) == '0' && seg.length() > 1) || Integer.parseInt(seg) > 255)
                break;
            int oldLen = ip.length();
            ip.append(seg).append('.');
            dfs(s, i, segNum + 1, ip, result);
            ip.delete(oldLen, ip.length());
        }
    }
}

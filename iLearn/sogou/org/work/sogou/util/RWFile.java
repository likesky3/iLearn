package org.work.sogou.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class RWFile {

    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/input.txt"), "gbk"));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                 builder.append(line).append("_");
            }
//            System.out.println(builder.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
        RWFile obj = new RWFile();
//        obj.buildCheckInterleavingCmd();
//        obj.buildHost();
        String a = "A";
        String aa = "8";
        System.out.println(a.charAt(0) >= '0' && a.charAt(0) <='9');
        System.out.println(aa.charAt(0) >= '0' && aa.charAt(0) <='9');
    }
    
    public void buildDumpHost() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 24; i++) {
            if (i < 10)
                sb.append("resinhub0").append(i).append(".wap.sjs");
            else
                sb.append("resinhub").append(i).append(".wap.sjs");
            sb.append("_");
        }
        System.out.println(sb.toString());
    }
    
    public void buildHost() {
        for (int i = 1; i <= 24; i++) {
            StringBuilder sb = new StringBuilder();
            if (i < 10)
                sb.append("resinhub0").append(i).append(".wap.sjs");
            else
                sb.append("resinhub").append(i).append(".wap.sjs");
            System.out.println(sb.toString());
        }
        
    }
    
//    a=`fgrep interOrder /search/odin/daemon/searchhub/log/history/searchhub.err.log.2015-07-21_20 -c`;
//    echo -n $a" "
//    b=`fgrep interOrder /search/odin/daemon/searchhub/log/history/searchhub.err.log.2015-07-21_20 |fgrep send0=1 -c`;
//    echo -n $b" "
//    c=`fgrep interOrder /search/odin/daemon/searchhub/log/history/searchhub.err.log.2015-07-21_20 |fgrep send0=-1 -c`;
//    echo -n $c" "
//    d=`fgrep interOrder /search/odin/daemon/searchhub/log/history/searchhub.err.log.2015-07-21_20 |fgrep send0=0 -c`;
//    echo -n $d" "
//    echo
    public void buildCheckInterleavingCmd() {
        for (int d = 17; d < 20; d++) {
            for (int i = 0; i <=23; i++) {
                StringBuilder hour = new StringBuilder("");
                if (i < 10)
                    hour.append("0").append(i);
                else
                    hour.append(i);
                StringBuilder common = new StringBuilder("`fgrep interOrder /search/odin/daemon/searchhub/log/history/searchhub.err.log.2015-07-");
                common.append(d).append("_").append(hour);
                StringBuilder total = new StringBuilder();
                StringBuilder send1 = new StringBuilder();
                StringBuilder send_1 = new StringBuilder();
                StringBuilder send0 = new StringBuilder();
                total.append("a=").append(common).append(" -c`; echo -n $a' '");
                send1.append("b=").append(common).append("|fgrep send0=1 -c`; echo -n $b' '");
                send_1.append("c=").append(common).append("|fgrep send0=-1 -c`; echo -n $c' '");
                send0.append("d=").append(common).append("|fgrep send0=0 -c`; echo -n $d' '");
                String time = "'07-" + d + " " + hour + "    '";
                System.out.println("echo -n " + time);
                System.out.println(total.toString());
                System.out.println(send1.toString());
                System.out.println(send_1.toString());
                System.out.println(send0.toString());
                System.out.println("echo");
            }
        }
    }
}

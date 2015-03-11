package org.work.sogou.searchhub;

//import com.sohu.hadoop.tool.ToolKits;
//import com.sohu.uigs.app.web.BdBlacklist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebChannelClassifier {
    public static Set<String> finalpageMap    = new HashSet<String>();
    public static Set<String>           newFinalpageMap = new HashSet<String>();

    public static String getStdPid(String pid) {
        if (pid == null || "".equals(pid)) {
            return null;
        }
        String regex = "^sogou-[a-z]{4}-[0-9a-f]{16}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pid);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 用来表示与商业统一起来的分渠道逻辑层次的第二层的类型
     *
     * @author loudly
     */
    public enum SecondCategoryType {
        BD("bd"), // BD购买
        BD_BLACKLIST("bd_blacklist"), // 商业部门的黑名单
        BD123("bd123"), // 123购买
        BROWSER_BAD("browser_bad"), // 浏览器极差
        IME_BAD("ime_bad"), // 输入法极差
        BROWSER_GOOD("browser_good"), // 浏览器优质
        IME_GOOD("ime_good"), // 输入法优质
        WEB("web"), // /web流量及/sogou下无pid及无效pid的流量，除去sogou-free的流量（指/web下无refer的流量）
        SOHU("sohu"), // /sohu流量除去sohu_free的流量/sohu下无refer的流量
        BROWSER_LOW_RPM("browser_low_rpm"), // 浏览器低rpm
        IME_LOW_RPM("ime_low_rpm"), // 输入法低rpm
        SOGOU_FREE("sogou_free"), // /web下无refer的流量
        SOHU_FREE("sohu_free"), // /sohu下无refer的流量
        TB_GOOD("tb_good"), // tbh
        TBH_GOOD("tbh_good"), //tb
        TENCENT("tencent"),
        SOSO_BUY("soso_buy"),
	    NEW_CHANNEL("new_channel"),
	    UNKNOWN_SECOND_CATEGORY("unknown_second_category");

	    private SecondCategoryType(String name) {
		    this.name = name;
	    }

	    private String name;

	    public String getName() {
		    return this.name;
	    }
    }

    /**
     * 用来表示与商业统一起来的分渠道逻辑层次的主要层的类型
     *
     * @author loudly
     *
     */
    public enum MainCategoryType {
        BD("bd"), // BD购买
        BD123("bd123"), // 123购买
        OWN_GOOD("own_good"), // 自有优质
        OWN_NOT_GOOD("own_not_good"), // 自有非优质
        OTHER_GOOD("other_good"),
        // 其它优质
        SOSO("soso"),
	    NEW_CHANNEL("new_channel"),
	    UNKNOWN_MAIN_CATEGORY("unknown_main_category");

	    private MainCategoryType(String name) {
		    this.name = name;
	    }

	    private String name;

	    public String getName() {
		    return this.name;
	    }
    }

    static {
        WebChannelClassifier.finalpageMap.add("31210100");
        WebChannelClassifier.finalpageMap.add("02210100");
        WebChannelClassifier.finalpageMap.add("08210100");
        WebChannelClassifier.finalpageMap.add("10210100");
        WebChannelClassifier.finalpageMap.add("06210100");
        WebChannelClassifier.finalpageMap.add("03210100");
        WebChannelClassifier.finalpageMap.add("07210100");
        WebChannelClassifier.finalpageMap.add("14210100");
        WebChannelClassifier.finalpageMap.add("31210103");
        WebChannelClassifier.finalpageMap.add("05210100");
    }
    static {
        WebChannelClassifier.newFinalpageMap.add("02210102");
    }

    public static class ClassifyException extends Exception{
        private final String category;
        private final String message;

        public ClassifyException(String category, String message){
            super(category + " " + message);
            this.category = category;
            this.message = message;
        }

        public String getCategory() {
            return category;
        }

        public String getMessage() {
            return message;
        }
    }
    public static class Classifier {

        final SimpleDateFormat dateFormatter    = new SimpleDateFormat("yyyyMMdd");

        private String url;
        private String pid;
        private String p;
        private String urlType;
        private String channel;
        private String subChannel;
        private WebChannelClassifier.MainCategoryType mainCategory;
        private WebChannelClassifier.SecondCategoryType secondCategory;
	    private String financeFirstCategory;
	    private String financeSecondCategory;
        private String uri;
	    private String stdPid;
	    private String intcat;

        public Classifier(String url, String pid, String p, String intcat) {
            this.url = url;
            this.pid = pid;
            this.p = p;
	        this.intcat = intcat;
        }

        public String getUrlType() {
            return urlType;
        }

        public String getChannel() {
            return channel;
        }

        public String getSubChannel() {
            return subChannel;
        }

        public WebChannelClassifier.MainCategoryType getMainCategory() {
            return mainCategory;
        }

        public WebChannelClassifier.SecondCategoryType getSecondCategory() {
            return secondCategory;
        }

	    public String getFinanceFirstCategory() {
		    return this.financeFirstCategory;
	    }

	    public String getFinanceSecondCategory() {
		    return this.financeSecondCategory;
	    }

        public String getUri() {
            return uri;
        }

        public String getP() {
        	return p;
        }

	    public String getIntcat() {
		    return this.intcat;
	    }

        public String getStdPid() {
        	return stdPid;
        }

        /**
         *
         * @return
         * @throws ClassifyException 遇到一条无法分类的日志，上层应用应该抛弃并计数
         */
        public Classifier invoke( long    logtimestamp  ) throws ClassifyException{
            // 渠道划分
            urlType = "";
            channel = "unknown_channel";
            subChannel = "unknown_subChannel";
            mainCategory = MainCategoryType.UNKNOWN_MAIN_CATEGORY;
            secondCategory = SecondCategoryType.UNKNOWN_SECOND_CATEGORY;
	        financeFirstCategory = "financeFirstCategory_unknown";
	        financeSecondCategory = "financeSecondCategory_unknown";
            uri = "";
            if (url.indexOf("http://www.sogou.com/sogou?") == 0 || url.indexOf("http://wwwtest.sogou.com/sgtest?") == 0
		            || url.indexOf("http://wwwtest.sogou.com/sogou?") == 0) {
                uri = "sogou";

//                if(url.indexOf("http://wwwtest.sogou.com/sgtest?") == 0) {
//                	Map<String, String> urlHm = ToolKits.getParameterHmFromURL(url);
//        			if(urlHm.containsKey("unid")) {
//        				pid = urlHm.get("unid");
//        			}
//                }

                stdPid = WebChannelClassifier.getStdPid(pid);
                if (pid == null || "".equals(pid) || pid.equalsIgnoreCase("null")) {
                    channel = "NO_PID"; // 无pid参数计入web
                    subChannel = "NO_PID";
                    urlType = "sogou";

                    // 在与商业统计的逻辑分层中，将此类流量算作Web流量
                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                    secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

	                financeFirstCategory = "pc自有—搜狗渠道";
	                financeSecondCategory = "NO_PID";
                }
                else if (stdPid != null) {
                    urlType = "sogou";
	                if(FinancePidCategory.containsPid(stdPid)) {
		                financeFirstCategory = FinancePidCategory.getFirstCategory(stdPid);
		                financeSecondCategory = FinancePidCategory.getSecondCategory(stdPid);
	                }

                    if (SogouPidChannel.containsPid(stdPid)) {
                        channel = SogouPidChannel.getChannel(stdPid);
                        subChannel = SogouPidChannel.getSubChannel(stdPid);

                        if (subChannel.equals("tuozhuai")) {
                            mainCategory = WebChannelClassifier.MainCategoryType.OWN_NOT_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.BROWSER_BAD;
                        }
                        else if (subChannel.equals("fenhao")) {
                            mainCategory = WebChannelClassifier.MainCategoryType.OWN_NOT_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.IME_BAD;
                        }
                        else if (subChannel.equals("tishi") || subChannel.equals("anniu")) {
                            mainCategory = WebChannelClassifier.MainCategoryType.OTHER_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.IME_LOW_RPM;
                        }
                        else if (channel.equals("browser")) {
                            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.BROWSER_GOOD;
                        }
                        else if (channel.equals("ime")) {
                            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.IME_GOOD;
                        }
                        else if(channel.equals("tb")) {
                        	mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        	secondCategory = WebChannelClassifier.SecondCategoryType.TB_GOOD;
                        }
                        else if(channel.equals("tbh")) {
                        	mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        	secondCategory = WebChannelClassifier.SecondCategoryType.TBH_GOOD;
                        }
                        else if(channel.startsWith("tx.")){
                            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                            secondCategory = WebChannelClassifier.SecondCategoryType.TENCENT;
                        }
                        else{
                            //mainCategory = WebChannelClassifier.MainCategoryType.BD;
                           // secondCategory = WebChannelClassifier.SecondCategoryType.BD;
                        }
                    }
                    else if (stdPid.startsWith("sogou-site-")) {
                        channel = "BD"; // BD推广渠道
                        subChannel = "网址站"; // 网址站

                        mainCategory = WebChannelClassifier.MainCategoryType.BD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.BD;
                    }
                    else if (stdPid.startsWith("sogou-netb-")) {
                        channel = "BD";
                        subChannel = "网吧"; // 网吧

                        mainCategory = WebChannelClassifier.MainCategoryType.BD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.BD;
                    }
                    else if (stdPid.startsWith("sogou-navi-")) {
                        channel = "BD123";
                        subChannel = "BD123"; // 123购买

                        mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.BD123;

	                    financeFirstCategory = "pc自有—搜狗渠道";
	                    financeSecondCategory = "123购买";
                    }
                    else {
	                    
/*	                     * 目前对于未在网页渠道配置文件，不是123购买、BD网吧、BD网址站的情况
	                     * 1. 如果其存在瀚海配置文件，且可以通过网页瀚海关联文件进行转换，则转化为对应的网页渠道
	                     * 2. 如果其存在瀚海配置文件，但不能转化的归类的，在网页增加新的渠道类别
	                     * 3. 如果不在瀚海配置文件中，不再将其归至BD其他，而是放到无效PID中*/
	                     

	                    // 未在网页渠道配置文件，但在瀚海渠道配置文件中
	                    if(FinancePidCategory.containsPid(stdPid)) {
		                    FinanceCategoryChannel.FinanceCategory financeCategory = new FinanceCategoryChannel.FinanceCategory(financeFirstCategory, financeSecondCategory);

		                    // 通过读取网页海涵关联文件，可以计算出网页的渠道（大部分情况归为一级渠道下的未归类）
		                    if(FinanceCategoryChannel.containsFinanceCategory(financeCategory)) {
		                        channel = FinanceCategoryChannel.getChannelByFinanceCategory(financeCategory);
		                        subChannel = FinanceCategoryChannel.getSubChannelByFinanceCategory(financeCategory);

			                    if (channel.equals("browser")) {
				                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.BROWSER_GOOD;
			                    }
			                    else if (channel.equals("ime")) {
				                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.IME_GOOD;
			                    }
			                    else if(channel.equals("tb")) {
				                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.TB_GOOD;
			                    }
			                    else if(channel.equals("tbh")) {
				                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.TBH_GOOD;
			                    }
			                    else if(channel.startsWith("tx.")){
				                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.TENCENT;
			                    }
			                    else if(channel.equals("BD")){
//			                         在瀚海配置文件中的pc外购中，才能归类成BD其他 
				                    mainCategory = WebChannelClassifier.MainCategoryType.BD;
				                    secondCategory = WebChannelClassifier.SecondCategoryType.BD;
			                    }
		                    } else {
//								 在瀚海配置文件中，但不能转化成网页渠道，应该自动归入新的网页渠道 
			                    channel = financeSecondCategory;
			                    subChannel = financeSecondCategory;
			                    mainCategory = MainCategoryType.NEW_CHANNEL;
			                    secondCategory = SecondCategoryType.NEW_CHANNEL;
		                    }
	                    } else {
		                    channel = "BD";
		                    subChannel = "PID_INVALID";
		                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
		                    secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

		                    financeFirstCategory = "financeFirstCategory_unknown";
		                    financeSecondCategory = "financeSecondCategory_not_in_channeldesc";
	                    }
                    }

                    // 命中了BD黑名单的pid要归为自有非优质
//                    final Date date = new Date(logtimestamp);
//                    if (mainCategory == WebChannelClassifier.MainCategoryType.BD
//                            && BdBlacklist.getInstance(dateFormatter.format(date)).isPidInBlacklist(pid)) {
//                        mainCategory = WebChannelClassifier.MainCategoryType.OWN_NOT_GOOD;
//                        secondCategory = WebChannelClassifier.SecondCategoryType.BD_BLACKLIST;
//
//	                    channel = "BD黑名单";
//	                    subChannel = "BD黑名单";
//
//	                    financeFirstCategory = "pc自有—搜狗渠道";
//	                    financeSecondCategory = "BD黑名单";
//                    }
                }
                else if (pid != null && stdPid == null) {
                    if(pid.toLowerCase().startsWith("5f0ef")) {
                        urlType = "sogou";
                        channel = "ime";
                        subChannel = "lanjie";

                        mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.IME_GOOD;

	                    financeFirstCategory = "pc自有—搜狗渠道";
	                    financeSecondCategory = "输入法地址栏";
                    } else {

                        // 虽然是http://www.sogou.com/sogou页面，但是pid无效的情况，可能是网站主填错pid
                        urlType = "sogou";
                        channel = "BD";
                        subChannel = "PID_INVALID"; // 其他pid

                        // 在与商业统计的逻辑分层中，将此类流量算作Web流量
                        mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

	                    financeFirstCategory = "pc自有—搜狗渠道";
	                    financeSecondCategory = "无效PID";
                    }
                }
            }
            else if (url.indexOf("http://www.sogou.com/web?") == 0) {
                uri = "web";
                urlType = "web";
				if ("sogou__free".equals(pid)) {
                    channel = "sogoufree"; // no refer
                    subChannel = "sogoufree";

                    mainCategory = WebChannelClassifier.MainCategoryType.OTHER_GOOD;
                    secondCategory = WebChannelClassifier.SecondCategoryType.SOGOU_FREE;

	                financeFirstCategory = "pc自有—搜狗渠道";
	                financeSecondCategory = "sogou-free";
                } else if(pid != null && pid.startsWith("sogou-know-")) {
					channel = "vertical_ad";
	                subChannel = "vertical_ad";

	                mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
	                secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

	                financeFirstCategory = "financeFirstCategory_unknown";
	                financeSecondCategory = "financeSecondCategory_vertical_ad";
                } else if("sogou".equals(pid)) {
		            if (WebChannelClassifier.finalpageMap.contains(p)) {
			            channel = "finalpage"; // 最终页
			            subChannel = "finalpage";

			            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
			            secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

			            financeFirstCategory = "pc自有—搜狗渠道";
			            financeSecondCategory = "搜狗自有（含最终页）";
		            }
		            else if (WebChannelClassifier.newFinalpageMap.contains(p)) {
			            channel = "newfinalpage"; // 新版最终页
			            subChannel = "newfinalpage";

			            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
			            secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

			            financeFirstCategory = "pc自有—搜狗渠道";
			            financeSecondCategory = "搜狗自有（含最终页）";
		            } else if(intcat != null && !"web".equals(intcat)) {
		                channel = "vertical_no_ad";
		                subChannel = "vertical_no_ad";

		                mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
		                secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

		                financeFirstCategory = "financeFirstCategory_unknown";
		                financeSecondCategory = "financeSecondCategory_vertical_no_ad";
	                } else {
                        channel = "sogouown"; // 搜狗自有
                        subChannel = "sogouown";

                        mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                        secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

		                financeFirstCategory = "pc自有—搜狗渠道";
		                financeSecondCategory = "搜狗自有（含最终页）";
	                }
                } else {
	                channel = "web_PID_INVALID";
	                subChannel = "web_PID_INVALID";
//	                subChannel = "PID_INVALID";

	                mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
	                secondCategory = WebChannelClassifier.SecondCategoryType.WEB;

	                financeFirstCategory = "financeFirstCategory_unknown";
	                financeSecondCategory = "financeSecondCategory_web_PID_INVALID";
                }
            }
            else if (url.indexOf("http://www.sogou.com/sohu?") == 0) {
                uri = "sohu";
                urlType = "sohu";
                channel = "sohu"; // 搜狐
                subChannel = "sohu";

                if ("sohu__free".equals(pid)) {
                    channel = "sohufree"; // no refer
                    subChannel = "sohufree";

                    mainCategory = WebChannelClassifier.MainCategoryType.OTHER_GOOD;
                    secondCategory = WebChannelClassifier.SecondCategoryType.SOHU_FREE;

	                financeFirstCategory = "financeFirstCategory_unknown";
	                financeSecondCategory = "financeSecondCategory_sohu_free";
                }
                else if ("sohu".equals(pid)){
                    channel = "sohuskip";//modify by litong 20130605
                    subChannel = "sohuskip";//modify by litong 20130605
                    mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
                    secondCategory = WebChannelClassifier.SecondCategoryType.SOHU;

	                financeFirstCategory = "pc自有—搜狗渠道";
	                financeSecondCategory = "sohu";
                } else {
	                channel = "sohu_PID_INVALID";
	                subChannel = "sohu_PID_INVALID";
//	                subChannel = "PID_INVALID";
	                mainCategory = MainCategoryType.OWN_GOOD;
	                secondCategory = SecondCategoryType.SOHU;

	                financeFirstCategory = "financeFirstCategory_unknown";
	                financeSecondCategory = "financeSecondCategory_sohu_PID_INVALID";
                }
            }
            else if (url.indexOf("http://www.sogou.com/quan?") == 0) {
	            uri = "web";
	            urlType = "web";
	            channel = "quan";
	            subChannel = "quan";
	            mainCategory = MainCategoryType.OWN_GOOD;
	            secondCategory = SecondCategoryType.WEB;

	            financeFirstCategory = "financeFirstCategory_unknown";
	            financeSecondCategory = "financeSecondCategory_quan";
            }
            else if (url.indexOf("http://www.soso.com/q?") == 0 || url.indexOf("http://www.soso.com/w.q?") == 0
            		|| url.indexOf("http://www.soso.com/cs.q?") == 0) {
            	uri = "soso";
            	urlType = "soso";
            	channel = "other";
            	subChannel = "other";
            	mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
            	secondCategory = WebChannelClassifier.SecondCategoryType.TENCENT;
	            financeFirstCategory = "financeFirstCategory_soso_unknown";
	            financeSecondCategory = "financeSecondCategory_soso_unknown";

            	stdPid = WebChannelClassifier.getStdPid(pid);

//	             财务渠道计算直接使用pid 
	            if(stdPid != null && FinancePidCategory.containsPid(stdPid)) {
		            financeFirstCategory = FinancePidCategory.getFirstCategory(stdPid);
		            financeSecondCategory = FinancePidCategory.getSecondCategory(stdPid);
	            }

            	if(stdPid != null && SogouPidChannel.containsPid(stdPid)) {
					channel = SogouPidChannel.getChannel(stdPid);
					subChannel = SogouPidChannel.getSubChannel(stdPid);

		            // todo: ugly code to adpater old and new lvpi data statistics logic
		            if(channel.equals("tbh") || channel.equals("tb")) {
			            subChannel = subChannel + "_soso";
		            }
                } else {
//                    Map<String, String> urlHm = ToolKits.getParameterHmFromURL(url);
//                    String cid = null;
//                    if(urlHm.containsKey("cid")) {
//                        cid = urlHm.get("cid");
//                    } else if(pid != null){
//                        String[] pidArray = pid.split("-");
//                        if(pidArray != null && pidArray.length >= 2) {
//                            cid = pidArray[pidArray.length - 1];
//                        }
//                    }
//                    if(cid != null && SosoCidChannel.hasCid(cid)) {
//                        channel = SosoCidChannel.getChannel(cid);
//                        subChannel = SosoCidChannel.getChannel(cid);
//                    }
            	}

	            if(channel != null) {
		            if(channel.equals("union") || channel.equals("InternetCafes")) {
			            mainCategory = WebChannelClassifier.MainCategoryType.SOSO;
			            secondCategory = WebChannelClassifier.SecondCategoryType.SOSO_BUY;
		            } else if (channel.equals("tb")) {
			            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
			            secondCategory = WebChannelClassifier.SecondCategoryType.TB_GOOD;
		            } else if (channel.equals("tbh")) {
			            mainCategory = WebChannelClassifier.MainCategoryType.OWN_GOOD;
			            secondCategory = WebChannelClassifier.SecondCategoryType.TBH_GOOD;
		            }
	            }
            }

	        return this;
        }

    }

	public static void main(String[] args) throws IOException, ParseException, ClassifyException {
		String ymd = "20140317";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(ymd);
		long logtimestamp = date.getTime();

		String pidListFile = "D:/url_pid";
		File sogou_output = new File("D:/sogou_invalid_pid");
		PrintWriter sogou_out= new PrintWriter(sogou_output);
		File web_output = new File("D:/web_invalid_pid");
		PrintWriter web_out= new PrintWriter(web_output);
		File sohu_output = new File("D:/sohu_invalid_pid");
		PrintWriter sohu_out= new PrintWriter(sohu_output);
		BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(pidListFile)));
		String line = null;
		int sogou_invalid_pid_num = 0;
		int web_invalid_pid_num = 0;
		int sohu_invalid_pid_num = 0;
		int total_pid_num = 0;
		long start = System.currentTimeMillis();
		while ((line = stdin.readLine()) != null) {
			String[] info = line.split("\t");
			if (info != null && info.length == 2) {
				total_pid_num++;
				String url = info[0];
				String pid = info[1];
//				String intcat = info[2];
//				String p = info[3];
				String intcat = null;
				String p = null;

				Classifier classifier = new Classifier(url, pid, p, intcat).invoke(logtimestamp);
//				String channel = classifier.getChannel();
				String subChannel = classifier.getSubChannel();
//				String financeFirstCategory = classifier.getFinanceFirstCategory();
//				String financeSecondCategory = classifier.getFinanceSecondCategory();
				if(subChannel.equals("PID_INVALID")) {
					sogou_invalid_pid_num++;
					sogou_out.println(url + "\t" + pid  + "\t" + subChannel);
				} else if (subChannel.equals("web_PID_INVALID")) {
					web_invalid_pid_num++;
					web_out.println(url + "\t" + pid  + "\t" + subChannel);
				} else if (subChannel.equals("sohu_PID_INVALID")) {
					sohu_invalid_pid_num++;
					sohu_out.println(url + "\t" + pid  + "\t" + subChannel);
				}
//				System.out.println(url + "\t" + pid  + "\t" + intcat + "\t" + p  + "\t" +
//						channel + "\t" + subChannel + "\t" + financeFirstCategory + "\t" + financeSecondCategory);
			}
		}
		System.out.println("sogou_invalid_pid_num = " + sogou_invalid_pid_num);
		System.out.println("web_invalid_pid_num = " + web_invalid_pid_num);
		System.out.println("sohu_invalid_pid_num = " + sohu_invalid_pid_num);
		System.out.println("all_invalid_pid_num = " + (sogou_invalid_pid_num + web_invalid_pid_num + sohu_invalid_pid_num));
		System.out.println("total_pid_num = " + total_pid_num);
		System.out.println("cost time = " + (System.currentTimeMillis() - start) / 1000);
		stdin.close();
		sogou_out.close();
		web_out.close();
		sohu_out.close();
	}
}
package org.work.spotoffer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

class IPRangeData {
    String startip;
    String endip;
    String city;
    public IPRangeData(String startip, String endip, String city) {
        this.startip = startip;
        this.endip = endip;
        this.city = city;
    }
}

class IPComparator implements Comparator<IPRangeData> {

    @Override
    public int compare(IPRangeData o1, IPRangeData o2) {
        return o1.startip.compareTo(o2.startip);
    }
    
}
public class Main {
    
    
	public static void main(String[] args) {
//		Scanner cin = new Scanner(System.in);
//		
//		int n = 0, k = 0, bit = 1;
//		StringBuilder str = new StringBuilder();
//	    IPRangeData item1 = new IPRangeData("10.0.1.1", "10.0.1.10", "NYC");
//	    IPRangeData item2 = new IPRangeData("10.1.1.1", "10.1.1.10", "WD");
//	    IPRangeData item3 = new IPRangeData("10.0.2.1", "10.0.2.10", "SF");
//	    
//	    IPRangeData[] data = {item1, item2, item3};
//	    printData(data);
//	    Arrays.sort(data, new IPComparator());
//	    printData(data);
	    
	    Main driver = new Main();
	    String s1 = "leetcodecode";
	    String s2 = "leetcodecodecode";
	    String s3 = "codeleetleet";
	    HashSet<String> dict = new HashSet<>();
	    dict.add("leet");
	    dict.add("code");
	    dict.add("co");
	    dict.add("de");
	    System.out.println(driver.wordBreak(s1, dict));
	    System.out.println("\n\n");
	    
	    dict.clear();
	    dict.add("leet");
        dict.add("code");
        dict.add("co");
        dict.add("de");
	    System.out.println(driver.wordBreak(s2, dict));
	    System.out.println("\n\n");
	    
	    dict.clear();
	    dict.add("leet");
        dict.add("code");
        dict.add("co");
        dict.add("de");
	    System.out.println(driver.wordBreak(s3, dict));
	}
	
	// not work
	public boolean wordBreak(String s, HashSet<String> dict) {
	        if (s == null || s.length() == 0)
	            return false;
	        int n = s.length();
	        boolean[] dp = new boolean[n + 1];
	        dp[0] = true;
	        for (int i = 1; i <= n; i++) {
	            StringBuilder sb = new StringBuilder(s.substring(0, i));
	            for (int j = 0; j < i; j++) {
//	                System.out.println(i + " " + j);
	                dp[i] |= dp[j] && dict.contains(sb.toString());
	                if (dp[i]) {
	                    System.out.println(sb.toString());
	                    dict.remove(sb.toString());
	                    break;
	                }
	                sb.deleteCharAt(0);
	            }
	        }
	        return dp[n];
	}
	public static void printData(IPRangeData[] data) {
	    for (IPRangeData item : data) {
	        System.out.println(item.startip + " " + item.city);
	    }
	}
	
	public static boolean check(String str){
//		boolean[] pat = new boolean[];
		return true;
	}
//	private static int[][] treeNode;
//	private static int k;
	public static void dfs(int i, int prev, LinkedList<Integer>path, int[][] treeNode, int k){
		if(treeNode[i][1] == -1 && treeNode[i][2] == -1){//leaf node
			if(prev + treeNode[i][0] == k){
				System.out.print("A path is found: ");
				for(int j = 0; j < path.size(); j++)
					System.out.print(path.get(j) + " ");
				System.out.println(i);
			}
			return;
		}
		
		path.add(i);
		prev += treeNode[i][0];
		dfs(treeNode[i][1], prev, path, treeNode, k);
		dfs(treeNode[i][2], prev, path, treeNode, k);
		path.removeLast();
	}
}

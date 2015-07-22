package org.work.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E000_Solution {
	public static void main(String[] args) {
		E000_Solution obj = new E000_Solution();
		StringBuilder sb = new StringBuilder();
		String aString = "/./././";
		aString = aString.replaceAll("/./", "/");
		System.out.println(aString);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		System.out.println(map.get("abc"));
	}
	
	public List<List<Integer>> generate(int numRows) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows <= 0) return result;
        List<Integer> row1 = new ArrayList<Integer>();
        row1.add(1);
        result.add(row1);
        if (numRows == 1) return result;
        List<Integer> lastRow = row1;
        for (int i = 2; i <= numRows; i++) {
            List<Integer> currRow = new ArrayList<Integer>();
            currRow.add(1);
            for (int j = 0; j < lastRow.size() - 1; j++) {
                currRow.add(lastRow.get(i) + lastRow.get(i + 1));
            }
            currRow.add(1);
            result.add(currRow);
            lastRow = currRow;
        }
        return result;
    }
	
    class ProfitInfo {
        int buyDay;
        int sellDay;
        int profit;
        public ProfitInfo(int buyDay, int sellDay, int profit) {
            this.buyDay = buyDay;
            this.sellDay = sellDay;
            this.profit = profit;
        }
    }
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        ProfitInfo profit1 = maxProfit(prices, 0, prices.length - 1);
        ProfitInfo profit2 = maxProfit(prices, 0, profit1.buyDay - 1);
        ProfitInfo profit3 = maxProfit(prices, profit1.sellDay + 1, prices.length - 1);
        return profit1.profit + Math.max(profit2.profit, profit3.profit);
    }
    private ProfitInfo maxProfit(int[] prices, int start, int end) {
        if (start >= end) // length of range <= 1
            return new ProfitInfo(start, end, 0);
        int min = start;
        int buy = start;
        int profit = 0;
        int sell = start;
        for (int i = start + 1; i <= end; i++) {
            if (prices[i] < prices[min]) {
                System.out.println("if: " + i + "=" + prices[i] + " " + prices[min]);
                min = i;
            } else if (prices[i] > prices[min]){
                System.out.println("else" + i + "=" + prices[i] + " " + prices[min]);
                int currProfit = prices[i] - prices[min];
                if (currProfit >= profit) {
                    profit = currProfit;
                    buy = min;
                    sell = i;
                }
            }
        }
        System.out.println("[" + start + "," + end + "], " + profit + "[" + buy + "," + sell + "]");
        return new ProfitInfo(buy, sell, profit);
    }
    
    public int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int left = (i - 1) / 2;
            for (int j = 0; j <= left; j++) {
                System.out.println(dp[i] + " " + " j=" + j + " " + dp[j] + " " + dp[n - 1 - j] + " " + (n - 1 - j));
                dp[i] += 2 * dp[j] * dp[i - 1 - j];
            }
            if (i % 2 == 1) {
                dp[i] -= dp[left] * dp[left];
                System.out.println("odd");
            }
            System.out.println(i + " " + dp[i] + " " + left + "\n\n");
        }
        return dp[n];
    }
	
	public List<String> findMissingRanges2(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<String>();
        if (vals == null || vals.length == 0) {
            ranges.add(start + "->" + end);
            return ranges;
        }
        int need = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > need) {
                ranges.add(getRange(need, vals[i] - 1));
            }
            need = vals[i] + 1;
        }
        if (need <= end)
            ranges.add(getRange(need, end));
        return ranges;
    }

	// 转载：http://www.danielbit.com/blog/puzzle/leetcode/leetcode-missing-ranges
   public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<String>();
        if (vals == null || vals.length == 0) {
            ranges.add(start + "->" + end);
            return ranges;
        }
        int prev = start - 1;
        for (int i = 0; i <= vals.length; i++) {
            int curr = (i == vals.length) ? end + 1 : vals[i];
            if (curr - prev >= 2) {
                ranges.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return ranges;
    }

    private String getRange(int from, int to) {
        return (from == to) ? String.valueOf(from) : from + "->" + to;
    }
	
    public int trailingZeroes2(int n) {
        if (n <= 0)
            return 0;
        int ret = 0;
        int divisor = 5;
        int factor;
        while (true) {
            factor = n / divisor;
            if (factor <= 0)
                break;
            ret += factor;
            divisor *= 5;
            if (divisor < 0)
                break;
        }
        System.out.println("max divisor=" + divisor);
        return ret;
    }
    
    public int trailingZeroes(int n) {
        if ( n<0 ) return -1;
        int count = 0;
        for (long i = 5; i <= Integer.MAX_VALUE && n/i>=1; i*=5) {
            count += n / i;
            System.out.println(i + " " + n / i + " " + count);
        }   
        return count;
    }
    
    public static  int trailingZeroes3(int n) {
        int count=0;
        while(n>0){
            count = count + n/5;
            n=n/5;
        }       
        return count;
    }
	
	public static final int INT_BITS = 32;
    public int majorityElement(int[] num) {
        int[] bits = new int[INT_BITS];
        // 数出num[]中 每个比特位上为1的个数
        for (int n : num) {
            for (int i = 0; i < INT_BITS; i++) {
                System.out.println(n + " " + (1 << i));
                if ((n & (1 << i)) > 0)
                    bits[i]++;
            }
        }
        
        int max = 0;
        int half = num.length / 2;
        for (int i = 0; i < INT_BITS; i++) {
//            System.out.println("bits " + i + "=" + bits[i]);
            if (bits[i] > half)
                max += (1 << i);
        }
        return max;
    }
    
	public int reverseBits(int n) {
	     System.out.println("input=" + n);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 32; i++) {
	            sb.append(n & 1);
	            n = n >> 1;
	        }
	        return Integer.valueOf(sb.toString());
	 }
	
	
	public ArrayList<String> generatePwd(){
		ArrayList<String> res = new ArrayList<String>();
		neighbors = new int[10][];
		neighbors[0] = new int[]{1,2,3,4,5,6,7,8,9};
		neighbors[1] = new int[]{2, 4, 5};
		neighbors[2] = new int[]{1, 3,4, 5,6};
		neighbors[3] = new int[]{2, 5, 6};
		neighbors[4] = new int[]{1,2,5,7,8};
		neighbors[5] = new int[]{1,2,3,4,6,7,8,9};
		neighbors[6] = new int[]{2,3,5,8,9};
		neighbors[7] = new int[]{4,5,8};
		neighbors[8] = new int[]{4,5,6,7,9};
		neighbors[9] = new int[]{5,6,8};
		used = new boolean[10];
		
		int start = 0;
		for(int len = 2; len <= 9; len++){
//			for(start = 1; start < 10; start++)
			for(StringBuilder item: build(0, len)){
//				item.insert(0, start);
				res.add(item.toString());
			}
		}
			
		return res;
	}
	private int[][] neighbors;
	private boolean[] used;
	public ArrayList<StringBuilder> build(int prev, int len){
		ArrayList<StringBuilder> res = new ArrayList<StringBuilder>();
		if(len == 1){
			for(int next : neighbors[prev]){
				if(!used[next]){
					used[next] = true;
					StringBuilder str = new StringBuilder(next);
						res.add(str);
					}
					used[next] = false;
				}
			return res;
		}

		for(int next : neighbors[prev]){
			if(!used[next]){
				used[next] = true;
				for(StringBuilder str : build(next, len - 1)){
					str.insert(0, next);
					res.add(str);
				}
				used[next] = false;
			}
		}
		
		return res;
	}

}

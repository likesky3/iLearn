/**
 * BuySellStockII_20.java
 * 
 * Copyright (c) 2011 State Street Bank and Trust Corp. 225 Franklin Street, Boston, MA
 * 02110, U.S.A. All rights reserved.
 * 
 * BuySellStockII_20.java is the copyrighted, proprietary property of State Street Bank
 * and Trust Company and its subsidiaries and affiliates which retain all right, title and
 * interest therein."
 * 
 * Revision History
 * 
 * Date Programmer Notes ------------ -------------------- ----------------------------
 * May 28, 2013 a524690 Init
 * 
 */
package org.work.leetcode;

import java.util.ArrayList;

/**
 * @author a524690 > 5h, too much time in debugging
 * 
 *         V1:只计算下坡中各点和波峰处（事实上可以只计算波峰波谷处）的singlePro & doublePro
 *         依次选之前的每个波谷处买进，在该波峰卖出作为第二笔交易，加上所选定波谷前的最大单次交易值，所得和为这个波谷处doublePro的候选值，
 *         这些候选值以及该波峰前的一个波谷处的最大二次交易值中的最大值作为该波峰最终的doublePro
 * 
 *         V2: max([0..i] + [i..n])
 */
public class E021_BuySellStockIII {
	public static void main(String[] args) {
		int[] test = {2,1,2,0,1 };
		int profit2 = E021_BuySellStockIII.maxProfit5(test);
		System.out.println(profit2);
	}

	public static int maxProfitV1(int[] prices) {
		int n = prices.length;
		if (n == 0 || n == 1)
			return 0;

		int lowest = 0; // 最低价的下标
		int low = 0;
		int[] singlePro = new int[n]; // singlePro[i]: i处 为止单笔交易的最大值

		int[] doublePro = new int[n]; // doublePro[i]: i处为止至多两笔交易的最大值
		singlePro[0] = 0;
		doublePro[0] = 0;
		int tmp;
		ArrayList<Integer> troughs = new ArrayList<Integer>();// 存储所有波谷的下标
		if (prices[0] < prices[1])
			troughs.add(0);

		for (int i = 1; i < n; i++) {
			if (prices[i] <= prices[i - 1]) {
				// 下坡中singlePro和doublePro均用上一个波峰处的值
				// 如果维护一个波峰列表的话，可以只计算波谷处的值
				singlePro[i] = singlePro[i - 1];
				doublePro[i] = doublePro[i - 1];

				if (prices[i] < prices[lowest])
					lowest = i;
				if (i == n - 1 || prices[i] < prices[i + 1]) // @波谷
					troughs.add(new Integer(i));

			} else if (i == n - 1 || prices[i] >= prices[i + 1]) {
				low = troughs.get(troughs.size() - 1);
				tmp = prices[i] - prices[lowest];

				// singlePro[i] = (tmp > singlePro[i - 1]) ? tmp
				// : singlePro[i - 1]; //wrong,非波峰和下坡处的点没有计算其singlePro
				singlePro[i] = (tmp > singlePro[low]) ? tmp : singlePro[low];

				doublePro[i] = doublePro[low]; // 用该点之前的波谷处的doublePro初始化
				for (int k = low; k >= 0; k--) {
					tmp = (prices[i] - prices[k]) + singlePro[k];
					doublePro[i] = (tmp > doublePro[i]) ? tmp : doublePro[i];
				}
			}

		}

		return doublePro[n - 1];
	}

	// max(max[1..i] + max[i..n]) (1 < i < n)
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;

		int n = prices.length;
		// part 1, calculate max profit of [1..i]
		int[] maxEndsAt = new int[n];
		int lowest = prices[0];
		maxEndsAt[0] = 0;
		for (int i = 1; i < n; i++) {
			int candidate = prices[i] - lowest;
			maxEndsAt[i] = candidate > maxEndsAt[i - 1] ? candidate: maxEndsAt[i - 1];
			if (prices[i] < lowest)
				lowest = prices[i];
		}

		// part2, calculate max profit of [i..n], meanwhile, update
		// max2tradeProfit
		int max2 = 0;// the max profit of 2 trade till now
		int curr2 = 0; // the profit of 2 trade which the second trade begin in this day
		int startsAt = 0;// the profit of the current second trade
		int highest = prices[n - 1];
		for (int j = n - 1; j > 0; j--) {
			if (prices[j] > highest) {
				highest = prices[j];
				startsAt = 0;
			} else
				startsAt = highest - prices[j];
			curr2 = maxEndsAt[j] + startsAt;
			max2 = curr2 > max2 ? curr2 : max2;
		}

		return max2;
	}

	//数组中增幅最大和次大的递增子数组 - 不对，fail @ [1,2,4,2,5,7,2,4,9,0]
    //问题的数学模型是：在无序数组中找到首尾差最大的两个区间
	//后面两部分可以合并来求，参照上一版本& version5
	public static int maxProfit4(int[] prices) {
        if(prices == null || prices.length < 2) 
            return 0;
        
        int n = prices.length;
        int[] maxProfitSellAt = new int[n];
        int[] maxProfitBuyAt = new int[n];
        int max = 0, curr = 0;
        int low = prices[0];
        int high = prices[n-1];
       
        //scan from 0 to n-1 to calculate maxProfitSellAt day i
        maxProfitSellAt[0] = 0;
        for(int i = 1; i < n; i++){
            if(prices[i] < low)
                low = prices[i];
             curr = prices[i] - low;
             maxProfitSellAt[i] = maxProfitSellAt[i - 1] > curr ? maxProfitSellAt[i] : curr;
        }
        
        //scan from n-1 to 0 to calculate maxProfitBuyAt day i
         maxProfitBuyAt[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--){
            if(prices[i] > high)
                high = prices[i];
            curr = high - prices[i];
            maxProfitBuyAt[i] = maxProfitBuyAt[i + 1] > curr ? maxProfitBuyAt[i + 1] : curr;
        }
        for(int i = 0; i < n; i++){
            curr = maxProfitSellAt[i] + maxProfitBuyAt[i];
            if(curr > max)
                max = curr;
        }
        return max;
    }
	
	public static int maxProfit5(int[] prices) {
        if(prices == null || prices.length < 2) 
            return 0;
        
        int n = prices.length;
        int[] maxProfitSellAt = new int[n];
        //int[] maxProfitBuyAt = new int[n];
        int max = 0, curr = 0;
        int low = prices[0];
        int high = prices[n-1];
       
        //scan from 0 to n-1 to calculate maxProfitSellAt day i
        maxProfitSellAt[0] = 0;
        for(int i = 1; i < n; i++){
            if(prices[i] < low)
                low = prices[i];
             curr = prices[i] - low;
             maxProfitSellAt[i] = maxProfitSellAt[i - 1] > curr ? maxProfitSellAt[i - 1] : curr;
             System.out.println("low:" + low + " sellat:" + maxProfitSellAt[i]);
        }
        
        System.out.println("---------");
        //scan from n-1 to 0 to calculate maxProfitBuyAt day i
        //maxProfitBuyAt[n - 1] = 0;
         int twoTradesProfit = 0;
         int buyAt = 0;
        for(int i = n - 1; i >= 0; i--){
            if(prices[i] >= high){
                high = prices[i];
                buyAt = 0;
            }else
                buyAt = high - prices[i];
           System.out.println(buyAt);
            twoTradesProfit = maxProfitSellAt[i] + buyAt;
            if(twoTradesProfit > max)
                max = twoTradesProfit;
        }
        return max;
    }
}

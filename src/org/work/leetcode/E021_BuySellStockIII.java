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
 * V1:只计算下坡中各点和波峰处（事实上可以只计算波峰波谷处）的singlePro & doublePro		
 * 依次选之前的每个波谷处买进，在该波峰卖出作为第二笔交易，加上所选定波谷前的最大单次交易值，所得和为这个波谷处doublePro的候选值，
 * 这些候选值以及该波峰前的一个波谷处的最大二次交易值中的最大值作为该波峰最终的doublePro
 * 
 * V2: max([0..i] + [i..n])
 */
public class E021_BuySellStockIII {
	public static void main(String[] args) {
		int[] test = { 2, 4, 1 };
		int profit2 = E021_BuySellStockIII.maxProfit(test);
		System.out.println(profit2);
	}

	public static int maxProfitV1(int[] prices) {
		int n = prices.length;
		if (n == 0 || n == 1)
			return 0;

		int lowest = 0; //最低价的下标
		int low = 0;
		int[] singlePro = new int[n]; // singlePro[i]: i处 为止单笔交易的最大值
										
		int[] doublePro = new int[n]; // doublePro[i]: i处为止至多两笔交易的最大值
		singlePro[0] = 0;
		doublePro[0] = 0;
		int tmp;
		ArrayList<Integer> troughs = new ArrayList<Integer>();//存储所有波谷的下标
		if(prices[0] < prices[1])
			troughs.add(0);

		for (int i = 1; i < n; i++) {
			if (prices[i] <= prices[i - 1]) {
				// 下坡中singlePro和doublePro均用上一个波峰处的值
				//如果维护一个波峰列表的话，可以只计算波谷处的值
				singlePro[i] = singlePro[i - 1];
				doublePro[i] = doublePro[i - 1];

				if (prices[i] < prices[lowest])
					lowest = i;
				if (i == n - 1 || prices[i] < prices[i + 1]) // @波谷
					troughs.add(new Integer(i));

			} else if (i == n - 1 || prices[i] >= prices[i + 1]) {
					low = troughs.get(troughs.size() - 1);
					tmp = prices[i] - prices[lowest];
					
//					singlePro[i] = (tmp > singlePro[i - 1]) ? tmp
//							: singlePro[i - 1]; //wrong,非波峰和下坡处的点没有计算其singlePro
					singlePro[i] = (tmp > singlePro[low]) ? tmp
							: singlePro[low];
					
					doublePro[i] = doublePro[low]; // 用该点之前的波谷处的doublePro初始化
					for (int k = low; k >= 0; k--) {
						tmp = (prices[i] - prices[k]) + singlePro[k];
						doublePro[i] = (tmp > doublePro[i]) ? tmp
								: doublePro[i];
					}
				}

		}

		return doublePro[n - 1];
	}
	
	public static int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int n = prices.length;
        if(n == 0 || n == 1)
            return 0;
        
        //compute max profit of 1 transaction [0 .. i]
        int low = prices[0];
        int endsAt[] = new int[n]; //endsAt[0] is useless
        for(int i = 1; i < n; i++)
        {
            endsAt[i] = (prices[i] - low) > endsAt[i - 1] ? (prices[i] - low) : endsAt[i - 1];
            low = prices[i] < low ? prices[i] : low; //update low along the way
        }
        
        //compute max profit of 1 transaction [i.. n -1] and then compute max profit of 2 transactions
        int delta = 0; //profit of a second transaction
        int high = prices[n - 1]; //highest prices scanned
        int max2 = 0; //store max profit of the second transaction
        int currMax = 0; //sum of a pair of two transactions
        int res = 0; //store max value of two transactions
        for(int i = n - 2; i >= 0; i--)
        {
            delta = high - prices[i];
            max2 = delta > max2 ? delta : max2;
            if(prices[i] > high)
                high = prices[i];
            currMax = max2 + endsAt[i];
            res = currMax > res ? currMax : res;
        }
        
        return res;
    }
}


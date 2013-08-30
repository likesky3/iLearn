/**
 * BuySellStock_17.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * BuySellStock_17.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 22, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;



/**
 * @author a524690
 * the first reaction is to sort the prices and the maximum = max - min, sadly it's wrong.
 * buy stock at day i and sell at day j, i & j should satisfy j > i
 * 
 * 从前往后，用当前价格减去此前最低价格，就是在当前点卖出股票能获得的最高利润。
*  扫描的过程中更新最大利润和最低价格就行了。
 */
public class E017_BuySellStock
{
    public static void main(String[] args)
    {
	int[] prices = {10,1,5,7};
	E017_BuySellStock.maxProfit(prices);
    }
    
    public static int maxProfit(int[] prices) {
	int len = prices.length;
	if(0 == len || 1 == len)
	    return 0;
	
	int profit = 0;
	int lowest = prices[0];
	for(int i = 1; i < len; i++)
	{
	    if(prices[i - 1] < lowest)
		lowest = prices[i - 1];
	    int tmp = prices[i] - lowest;
	    if(tmp > profit)
		profit = tmp;
	}
	
        return profit;
    }
    
   
   
}

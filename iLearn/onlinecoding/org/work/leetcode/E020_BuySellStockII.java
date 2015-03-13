/**
 * BuySellStockII_20.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * BuySellStockII_20.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 28, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

/**
 * @author a524690
 *累加所有的上升区间。
 *第一个版本每次走到最高点，下降前累加上一段上升区间；
 *第二个版本是每次遇到上升区间就停下来累加。
 */
public class E020_BuySellStockII
{
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1)
            return 0;
            
        int low = prices[0];
        int high = prices[0];
        int profit = 0;
        
        for(int i = 0; i < prices.length; i++)
        {
            if(prices[i] > high)
                high = prices[i];
            else
            {
                if(high != low)
                    profit += high - low;
                low = prices[i];
                high = prices[i];
            }
        }
        if(high != low)
            profit += high - low;
        return profit;
    }
    
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int low = prices[0], high = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > low){
                high = prices[i];
                profit += high - low;
                low = high;
            }else{
                low = prices[i];
                high = prices[i];
            }
        }
        return profit;
    }
}

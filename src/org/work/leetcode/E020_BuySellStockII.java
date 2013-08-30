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
 *
 */
public class E020_BuySellStockII
{
    public int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
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
}

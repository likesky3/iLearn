/**
 * PascalTriangleI.java
 * 
 * Copyright (c) 2011 State Street Bank and Trust Corp. 225 Franklin Street, Boston, MA
 * 02110, U.S.A. All rights reserved.
 * 
 * PascalTriangleI.java is the copyrighted, proprietary property of State Street Bank and
 * Trust Company and its subsidiaries and affiliates which retain all right, title and
 * interest therein."
 * 
 * Revision History
 * 
 * Date Programmer Notes ------------ -------------------- ----------------------------
 * May 21, 2013 a524690 Init
 * 
 */
package org.work.leetcode;

import java.util.ArrayList;

/**
 * @author a524690
 * 
 */
public class E014_PascalTriangleI
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
	
	ArrayList<ArrayList<Integer>> res = E014_PascalTriangleI.generate(3);
	System.out.println(res);
    }

    public static ArrayList<ArrayList<Integer>> generate(int numRows)
    {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	
	if (numRows == 0)
	    return result;
	
	ArrayList<Integer> firstRow = new ArrayList<Integer>();
	firstRow.add(1);
	result.add(firstRow);
	if(numRows == 1)
	    return result;
	
	ArrayList<Integer> secRow = new ArrayList<Integer>();
	secRow.add(1);
	secRow.add(1);
	result.add(secRow);
	if(numRows == 2)
	    return result;
	
	ArrayList<Integer> lastRow = secRow;
	for(int k = 3; k <= numRows; k++)
	{
	    ArrayList<Integer> newRow = new ArrayList<Integer>();
	    newRow.add(lastRow.get(0));
	    int elem = 0;
	    int i = 1;
	    for(; i < lastRow.size(); i++)
	    {
		elem = lastRow.get(i) + lastRow.get(i - 1);
		newRow.add(elem);
	    }
	    newRow.add(lastRow.get(i - 1));
	    result.add(newRow);
	    lastRow = newRow;
	}
	return result;
    }

}

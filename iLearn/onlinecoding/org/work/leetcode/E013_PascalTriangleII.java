/**
 * PascalTriangleII.java 
 *
 * Copyright (c) 2011 State Street Bank and Trust Corp.
 * 225 Franklin Street, Boston, MA 02110, U.S.A.
 * All rights reserved.
 *
 * PascalTriangleII.java is the copyrighted,
 * proprietary property of State Street Bank and Trust Company and its
 * subsidiaries and affiliates which retain all right, title and interest therein."
 * 
 * Revision History
 * 
 * Date            Programmer                Notes
 * ------------    --------------------      ----------------------------
 * May 21, 2013 	   a524690		     Init
 *
 */
package org.work.leetcode;

import java.util.ArrayList;

/**
 * @author a524690
 *
 */
public class E013_PascalTriangleII
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
	// TODO Auto-generated method stub
	ArrayList<Integer> res = E013_PascalTriangleII.getRow2(3);
	System.out.println(res);

    }
    
    //extra space O(n^2)
    public static ArrayList<Integer> getRow(int rowIndex)
    {
	
//	if (rowIndex == 0)
//	    return new ArrayList<Integer>();
	
	ArrayList<Integer> firstRow = new ArrayList<Integer>();
	firstRow.add(1);
	if(rowIndex == 0)
	    return firstRow;
	
	ArrayList<Integer> secRow = new ArrayList<Integer>();
	secRow.add(1);
	secRow.add(1);
	if(rowIndex == 1)
	    return secRow;
	
	ArrayList<Integer> lastRow = secRow;
	for(int k = 3; k <= rowIndex + 1; k++)
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
	    lastRow = newRow;
	}
	return lastRow;
    }
    
    //extra space O(1)
    public static ArrayList<Integer> getRow2(int rowIndex)
    {
	if(rowIndex < 0)
	    return new ArrayList<Integer>();
	
	ArrayList<Integer> ret = new ArrayList<Integer>();
	for(int irow = 0; irow <= rowIndex; irow++)
	{
	    ret.add(1);
	    for(int icol = irow - 1; icol > 0; icol--)
	    {
		ret.set(icol, ret.get(icol) + ret.get(icol - 1));
	    }
	}
	return ret;
    }

}

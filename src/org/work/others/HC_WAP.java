package org.work.others;

public class HC_WAP {

}

class listProcess {
	int start;
	int end;
	int length;
	public String longestSeq(String input, compare com)
	{
		if(input.length() < 2)
			return input;
		start = 0; 
		end = 1; 
		length = 1;
		int curStart = 0, curEnd = 0;
		
		for(int i = 1; i < input.length(); i++)
		{
			if(com.charCom(input.charAt(i-1), input.charAt(i)))
			{
				curEnd = i + 1;
			}
			else
			{
				checkLength(curEnd, curStart);
				curStart = i;
			}
		}
		checkLength(curEnd, curStart);
		return input.substring(start, end);
	}
	
	private void checkLength(int curEnd, int curStart)
	{
		if(curEnd - curStart > length)
		{
			start = curStart;
			end = curEnd;
			length = curEnd - curStart;
		}
	}
	
	public static void main(String[] args)
	{
		listProcess sp = new listProcess();
		String binary_small1 = "0";
		System.out.println(sp.longestSeq(binary_small1, new equalCom()));
		String binary = "00001111100111000000";
		System.out.println(sp.longestSeq(binary, new equalCom()));
		String stringArray = "abAAgdfhTYHGGGGdfFFFggg";
		System.out.println(sp.longestSeq(stringArray, new equalCom()));
		String numArray = "1234543898901123489";
		System.out.println(sp.longestSeq(numArray, new increaseCom()));
		
	}
}

interface compare
{
	public boolean charCom(char first, char second);
}

class equalCom implements compare
{
	@Override
	public
	boolean charCom(char first, char second)
	{
		if(first == second)
			return true;
		return false;
	}
}

class increaseCom implements compare
{
	@Override
	public boolean charCom(char first, char second)
	{
		if( second > first)
			return true;
		return false;
	}
}

/*interface compare
{
	public boolean charCom(char first, char second);
	public boolean intCom(int first, int second);
}

2069724217 2014/4/30 21:47:26
interface compare中增加一个，但是intCom内部代码和charCom完全一样，感觉重复

2069724217 2014/4/30 21:48:03
题目是，输入数字串，输出最长的增长序列

2069724217 2014/4/30 21:48:23
输入字符串，输出最长的相等序列*/

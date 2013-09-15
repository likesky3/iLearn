package org.work.leetcode;

import java.util.TreeSet;

public class E101_WildcardMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E101_WildcardMatching obj = new E101_WildcardMatching();
		boolean res = obj.isMatch2("baabb", "bb?a?a");
		System.out.println(res);
	}

	public boolean isMatch(String s, String p) {
		return dfs(0, s.length(), s, p, 0);
	}

	// recursive version
	private boolean dfs(int dep, int maxDep, String s, String p, int i) {
		if (dep == maxDep) {
			if (i == p.length())
				return true;

			while (i < p.length() && p.charAt(i) == '*')
				i++;
			return i < p.length() ? false : true;

		}

		if (i == p.length())
			return false;
		else if (p.charAt(i) == '*') {
			return dfs(dep + 1, maxDep, s, p, i)
					|| dfs(dep, maxDep, s, p, i + 1);
		} else if (s.charAt(dep) == p.charAt(i) || p.charAt(i) == '?')
			return dfs(dep + 1, maxDep, s, p, i + 1);
		else
			return false;
	}
	
	//抓住重点，再填充细节
	private boolean dfs2(String s, String p, int si, int pi) {
		if (si == s.length()) {
			if (pi == p.length())
				return true;

			while (pi < p.length() && p.charAt(pi) == '*')
				pi++;
			return pi < p.length() ? false : true;

		}

		if (pi == p.length())
			return false;
		else if (p.charAt(pi) == '*') {
			return dfs2(s, p, si + 1, pi)
					|| dfs2(s, p, si, pi + 1);
		} else if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')
			return dfs2(s, p, si + 1, pi + 1);
		else
			return false;
	}

	// dynamic programming version
	//http://discuss.leetcode.com/questions/222/wildcard-matching?
	//c++ version ok, java version fail @ large
	//dp[i, j]为true，表示目标串s[0..j]能够被p[0..i]匹配，状态转移方程是
	//if(p[i] = '*') dp[i, j] = dp[i - 1, j] || dp[i, j - 1];
	//else if(p[i] = '?' || p[i] = s[j]) dp[i, j] = dp[i - 1, j - 1]
	//else  dp[i, j] =false;
	public boolean isMatch2(String s, String p) {
		int m = p.length();
		int n = s.length();
		if ((s == null || s.isEmpty()) && (p == null || p.isEmpty()))
			return true;
		
		//直接的想法是维护一个m * n的判断矩阵，
		//因为当前行的计算只依赖于前一行，所以只需维护两个长度为n + 1的数组
		boolean[][] r = new boolean[2][n + 1];
		r[0][0] = true;//相当于两个空串匹配
		for (int i = 1; i < r[0].length; i++)
			r[0][i] = false;

		int prev = 0, curr = 1;
		for (int i = 0; i < m; i++) {
			curr = prev == 0 ? 1 : 0;
			if (p.charAt(i) == '*') {
				// successive '*' is equivalent to a single '*', so we may
				// surpass them together
				while (++i < m && p.charAt(i) == '*')
					;
				i--;

				//解释原理时，设i, j分别为p & s的当前下标，从0开始
				//p[i] =='*'时，计算r[curr][j + 1]只需要看左边一格和上边一格
				//看左边格是因为如果s[0.. j - 1]能被p[0..i]匹配的话，那么s[0..j]也能被匹配，因为*可以匹配任意多个字符
				//看上边格是因为如果s[0.. j]能被p[0.. i - 1]匹配的话，那么也能被p[0..i]匹配，因为*可以匹配0个字符
				r[curr][0] = r[prev][0];
				int j = 1;
				for (; j <= n; j++) {
					r[curr][j] = r[prev][j] || r[curr][j - 1];
					if (r[curr][j]) {
						break;
					}
				}
				//一旦r[curr][j]为true，则同行后面格子都为true
				for (; j <= n; j++)
					r[curr][j] = true;

				prev = curr;
			} else {
				r[curr][0] = false;
				for (int j = 1; j <= n; j++) {
					if(s.charAt(j - 1) == p.charAt(i) || p.charAt(i) == '?')
						r[curr][j] = r[prev][j - 1];//若当前下标处匹配，看对角线上的前一个值
					else//当前下标处不匹配
						r[curr][j] = false;
				}
				prev = curr;
			}
//			for(int k = 0; k <= n; k++)
//				System.out.print(r[curr][k] + " ");
//			System.out.println();
		}

		return r[curr][n];
	}
	
	// passed java version
	// Time: O(|s||p|*log|s|), Space: O(|s|)
	// Time can also optimize to O(|s||p|)
	public boolean isMatch3(String s, String p) {
	    // without this optimization, it will fail for large data set
	    int plenNoStar = 0;
	    for (char c : p.toCharArray())
	        if (c != '*') plenNoStar++;
	    if (plenNoStar > s.length()) return false;

	    s = " " + s;
	    p = " " + p;
	    int slen = s.length();
	    int plen = p.length();

	    boolean[] dp = new boolean[slen];
	    TreeSet<Integer> firstTrueSet = new TreeSet<Integer>();
	    firstTrueSet.add(0);
	    dp[0] = true;

	    boolean allStar = true;
	    for (int pi = 1; pi < plen; pi++) {
	        if (p.charAt(pi) != '*')
	            allStar = false;
	        for (int si = slen - 1; si >= 0; si--) {
	            if (si == 0) {
	                dp[si] = allStar ? true : false;
	            } else if (p.charAt(pi) != '*') {
	                if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') dp[si] = dp[si-1];
	                else dp[si] = false;
	            } else {
	                int firstTruePos = firstTrueSet.isEmpty() ? Integer.MAX_VALUE : firstTrueSet.first();
	                if (si >= firstTruePos) dp[si] = true;
	                else dp[si] = false;
	            }
	            if (dp[si]) firstTrueSet.add(si);
	            else firstTrueSet.remove(si);
	        }
	    }
	    return dp[slen - 1];
	}
}

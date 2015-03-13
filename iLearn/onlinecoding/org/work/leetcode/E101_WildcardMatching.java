package org.work.leetcode;

import java.util.TreeSet;

//currently, version 4 is the best, version 5's idea is wonderful, more efficient, but need debugging 
public class E101_WildcardMatching {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		E101_WildcardMatching obj = new E101_WildcardMatching();
		boolean res = obj.isMatch("abcedef", "*ab*ef");
		System.out.println(res);
	}

	public boolean isMatch(String s, String p) {
		return dfs(s, p, 0, 0);
	}

	//抓住重点，再填充细节
	private boolean dfs(String s, String p, int si, int pi) {
		System.out.println("si: " + si + ",  pi: " + pi);
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
			return dfs(s, p, si + 1, pi)
					|| dfs(s, p, si, pi + 1);
		} else if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')
			return dfs(s, p, si + 1, pi + 1);
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
	    if (plenNoStar > s.length()) return false;//模式串中非*字符的长度 > 待匹配串长度，则一定不能完全匹配
	    if(plenNoStar == p.length() && plenNoStar < s.length()) return false; //模式串不含*，且长度<待匹配串长度，则不能完全匹配

	    //s, p前面均加上一个空格，是为了简化初始化过程，若不然，需根据p首个字符的情况来分别进行初始化
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
	                dp[si] = allStar ? dp[si] : false;
	            } else if (p.charAt(pi) != '*') {
	                if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') dp[si] = dp[si-1];
	                else dp[si] = false;
	            } else {//模式串当前位置为*，那么看上面和前面一个的状态值
	            	if(p.charAt(pi - 1) == '*') continue;//successive '*' is equivalent to a single '*', so we may surpass them all
	                int firstTruePos = firstTrueSet.isEmpty() ? Integer.MAX_VALUE : firstTrueSet.first();
	                if (si >= firstTruePos) dp[si] = true;//上一轮(p[pi - 1])匹配的位置以及其后的都能用*匹配，所以都为true
	                else dp[si] = false;
	            }
	            if (dp[si]) firstTrueSet.add(si);
	            else firstTrueSet.remove(si);
	        }
	    }
	    return dp[slen - 1];
	}
	
	//my version, faster than version 3 @ large,增加了更多的剪枝条件，而且没有用treeset
	public boolean isMatch4(String s, String p) {
	    // without this optimization, it will fail for large data set
	    int plenNoStar = 0;
	    for (char c : p.toCharArray())
	        if (c != '*') plenNoStar++;
	    if (plenNoStar > s.length()) return false;//模式串中非*字符的长度 > 待匹配串长度，则一定不能完全匹配
	    if(plenNoStar == p.length() && plenNoStar != s.length()) return false; //模式串不含*，且长度!=待匹配串长度，则不能完全匹配

	  //s, p前面均加上一个空格，是为了简化初始化过程，若不然，需根据p首个字符的情况来分别进行初始化
	    s = " " + s;
	    p = " " + p;
	    int slen = s.length();
	    int plen = p.length();

	    boolean[] dp = new boolean[slen];
	   int lastTruePos = 0;//记录上一轮非*字符匹配上的位置
	   int currTruePos = Integer.MAX_VALUE;
	    dp[0] = true;

	    boolean allStar = true;
	    for (int pi = 1; pi < plen; pi++) {
	      //successive '*' is equivalent to a single '*', so we may surpass them all
	        if(p.charAt(pi - 1) == '*' && p.charAt(pi) == '*') 
	        	continue;
	        
	        if (p.charAt(pi) != '*')
	            allStar = false;
	        currTruePos = Integer.MAX_VALUE;
	        for (int si = slen - 1; si >= 0; si--) {
	            if (si == 0) {
	                dp[si] = allStar ? dp[si] : false;
	            } else if (p.charAt(pi) != '*') {
	                if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') dp[si] = dp[si-1];
	                else dp[si] = false;
	            } else {//模式串当前位置为*，那么看上面和前面一个的状态值
	                if (si >= lastTruePos) dp[si] = true;//上一轮(p[pi - 1])匹配的位置以及其后的都能用*匹配，所以都为true
	                else dp[si] = false;
	            }
	            if (dp[si]) currTruePos = si;
	        }
	        if(currTruePos == Integer.MAX_VALUE)//p[0..pi]不能匹配任何一个s[0..si]
	        	return false;
	        lastTruePos = currTruePos;
	    }
	    return dp[slen - 1];
	}

	/*version 5******************************************************/
	//O(|s|)
	//fail @ "mississippi", "m*iss*iss*"
	public boolean isMatch5(String s, String p) {
        if(s == null || s.isEmpty()){
            if(p == null || p.isEmpty() || containsOnlyStar(p))
                return true;
            else
                return false;
        }
        if(p == null || p.isEmpty())
            return false;
        
        //both s & p is non-empty string
        //calculate #chars in p after ignoring *
        int plenNoStar = 0;
	    for (char c : p.toCharArray())
	        if (c != '*') plenNoStar++;
	    if (plenNoStar > s.length()) return false;//模式串中非*字符的长度 > 待匹配串长度，则一定不能完全匹配
	    if(plenNoStar == p.length() ){//模式串不含*
	    	if(plenNoStar != s.length()) 
	    		return false; //模式串不含*，且长度!=待匹配串长度，则不能完全匹配
	    	else
	    		 return check(s,0, p.length() - 1, p);
	    }
        
	    //s.length > plenNostar
        String[] tuples = p.split("\\*", -1);
        int i = 0, j = tuples.length - 1;
        int l = 0, r = s.length() - 1;
        while(i <  j){
        	int leftLen = tuples[i].length();
        	if(leftLen > 0){
        		if(!check(s, l, l + leftLen - 1, tuples[i]))
        			return false;
        		l += leftLen;
        	}
        	int rightLen = tuples[j].length();
        	if(rightLen > 0){
        		if(!check(s, r - rightLen + 1, r, tuples[j]))
        			return false;
        		r -= rightLen;
        	}
        	i++;
        	j--;
        }
        //对s[l..r] 和  tuples[i]进行匹配，tuples[i]中可能含有多个?，s[l..r]长度可能大于tuples[i]长度
        if(i == j){
        	int c = 0; //前面待匹配的？个数
        	for(int si = l, ti = 0; si <= r && ti < tuples[i].length(); ti++){
        		int curr = tuples[i].charAt(ti) ;
        		if(curr == '?'){
        			c++;
        			continue;
        		}else{
        			int match = s.indexOf(curr, l + c);
        			if(match == -1)
        				return false;
        			c = 0;
        			si++;
        			l = match + 1;
        		}
        			
        	}
        }
        
        return true;
        
    }
	
	private boolean check(String s, int beg, int end, String p){
		for(int si = beg, pi = 0; si <= end; si++, pi++){
			 if(p.charAt(pi) != '?' && s.charAt(si) != p.charAt(pi))
	                return false;
		}
		return true;
	}
    
    private boolean containsOnlyStar(String p){
        if(p == null || p.isEmpty())
            return false;
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) != '*')
                return false;
        }
        return true;
    }
   
    /*version 5******************************************************/
}

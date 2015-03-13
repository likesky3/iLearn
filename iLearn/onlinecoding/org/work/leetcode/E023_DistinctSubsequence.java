package org.work.leetcode;

public class E023_DistinctSubsequence {
	public static void main(String[] args) {
		E023_DistinctSubsequence instance = new E023_DistinctSubsequence();
		// System.out.println(instance.numDistinct("rabbbit", "rabbit"));
		System.out.println(instance.numDistinct("aacaacca", "ca"));
		System.out.println(instance.numDistinct3("aacaacca", "ca"));
	}

	// 设母串的长度为 j，子串的长度为 i，我们要求的就是长度为 i 的字串在长度为 j 的母串中出现的次数，设为 t[i][j]。
	// 若母串的最后一个字符与子串的最后一个字符不同，则长度为 i 的子串在长度为 j 的母串中出现的次数就是母串的前 j - 1
	// 个字符中子串出现的次数，即 t[i][j] = t[i][j - 1]
	// 若母串的最后一个字符与子串的最后一个字符相同，那么除了前 j - 1 个字符出现字串的次数外，还要加上子串的前 i - 1 个字符在母串的前 j
	// - 1 个字符中出现的次数，
	// 即 t[i][j] = t[i][j - 1] + t[i - 1][j - 1]。
	public int numDistinct(String S, String T) {
		if (S.length() < T.length())
			return 0;

		int sLen = S.length();
		int tLen = T.length();
		int[][] ways = new int[2][sLen];

		// first row, first character of T
		if (S.charAt(0) == T.charAt(0))
			ways[0][0] = 1;
		for (int i = 1; i < sLen; i++) {
			ways[0][i] = ways[0][i - 1];
			if (S.charAt(i) == T.charAt(0))
				ways[0][i] += 1;
		}

		// remaining triangle
		int prev = 0, curr = 1;
		for (int it = 1; it < tLen; it++) {
			curr = it & 1;
			// element on the diagonal
			ways[curr][it] = (S.charAt(it) == T.charAt(it) ? ways[prev][it - 1]: 0);

			// elements below the diagonal
			for (int is = it + 1; is < sLen; is++) {
				ways[curr][is] = ways[curr][is - 1];
				if (S.charAt(is) == T.charAt(it))
					ways[curr][is] += ways[prev][is - 1];
			}
			prev = curr;
		}
		return ways[prev][sLen - 1];
	}

	
	//@10-08, 在有思路的情況下> 1h
	public int numDistinct3(String S, String T) {
        if(S == null || T == null)
            return 0;
        int n1 = T.length();
        int n2 = S.length();
        if(n1 > n2)
            return 0;
        
        int[][] t = new int[2][n2];
        t[0][0] = T.charAt(0) == S.charAt(0) ? 1 :0;
        //first row
        for(int i = 1; i < n2; i++)
            t[0][i] = t[0][i - 1] + (S.charAt(i) == T.charAt(0) ? 1 : 0);
           
        int curr = 1;
        int prev = 0;
        for(int i = 1; i < n1; i++){
            curr = 1 - prev ;
            t[curr][i] = (S.charAt(i) == T.charAt(i)) ? t[prev][i - 1]  : 0;
            for(int j = i + 1; j < n2; j++){
                t[curr][j] = t[prev][j - 1];
                if(T.charAt(i) == S.charAt(j))
                    t[curr][j] += t[prev][j - 1];
            }
            prev = curr;
        }
        return t[prev][n2 - 1];
    }

	// intuition method, > 3h, not completely correct
	public int numDistinct_v0(String S, String T) {
		if (T == null || T == "")
			return 1;
		if (S.length() < T.length())
			return 0;
		if (S.equals(T))
			return 1;
		if (S.length() == T.length() && !S.equals(T))
			return 0;

		int fir = 0;
		int sec = S.length();
		int res = 0;
		int roundStart = 0;

		char c1 = '0';
		char c2 = '0';

		for (int i = 0; i < S.length();) {
			int sub = 1;
			for (int j = 0; j < T.length(); j++) {
				int cc = 0;
				c1 = T.charAt(j);
				int dup = 1;
				while (j + 1 < T.length() && c1 == T.charAt(j + 1)) {
					dup++;
					j++;
				}

				fir = S.substring(i, S.length()).indexOf(c1); // fir = sec;
				if (fir == -1) { // c does not exist in[fir..sec)
					i = S.length();
					break;
				}
				if (j == T.length() - 1)// reach the last char of T, a new round
										// should be start later
					roundStart = fir + 1;

				if (j + 1 < T.length()) { // case c1 is not the last char of T
					c2 = T.charAt(j + 1);
					sec = S.substring(fir, S.length()).indexOf(c2);
					if (sec == -1) {
						roundStart = S.length();
						break;
					}
				} else
					// c1 is the last char of T
					sec = S.length();

				// occurrance of char c in [fir..sec)
				int ptr = fir;
				while (ptr < sec) {
					if (c1 == S.charAt(ptr))
						cc++;
					ptr++;
				}
				if (dup == 1)
					sub *= cc;
				else {
					int up = 1;
					int down = 1;
					for (int k = 0; k < dup; k++) {
						up *= cc - k;
						down *= dup - k;
					}
					sub *= up / down;

				}
				i = sec;
			}
			i = roundStart;
			res += sub;
		}
		return res;
	}

}

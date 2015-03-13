package org.work.glow;

import java.math.BigInteger;
import java.util.Random;

import junit.framework.TestCase;

public class TestNextPalindrome extends TestCase {
	// test positive number
	private NextPalindrome palindrome = new NextPalindrome();

	public void testGetNextPalindromePostive() {
		System.out
				.println("-----------testGetNextPalindromePostive-----------");
		String[] numPos = { "1", "10", "120", "123" };
		for (String N : numPos) {
			System.out.println(N);
			try {
				String nextPalin = palindrome.getNextPalindrome(N).toString();
				assertEquals(true, isPalindromeNumber(nextPalin));
				assertEquals(true, isLarger(nextPalin, N));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// test negative number
	public void testGetNextPalindromeNegative() {
		System.out
				.println("-----------testGetNextPalindromeNegative-----------");
		String[] numNeg = { "-1", "-10", "-120", "-123" };
		for (String N : numNeg) {
			System.out.println(N);
			try {
				String nextPalin = palindrome.getNextPalindrome(N).toString();
				assertEquals(true, isPalindromeNumber(nextPalin.substring(1)));
				assertEquals(true, isLarger(nextPalin, N));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// test edge case, 0, 9, -11...
	public void testGetNextPalindromeSpecial() {
		System.out
				.println("-----------testGetNextPalindromeSpecial-----------");
		String[] numSpecialCase = { "0", "9", "999", "-11", "-1001" };
		for (String N : numSpecialCase) {
			System.out.println(N);
			try {
				String nextPalin = palindrome.getNextPalindrome(N).toString();
				if (nextPalin.charAt(0) != '-')
					assertEquals(true, isPalindromeNumber(nextPalin));
				else
					assertEquals(true,
							isPalindromeNumber(nextPalin.substring(1)));
				assertEquals(true, isLarger(nextPalin, N));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// test large number with 1000 digits
	public void testGetNextPalindromeLarge() {
		System.out.println("-----------testGetNextPalindromeLarge-----------");
		Random rand = new Random();
		StringBuilder largeNumP = new StringBuilder();
		StringBuilder largeNumN = new StringBuilder();
		largeNumP.append('9');
		largeNumN.append("-1");
		for (int i = 1; i < 1000; i++) {
			largeNumP.append(rand.nextInt(10));
			largeNumN.append(rand.nextInt(10));
		}

		String[] numLarge = { largeNumN.toString(), largeNumP.toString() };
		for (String N : numLarge) {
			System.out.println(N);
			try {
				String nextPalin = palindrome.getNextPalindrome(N).toString();
				if (nextPalin.charAt(0) != '-')
					assertEquals(true, isPalindromeNumber(nextPalin));
				else
					assertEquals(true,
							isPalindromeNumber(nextPalin.substring(1)));
				assertEquals(true, isLarger(nextPalin, N));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// test illegal input
	public void testGetNextPalindromeException() {
		String[] illegal = { null, "" };
		for (String N : illegal) {
			System.out.println(N);
			try {
				palindrome.getNextPalindrome(null).toString();
			} catch (Exception e) {
				assertEquals("Illegal input: null or empty", e.getMessage());
			}
		}
	}

	public boolean isPalindromeNumber(String absNStr) {
		if (absNStr == null)
			return false;
		int i = 0, j = absNStr.length() - 1;
		while (i < j) {
			if (absNStr.charAt(i++) != absNStr.charAt(j--))
				return false;
		}
		return true;
	}

	public boolean isLarger(String strP, String strN) {
		if (strN == null || strP == null || strN.length() == 0
				|| strP.length() == 0)
			return false;
		BigInteger N = new BigInteger(strN);
		BigInteger P = new BigInteger(strP);

		return N.compareTo(P) == -1;
	}
}

package org.work.beautyofp;

public class E31_RotateStringContains {

	public static void main(String[] args) {
		E31_RotateStringContains obj = new E31_RotateStringContains();
		System.out.println(obj.doesContain("BDAC", "ACBD"));
	}

	private boolean doesContain(String s1, String s2) {
		int start = 0;
		while (true) {
			start = s1.indexOf(s2.charAt(0), start);
			if (start == -1)
				return false;
			int i = start, j = 1;
			while(j < s2.length()){
				i = (i >= (s1.length() - 1)) ? 0 : (i + 1); //check if need to rotate
				if(s1.charAt(i) != s2.charAt(j))
					break;
				j++;
			}
			if(j == s2.length())
				return true;
		}

	}
}

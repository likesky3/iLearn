package org.work.others;

public class PrintABC {

	public static void main(String[] args) {
		
		int N = 5;
		StringBuilder str = new StringBuilder();
//		final char left
		for(int i = 0; i < N; i++)
			str.append('(');
		for(int L = 1; L <= N; L++){
			for(int i = 0; i <= N - L; i++){//i : start index
				str.delete(0, str.length());
				int j = i;
				for(; j < i + L - 1; j++) //append continuous L - 1 chars
					str.append((char)('a' + j));
				
				for(; j < N; j++){
					str.append((char)('a' + j));
					System.out.print(str.toString());
					str.deleteCharAt(str.length() - 1);
					System.out.print(",");
				}
				System.out.println();
			}
		}
	}

}

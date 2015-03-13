package org.work.spotoffer;

public class E028_Permutation {

	public static void main(String[] args) {
		permutation2(new String("abcd").toCharArray(), 0);
	}
	
	public static void permutation(StringBuilder str, int begin){
		if(begin == str.length()){
			System.out.println(str.toString());
			return;
		}

		String a = str.substring(begin, begin + 1);
		for(int i = begin; i < str.length(); i++){
			String b = str.substring(i, i + 1);
			str.replace(begin, begin + 1, b);
			str.replace(i, i + 1, a);
			
			permutation(str, begin + 1);
			
			str.replace(begin, begin + 1, a);
			str.replace(i, i + 1, b);					
		}
	}
	
	public static void permutation2(char[] str, int begin){
		if(begin == str.length - 1){
			for(int i = 0; i < str.length; i++)
				System.out.print(str[i]);
			System.out.println();
			return;
		}

		char a = str[begin];
		for(int i = begin; i < str.length; i++){
			char b = str[i];
			str[begin] = b;
			str[i] = a;
			permutation2(str, begin + 1);
			
			str[begin] = a;
			str[i] = b;	
		}
	}

}

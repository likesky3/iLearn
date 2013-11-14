package org.work.spotoffer;


public class E042_ReverseSentence {
	public static void main(String[] args) {
		E042_ReverseSentence obj = new E042_ReverseSentence();
		String a = "b   welcom to   google!   a";
		a = obj.reverseSentence(a);
		System.out.println(a);
	}
	
	public void reverse(char[] str, int i, int j){
		if(str == null || str.length < 2)
			return;
		
		char tmp = ' ';
		while(i < j){
			tmp = str[j];
			str[j--] = str[i];
			str[i++] = tmp;
		}
	}
	
	public String reverseSentence(String str){
		if(str == null || str.length() < 2)
			return "";
		
		//reverse the whole sentence
		char[] str2 = str.toCharArray();
		int len = str2.length;
		reverse(str2, 0, len - 1);
		
		//reverse each word
		int i = 0, j = 1;//停下来时，i指向单词的首字母，j指向单词的尾字母
		while(i < len){
			if(str2[i] == ' '){
				i++;
				j++;
			}else if(j == len || str2[j] == ' '){//两个判断条件不可交换次序
				reverse(str2, i, j - 1);
				i = ++j;
			}else
				j++;
		}
		return new String(str2);
	}

}

package org.work.spotoffer;


//具有可扩展性的代码，Java中用接口替代函数指针
public class E014_OddBeforeEven {

	public static void main(String[] args){
		E014_OddBeforeEven obj = new E014_OddBeforeEven();
		int[] num = {1, 4, 6, 7};
		obj.reorder(num, new OddChecker());
		for(int n : num)
			System.out.println(n);
		
	}
	public void reorder(int[] num, Checker checker){
		if(num == null || num.length < 2)
			return;
		
		int i = 0, j = num.length - 1;
		while(i < j){
			while(i < j && checker.check(num[i]))
				i++;
			while(i < j && !checker.check(num[j]))
				j--;
			if(i < j){
				int tmp = num[i];
				num[i] = num[j];
				num[j] = tmp;
			}
		}
	}
}

interface Checker{
	public boolean check(Object o);
}

class OddChecker implements Checker{
	public boolean check(Object o){
		return ((Integer)o & 1) == 1;
	}
}

class NegativeCheck implements Checker{
	public boolean check(Object o){
		return (Integer)o < 0;
	}
}

package org.work.others;

/*@baidu*/
public class ReverseIntByBit {

	public static void main(String[] args) {
		ReverseIntByBit obj = new ReverseIntByBit();
		if(obj.reverse(10000) == obj.reverse2(10000))
			System.out.println("yep");
		else
			System.out.println("oh, no");
	}
	//运算优先级，第三行00ff00ff误写
	public int reverse(int x){
		int tmp = x;
		tmp = ((tmp & 0xffff0000) >> 16) | ((tmp & 0x0000ffff) << 16);
		tmp = ((tmp & 0xff00ff00) >> 8) | ((tmp & 0x00ff00ff) << 8);
		tmp = ((tmp & 0xf0f0f0f0) >> 4) | ((tmp & 0x0f0f0f0f) << 4);
		tmp = ((tmp & 0xcccccccc) >> 2) | ((tmp & 0x33333333) << 2);
		tmp = ((tmp & 0xaaaaaaaa) >> 1) | ((tmp & 0x55555555) << 1);
		return tmp;
	}
	
	public int reverse2(int x){
		int res = 0;
		for(int i = 0; i < 32; i++){
			res = res << 1 | x & 1;
			x >>= 1;
		}
		return res;
	}

}

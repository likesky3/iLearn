package org.work.others;

public class GetCoordinate {

	public static void main(String[] args) {
		GetCoordinate obj = new GetCoordinate();
		obj.getCoordinate("123");
		obj.getCoordinate("222");
		obj.getCoordinate("010");
	}
	
	public int[] getCoordinate(String s){
		int[] res = {-1, -1};
		//validate input
		for(char c : s.toCharArray()){
			if(c != '0' && c != '1' && c != '2' && c != '3'){
				System.out.println("illegal input");
				return res;
			}
		}
		
		res[0] = 0;
		res[1] = 0;
		int n = s.length();
		for(int i = 0; i < s.length(); i++){
			int j = n - 1 - i;
			int curr = s.charAt(i) - '0';
			switch(curr){
			case 1:
				res[0] += Math.pow(2, j); break;
			case 2:
				res[1] += Math.pow(2, j); break;
			case 3:
				res[0] += Math.pow(2, j);
				res[1] += Math.pow(2, j);
				break;
			default:
				break;
			}
		}
		System.out.println(res[0] + " " + res[1]);
		return res;
	}

}

package org.work.basic;

public class StringDemo {

	public static void main(String[] args) {
		int LOOP_NUM = 1000000;
		long start, end;
		// way1: "str1" + "str2"
		/*long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_NUM; i++) {
			String a = "Today is" + " a	" + "beautiful day.";
		}
		long end = System.currentTimeMillis();
		System.out.println("way 1: " + (end - start));

		// way 2, String 对象连接
		start = System.currentTimeMillis();
		String a2 = "Today is";
		for (int i = 0; i < LOOP_NUM; i++) {
			String c = "a";
			a2  +=  c;
		}
		end = System.currentTimeMillis();
		System.out.println("way 2: " + (end - start));*/

		// way 3, StringBuffer
		start = System.currentTimeMillis();
		StringBuffer a3 = new StringBuffer("Today is");
		for (int i = 0; i < LOOP_NUM; i++) {
			a3.append("a");
		}
		end = System.currentTimeMillis();
		System.out.println("way 3: " + (end - start));

		// way 4, StringBuilder
		start = System.currentTimeMillis();
		StringBuilder a4 = new StringBuilder("Today is");
		for (int i = 0; i < LOOP_NUM; i++) {
			a4.append("a");
		}
		end = System.currentTimeMillis();
		System.out.println("way 4: " + (end - start));

		// way 5, StringBuffer,指定容量
		start = System.currentTimeMillis();
		StringBuffer a5 = new StringBuffer(LOOP_NUM + 10);
		a5.append("Today is");
		for (int i = 0; i < LOOP_NUM; i++) {
			a5.append("a");
		}
		end = System.currentTimeMillis();
		System.out.println("way 5: " + (end - start));
	}

}

//way4 > way5 > way3 >> way2
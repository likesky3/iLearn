package org.work.sogou.searchhub;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchHubMainTest {
	public static void main(String[] args) {
		SearchHubMainTest obj = new SearchHubMainTest();
		obj.test5();
	}
	
	public void test5() {
		try {
			String input = "123456zhoulinlin世界";
			String output = URLEncoder.encode(input);
			System.out.println(output);
			System.out.println(URLDecoder.decode("%E5%88%9A%E5%88%9A%E8%A3%85%E4%BF%AE%E5%A5%BD%E4%BD%8F"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//test if System.arraycopy is shallow copy
	public void test4() {
		ArrayList<DocTermInfo> origin = new ArrayList<>();
		DocTermInfo docInfo = new DocTermInfo();
		docInfo.cc = "origin";
		origin.add(docInfo);
		
		ArrayList<DocTermInfo> copy = new ArrayList<>();
		copy.addAll(origin);
		copy.get(0).cc = "copy";
		System.out.println("origin: " + origin.get(0).cc);
		System.out.println("copy: " + copy.get(0).cc);
	}
	
	public void test3() {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				long start = System.currentTimeMillis();
				DocTermInfo source = new DocTermInfo();
				String a;
				for (TermInfo term: source.title_terms) {
//					System.err.println(term.term);
//					System.out.println(term.term);
//					System.out.println(term.term);
					a = term.term;
					a = term.term;
					a = term.term;
				}
				System.out.println("ugly:" + (System.currentTimeMillis() - start));
			}});
		thread1.setName("ugly thread");
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				long start = System.currentTimeMillis();
				DocTermInfo source = new DocTermInfo();
				String a;
				for (TermInfo term: source.title_terms) {
					String termStr = term.term;
//					System.out.println(termStr);
					a = termStr;
					a = termStr;
					a = termStr;
				}
				System.out.println("pretty:" + (System.currentTimeMillis() - start));
			}});
		thread2.setName("pretty thread");
		thread2.start();
		
	}
	
	public void test2() {
		String two = "25:50";
		String one = "25";
		System.out.println(two.split(":")[0] + " " + two.split(":")[1]);
		System.out.println(one.split(":")[0]);
	}
	
	public void test1() {
		System.out.println("start");
		// style1 of use
		Thread thread1 = new Thread(new Runnable() {
			
			NLPBaseInfo nlpinfo = new NLPBaseInfo();
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				for (int ii = 0; ii < 10; ii++) {
					for (int i = 0; i < nlpinfo.getldaNum(); i++) {
						System.out.println(nlpinfo.getLdaIdArray()[i]);
						System.out.println(nlpinfo.getLdaScoreArray()[i]);
//						int id = nlpinfo.getLdaIdArray()[i];
//						double score = nlpinfo.getLdaScoreArray()[i]; 
					}
				}
				System.out.println("ugly:" + (System.currentTimeMillis() - start));
			}
		});
		thread1.setName("ugly thread");
//		thread1.start();
		
		// style2 of use
		Thread thread2 = new Thread(new Runnable() {
			
			NLPBaseInfo nlpinfo = new NLPBaseInfo();
			int ldaNum = nlpinfo.getldaNum();
			int[] ldaId = nlpinfo.getLdaIdArray();
			double[] ldaScore = nlpinfo.getLdaScoreArray();
			
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				for (int ii = 0; ii < 10; ii++)  {
//				while(true) {
					for (int i = 0; i < ldaNum; i++) {
						System.out.println(ldaId[i]);
						System.out.println(ldaScore[i]);
//						int id = ldaId[i];
//						double score = ldaScore[i]; 
					}
				}
				System.out.println("pretty:" + (System.currentTimeMillis() - start));
			}
		});
		thread2.setName("pretty thread");
		thread2.start();
		
		// style3 of use
				Thread thread3 = new Thread(new Runnable() {
					
					NLPBaseInfo nlpinfo = new NLPBaseInfo();
					int ldaNum = nlpinfo.getldaNum();
					int[] ldaId = nlpinfo.getLdaIdArray();
					double[] ldaScore = nlpinfo.getLdaScoreArray();
					
					@Override
					public void run() {
						long start = System.currentTimeMillis();
						int id;
						double score;
						for (int ii = 0; ii < 20000000; ii++)  {
//						while(true) {
							for (int i = 0; i < ldaNum; i++) {
//								System.out.println(ldaId[i]);
//								System.out.println(ldaScore[i]);
								id = ldaId[i];
								score = ldaScore[i]; 
							}
						}
//						System.out.println("pretty2:" + (System.currentTimeMillis() - start));
					}
				});
				thread3.setName("pretty thread2");
				thread3.start();
	}
}

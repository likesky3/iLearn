package org.work.sogou.searchhub;

import java.util.Random;

public class NLPBaseInfo {
	private static final int LDA_LENGTH = 300;
	int[] ldaIdArray = new int[LDA_LENGTH];
	double[] ldaScoreArray = new double[LDA_LENGTH];
	public NLPBaseInfo() {
		Random rand = new Random();
		for (int i = 0; i < LDA_LENGTH; i++) {
			ldaIdArray[i] = rand.nextInt();
			ldaScoreArray[i] = rand.nextDouble();
		}
	}
	public int[] getLdaIdArray() {
		return ldaIdArray;
	}
	public void setLdaIdArray(int[] ldaIdArray) {
		this.ldaIdArray = ldaIdArray;
	}
	public double[] getLdaScoreArray() {
		return ldaScoreArray;
	}
	public void setLdaScoreArray(double[] ldaScoreArray) {
		this.ldaScoreArray = ldaScoreArray;
	}
	
	public int getldaNum() {
		return LDA_LENGTH;
	}
	
}

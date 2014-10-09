package org.work.sogou.searchhub;

import java.util.LinkedList;
import java.util.List;


public class DocTermInfo {
	public List<TermInfo> title_terms = new LinkedList<TermInfo>();
	public DocTermInfo() {
		for (int i = 0; i < 300000; i++) {
			TermInfo term = new TermInfo();
			term.term = "123456.789101112";
			title_terms.add(term);
		}
	}
	
	public String cc;
}

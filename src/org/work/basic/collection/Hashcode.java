package org.work.basic.collection;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

class base{
	int d;
	base(int arg){
		d = arg;
	}
}

public class Hashcode extends base {
	public static void main(String[] args) {
		ArrayList<String> c = new ArrayList<String>();
		c.add("world");
		Hashcode obj1 = new Hashcode(1, "hi", c);
		Hashcode obj2 = new Hashcode(1, "hi", c);
		obj1.hashCode();
		obj2.hashCode();
		
	}
	
	public Hashcode(int arga, String argb, ArrayList<String> argc){
		super(arga);
		a = arga;
		b = argb;
		c = argc;
	}
	
	public int hashCode(){
		int res1 = HashCodeBuilder.reflectionHashCode(this, true);
		int res2 = HashCodeBuilder.reflectionHashCode(this, false);
		
		HashCodeBuilder obj = new HashCodeBuilder();
		obj.append(a);
		obj.append(b);
		obj.append(c);
		
		int res3 = obj.toHashCode();
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
		return res3;
	}
	
	public boolean equals(Object o){
		if(! (o instanceof Hashcode))
			return false;
		Hashcode hc = (Hashcode)o;
		return (hc.a == a && hc.b.equals(b) && hc.c.equals(c));
	}
	
	public String toString(){
		return a + " " + b + " " + c.toString();
	}
	
	private int a;
	private String b;
	private ArrayList<String> c;
	
}

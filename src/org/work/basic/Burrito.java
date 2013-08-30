package org.work.basic;

//Use with switch/Burrito.java
public class Burrito{
	Spiciness degree;
	public Burrito(Spiciness degree){this.degree = degree;}
	public void describe(){
		System.out.print("This burrito is ");
		switch(degree){
			//case Spiciness.NOT:  //compiler error
			case NOT: System.out.println("not spicy at all.");break; 
			case MILD:
			case MEDIUM: System.out.println("a little hot.");break;
			case HOT:
			case FLAMING: 
			default: System.out.println("maybe too hot.");
		}
	}
	public static void main(String[] args)
  {
	//Burrito plain = new Burrito(NOT); //compiler error
	Burrito plain = new Burrito(Spiciness.NOT);
	plain.describe();
	
	Burrito hot = new Burrito(Spiciness.HOT);
	System.out.println("how to you feel? " + hot);
  }
}

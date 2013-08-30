package org.work.basic;

//lazy man, thread safe, slow performance
public class Singleton
{
  private Singleton(){}
  private static Singleton instance;
  public static synchronized Singleton getInstance(){
	if(instance == null)
	    instance = new Singleton();
	return instance;
  }

}

//hungry man, easy, most popular
class Singleton2
{
  private Singleton2(){}
  private static Singleton2 instance = new Singleton2();
  public static Singleton2 getInstance(){
	return instance;
  }

}

//static inner class, lazy loading
class Singleton3{
  private Singleton3(){};
  
  private static class SingletonHolder{
	private static final Singleton3 INSTANCE = new Singleton3();
  }
  
  public static final Singleton3 getInstance(){
	return SingletonHolder.INSTANCE;
  }
}

//enum, 涉及到反序列化创建对象时用
enum Singleton4{
  INSTANCE;
  public void whateverMethod(){}
}

//双重校验锁，特殊场合
class Singleton5{
  private Singleton5(){};
  private volatile static Singleton5 singleton;
  public static Singleton5 getInstance(){
	if(singleton == null){
	    synchronized (Singleton5.class)
	    {
		if(singleton == null){
		    singleton = new Singleton5();
		}
	    }
	}
	return singleton;
  }
}


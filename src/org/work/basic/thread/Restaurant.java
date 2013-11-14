package org.work.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	Waitor waitor = new Waitor(this);
	Chef chef = new Chef(this);
	public Restaurant(){
		exec.execute(chef);
		exec.execute(waitor);
	}
	public static void main(String[] args) {
		new Restaurant();
	}

}

//define public resource
class Meal{
	private final int orderNum;
	public Meal(int orderNum){this.orderNum = orderNum;}
	public String toString(){return "Meal " + orderNum;}
}

//producer
class Chef implements Runnable{
	private Restaurant restaurant;
	private int orderNum;
	public Chef(Restaurant r){restaurant = r;}
	public void run(){
		try{
			while(! Thread.interrupted()){
				synchronized (this) {
					while(restaurant.meal != null)
						wait();//for the meal to be taken
				}
				if(++orderNum == 10){
					System.out.println("Out of food. Closing.");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up!");
				synchronized (restaurant.waitor) {
					restaurant.meal = new Meal(orderNum);
					restaurant.waitor.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch (InterruptedException e) {
			System.out.println("Chef interrupted.");
		}
	}
}

	class Waitor implements Runnable{
		private Restaurant restaurant;
		public Waitor(Restaurant r){restaurant = r;}
		public void run(){
			try{
				while(!Thread.interrupted()){
					synchronized (this) {
						while(restaurant.meal == null)
							wait();//wait a meal to be ready
					}
					System.out.println("Waitor got Meal " + restaurant.meal);
					synchronized (restaurant.chef) {
						restaurant.meal = null;
						restaurant.chef.notifyAll();
					}				
				}
			}catch (InterruptedException e) {
				System.out.println("Waitor interrupted");
			}
		}
	}

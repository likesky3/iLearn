package org.work.basic;

//use inheritance to construct a spaceship
//However, a Spaceship isn't really a type of SpaceShipController,
//even if you can tell a Spaceship to go forward().
//It's more accurate to say that a Spaceship contains a SpaceShipController
class SpaceShip extends SpaceShipControls{
	private String name;
	public SpaceShip(String name){this.name = name;}
	public String toString(){return name;}
	public static void main(String[] args){
		SpaceShip protector = new SpaceShip("NSEA Protector");
		protector.forward(100);
	}
}


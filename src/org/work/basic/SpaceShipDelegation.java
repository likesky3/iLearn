package org.work.basic;

//use delegation to construct a spaceship
//we will have more control with delegation since we
//can choose to provide only a subset of the methods in member object.
class SpaceShipDelegation{
	private String name;
	private SpaceShipControls controls = new SpaceShipControls();
	public SpaceShipDelegation(String name){
		this.name = name;
	}
	
	//Delegation methods:
	
	public void up(int veloctiy){
		controls.up(veloctiy);
	}
	
	public void down(int velocity){
		controls.down(velocity);
	}
	
	public void forward(int velocity) {
		controls.forward(velocity);
	}
	
	public void back(int velocity){
		controls.back(velocity);
	}
	
	public void  left(int velocity) {
		controls.left(velocity);
	}
	
	public void right(int velocity){
		controls.right(velocity);
	}
	
	public static void main(String[] args){
		SpaceShipDelegation protector = new SpaceShipDelegation("NSEA protector");
		protector.forward(100);
	}
}

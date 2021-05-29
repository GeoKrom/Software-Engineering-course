package view;

public class Car {

	
	
	private int x;
	private int  y;
	private Car car=null;
	
	
	
	
	
	
	
	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Car() {
		
	}
	
	
	public Car(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Car(Car a) {
		
		car=new Car();
		car.setX(a.getX());
		car.setY(a.getY());
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}

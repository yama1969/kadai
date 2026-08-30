public class Tire{
	private double direction = 0;
	private int speed = 0;

	public void setDirection(double direction){
		if(direction > 45.0){
			direction = 45.0;
		}else if(direction < -45.0){
			direction = -45.0;
		}
		this.direction = direction;
	}

	public void changeSpeed(int d_speed){
		speed += d_speed;
		if(speed < 0){
			speed = 0;
		}
	}

	public double getDirection(){
		return direction;
	}

	public int getSpeed(){
		return speed;
	}
}

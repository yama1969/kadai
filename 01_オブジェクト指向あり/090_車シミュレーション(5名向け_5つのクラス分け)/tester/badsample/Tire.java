public class Tire{
	private int direction = 0;
	private int speed = 0;

	public void setDirection(int direction){
		if(direction > 45){
			direction = 45;
		}else if(direction < -45){
			direction = -45;
		}
		this.direction = direction;
	}

	public void changeSpeed(int d_speed){
		speed += d_speed;
		if(speed < 0){
			speed = 0;
		}
	}

	public int getDirection(){
		return direction;
	}

	public int getSpeed(){
		return speed;
	}
}

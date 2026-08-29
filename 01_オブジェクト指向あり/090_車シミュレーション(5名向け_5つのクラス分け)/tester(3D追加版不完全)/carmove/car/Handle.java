package carmove.car;

public class Handle{
	private double direction = 0;

	public void moveDirection(double d_direction, Tire t){
		direction += d_direction;
		if(direction > 90.0){
			direction = 90.0;
		}else if(direction < -90.0){
			direction = -90.0;
		}
		
		setTireDirection(t);
	}

	public double getDirection(){
		return direction;
	}

	public void resetDirection(Tire t){
		direction = 0.0;
		setTireDirection(t);
	}

	private void setTireDirection(Tire t){
		t.setDirection(direction / 2.0);
	}
}

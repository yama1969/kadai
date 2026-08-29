package carmove.car;

public class Handle{
	private int direction = 0;

	public void moveDirection(int d_direction, Tire t){
		direction += d_direction;
		if(direction > 90){
			direction = 90;
		}else if(direction < -90){
			direction = -90;
		}
		
		setTireDirection(t);
	}

	public int getDirection(){
		return direction;
	}

	public void resetDirection(Tire t){
		direction = 0;
		setTireDirection(t);
	}

	private void setTireDirection(Tire t){
		t.setDirection((int)(direction / 2));
	}
}

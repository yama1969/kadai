public class Car{
	private double x = 0.0;
	private double y = 0.0;
	private double direction = 0;
	private String name = null;

	private Handle handle = new Handle();
	private Tire tire = new Tire();
	private Accel accel = new Accel();
	private Brake brake = new Brake();

	public Car(){
	}

	public Car(String name){
		this.name = name;
	}

	public void move(){
		brake.renewSpeed(tire);
		accel.renewSpeed(tire);
		
		move_car();
	}

	public void move(int brake_d_push, int accel_d_push, double handle_d_rotate){
		handle.moveDirection(handle_d_rotate, tire);
		brake.movePush(brake_d_push, tire);
		accel.movePush(accel_d_push, tire);
		
		move_car();
	}

	public void setName(String name){
		this.name = name;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public double getDirection(){
		return direction;
	}

	private void move_car(){
		int speed = tire.getSpeed();
		double direction = tire.getDirection();
		
//		this.direction += direction;
                //もう少し本物っぽく動かす式（前輪が転がった分だけ車体が回転するので、速度0のときの回転も止まる。ホイールベース18mとして簡易計算）
		this.direction += ((double)speed * Math.sin(direction * Math.PI / 180.0) * 3.0 / Math.PI);
		this.direction %= 360.0;
		if(this.direction > 180.0){
			this.direction -= 360.0;
		}else if(this.direction <= -180.0){
			this.direction += 360.0;
		}
		
		x = x + (double)speed * Math.sin(this.direction * Math.PI / 180.0) / 3.6;
		y = y + (double)speed * Math.cos(this.direction * Math.PI / 180.0) / 3.6;
	}
}

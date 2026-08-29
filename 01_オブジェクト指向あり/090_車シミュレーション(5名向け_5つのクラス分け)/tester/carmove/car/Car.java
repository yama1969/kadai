package carmove.car;

public class Car{
	private double x = 0.0;
	private double y = 0.0;
	private int direction = 0;
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

	public void move(int brake_d_push, int accel_d_push, int handle_d_rotate){
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

	public int getDirection(){
		return direction;
	}

	private void move_car(){
		int speed = tire.getSpeed();
		int direction = tire.getDirection();
		
		this.direction += direction;
                //もう少し本物っぽく動かす式（前輪が転がった分だけ車体が回転するので、速度0のときの回転も止まる。ホイールベース18mとして簡易計算）
//		this.direction += (int)((double)speed * Math.sin((double)direction * Math.PI / 180.0) * 3.0 / Math.PI);
		this.direction %= 360;
		if(this.direction>179){
			this.direction -= 360;
		}else if(this.direction<-180){
			this.direction += 360;
		}
		
		x = x + (double)speed * Math.sin((double)this.direction * Math.PI / 180.0) / 3.6;
		y = y + (double)speed * Math.cos((double)this.direction * Math.PI / 180.0) / 3.6;
	}
}

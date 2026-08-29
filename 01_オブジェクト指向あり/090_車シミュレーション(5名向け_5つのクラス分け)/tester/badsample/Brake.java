public class Brake{
	private int push = 0;

	public void movePush(int d_push, Tire t){
		push += d_push;
		if(push < 0){
			push = 0;
		}else if(push > 5){             //仕様通りなら15
			push = 5;                   //仕様通りなら15
		}
		
		renewSpeed(t);
	}

	public void resetPush(Tire t){
		push = 0;
		renewSpeed(t);
	}

	public void fullPush(Tire t){
		push = 15;
		renewSpeed(t);
	}

	public int getPush(){
		return push;
	}

	public void renewSpeed(Tire t){
		t.changeSpeed( -push );
	}
}

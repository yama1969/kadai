package carmove.testmove;

import carmove.gui.GUITester;
import carmove.gui.Controller;

public class TestMoveControll extends TestMove{
	private Controller ctrl;
	private int brake;
	private int accel;
	private int handle;
	private boolean cont;
	private int ctrl_x;
	
	public TestMoveControll(GUITester gt, int x){
		super(gt);
		
		ctrl_x = x;
	}
	
	public void run(){
		brake = 0;
		accel = 0;
		handle = 0;
		cont = true;
		ctrl = new Controller(this, ctrl_x);
		
		int b = 0;
		int a = 0;
		int h = 0;
		while(cont){
			int sb = brake;
			int sa = accel;
			int sh = handle;
			gt.move(sb - b, sa - a, sh - h);
			
			b = sb;
			a = sa;
			h = sh;
		}
		gt.endMove();
	}
	
	public void end(){
		cont = false;
		ctrl = null;
	}
	
	public void move(int brakeaccel, int handle){
		if(brakeaccel < 0){
			//ブレーキ
			brake = - brakeaccel;
			accel = 0;
		}else{
			//アクセル
			accel = brakeaccel;
			brake = 0;
		}
		//ハンドル
		this.handle = handle;
	}
}

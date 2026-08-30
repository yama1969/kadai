package carmove.testmove;

import carmove.gui.GUITester;

public class TestMoveBrake extends TestMove{

	public TestMoveBrake(GUITester gt){
		super(gt);
	}

	public void run(){
		int i;
		
		//加速
		gt.move(0, 15, 0.0);
		for(i=0; i<11; i++){
			gt.move(0, 0, 0.0);
		}
		//加速右折Ｕターン
		gt.move(0, 0, +40.0);
		for(i=0; i<8; i++){
			gt.move(0, 0, 0.0);
		}
		//オーバー減速
		gt.move(16, -15, -40.0);
		for(i=0; i<10; i++){
			gt.move(0, 0, 0.0);
		}
		
		gt.endMove();
	}
}

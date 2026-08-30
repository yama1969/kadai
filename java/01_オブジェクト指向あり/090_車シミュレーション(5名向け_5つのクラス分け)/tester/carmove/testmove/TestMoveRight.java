package carmove.testmove;

import carmove.gui.GUITester;

public class TestMoveRight extends TestMove{

	public TestMoveRight(GUITester gt){
		super(gt);
	}

	public void run(){
		int i;
		
		//加速
		gt.move(0,15,0);
		for(i=0; i<10; i++){
			gt.move(0,0,0);
		}
		//オーバー右折
		gt.move(0,-15,+91);
		for(i=0; i<10; i++){
			gt.move(0,0,0);
		}
		
		gt.endMove();
	}
}

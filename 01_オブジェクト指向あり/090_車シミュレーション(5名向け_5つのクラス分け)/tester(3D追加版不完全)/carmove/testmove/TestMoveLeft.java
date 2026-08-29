package carmove.testmove;

import carmove.gui.GUITester;

public class TestMoveLeft extends TestMove{

	public TestMoveLeft(GUITester gt){
		super(gt);
	}

	public void run(){
		int i;
		
		//加速
		gt.move(0, 15, 0.0);
		for(i=0; i<10; i++){
			gt.move(0, 0, 0.0);
		}
		//オーバー左折
		gt.move(0, -15, -91.0);
		for(i=0; i<10; i++){
			gt.move(0, 0, 0.0);
		}
		
		gt.endMove();
	}
}

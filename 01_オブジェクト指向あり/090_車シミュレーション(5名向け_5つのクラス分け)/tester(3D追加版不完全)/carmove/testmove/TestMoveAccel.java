package carmove.testmove;

import carmove.gui.GUITester;

public class TestMoveAccel extends TestMove{

	public TestMoveAccel(GUITester gt){
		super(gt);
	}

	public void run(){
		int i;
		
		//オーバー加速
		gt.move(0, 16, 0.0);
		for(i=0; i<10; i++){
			gt.move(0, 0, 0.0);
		}
		//オーバー加速のまま右折
		gt.move(0, 0, +40.0);
		for(i=0; i<8; i++){
			gt.move(0, 0, 0.0);
		}
		
		gt.endMove();
	}
}

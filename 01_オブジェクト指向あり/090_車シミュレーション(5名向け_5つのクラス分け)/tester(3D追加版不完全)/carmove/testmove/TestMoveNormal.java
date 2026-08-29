package carmove.testmove;

import carmove.gui.GUITester;

public class TestMoveNormal extends TestMove{
	public TestMoveNormal(GUITester gt){
		super(gt);
	}
	
	public void run(){
		int i;
		
		//加速
		gt.move(0, 15, 0.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//慣性走行
		gt.move(0, -15, 0.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//右折
		gt.move(0, 0, 40.0);
		for(i=0; i<9; i++){
			gt.move(0, 0, 0.0);
		}
		//慣性走行
		gt.move(0, 0, -40.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//加速左折
		gt.move(0, 15, -40.0);
		for(i=0; i<13; i++){
			gt.move(0, 0, 0.0);
		}
		//慣性走行
		gt.move(0, -15, +40.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//停止
		gt.move(15, 0, 0.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//加速左折
		gt.move(-15, 15, -40.0);
		for(i=0; i<4; i++){
			gt.move(0, 0, 0.0);
		}
		//慣性左折
		gt.move(0, -15, 0.0);
		for(i=0; i<4; i++){
			gt.move(0, 0, 0.0);
		}
		//慣性右折
		gt.move(0, 0, +50.0);
		for(i=0; i<10; i++){
			gt.move(0, 0, 0.0);
		}
		//停止
		gt.move(3, 0, -10.0);
		for(i=0; i<5; i++){
			gt.move(0, 0, 0.0);
		}
		//ブレーキ開放
		gt.move(-3, 0, 0.0);
		
		gt.endMove();
	}
}

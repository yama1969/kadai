package carmove.testmove;

import carmove.gui.GUITester;

public abstract class TestMove implements Runnable{
	protected GUITester gt;
	
	public TestMove(GUITester gt){
		this.gt = gt;
	}
}

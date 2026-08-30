package carmove.testmove;

import carmove.gui.GUITester;
import carmove.gui.Controller;
import carmove.gui.gui3D.SceneryMap;

public class TestMoveControll3D extends TestMoveControll{
	private Controller ctrl;
	private int brake;
	private int accel;
	private double handle;
	private boolean cont;
	private int ctrl_x;
	private SceneryMap map;
	private int xmax;
	private int ymax;
	
	public TestMoveControll3D(GUITester gt, int x){
		super(gt, x);
		
		ctrl_x = x;
	}
	
	public void run(){
		brake = 0;
		accel = 0;
		handle = 0.0;
		cont = true;
		ctrl = new Controller(this, ctrl_x);
		map = new SceneryMap();
		map.setWidth(gt.getXMax());
		map.setHeight(gt.getYMax());
		
		
		int b = 0;
		int a = 0;
		double h = 0.0;
		while(cont){
			int sb = brake / 2;
			int sa = accel / 2;
			double sh = handle / 10.0;
//			gt.move(sb - b, sa - a, sh - h);
			gt.move3D(sb - b, sa - a, sh - h, map);
			
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
	
	public void move(int brakeaccel, double handle){
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

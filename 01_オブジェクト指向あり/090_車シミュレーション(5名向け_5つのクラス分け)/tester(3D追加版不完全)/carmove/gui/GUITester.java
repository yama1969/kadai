package carmove.gui;

import java.awt.Graphics;
import carmove.gui.gui3D.SceneryMap;

public interface GUITester{
	public void move(int b,int a,double h);
	public void move3D(int b,int a,double h, SceneryMap map);
	public void endMove();
	public int getXMax();
	public int getYMax();
}

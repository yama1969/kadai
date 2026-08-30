package mousedraw;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class MouseListen implements MouseListener, MouseMotionListener{
	int sx;
	int sy;
	MouseGUI mgui;
	
	public MouseListen(MouseGUI mgui){
		this.mgui = mgui;
	}
	
	public void mousePressed(MouseEvent e){
		sx = e.getX();
		sy = e.getY();
	}
	
	public void mouseReleased(MouseEvent e){
		drawLine(e.getX(), e.getY());
	}
	
	public void mouseDragged(MouseEvent e){
		drawLine(e.getX(), e.getY());
	}
	
	private void drawLine(int ex,int ey){
		mgui.setXY(sx, sy, ex, ey);
		sx = ex;
		sy = ey;
	}
	
	
	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
	}

	public void mouseExited(MouseEvent e){
	}

	public void mouseMoved(MouseEvent e){
	}
}

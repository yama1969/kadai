import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;

/*
 AWTのみで作成してみた。その方がかえってextendsとimplementの状況が分かりやすいように思える。
 マウスドラッグで線を描くことから始めて、マウスイベント処理を追加しながら少しずつ機能追加すればよい。
*/
public class Draw extends Frame implements WindowListener, MouseListener, MouseMotionListener{
	public static void main(String[] args){
		new Draw();
	}
	
	private Image off_img;
	private Graphics off_g;
	
	private int sx;	//描き開始点x座標
	private int sy;	//描き開始点y座標
	private int ex;	//描き終了点x座標
	private int ey;	//描き終了点y座標
	
	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	
	public Draw(){
		addWindowListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		off_img = createImage(WIDTH, HEIGHT);
		off_g = off_img.getGraphics();
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(off_img, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g)
	{
		paint(g);
	}
	
	//MouseListener implements
	public void mouseClicked(MouseEvent e){	//マウス・ボタンをクリック(押してから離す)したとき
		if(e.getButton() == MouseEvent.BUTTON2){
			off_g.clearRect(0, 0, WIDTH, HEIGHT);
			repaint();
		}
	}
	
	public void mouseEntered(MouseEvent e){	//マウスが入ったとき
	}
	
	public void mouseExited(MouseEvent e){	//マウスが出たとき
	}
	
	public void mousePressed(MouseEvent e){	//マウス・ボタンが押されたとき
		sx = e.getX();
		sy = e.getY();
		if(e.getButton() == MouseEvent.BUTTON3){
			off_g.setColor(Color.WHITE);
		}else{
			off_g.setColor(Color.BLACK);
		}
	}
	
	public void mouseReleased(MouseEvent e){	//マウス・ボタンが離されたとき
	}
	
	//MouseMotionListener implements
	public void mouseDragged(MouseEvent e){	//ドラッグしたとき
		ex = e.getX();
		ey = e.getY();
		off_g.drawLine(sx, sy, ex, ey);
		sx = ex;
		sy = ey;
		repaint();
	}
	
	public void mouseMoved(MouseEvent e){	//移動したとき
	}
	
	//WindowListener implements
	@Override
	public void windowActivated(WindowEvent e){	//アクティブになったとき
	}
	
	@Override
	public void windowClosed(WindowEvent e){	//処理の結果クローズされたとき
	}
	
	@Override
	public void windowClosing(WindowEvent e){	//ユーザが閉じる操作をしたとき
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e){	//アクティブでなくなったとき
	}
	
	@Override
	public void windowDeiconified(WindowEvent e){	//最小化から通常に戻ったとき
	}
	
	@Override
	public void windowIconified(WindowEvent e){	//最小化されたとき
	}
	
	@Override
	public void windowOpened(WindowEvent e){	//はじめて可視になったとき
	}
}

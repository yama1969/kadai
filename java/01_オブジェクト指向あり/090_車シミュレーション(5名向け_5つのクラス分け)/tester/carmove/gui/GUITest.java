package carmove.gui;

import carmove.testmove.TestMove;
import carmove.testmove.TestMoveAccel;
import carmove.testmove.TestMoveBrake;
import carmove.testmove.TestMoveControll;
import carmove.testmove.TestMoveLeft;
import carmove.testmove.TestMoveNormal;
import carmove.testmove.TestMoveRight;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class GUITest extends JFrame implements GUITester{

	final int XMAX = 550;
	final int YMAX = 500;

	final double CAR_X = 4.0;
	final double CAR_Y = 8.0;

	JButton	 btnStart;
	JPanel   pnlScreen;
	Graphics g;
	Image    offi;
	Graphics offg;

	JCheckBox    chkSleep;
	boolean sw_sleep;

	JRadioButton     rbnControll;
	JRadioButton     rbnNormal;
	JRadioButton     rbnRight;
	JRadioButton     rbnLeft;
	JRadioButton     rbnAccel;
	JRadioButton     rbnBrake;
	RadioButtonGroup rbnGroup;
	JPanel           pnlOption;
	int              tm_select;
	TestMove         tm;

	carmove.car.Car car_ans;
	carmove.gui.Car car;

	public GUITest(Car car){
		super("Car Simulation");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btnStart = new JButton("START");
		pnlScreen = new JPanel();
		
		chkSleep = new JCheckBox("0.5ïbSleep",true);
		sw_sleep = true;
		
		rbnGroup = new RadioButtonGroup();
		rbnGroup.add( rbnControll = new JRadioButton("ëÄçÏ",true) );
		rbnGroup.add( rbnNormal = new JRadioButton("ê≥èÌâ^ì]") );
		rbnGroup.add( rbnRight = new JRadioButton("âﬂâEê‹") );
		rbnGroup.add( rbnLeft = new JRadioButton("âﬂç∂ê‹") );
		rbnGroup.add( rbnAccel = new JRadioButton("âﬂâ¡ë¨") );
		rbnGroup.add( rbnBrake = new JRadioButton("âﬂå∏ë¨") );
		tm_select = 0;
		
		pnlOption = new JPanel();
		pnlOption.add(chkSleep, BorderLayout.NORTH);
		pnlOption.add(rbnControll, BorderLayout.CENTER);
		pnlOption.add(rbnNormal, BorderLayout.CENTER);
		pnlOption.add(rbnRight, BorderLayout.CENTER);
		pnlOption.add(rbnLeft, BorderLayout.CENTER);
		pnlOption.add(rbnAccel, BorderLayout.CENTER);
		pnlOption.add(rbnBrake, BorderLayout.CENTER);
		
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				tm_select = rbnGroup.getSelectedNo();
				rbnGroup.setAllEnabled(false);
				btnStart.setEnabled(false);
				
				offg.clearRect(0,0,2000,2000);
				g.clearRect(0,0,2000,2000);
				drawCar();
			}
		});
		
		chkSleep.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				sw_sleep = !sw_sleep;
			}
		});
		
		rbnControll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(0);
			}
		});
		
		rbnNormal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(1);
			}
		});
		
		rbnRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(2);
			}
		});
		
		rbnLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(3);
			}
		});
		
		rbnAccel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(4);
			}
		});
		
		rbnBrake.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				rbnGroup.selectButton(5);
			}
		});
		
		getContentPane().add(btnStart, BorderLayout.NORTH);
		getContentPane().add(pnlScreen, BorderLayout.CENTER);
		getContentPane().add(pnlOption, BorderLayout.SOUTH);
		
		setSize(XMAX,YMAX);
		setVisible(true);
		
		car_ans = new carmove.car.Car("ANSER_CAR");
		this.car = car;
		
		g = pnlScreen.getGraphics();
		offi = createImage(XMAX, YMAX);
		offg = offi.getGraphics();
	}

	public void move(int b,int a,int h){
		if(b==0 && a==0 && h==0){
			car_ans.move();
			car.move();
		}else{
			car_ans.move(b,a,h);
			car.move(b,a,h);
		}
		System.out.print("ç¿ïW(X,Y)=("+(int)car_ans.getX()+","+(int)car_ans.getY()+") å¸Ç´="+car_ans.getDirection());
		System.out.print("\t\t");
		System.out.print("ç¿ïW(X,Y)=("+(int)car.getX()+","+(int)car.getY()+") å¸Ç´="+car.getDirection());
		System.out.println();
		
		double x = car_ans.getX();
		double y = car_ans.getY();
		double d = (double)car_ans.getDirection() * Math.PI / 180.0;
		paintCar(x, y, d, Color.RED);
		
		x = car.getX();
		y = car.getY();
		d = (double)car.getDirection() * Math.PI / 180.0;
		paintCar(x, y, d, Color.BLUE);
		
		if(sw_sleep){
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void paintCar(double x, double y, double d, Color color){
		int[] sx = new int[3];
		int[] sy = new int[3];
		sx[0] = (int)( x - CAR_X * Math.cos(d) - CAR_Y * Math.sin(d) );
		sy[0] = (int)( y + CAR_X * Math.sin(d) - CAR_Y * Math.cos(d) );
		sx[1] = (int)( x + CAR_X * Math.cos(d) - CAR_Y * Math.sin(d) );
		sy[1] = (int)( y - CAR_X * Math.sin(d) - CAR_Y * Math.cos(d) );
		sx[2] = (int)( x + CAR_Y * Math.sin(d) );
		sy[2] = (int)( y + CAR_Y * Math.cos(d) );
		
		for(int i = 0; i < 3; i++){
			sx[i] = sx[i] + XMAX / 2;
			sy[i] = -sy[i] + YMAX / 2;
		}
		g.setColor(color);
		g.fillPolygon(sx,sy,3);
		offg.setColor(color);
		offg.fillPolygon(sx,sy,3);
	}

	public void endMove(){
		rbnGroup.setAllEnabled(true);
		btnStart.setEnabled(true);

		car_ans = new carmove.car.Car("ANSER_CAR");
		car.createCar();
	}

	public void paint(Graphics g){
		super.paint(g);
		this.g.drawImage(offi, 0, 0, this);
	}

	public void drawCar(){
		switch(tm_select){
			case 1:
				tm = new TestMoveNormal(this);
				break;
			case 2:
				tm = new TestMoveRight(this);
				break;
			case 3:
				tm = new TestMoveLeft(this);
				break;
			case 4:
				tm = new TestMoveAccel(this);
				break;
			case 5:
				tm = new TestMoveBrake(this);
				break;
			default:
				tm = new TestMoveControll(this, XMAX);
		}
		Thread thread = new Thread(tm);
		thread.start();
	}

/*
	public static void main(String[] args){
		GUITest gt = new GUITest();
	}
*/
}

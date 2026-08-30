package mousedraw;

import connect.connectGUI;
import connect.Controller;
import connect.ConnectException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MouseGUI extends JFrame implements connectGUI {

	private JTextField	txtHost;
	private JButton	btnConnect;
	private JPanel		pnlConnect;
	private JPanel		pnlScreen;
	
	private Controller	controll;
	private boolean	canDraw;
	
	public static void main(String[] args){
		new MouseGUI();
	}
	
	public MouseGUI(){
		super("MouseDraw");
		
		controll = new Controller(this);
		
		txtHost = new JTextField(18);
		btnConnect = new JButton("ê⁄ë±");
		pnlConnect = new JPanel();
		pnlConnect.add(txtHost, BorderLayout.CENTER);
		pnlConnect.add(btnConnect, BorderLayout.EAST);
		
		pnlScreen = new JPanel();
		canDraw = false;
		
		Container frame = getContentPane();
		frame.add(pnlConnect, BorderLayout.NORTH);
		frame.add(pnlScreen, BorderLayout.CENTER);
		
		btnConnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String host = txtHost.getText().trim();
				if(!host.equals("")){
					controll.sendConnect(host);
					changeConnectButton();
				}
			}
		});
		
		MouseListen ml = new MouseListen(this);
		pnlScreen.addMouseListener(ml);
		pnlScreen.addMouseMotionListener(ml);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				controll.end_conn();
				System.exit(0);
			}
		});
		
		setSize(300,300);
		setVisible(true);
	}
	
	public void receiveMessage(String strMess){
		int 	start_pos = 0;
		int 	end_pos = 0;
		int 	cnt = 0;
		int[]	xy = {0, 0, 0, 0};
		
		for(cnt = 0; cnt < 4; cnt++){
			end_pos = strMess.indexOf((int)',', start_pos);
			try{
				xy[cnt] = Integer.parseInt(strMess.substring(start_pos, end_pos));
			}catch(IndexOutOfBoundsException ie){
				break;
			}
			start_pos = end_pos + 1;
		}
		if(canDraw){
			Graphics g = pnlScreen.getGraphics();
			g.drawLine(xy[0],xy[1],xy[2],xy[3]);
		}
	}
	
	public void receiveConnect(String strHost){
		txtHost.setText("This host is the server.");
		changeConnectButton();
	}
	
	public void setXY(int x1, int y1, int x2, int y2){
		if(canDraw){
			Graphics g = pnlScreen.getGraphics();
			g.drawLine(x1,y1,x2,y2);
		
			String strData = Integer.toString(x1) + ","
							+ Integer.toString(y1) + ","
							+ Integer.toString(x2) + ","
							+ Integer.toString(y2) + ",";
			try{
				controll.sendMessage(strData);
			}catch(ConnectException ce){
				System.out.println(ce.getMessage());
			}
		}
	}
	
	private void changeConnectButton(){
		canDraw = true;
		txtHost.setEnabled(false);
		btnConnect.setEnabled(false);
	}
}

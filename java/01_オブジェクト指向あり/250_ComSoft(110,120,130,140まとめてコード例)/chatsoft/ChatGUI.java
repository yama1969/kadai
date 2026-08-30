package chatsoft;

import connect.connectGUI;
import connect.Controller;
import connect.ConnectException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatGUI extends JFrame implements connectGUI {
	
	private JTextField		txtHost;
	private JButton		btnConnect;
	private JPanel			pnlConnect;
	private JTextArea		tarMessages;
	private JScrollPane	sclMessages;
	private JTextField		txtMessage;
	private JButton		btnSend;
	private JPanel			pnlSend;
	
	private Controller		controll;
	
	public static void main(String[] args){
		new ChatGUI();
	}
	
	public ChatGUI(){
		super("ChatSoft");
		
		controll = new Controller(this);
		
		txtHost = new JTextField(18);
		btnConnect = new JButton("接続");
		pnlConnect = new JPanel();
		pnlConnect.add(txtHost, BorderLayout.CENTER);
		pnlConnect.add(btnConnect, BorderLayout.EAST);
		
		tarMessages = new JTextArea();
		tarMessages.setEditable(false);
		sclMessages = new JScrollPane(tarMessages);
		
		txtMessage = new JTextField(18);
		btnSend = new JButton("送信");
		pnlSend = new JPanel();
		pnlSend.add(txtMessage, BorderLayout.CENTER);
		pnlSend.add(btnSend, BorderLayout.EAST);
		
		Container frame = getContentPane();
		frame.add(pnlConnect, BorderLayout.NORTH);
		frame.add(sclMessages, BorderLayout.CENTER);
		frame.add(pnlSend, BorderLayout.SOUTH);
		
		btnConnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String host = txtHost.getText().trim();
				if(!host.equals("")){
					try{
						controll.sendConnect(host);
						changeConnectButton();
						tarMessages.append("---"+host+"に接続しました。\n");
					}catch(ConnectException ce){
						tarMessages.append("---"+ce.getMessage()+"\n");
					}
				}
			}
		});
		
		btnSend.addActionListener(new ActionListener(){
			public void  actionPerformed(ActionEvent ae){
				String str = txtMessage.getText();
				tarMessages.append(str+"\n");
				try{
					controll.sendMessage(str);
				}catch(ConnectException ce){
					tarMessages.append("---"+ce.getMessage()+"\n");
				}
				txtMessage.setText("");
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				try{
					controll.sendMessage("切断します。ごきげんよう。");
				}catch(ConnectException ce){
					tarMessages.append("---"+ce.getMessage()+"\n");
				}
				controll.end_conn();
				System.exit(0);
			}
		});
		
		setSize(300,300);
		setVisible(true);
	}
	
	public void receiveMessage(String strMess){
		tarMessages.append(">>"+strMess+"\n");
	}
	
	public void receiveConnect(String strHost){
		tarMessages.append("---"+strHost+"が接続しました。\n");
		txtHost.setText("This host is the server.");
		changeConnectButton();
	}
	
	private void changeConnectButton(){
		txtHost.setEnabled(false);
		btnConnect.setEnabled(false);
	}
}

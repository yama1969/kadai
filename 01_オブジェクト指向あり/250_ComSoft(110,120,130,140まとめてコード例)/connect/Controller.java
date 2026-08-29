package connect;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Controller {

	private connectGUI	gui;

	private WaitConnect	waitconn;
	private int port_no = 65535;

	private Socket			socket;
	private BufferedWriter	bufWriter;
	private Receiver		receiver;

	private boolean 	blReceiveOk;
	
	public Controller(connectGUI gui){
		initController(gui, port_no, true);
	}
	
	public Controller(connectGUI gui, int port){
		this.port_no = port;
		initController(gui, port_no, true);
	}
	
	public Controller(connectGUI gui, boolean waitOn){
		initController(gui, port_no, waitOn);
	}
	
	public Controller(connectGUI gui, int port, boolean waitOn){
		this.port_no = port;
		initController(gui, port_no, waitOn);
	}
	
	private void initController(connectGUI gui, int port, boolean waitOn){
		this.gui = gui;
		if(waitOn){
			waitconn = new WaitConnect(this, port_no);
		}
		setReceiveOk(false);
	}
	
	public void sendConnect(String strHost){
		if(socket != null){
			throw new ConnectException("接続済みです。");
		}
		try{
			socket = new Socket(strHost, port_no);
		}catch(UnknownHostException uhe){
			throw new ConnectException("不明なホスト名です。接続できません。");
		}catch(IOException ie){
			throw new ConnectException("入出力エラーです。接続できません。");
		}
		if(waitconn != null){
			waitconn.close();
		}
		createReaderWriter();
		setReceiveOk(true);
	}
	
	public void receiveConnect(Socket socket){
		if(this.socket != null || socket == null){
			return;
		}
		this.socket = socket;
		createReaderWriter();                                          //GUIに接続通知をする前にストリームを作成しておかないと、GUIがいきなり出力を
		gui.receiveConnect(socket.getInetAddress().getHostName());     //した時に対応できない。しかし先にストリームを作成すると、GUI側の準備が整う前に
		setReceiveOk(true);                                            //相手側から入力があるかもしれない。そこでセマフォを用意し、GUI側の準備後に
	}                                                                  //受信可能とする仕組みにした。
	
	public void sendMessage(String strMess){
		if(bufWriter == null){
			throw new ConnectException("まだ接続されていません。");
		}
		try{
			bufWriter.write(strMess);
			bufWriter.newLine();
			bufWriter.flush();
		}catch(IOException ie){
			throw new ConnectException("送信に失敗しました。");
		}
	}
	
	public void receiveMessage(String strMess){
		checkReceiveOk(true);
		gui.receiveMessage(strMess);
	}
	
	public void end_conn(){
		if(waitconn != null){
			waitconn.close();
			waitconn = null;
		}
		if(bufWriter != null){
			try{
				bufWriter.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}finally{
				bufWriter = null;
			}
		}
		if(receiver != null){
			receiver.close();
			receiver = null;
		}
		if(socket != null){
			try{
				socket.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}finally{
				socket = null;
			}
		}
		setReceiveOk(false);
	}
	
	private void createReaderWriter(){
		try{
			bufWriter = new BufferedWriter(
					new OutputStreamWriter(
							socket.getOutputStream()
					)
			);
		}catch(IOException ie){
			throw new ConnectException("送信ストリームを取得できませんでした。");
		}
		
		try{
			BufferedReader bufReader = new BufferedReader(
				new InputStreamReader(
					socket.getInputStream()
				)
			);
			receiver = new Receiver(this, bufReader);
		}catch(IOException ie){
			throw new ConnectException("受信ストリームを取得できませんでした。");
		}
	}
	
	private synchronized boolean setReceiveOk(boolean ok){
		blReceiveOk = ok;
		notifyAll();
		return blReceiveOk;
	}
	
	private synchronized void checkReceiveOk(boolean ok){
		while(blReceiveOk != ok){
			try{
				wait();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		notifyAll();
	}
}

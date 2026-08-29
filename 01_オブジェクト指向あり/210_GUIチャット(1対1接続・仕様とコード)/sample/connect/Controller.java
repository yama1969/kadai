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
	
	public Controller(connectGUI gui){
		this.gui = gui;
		waitconn = new WaitConnect(this, port_no);
	}
	
	public Controller(connectGUI gui, int port){
		this.gui = gui;
		this.port_no = port;
		waitconn = new WaitConnect(this, port_no);
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
		waitconn.close();
		createReaderWriter();
	}
	
	public void receiveConnect(Socket socket){
		if(this.socket != null || socket == null){
			return;
		}
		this.socket = socket;
		gui.receiveConnect(socket.getInetAddress().getHostName());
		createReaderWriter();
	}
	
	public void sendMessage(String strMess){
		if(bufWriter == null){
			throw new ConnectException("まだ接続されていません。");
		}
		try{
			bufWriter.write(strMess+"\n");
			bufWriter.flush();
		}catch(IOException ie){
			throw new ConnectException("送信に失敗しました。");
		}
	}
	
	public void receiveMessage(String strMess){
		gui.receiveMessage(strMess);
	}
	
	public void end_conn(){
		if(waitconn != null){
			waitconn.close();
		}
		if(bufWriter != null){
			try{
				bufWriter.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}
		if(socket != null){
			try{
				socket.close();
			}catch(IOException ie){
				ie.printStackTrace();
			}
		}
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
}

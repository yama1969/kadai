package connect;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver implements Runnable{
	
	private Controller controll;
	private BufferedReader bufReader;
	
	public Receiver(Controller controll, BufferedReader bufReader){
		this.controll = controll;
		this.bufReader = bufReader;
		Thread th = new Thread(this);
		th.start();
	}
	
	public void run(){
		System.out.println("受信スレッドを開始します。");
		String strMess;
		try{
			while( (strMess = bufReader.readLine()) != null){
				controll.receiveMessage(strMess);
			}
		}catch(IOException ie){
			throw new ConnectException("入力エラーです。");
		}
		System.out.println("受信スレッドを終了します。");
	}
}

package connect;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver implements Runnable{
	
	private Controller controll;
	private BufferedReader bufReader;
	private boolean open;
	
	public Receiver(Controller controll, BufferedReader bufReader){
		this.controll = controll;
		this.bufReader = bufReader;
		this.open = true;
		Thread th = new Thread(this);
		th.start();
	}
	
	public void run(){
		System.out.println("受信スレッドを開始します。"+Thread.currentThread().getName());
		String strMess;
		try{
			while( (strMess = bufReader.readLine()) != null){
				controll.receiveMessage(strMess);
			}
		}catch(IOException ie){
			if(open){
				throw new ConnectException("入力エラーです。");
			}else{
				System.out.println("受信ストリームが閉じられました。");
			}
		}
		System.out.println("受信スレッドを終了します。"+Thread.currentThread().getName());
	}
	
	public void close(){
		try{
			open = false;
			bufReader.close();
		}catch(IOException ie){
			throw new ConnectException("受信ストリームを閉じるときに例外が発生しました。");
		}finally{
			bufReader = null;
		}
	}
}

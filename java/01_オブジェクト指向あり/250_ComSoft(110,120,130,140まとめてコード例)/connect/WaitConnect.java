package connect;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


public class WaitConnect implements Runnable{

	private ServerSocket	svSock;
	private int				port;
	private Controller		controll;
	
	private boolean	end_reason = true;
	
	public WaitConnect(Controller controll, int port){
		this.controll = controll;
		this.port = port;
		Thread th = new Thread(this);
		th.start();
	}
	
	public void run(){
		System.out.println("待受けスレッドを開始します。" + Thread.currentThread().getName());
		
		Socket socket = null;
		
		try{
			svSock = new ServerSocket(port);
			socket = svSock.accept();
		}catch(IOException ie){
			if(end_reason){
				throw new ConnectException("待受け状態の作成、あるいは接続受けに失敗しました。");
			}
			System.out.println("待受けスレッドを終了します。"+Thread.currentThread().getName());
			return;
		}
		
		controll.receiveConnect(socket);
		close();
		
		System.out.println("待受けスレッドを終了します。"+Thread.currentThread().getName());
	}
	
	public void close(){
		try{
			if(svSock != null){
				svSock.close();
			}
		}catch(IOException ie){
			throw new ConnectException("待受け状態の解除が異常終了しました。");
		}finally{
			svSock = null;
		}
		end_reason = false;
	}
}

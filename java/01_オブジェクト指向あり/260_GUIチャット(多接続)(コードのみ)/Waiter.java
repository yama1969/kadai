import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
  ソケット接続を待受け、接続があればSocketPoolに登録します。
  待受け処理に専用スレッドを起動します。
*/
public class Waiter extends Thread{
    //-----フィールド-----------------------------------------------------------
    private ServerSocket ssock;                                                 //listen用サーバソケット
    private SocketPool sockPool;                                                //利用SocketPool
    private boolean run;                                                        //待受け実行状態(true=実行中)
    
    //-----コンストラクタ：待受けスレッド開始-----------------------------------
    public Waiter(SocketPool sp, int port) throws WaiterException{
        try{
            ssock = new ServerSocket(port);
        }catch(IOException ie){
            throw new WaiterException("待受開始しませんでした。");
        }
        run = true;
        sockPool = sp;
        start();
    }
    
    //-----待受け＆SocketPool登録処理-------------------------------------------
    public void run(){
        while(run){
            Socket s;
            try{
                s = ssock.accept();
            }catch(IOException e){
                run = false;
                break;
            }
            
            sockPool.add(s);
        }
    }
    
    //-----待受け停止-----------------------------------------------------------
    public void stopWait(){
        if(ssock != null){
            try{
                ssock.close();
            }catch(IOException e){
            }
        }
        run = false;
    }
}

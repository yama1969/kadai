import java.util.ArrayList;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
  SocketPoolは、複数のソケット通信を束ね、各ソケットにおける送信と受信を
  一括して引き受けるクラスです。送受信メッセージはStringを用います。
*/
public class SocketPool{
    //-----フィールド-----------------------------------------------------------
    private SocketPoolUser user;                                                //このクラスの利用者
    private ArrayList<Socket>   sockList;                                       //ソケットリスト
    private ArrayList<Receiver> recList;                                        //受信インスタンスリスト
    private ArrayList<Sender>   sendList;                                       //送信インスタンスリスト
    
    //-----コンストラクタ-------------------------------------------------------
    public SocketPool(SocketPoolUser u){
        sockList = new ArrayList<Socket>();
        recList = new ArrayList<Receiver>();
        sendList = new ArrayList<Sender>();
        user = u;
    }
    
    /***************************************************************************
      SocketPool API ユーザから呼ばれるメソッド群
    ***************************************************************************/
    //-----ソケットの追加-------------------------------------------------------
    public void add(Socket sock){
        Receiver r = new Receiver(sock,this);                                   //受信スレッドを開始
        Sender s = new Sender(sock,this);
        synchronized(this){
            sockList.add(sock);                                                 //ソケットを登録
            recList.add(r);
            sendList.add(s);
        }
        user.addSocket(sock);                                                   //ユーザに通知
    }
    
    //-----ソケットの削除-------------------------------------------------------
    //removeAll()を実行すると、全ソケットのstopReceive()が実行され、
    //各ソケットのReceiver.run()ではIOExceptionが発生し、stopScket(Receiver r)が
    //実行される。それによりremove(Socket sock)が実行されるが、このときremoveAll()で
    //コレクションを処理中なので、remove()はsynchronizedにより待合わせ状態となる。
    //待合わせ後にremove()が実行され始めたときには、removeAll()によって全ソケットが
    //削除された後なので、noは-1となり、if文内は実行されずに抜ける。
    public synchronized void remove(Socket sock){
        int no = sockList.indexOf(sock);
        if(no >= 0){
            try{
                sendList.get(no).stopSend();                                    //指定ソケットの送信を閉じる
                recList.get(no).stopReceive();                                  //指定ソケットの受信スレッドを停止
                sock.close();                                                   //指定ソケットを閉じる
                sendList.remove(no);                                            //ソケット削除
                recList.remove(no);
                sockList.remove(no);
                user.removeSocket(sock);                                        //ユーザに通知
            }catch(IndexOutOfBoundsException e){
                                                                                //ここに来ると異常。
                System.out.println("SocketPool.remove():ソケット管理に矛盾が発生");
            }catch(IOException e){
            }
        }
    }
    
    //-----全ソケットの削除-----------------------------------------------------
    public synchronized void removeAll(){
        for(Sender s : sendList){                                                //Collectionに対してIterator使用中にCollectionの変更すると例外発生するので、remove()とremoveAll()はそれぞれ独立に作った。
            s.stopSend();
        }
        for(Receiver r : recList){
            r.stopReceive();
        }
        for(Socket so : sockList){
            try{
                so.close();
            }catch(IOException e){
            }
        }
        sendList.clear();
        recList.clear();
        sockList.clear();
    }
    
    //-----メッセージの送信：指定ソケット以外へ送信指令-------------------------
    public void sendMessage(String mess, Socket no_sock){                       //全ソケットに送信する場合はno_sendをnullにする。
        Sender no_send = null;
        if(no_sock != null){
            int no = sockList.indexOf(no_sock);
            no_send = sendList.get(no);
        }
        for(Sender s : sendList){
            if(!s.equals(no_send)){
                s.sendMessage(mess);
            }
        }
    }
    
    //-----メッセージの送信：指定ソケットのみへ送信指令-------------------------
    public void sendMessageOne(String mess, Socket sock){
         int no = sockList.indexOf(sock);
         if(no >= 0){
             try{
                 Sender s = sendList.get(no);
                 s.sendMessage(mess);
             }catch(IndexOutOfBoundsException e){
                                                                                //ここに来ると異常。
                 System.out.println("SocketPool.sendMessageOne():ソケット管理に矛盾が発生");
             }
         }
    }
    
    //-----メッセージの送信：全ソケットへ送信指令-------------------------------
    public void sendMessage(String mess){
        sendMessage(mess, null);
    }
    
    /***************************************************************************
      ReceiverまたはSenderから呼ばれるメソッド群
    ***************************************************************************/
    //-----メッセージ受信：ユーザへ通知-----------------------------------------
    private void receiveMessage(Receiver r){
        String mess = r.getMessage();
        if(mess == null){                                                       //ソケット切断時、受信強制終了のときに空文字列が送られてくる
            return;
        }
        int no = recList.indexOf(r);
        if(no >= 0){
            try{
                user.receiveMessage(sockList.get(no), mess);                    //ユーザへ通知
            }catch(IndexOutOfBoundsException e){
                                                                                //ここに来ると異常。
                System.out.println("SocketPool.receiveMessage(Reciever r):ソケット管理に矛盾が発生");
            }
        }
    }
    
    //-----異常停止通知：Receiverからの停止通知---------------------------------
    private void stopSocket(Receiver r){                                        //無駄の多いコードだが保守性を優先
        int no = recList.indexOf(r);
        try{
            remove(sockList.get(no));                                           //ソケット削除
        }catch(IndexOutOfBoundsException e){
                                                                                //ここに来ると異常。
            System.out.println("SocketPool.stopSocket(Receiver r):ソケット管理に矛盾が発生");
        }
    }
    
    //-----異常停止通知：Senderからの停止通知-----------------------------------
    private void stopSocket(Sender s){
        int no = sendList.indexOf(s);
        try{
            remove(sockList.get(no));                                           //ソケット削除
        }catch(IndexOutOfBoundsException e){
                                                                                //ここに来ると異常。
            System.out.println("SocketPool.stopSocket(Sender s):ソケット管理に矛盾が発生");
        }
    }
    
    /***************************************************************************
      ReceiverおよびSender定義
    ***************************************************************************/
    //-----受信クラス-----------------------------------------------------------
    private class Receiver extends Thread{
        //-----フィールド-------------------------------------------------------
        private SocketPool     boss;                                            //状態通知先
        private BufferedReader bufReader;                                       //受信リーダ
        private boolean        run;                                             //受信実行状態
        private String         message;                                         //受信文字列
        private boolean        flg_getMess;                                     //文字列取得フラグ(true=未取得)
        
        //-----コンストラクタ：ソケットからリーダを得る＆受信開始---------------
        public Receiver(Socket s, SocketPool boss){
            this.boss = boss;
            try{
                bufReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            }catch(IOException ie){
                boss.stopSocket(this);                                          //異常をボスに通知
                return;
            }
            message = null;
            flg_getMess = false;
            run = true;
            start();
        }
        
        //-----受信処理：受信すればSocketPoolに通知-----------------------------
        public void run(){
            try{
                while(run){
                    message = bufReader.readLine();
                    flg_getMess = true;
                    boss.receiveMessage(this);
                    synchronized(this){                                         //文字列がgetされるまで待つ
                        while(flg_getMess){
                            try{
                                wait();
                            }catch(InterruptedException e){
                                boss.stopSocket(this);                          //異常をボスに通知
                            }
                        }
                    }
                }
            }catch(IOException ie){
                boss.stopSocket(this);                                          //異常をボスに通知
            }
        }
        
        //-----受信文字列取得---------------------------------------------------
        public synchronized String getMessage(){
            flg_getMess = false;
            notifyAll();
            return message;
        }
        
        //-----受信停止---------------------------------------------------------
        public void stopReceive(){
             if(flg_getMess){
                 getMessage();                                                  //文字列getの待ちを解除した上で停止
             }
             run = false;
             try{
                 if(bufReader != null){
                     bufReader.close();
                 }
             }catch(IOException e){
             }
        }
    }
    
    //-----送信クラス-----------------------------------------------------------
    private class Sender{
        //-----フィールド-------------------------------------------------------
        private SocketPool  boss;                                               //状態通知先
        private PrintWriter bufWriter;                                          //送信ライタ
        
        //-----コンストラクタ：ソケットからライタを得る-------------------------
        public Sender(Socket s, SocketPool boss){
            this.boss = boss;
            try{                                                                //オートflush付でPrintWriterを使う
                bufWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            }catch(IOException ie){
                boss.stopSocket(this);                                          //異常をボスに通知
                return;
            }
        }
        
        //-----送信処理---------------------------------------------------------
        public void sendMessage(String mess){
            bufWriter.println(mess);
        }
        
        //-----送信停止---------------------------------------------------------
        public void stopSend(){
            if(bufWriter != null){
                bufWriter.close();
            }
        }
    }
}

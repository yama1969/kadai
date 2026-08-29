import java.util.ArrayList;
import java.util.HashMap;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

/**
  Chatのアプリケーション層プロトコル実装クラスです。
  
  ChatProtocol

  ( 1)接続とサーバ
    ( 1- 1)どのアプリケーションもサーバとして新規接続を受付けることができる。接続の最大数は実装による。
    ( 1- 2)通信中のサーバに別アプリケーションが接続した時、当該アプリケーションはチャットに参加することになる。
           ひとつのアプリケーションから発信されたメッセージは、接続をたどることの出来る全てのアプリケーションに送信される。
    ( 1- 3)最初のアプリケーションは接続先がなく、サーバとして接続を受けるだけである。
    ( 1- 4)アプリケーションが接続できるサーバは１つのみとする。(1-3)～(1-4)により、リング状の接続が不可能となる。
           (リング状の接続をすると、メッセージが永遠に回り続けてしまう。）
    ( 1- 5)サーバの待受けポート番号は5555。

  ( 2)ハンドル名
    ( 2- 1)アプリケーションユーザは必ずハンドル名を持つ。ハンドル名の最大長は実装による。
    ( 2 -2)ハンドル名重複時の対処方法は後述

  ( 3)メッセージ
    ( 3- 1)メッセージは、先頭5byteがコマンドであり、続く文字列が内容である。メッセージの最後尾は\nとする。
    ( 3- 2)受信したメッセージは、受信したソケットを除く全てのソケットへ転送する。

    以下に送信メッセージ書式を示す。(ハンドル名)の両側の()は実際には付けない。
    ( 4- 1)接続開始メッセージ・・・接続を開始したとき最初に１度だけ、接続先サーバへ送信する。
             helo (ハンドル名)
    ( 4- 2)接続中確認メッセージ・・・他アプリケーションのheloを受けたときの応答として、heloを受信したソケットのみに返信する。
                                     liveメッセージで自分と同一のハンドル名を受けた場合は、自分のハンドル名を変更する。
             live (ハンドル名)
    ( 4- 3)発言メッセージ・・・ユーザの発言を送信する。
             mess <(ハンドル名)> (発言文字列)
    ( 4- 4)接続終了メッセージ・・・切断時、最後に１度だけ全ソケットへ送信する。
             exit (ハンドル名)
    ( 4- 5)切断メッセージ・・・接続相手のアプリケーションがexitメッセージなしにソケット切断した場合、当該ソケットを除く全てのソケットに、
                               切断したハンドル名で送信する。
             disc (切断したハンドル名)
*/
public class ChatProtocol implements SocketPoolUser{
    //-----フィールド-----------------------------------------------------------
    private final int         PORT = 5555;                                      //listenポート
    private String            name = null;                                      //ハンドル名
    private Waiter            waiter = null;                                    //接続待受けクラス
    private SocketPool        sockPool = null;                                  //ソケットの管理クラス
    private Socket            sock = null;                                      //サーバへの接続ソケット
    private Chat              user = null;                                      //Chatプロトコル利用アプリケーションクラス
    private HashMap<String,Socket> handleMap = null;                            //接続中ハンドル・ソケット対応リスト
    
    //-----コンストラクタ-------------------------------------------------------
    public ChatProtocol(Chat user, String name) throws ChatProtocolException{
        if(user == null){                                                       //利用クラスがnullなら、動作せず
            throw new ChatProtocolException("利用クラスまたはハンドル名がnullです。");
        }
        if(name == null || name.equals("")){                                    //ハンドル名がnullなら、動作せず
            throw new ChatProtocolException("利用クラスまたはハンドル名がnullです。");
        }
        
        this.user = user;
        this.name = name;
        handleMap = new HashMap<String,Socket>();
        handleMap.put(this.name, null);
        sockPool = new SocketPool(this);
    }
    
    /***************************************************************************
      アプリケーションから呼ばれるメソッド群
    ***************************************************************************/
    //-----サーバモード起動-----------------------------------------------------
    public boolean startWait(){
        if(waiter != null){
            return true;
        }
        
        try{
            waiter = new Waiter(sockPool, PORT);                                //接続待受け開始
        }catch(WaiterException e){
            return false;
        }
        return true;
    }
    
    //-----サーバモード停止-----------------------------------------------------
    public void stopWait(){
        if(waiter == null){
            return;
        }
        
        waiter.stopWait();
        waiter = null;
        return;
    }
    
    //-----接続待受け状態取得---------------------------------------------------
    public boolean isWait(){
        if(waiter == null){
             return false;
        }
        return true;
    }
    
    //-----接続-----------------------------------------------------------------
    public void connect(String add) throws ChatProtocolException{
        if(add != null){                                                        //接続アドレス指定があれば、相手ソフトに接続
            if(isWait() && (add.equals("") || add.equals("localhost"))){
                throw new ChatProtocolException("サーバモードでは自分自身への接続はできません。");
            }
            try{
                Socket s = new Socket(add, PORT);
                sockPool.add(s);
                sockPool.sendMessage("helo " + this.name);
            }catch(UnknownHostException e){
                throw new ChatProtocolException("接続先が不明です。");
            }catch(IOException e){
                throw new ChatProtocolException("接続不能です。");
            }
        }
    }
    
    //-----メッセージ送信-------------------------------------------------------
    public void sendMessage(String mess){
        sockPool.sendMessage("mess <" + name + "> " + mess);                   //全員へ送信
    }
    
    //-----ハンドル名変更-------------------------------------------------------
    public void setName(String name){
        this.name = name;
        handleMap = new HashMap<String,Socket>();
        handleMap.put(this.name, null);
        sockPool.sendMessage("helo " + this.name);
    }
    
    //-----接続ハンドル一覧取得-------------------------------------------------
    public ArrayList<String> getNames(){
         ArrayList<String> al = new ArrayList<String>(handleMap.keySet());
         return al;
    }
    
    //-----接続回線一覧取得-----------------------------------------------------
    public ArrayList<String> getConns(){
        ArrayList<String> keyhandle = new ArrayList<String>(handleMap.keySet());
        ArrayList<String> al = new ArrayList<String>();
        
        for(String str : keyhandle){
            Socket s = handleMap.get(str);
            String n = str;
            if(s != null){                                                      //自分自身のエントリはヌルソケットなので。
                n = n + " " + s.getLocalAddress() + ":" + s.getLocalPort() + "-->" + s.getInetAddress() + ":" + s.getPort();
            }
            al.add(n);
        }
        return al;
    }
    
    //----切断------------------------------------------------------------------
    public void exit(){
        sockPool.sendMessage("exit " + name);
        sockPool.removeAll();
        if(waiter != null){
            waiter.stopWait();
        }
        handleMap = new HashMap<String,Socket>();
        handleMap.put(name, null);
    }
    
    /***************************************************************************
      SocketPoolUserの実装
    ***************************************************************************/
    //-----接続あり-------------------------------------------------------------
    public void addSocket(Socket sock){
    }
    
    //-----メッセージ受信-------------------------------------------------------
    public void receiveMessage(Socket sock, String mess){
        sockPool.sendMessage(mess, sock);                                       //同じメッセージを送信元以外に送る
        
        String cmd = mess.substring(0,4).trim().toLowerCase();                  //コマンドと内容を分離
        String str = mess.substring(5);
        
        if(cmd.equals("helo")){                                                 //新接続
            sockPool.sendMessageOne("live " + name, sock);                      //  送信元へ応答
            handleMap.put(str,sock);                                            //  ハンドル・ソケットリストに追加
            user.newUser(str);                                                  //  アプリケーションに通知
        }else if(cmd.equals("live")){                                           //接続中確認
            if(name.equals(str)){                                               //  自分のハンドル名と重複していた場合
                user.changeName();                                              //  アプリケーションに通知
            }
            if(!handleMap.containsKey(str)){                                    //  ハンドルリストに存在しないユーザならば
                handleMap.put(str,sock);                                        //  ハンドル・ソケットリストに追加
                user.addUser(str);                                              //  アプリケーションに通知
            }
        }else if(cmd.equals("mess")){                                           //発言
            user.message(str);                                                  //  アプリケーションに通知
        }else if(cmd.equals("exit")){                                           //終了
            handleMap.remove(str);                                              //  ハンドル・ソケットリストから削除
            user.exitUser(str);                                                 //  アプリケーションに通知
        }else if(cmd.equals("disc")){                                           //異常切断
            handleMap.remove(str);                                              //  ハンドル・ソケットリストから削除
            user.disconUser(str);                                               //  アプリケーションに通知
        }
    }
    
    //-----切断あり-------------------------------------------------------------
    public void removeSocket(Socket sock){
        ArrayList<String> keyhandle = new ArrayList<String>(handleMap.keySet());
        for(String str : keyhandle){
            if(sock.equals(handleMap.get(str))){                                //ソケットのエントリが残っていた場合＝eixtなし切断
                handleMap.remove(str);                                          //  ハンドル・ソケットリストから削除
                user.disconUser(str);                                           //  アプリケーションに通知
                sockPool.sendMessage("disc " + str);                            //  discを切断ソケット以外に送る
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/**
  コンソールチャットソフトです。
*/
public class ChatCUI implements Chat{
    //-----フィールド-----------------------------------------------------------
    private BufferedReader keyIn;
    private ChatProtocol   chatPro;
    
    //-----コンストラクタ-------------------------------------------------------
    public ChatCUI(String name){
        keyIn = new BufferedReader(new InputStreamReader(System.in));
        try{
            chatPro = new ChatProtocol(this, name);
        }catch(ChatProtocolException e){
            e.printStackTrace();
        }
    }
    
    //-----チャット実行---------------------------------------------------------
    public void startChat(String add){
        if(!chatPro.startWait()){
            System.out.println("接続待受けは開始しませんでした。");
        }
        
        try{
            chatPro.connect(add);
        }catch(ChatProtocolException e){
            System.out.println(e.getLocalizedMessage());
        }
        while(true){
            String str;
            System.out.print("message >");
            try{
                str = keyIn.readLine();
            }catch(IOException e){
                chatPro.exit();
                break;
            }
            
            if(str.equals("exit")){
                chatPro.exit();
                break;
            }else if(str.equals("cons")){
                ArrayList<String> al = chatPro.getConns();
                for(String s : al){
                    System.out.println(s);
                }
            }else if(str.equals("name")){
                ArrayList<String> al = chatPro.getNames();
                for(String s : al){
                    System.out.println(s);
                }
            }else if(str.equals("?") || str.equals("help")){
                System.out.println("ハンドル一覧　　　　:　name");
                System.out.println("回線＆ハンドル一覧　:　cons");
                System.out.println("終了　　　　　　　　:　exit");
            }else{
                chatPro.sendMessage(str);
            }
        }
    }
    
    /***************************************************************************
      Chatの実装
    ***************************************************************************/
    //-----新ユーザ接続---------------------------------------------------------
    public void newUser(String name){
        System.out.println("\n（" + name + "さんが参加しました。）");
        System.out.print("message >");
    }
    
    //-----既存ユーザ発見-------------------------------------------------------
    public void addUser(String name){
    }
    
    //-----重複ユーザ発見-------------------------------------------------------
    public void changeName(){
        System.out.println("\n（重複ユーザが見つかったので、終了します。）");
        chatPro.exit();
    }
    
    //-----メッセージ着信-------------------------------------------------------
    public void message(String mess){
        System.out.println("\n" + mess);
        System.out.print("message >");
    }
    
    //-----ユーザ切断-----------------------------------------------------------
    public void exitUser(String name){
        System.out.println("\n（" + name + "さんが退室しました。）");
        System.out.print("message >");
    }
    
    //----ユーザ回線切断--------------------------------------------------------
    public void disconUser(String name){
        System.out.println("\n（" + name + "さんの接続が切れました。）");
        System.out.print("message >");
    }
    
    /***************************************************************************
      main()
    ***************************************************************************/
    public static void main(String[] args){
        if(args.length < 1 || args.length > 2){
            System.out.println("USAGE : java ChatSoft (name) [(address)]");
            return;
        }
        
        String name = args[0];
        String add = null;
        if(args.length == 2){
            add = args[1];
        }
        ChatCUI cc = new ChatCUI(name);
        cc.startChat(add);
    }
}

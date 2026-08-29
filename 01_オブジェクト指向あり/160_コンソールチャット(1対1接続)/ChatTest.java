import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChatTest extends Thread{
    private String destAdd;
    private final int PORT = 5555;
    private Socket         socket;
    private BufferedReader keyReader;
    private BufferedReader netReader;
    private BufferedWriter netWriter;
    private boolean bRun;

    public ChatTest(boolean serverMode, String add){
        destAdd = add;
        try{
            if(serverMode){
                System.out.println("相手の接続を待受けています。");
                socket = new ServerSocket(PORT).accept();
                System.out.println("接続を受けました。");
            }else{
                socket = new Socket(destAdd, PORT);
            }
            netReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            netWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(IOException ie){
            ie.printStackTrace();
        }
        keyReader = new BufferedReader(new InputStreamReader(System.in));
        bRun = false;
    }
    
    public void startChat(){
        bRun = true;
        start();
        
        String inStr;
        while(true){
            try{
                System.out.print("メッセージ＞");
                inStr = keyReader.readLine();
            }catch(IOException ie){
                ie.printStackTrace();
                System.out.println("キーボード入力時例外です。");
                break;
            }
//            System.out.println(inStr);
            try{
                netWriter.write(inStr + "\n");
                netWriter.flush();
            }catch(IOException ie){
                ie.printStackTrace();
                System.out.println("ネットワーク出力時例外です。");
                break;
            }
            if(inStr.equals("exit")){
                break;
            }
        }
        bRun = false;
        try{
            socket.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }
        try{
            this.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
            System.out.println("受信スレッド待機時の割込例外です。");
        }
    }
    
    public void run(){
/*
        System.out.println();
        System.out.println("受信スレッド開始");
        System.out.print("メッセージ＞");
*/
        String inStr;
        while(bRun){
            try{
                inStr = netReader.readLine();
                System.out.println();
                if(inStr == null){
                    System.out.println("通信が終了しました。");
                    bRun = false;
                }else if(inStr.equals("exit")){
                    System.out.println("相手が切断しました。");
                }else{
                    System.out.println(inStr);
                }
                System.out.print("メッセージ＞");
            }catch(IOException ie){
                //ie.printStackTrace();
                System.out.println("通信が終了しました。");
            }
        }
        System.exit(0);
    }
    
    public static void main(String[] args){
        String add;
        boolean mode;
        if(args.length > 0){
            add = args[0];
            mode = false;
        }else{
            add = null;
            mode = true;
        }
        ChatTest ct = new ChatTest(mode, add);
        ct.startChat();
    }
}
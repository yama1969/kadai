import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MailSend{
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sendto = null;
        String message = null;
        
        //メールサーバの指定
        System.out.println("これからメールを送信します。");
        System.out.print("送信先メールアドレス => ");
        try{
            sendto = reader.readLine();
        }catch(IOException e){
            System.out.println("キーボードエラーが発生しました。");
            System.exit(0);
        }
        
        //メッセージの入力
        System.out.print("メッセージ => ");
        try{
            message = reader.readLine();
        }catch(IOException e){
            System.out.println("キーボードエラーが発生しました。");
            System.exit(0);
        }
        
        //メールの送信
        Socket sock = null;
        try{
            sock = new Socket("115.146.59.23",25);
        }catch(UnknownHostException e){
            System.out.println("メールサーバが見つかりません。");
            System.exit(0);
        }catch(IOException e){
            System.out.println("ネットワークエラーが発生しました。");
            System.exit(0);
        }
        
        BufferedReader receiver = null;
        PrintWriter sender = null;
        String line = null;
        try{
            receiver = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            sender = new PrintWriter(sock.getOutputStream(), true);
            
            //接続時サーバ応答受信
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("220")){
                System.out.println("接続時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            //送信者メールアドレス送信
            sender.println("mail from:<test@test.com>");
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("250")){
                System.out.println("送信者メール通知時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            
            //宛先メールアドレス送信
            sender.println("rcpt to:<" + sendto + ">");
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("250")){
                System.out.println("宛先メール通知時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            
            //メール本文開始送信
            sender.println("data");
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("354")){
                System.out.println("メール本文開始時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            
            //メール本文送信
            sender.println("From: Java Program<test@test.com>");
            sender.println("To: <" + sendto + ">");
            sender.println("Subject: Java Mail Test");
            sender.println();
            sender.println("This is test message.");
            sender.println(message);
            sender.println(".");
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("250")){
                System.out.println("メール本文送信時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            
            //切断要求
            sender.println("quit");
            line = receiver.readLine();
System.out.println(line);
            if(!line.substring(0,3).equals("221")){
                System.out.println("切断時サーバ応答が不正です。" + line);
                System.exit(0);
            }
            
            System.out.println("送信しました。");
        }catch(IOException e){
            System.out.println("ネットワークエラーが発生しました。");
        }finally{
            if(sender != null){
                sender.close();
            }
            if(receiver != null){
                try{
                    receiver.close();
                }catch(IOException e){
                    System.out.println("受信クローズエラーが発生しました。");
                }
            }
            if(sock != null){
                try{
                    sock.close();
                }catch(IOException e){
                    System.out.println("通信クローズエラーが発生しました。");
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread{
    private final BufferedReader conReader;
    private Socket sock;
    private boolean run;
    
    public Client(){
        conReader = new BufferedReader(new InputStreamReader(System.in));
        run = true;
    }
    
    public void startComm(){
        try{
            System.out.print("サーバアドレス入力：");
            String add = conReader.readLine();
            System.out.print("ポート番号入力：");
            int port = Integer.parseInt(conReader.readLine());
            sock = new Socket(add, port);
            startReceive();
            startSend();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void startSend(){
        try{
            PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
            while(run){
                System.out.print("mess>");
                String line = conReader.readLine();
                writer.println(line);
                if(line.equals("exit")){
                    stopComm();
                }
            }
        }catch(Exception e){
            if(run){
                e.printStackTrace();
            }
        }
    }
    
    public void startReceive(){
        start();
    }
    
    public void run(){
        try{
            BufferedReader sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            while(run){
                System.out.println(sockReader.readLine());
                System.out.print("mess>");
            }
        }catch(Exception e){
            if(run){
                e.printStackTrace();
            }
        }
    }
    
    public void stopComm(){
        System.out.println("通信終了");
        run = false;
        try{
            sock.close();
        }catch(Exception e){
        }
    }
    
    public static void main(String[] args){
        new Client().startComm();
    }
}

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ConnectWaiter extends Thread{
    private final ServerSocket serverSock;
    private final SocketEntity socketEntity;
    
    public ConnectWaiter(int port, SocketEntity se){
        if(port < 1024 || port > 65535){
            port = 0;
        }
        serverSock = new ServerSocket(port);
        socketEntity = se;
    }
    
    public ConnectWaiter(SocketEntity se){
        ConnectWaiter(0, se);
    }
    
    public void startWait(){
        start();
    }
    
    public void run(){
        try{
            Socket sock = serverSock.accept();
            se.addSocket(sock);
        }catch(IOException e){
        }
    }
    
    public void shutdown(){
        try{
            serverSock.close();
        }catch(IOException e){
        }
    }
}

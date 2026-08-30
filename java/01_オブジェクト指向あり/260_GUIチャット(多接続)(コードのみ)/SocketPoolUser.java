import java.net.Socket;

/**
  SocketPoolを利用するクラスは、このSocketPoolUserインターフェースを実装してください。
  実装メソッドを呼び出すことにより、SocketPoolから状況を知らせます。
*/
public interface SocketPoolUser{
    /**
      SocketPoolに新しいソケットが登録された時に、このメソッドが呼び出されます。
    */
    public abstract void addSocket(Socket sock);
    
    /**
      SocketPoolに登録された何れかのソケットで受信があった時、このメソッドが呼び出されます。
    */
    public abstract void receiveMessage(Socket sock, String mess);
    
    /**
      SocketPoolに登録された何れかのソケットが登録削除された時、このメソッドが呼び出されます。
    */
    public abstract void removeSocket(Socket sock);
}

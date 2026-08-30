/**
 *  AutoReceiveクラスを利用するには、このインターフェースを
 *  インプリメントしてください。<br>
 *  AutoReceiveクラスは、AutoReceiveInterfaceで定義されたreceive()メソッドを
 *  定期的にコールします。
 */
public interface AutoReceiveInterface{

    /**
     *  メール受信を実行するメソッドです。<br>
     *  このメソッドがAutoReceiveクラスによって定期的に実行されます。
     */
    public void receive();
}

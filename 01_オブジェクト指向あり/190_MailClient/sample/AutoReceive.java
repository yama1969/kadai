/**
 *  メールの定期的自動受信を提供するクラスです。<br>
 *  このクラスを利用するクラスはAutoReceiveInterfaceインターフェースを
 *  インプリメントしてください。その上でAutoReceiveクラスのコンストラクタに
 *  自分自身を引数に入れてインスタンスを作成し、スレッドとして立ち上げると、
 *  AutoReceiveInterfaceで定義されたreceive()メソッドを定期的にコールします。
 */
public class AutoReceive implements Runnable{

    /**
     *  受信間隔　単位ms
     */
    private int                  interval = 10000;

    /**
     *  AutoReceiveクラスを利用するインスタンス
     */
    private AutoReceiveInterface ariClient;

    /**
     *  このフラグがtrueである限り、受信を続けます。
     *  falseになると受信停止します。
     */
    private boolean              active;

    /**
     *  デフォルトインターバル10秒でのコンストラクタです。<br>
     *  引数には、このクラスを利用するクラスのインスタンスを指定します。
     *  @param ari AutoReceiveInterfaceをインプリメントしたクラスのインスタンス
     */
    public AutoReceive(AutoReceiveInterface ari){
        ariClient = ari;
        active = true;
    }

    /**
     *  インターバル秒数を指定するコンストラクタです。<br>
     *  引数には、このクラスを利用するクラスのインスタンスと、
     *  インターバル秒数を指定します。
     *  @param ari   AutoReceiveInterfaceをインプリメントしたクラスのインスタンス
     *  @param inter インターバルの秒数
     */
    public AutoReceive(AutoReceiveInterface ari, int inter){
        this(ari);
        interval  = inter * 1000;
    }

    /**
     *  メール受信メソッドreceive()を実行します。<br>
     *  Runnableインターフェースのrun()メソッド実装です。
     *  このメソッドから実行されるreceive()は、AutoReceiveInterfaceで定義された
     *  メソッドです。
     */
    public void run(){
        while(active){
            ariClient.receive();
            try{
                Thread.sleep(interval);
            }catch(InterruptedException ie){
            }
        }
    }

    /**
     *  自動受信を停止します。<br>
     *  スレッドとして実行していた場合、スレッドそのものが終了します。
     */
    public void stop(){
        active = false;
    }
}

/**
 * チャットソフトのユーザインターフェースを提供するクラスは、このインターフェースを実装する。
 * これらのメソッドは、別スレッドから非同期に呼び出される。
 */
public interface ChatUI{
    /** 待受終了を通知する */
    void waitStoped();
    /** メッセージの受信を通知する @param mess 受信文字列*/
    void messageReceived(String mess);
}

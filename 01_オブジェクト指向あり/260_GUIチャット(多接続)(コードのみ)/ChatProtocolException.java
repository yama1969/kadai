/**
  ChatProtocolの処理中に、プロトコルで対応できない事象が発生したときにthrowされる例外です。
*/
public class ChatProtocolException extends Exception{
    //-----フィールド-----------------------------------------------------------
    private String message;
    
    //-----コンストラクタ:引数なし＝メッセージなし------------------------------
    public ChatProtocolException(){
        message = null;
    }
    
    //-----コンストラクタ:引数＝メッセージ--------------------------------------
    public ChatProtocolException(String mess){
        message = mess;
    }
    
    //-----ローカライズメッセージ取得:Throwableクラスのオーバーライド-----------
    public String getLocalizedMessage(){
        return message;
    }
}

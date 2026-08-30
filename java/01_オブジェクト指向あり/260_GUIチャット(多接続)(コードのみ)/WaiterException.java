/**
  Waiterの処理中に、異常が発生したときにthrowされる例外です。
*/
public class WaiterException extends Exception{
    //-----フィールド-----------------------------------------------------------
    private String message;
    
    //-----コンストラクタ:引数なし＝メッセージなし------------------------------
    public WaiterException(){
        message = null;
    }
    
    //-----コンストラクタ:引数＝メッセージ--------------------------------------
    public WaiterException(String mess){
        message = mess;
    }
    
    //-----ローカライズメッセージ取得:Throwableクラスのオーバーライド-----------
    public String getLocalizedMessage(){
        return message;
    }
}

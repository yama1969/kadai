/**
 *  メールサーバ応答が異常のときに発生する例外です。
 */
class MailServerResponseException extends Exception{

    /**
     *  引数なしのコンストラクタです。
     */
    public MailServerResponseException(){
        super();
    }

    /**
     *  getMessage()で取得できるメッセージをセットするコンストラクタです。
     */
    public MailServerResponseException(String str){
        super(str);
    }
}

/**
 *  サーバ及びメールアドレスが未設定のまま送信しようとしたときに
 *  発生する例外です。
 */
class MailSettingException extends Exception{

    /**
     *  引数なしのコンストラクタです。
     */
    public MailSettingException(){
        super();
    }

    /**
     *  getMessage()で取得できるメッセージをセットするコンストラクタです。
     */
    public MailSettingException(String str){
        super(str);
    }
}

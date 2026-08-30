/**
 *  何らかの原因でメール送信が失敗したときに発生する例外です。
 */
class MailSendException extends Exception{

    /**
     *  引数なしのコンストラクタです。
     */
    public MailSendException(){
        super();
    }

    /**
     *  getMessage()で取得できるメッセージをセットするコンストラクタです。
     */
    public MailSendException(String str){
        super(str);
    }
}

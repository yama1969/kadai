/**
 *  何らかの原因でメール受信が失敗したときに発生する例外です。
 */
class MailReceiveException extends Exception{

    /**
     *  引数なしのコンストラクタです。
     */
    public MailReceiveException(){
        super();
    }

    /**
     *  getMessage()で取得できるメッセージをセットするコンストラクタです。
     */
    public MailReceiveException(String str){
        super(str);
    }
}

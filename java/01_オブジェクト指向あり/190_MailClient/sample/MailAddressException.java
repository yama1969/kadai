/**
 *  不正なメールアドレスであるときに発生する例外です。
 */
class MailAddressException extends Exception{

    /**
     *  引数なしのコンストラクタです。
     */
    public MailAddressException(){
        super();
    }

    /**
     *  getMessage()で取得できるメッセージをセットするコンストラクタです。
     */
    public MailAddressException(String str){
        super(str);
    }
}

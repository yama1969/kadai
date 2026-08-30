import java.util.ArrayList;

/**
 *  メール一覧を保持するクラスです。
 */
public class ReceivedMail{

    /**
     *  送信元アドレスの一覧
     */
    private ArrayList alFrom    = new ArrayList();
    /**
     *  件名の一覧
     */
    private ArrayList alSubject = new ArrayList();
    /**
     *  本文の一覧
     */
    private ArrayList alMessage = new ArrayList();

    /**
     *  現在保持されているメールの件数を返します。
     *  @return メール件数
     */
    public int getMailCount(){
        return alMessage.size();
    }

    /**
     *  送信元メールアドレスの配列を返します。
     *  @return 送信元メールアドレスArrayList
     */
    public ArrayList getArrayFrom(){
        return alFrom;
    }

    /**
     *  件名の配列を返します。
     *  @return 件名ArrayList
     */
    public ArrayList getArraySubject(){
        return alSubject;
    }

    /**
     *  本文の配列を返します。
     *  @return 本文ArrayList
     */
    public ArrayList getArrayMessage(){
        return alMessage;
    }

    /**
     *  指定されたメールの送信元メールアドレスを返します。<br>
     *  引数には、メール配列の番号を渡します。
     *  @param  no メール番号
     *  @return 送信元メールアドレス
     *  @exception IndexOutOfBoundsException インデックスが範囲外の場合
     */
    public String getFromAddress(int no){
        String from;
        try{
            from = (String)alFrom.get(no);
        }catch(IndexOutOfBoundsException e){
            throw e;
        }
        return from;
    }

    /**
     *  指定されたメールの件名を返します。<br>
     *  引数には、メール配列の番号を渡します。
     *  @param  no メール番号
     *  @return 件名
     *  @exception IndexOutOfBoundsException インデックスが範囲外の場合
     */
    public String getSubject(int no){
        String subject;
        try{
            subject = (String)alSubject.get(no);
        }catch(IndexOutOfBoundsException e){
            throw e;
        }
        return subject;
    }

    /**
     *  指定されたメールの本文を返します。<br>
     *  引数には、メール配列の番号を渡します。
     *  @param  no メール番号
     *  @return 本文文字列
     *  @exception IndexOutOfBoundsException インデックスが範囲外の場合
     */
    public String getMessage(int no){
        String message;
        try{
            message = (String)alMessage.get(no);
        }catch(IndexOutOfBoundsException e){
            throw e;
        }
        return message;
    }

    /**
     *  メール一覧へ新たにメールを追加します。<br>
     *  @param  from    送信元メールアドレス
     *  @param  subject 件名
     *  @param  message メール本文
     */
    public void addMail(String from, String subject, String message){
        alFrom.add(from);
        alSubject.add(subject);
        alMessage.add(message);
    }

    /**
     *  メール一覧からメールを１件削除します。<br>
     *  @param no メール番号
     *  @exception IndexOutOfBoundsException インデックスが範囲外の場合
     */
    public void delMail(int no){
        try{
            alFrom.remove(no);
            alSubject.remove(no);
            alMessage.remove(no);
        }catch(IndexOutOfBoundsException e){
            throw e;
        }
    }

    /**
     *  メール一覧に他のメール一覧を追加します。<br>
     *  @param rm 追加するメール一覧
     */
    public void addReceivedMail(ReceivedMail rm){
        int cnt = rm.getMailCount();
        for(int i = 0; i < cnt; i++){
            alFrom.add(rm.getFromAddress(i));
            alSubject.add(rm.getSubject(i));
            alMessage.add(rm.getMessage(i));
        }
    }
}

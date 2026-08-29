//
//  メール受信クラス  by 山田　洋
//

import java.net.Socket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 *  メール受信の機能を提供します。ひとまず最小限の機能です。<br>
 *  受信したメール一覧は、ReceivedMailクラスのインスタンスの形で返します。
 */
public class MailReceive{

    /**
     *  サーバへ送信するコマンド文字列を入れる配列です。
     */
    private String[] comm = new String[]{"user ","pass ","stat"};
    /**
     *  メールサーバ名
     */
    private String   server = null;
    /**
     *  メールサーバとのソケットポート番号
     */
    private int      port_no = 110;
    /**
     *  ローカルホスト名
     */
    private String   localhost = "nemo";

    /**
     *  受信メール一覧
     */
    private ReceivedMail rm;

    /**
     *  受信メール一覧
     */
    private boolean mailDel = false;

    /**
     *  メール送信サーバの設定を行います。
     *  @param srv サーバ名またはサーバIPアドレス
     */
    public void setServer(String srv){
        server = srv;
    }

    /**
     *  メール受信ユーザの設定します。
     *  @param user ユーザアカウント名文字列
     */
    public void setUser(String user){
        comm[0] = "user " + user;
    }

    /**
     *  メール受信ユーザのパスワードを設定します。
     *  @param pass パスワード文字列
     */
    public void setPass(String pass){
        comm[1] = "pass " + pass;
    }

    /**
     *  受信済みになったメールをサーバから削除するか否かを設定します。
     *  @param sw 削除する：true　削除しない：false
     */
    public void setDelete(boolean sw){
        mailDel = sw;
    }

    /**
     *  メール受信を行います。
     *  @return    ReceivedMail 受信メール一覧
     *  @exception MailSettingException 未設定項目あり
     *  @exception MailReceiveException メール受信失敗
     */
    public ReceivedMail receiveMail()
      throws MailSettingException,MailReceiveException{

        //設定状況確認
        if(   comm[0].equals("user ")
           || comm[1].equals("pass ")
           || server == null
           || server.equals("")){
            throw new MailSettingException("設定不足です。");
        }

        //ソケット・ストリームの取得
        Socket         mySocket    = null;
        BufferedWriter myBufWriter = null;
        BufferedReader myBufReader = null;

        try{
            mySocket = new Socket(server, port_no);
            mySocket.setSoTimeout(10000);
        }catch(UnknownHostException ue){
            throw new MailReceiveException("ホストが見つかりません。");
        }catch(SocketException se){
            throw new MailReceiveException("ソケットエラーです。");
        }catch(IOException ie){
            throw new MailReceiveException("ソケット接続時の入出力エラーです。");
        }

        try{
            myBufWriter = new BufferedWriter(
                                new OutputStreamWriter(
                                      mySocket.getOutputStream()
                                    )
                              );
        }catch(IOException ie){
            //getOutputStream()による例外スロー
            closeSocket(mySocket);
            throw new MailReceiveException("出力ストリームが得られませんでした。");
        }

        try{
            myBufReader = new BufferedReader(
                                new InputStreamReader(
                                      mySocket.getInputStream()
                                    )
                              );
        }catch(IOException ie){
            //getInputStream()による例外スロー
            closeSocket(mySocket);
            throw new MailReceiveException("入力ストリームが得られませんでした。");
        }

        //受信開始
        ReceivedMail rm     = new ReceivedMail();
        String       errStr = null;

        try{
            int    i       = 0;         //送信コマンドカウンタ
            int    mailCnt = 0;         //メール数
            String strRes  = null;      //受信メッセージ文字列
            String strFrom;             //送信元メールアドレス
            String strSubject;          //件名
            String strMessage;          //本文

            System.out.println(strRes = myBufReader.readLine());
            chkRes("+OK",strRes);

            //ユーザ認証～メール数取得
            for( i = 0; i < 3; i++){
                System.out.println(comm[i]);
                myBufWriter.write(comm[i]);
                myBufWriter.newLine();
                myBufWriter.flush();
                System.out.println(strRes = myBufReader.readLine());
                chkRes("+OK",strRes);
            }
            mailCnt = getMailCount(strRes);

            //本文取得
            for( i = 1; i <= mailCnt; i++){
                System.out.println("retr " + i);
                myBufWriter.write("retr " + i);
                myBufWriter.newLine();
                myBufWriter.flush();
                System.out.println(strRes = myBufReader.readLine());
                chkRes("+OK",strRes);

                strFrom    = "";
                strSubject = "";
                strMessage = "";
                strRes = "";
                while(!(strRes = myBufReader.readLine()).equals(".")){
                    int len;

                    len = strRes.length();
                    if(len > 5 && strRes.substring(0,5).equals("From:")){
                        strFrom = strRes.substring(6);
                    }
                    if(len > 8 && strRes.substring(0,8).equals("Subject:")){
                        strSubject = strRes.substring(9);
                    }
                    strMessage =  strMessage
                                + strRes
                                + System.getProperty("line.separator");
                    System.out.println(strRes);
                }
                rm.addMail(strFrom, strSubject, strMessage);

                //削除
                if(mailDel){
                    System.out.println("dele " + i);
                    myBufWriter.write("dele " + i);
                    myBufWriter.newLine();
                    myBufWriter.flush();
                    System.out.println(strRes = myBufReader.readLine());
                    chkRes("+OK",strRes);
                }
            }

            //切断
            System.out.println("quit");
            myBufWriter.write("quit");
            myBufWriter.newLine();
            myBufWriter.flush();
            System.out.println(strRes = myBufReader.readLine());
            chkRes("+OK",strRes);
        }catch(SocketTimeoutException ste){
            errStr = "サーバタイムアウトです。";
        }catch(IOException ie){
            //wirte(),readLine()による例外スロー
            errStr = "入出力エラーです。";
        }catch(MailServerResponseException msre){
            errStr = "サーバ応答エラーです。";
        }finally{
            closeReader(myBufReader);
            closeWriter(myBufWriter);
            closeSocket(mySocket);
            if(errStr != null){
                throw new MailReceiveException(errStr);
            }
        }

        return rm;
    }

    /**
     *  メールサーバとのソケットをクローズします。
     *  このメソッドはIOExceptionを処理してしまいます。
     *  @param sc クローズするソケット
     */
    private void closeSocket(Socket sc){
        try{
            sc.close();
        }catch(IOException e){
            System.out.println("ソケットクローズエラーです。");
        }
    }

    /**
     *  メールサーバからの入力ストリームをクローズします。
     *  このメソッドはIOExceptionを処理してしまいます。
     *  @param br クローズする入力ストリーム
     */
    private void closeReader(BufferedReader br){
        try{
            br.close();
        }catch(IOException e){
            System.out.println("入力ストリームクローズエラーです。");
        }
    }

    /**
     *  メールサーバへの出力ストリームをクローズします。
     *  このメソッドはIOExceptionを処理してしまいます。
     *  @param bw クローズする出力ストリーム
     */
    private void closeWriter(BufferedWriter bw){
        try{
            bw.close();
        }catch(IOException e){
            System.out.println("出力ストリームクローズエラーです。");
        }
    }

    /**
     *  サーバ応答の正常・異常をチェックします。
     *  @param norm 正常時文字列 "+OK"
     *  @param chk  チェック対象文字列
     *  @exception MailServerResponseException サーバ応答異常
     */
    private void chkRes(String norm, String chk)
      throws MailServerResponseException {
        if(!chk.substring(0,3).equals(norm)){
            throw new MailServerResponseException();
        }
    }

    /**
     *  statコマンドの応答からメール件数を抽出します。
     *  @param str statコマンドのサーバ応答文字列
     *  @return メール件数
     */
    private int getMailCount(String str){
        int s;
        int e;
        s = str.indexOf(" ");
        e = str.indexOf(" ", s+1);
        return Integer.parseInt(str.substring(s+1,e));
    }
}

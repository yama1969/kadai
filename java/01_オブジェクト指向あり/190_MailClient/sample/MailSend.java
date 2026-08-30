//
//  メール送信クラス  by 山田　洋
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
 *  メール送信の機能を提供します。ひとまず最小限の機能です。
 */
public class MailSend{

    /**
     *  サーバへ送信するコマンドと、サーバ応答チェック用の
     *  文字列を入れる配列です。
     */
    private String[] comm = new String[]{
            "helo nemo",   "250",
            "mail from:<>","250",
            "rcpt to:<>",  "250",
            "data",        "354",
            "",            "",
            ".",           "250",
            "quit",        "221",
        };
    /**
     *  メールサーバ名
     */
    private String   server = null;
    /**
     *  メールサーバとのソケットポート番号
     */
    private int      port_no = 25;
    /**
     *  ローカルホスト名
     */
    private String   localhost = "nemo";

    /**
     *  メール送信サーバの設定を行います。
     *  @param srv サーバ名またはサーバIPアドレス
     */
    public void setServer(String srv){
        server = srv;
    }

    /**
     *  メール送信元メールアドレスの設定を行います。
     *  @param add 送信元メールアドレス文字列
     *  @exception MailAddressException メールアドレスの形式が不正
     */
    public void setFromAddress(String add)
      throws MailAddressException{
        if(chkAddress(add)){
            comm[2] = "mail from:<" + add + ">";
        }else{
            throw new MailAddressException("送信元アドレス不正です。");
        }
    }

    /**
     *  メール宛先メールアドレスの設定を行います。
     *  @param add 宛先メールアドレス文字列
     *  @exception MailAddressException メールアドレスの形式が不正
     */
    public void setToAddress(String add)
      throws MailAddressException{
        if(chkAddress(add)){
            comm[4] = "rcpt to:<" + add + ">";
        }else{
            throw new MailAddressException("宛先アドレス不正です。");
        }
    }

    /**
     *  メール本文の設定を行います。
     *  @param mes メール本文文字列
     */
    public void setMessage(String mes){
        comm[8] = mes;
    }

    /**
     *  メール送信を行います。
     *  @exception MailSettingException 未設定項目あり
     *  @exception MailSendException    メール送信失敗
     */
    public void sendMail()
      throws MailSettingException,MailSendException{

        //設定状況確認
        if(   comm[2].equals("mail from:<>")
           || comm[4].equals("rcpt to:<>")
           || server == null
           || server.equals("")){
            throw new MailSettingException("設定不足です。");
        }

        //ホスト情報取得
        try{
            InetAddress inaHost = InetAddress.getLocalHost();
            localhost = inaHost.getHostName();
        }catch(UnknownHostException ue){
            localhost = "nemo";
        }
        comm[0] = "helo " + localhost;

        //ソケット・ストリームの取得
        Socket         mySocket    = null;
        BufferedWriter myBufWriter = null;
        BufferedReader myBufReader = null;

        try{
            mySocket = new Socket(server, port_no);
            mySocket.setSoTimeout(10000);
        }catch(UnknownHostException ue){
            throw new MailSendException("ホストが見つかりません。");
        }catch(SocketException se){
            throw new MailSendException("ソケットエラーです。");
        }catch(IOException ie){
            throw new MailSendException("ソケット接続時の入出力エラーです。");
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
            throw new MailSendException("出力ストリームが得られませんでした。");
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
            throw new MailSendException("入力ストリームが得られませんでした。");
        }

        //送信開始
        String  errStr = null;
        try{
            int     i     = 0;
            String strRes = null;

            System.out.println(strRes = myBufReader.readLine());
            if(!strRes.substring(0,3).equals("220")){
                throw new MailSendException("接続時サーバ応答エラーです。");
            }

            for( i = 0; i < 13; i = i + 2){
                System.out.println(comm[i]);
                myBufWriter.write(comm[i]);
                myBufWriter.newLine();
                myBufWriter.flush();

                if(i != 8){
                    System.out.println(strRes = myBufReader.readLine());
                    if(!strRes.substring(0,3).equals(comm[i+1])){
                        throw new MailSendException("サーバ応答エラーです。");
                    }
                }
            }
        }catch(SocketTimeoutException ste){
            //readLine()による例外スロー
            errStr = "サーバータイムアウトです。";
        }catch(IOException ie){
            //wirte(),readLine()による例外スロー
            errStr = "入出力エラーです。";
        }finally{
            closeReader(myBufReader);
            closeWriter(myBufWriter);
            closeSocket(mySocket);
            if(errStr != null){
                throw new MailSendException(errStr);
            }
        }

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
     *  メールアドレスのチェックをします。
     *  @param  add チェック対象文字列
     *  @return 正常:true　異常:false
     */
    private boolean chkAddress(String add){
        int i;
        int c = (int)'@';

        if(( i = add.indexOf(c) ) != -1){
            if(add.indexOf(c,i+1) == -1){
                return true;
            }
        }
        return false;
    }
}

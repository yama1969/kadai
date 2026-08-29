import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Keyboardクラスは、簡単に利用できるキーボード入力機能を提供する。<br>
 * 各メソッドでは、入力時に発生した異常を戻り値で通知する。<br>
 * Keyboardクラスはクラスメソッドのみ持つので、コンストラクタはprivateアクセス
 * とし、インスタンス化を禁止する。<br>
 * <br>
 * <h3>補足</h3>
 * 入力に使用するAPIクラスであるBufferedReaderは、次のようにtry～catch構文の
 * tryブロック内で使用すると、メソッドの後ろのthrows・・・の記述が不要となり、
 * そのメソッドを呼び出す側のメソッドの後ろもthrows・・・の記述が不要となる。<br>
 * (try～catchについてはLesson14で学ぶ)<br>
 * <pre>
 * public static String readString(){
 *     String str = null;
 *     try{
 *         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *         str = br.readLine();
 *         //つづきのコード
 * 
 *     }catch(Exception e){
 *         //エラーが発生したときの処理(今回はなんの処理も要らない)
 *     }
 *     return str;
 * }
 * </pre>
 * 上記のtry～catch構文は、Integer.parseInt()での整数への変換可否のチェックにも
 * 使える。<br>
 * <pre>
 * public static int readInt(){
 *     int ans = Integer.MIN_VALUE;
 *     String str = null;
 *     try{
 *         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *         str = br.readLine();
 *         ans = Integer.parseInt();
 *         //つづきのコード
 * 
 *     }catch(Exception e){
 *         //入力エラーや整数変換エラーが発生したときの処理(今回はなんの処理も要らない)
 *     }
 *     return ans;
 * }
 * </pre>
 * <h3>動作確認</h3>
 * Keyboardクラスが出来上がったら、Keyboardクラスの各メソッドを呼び出す下記の
 * ようなmain()メソッドを持つクラスを作成し、実行して動作確認を行うこと。<br>
 * このような、作成したプログラムを駆動する動作確認のためのモジュール(クラス)を
 * 「ドライバ」という。(基本情報p463)<br>
 * <pre>
 * public static void main(String[] args){
 *     System.out.println("Keyboardクラスの動作テストを行います。");
 *     System.out.println("最初は文字列の入力テストです。");
 *     System.out.println();
 *     
 *     String str = Keyboard.readString();
 *     System.out.println("文字列「" + str + "」が入力されました。");
 *     System.out.println();
 *     
 *     str = Keyboard.readString("文字列を入力してください : ");
 *     System.out.println("文字列「" + str + "」が入力されました。");
 *     System.out.println();
 *     
 *     System.out.println("続いて整数の入力テストです。");
 *     System.out.println();
 *     int i = Keyboard.readInt();
 *     System.out.println("整数" + i + "が入力されました。");
 *     System.out.println();
 *     
 *     int i = Keyboard.readInt("整数を入力してください : ");
 *     System.out.println("整数" + i + "が入力されました。");
 *     System.out.println();
 * }
 * </pre>
 */
public class Keyboard{
    
    private Keyboard(){
    }
    
    /**
     * 入力された文字列を得る。<br>
     * 入力待ち状態になり、入力されたStringが得られる。<br>
     * <br>
     * <table border="1">
     *   <caption>使用イメージ</caption>
     *   <tr><td>呼出しコード</td><td>Keyboard.readString();</td></tr>
     *   <tr><td>画面表示</td><td>あいうえお<br> (「あいうえお」は入力した文字列)</td></tr>
     * </table>
     *
     * @return 入力された文字列への参照。異常時はnull。
     */
    public static String readString(){
        String         str = null;
        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        try{
            str = in.readLine();
        }catch(Exception e){
        }
        return str;
    }
    
    /**
     * メッセージを表示後、入力された文字列を得る。<br>
     * メッセージの右側で入力待ち状態となり、入力されたStringが得られる。<br>
     * <br>
     * <table border="1">
     *   <caption>使用イメージ</caption>
     *   <tr><td>呼出しコード</td><td>Keyboard.readString("挨拶を入力 =&gt; ");</td></tr>
     *   <tr><td>画面表示</td><td>挨拶を入力 =&gt; あいうえお<br> (「あいうえお」は入力した文字列)</td></tr>
     * </table>
     *
     * @param  message メッセージ文字列
     * @return 入力された文字列への参照。異常時はnull。
     */
    public static String readString(String message){
        System.out.print(message);
        return readString();
    }
    
    /**
     * 入力されたint値を得る。<br>
     * 入力待ち状態になり、入力されたint値が得られる。<br>
     * <br>
     * <table border="1">
     *   <caption>使用イメージ</caption>
     *   <tr><td>呼出しコード</td><td>Keyboard.readInt();</td></tr>
     *   <tr><td>画面表示</td><td>48<br> (「48」は入力した整数)</td></tr>
     * </table>
     *
     * @return 入力されたint値。入力異常時、および入力された文字列がint値に変換できない場合は、int型整数の最小値(Integer.MIN_VALUE)。
     */
    public static int readInt(){
        String str = readString();
        int    ans = Integer.MIN_VALUE;
        
        if(str != null){
            try{
                ans = Integer.parseInt(str);
            }catch(Exception e){
            }
        }
        return ans;
    }
    
    /**
     * メッセージを表示後、入力されたint値を得る。<br>
     * メッセージの右側で入力待ち状態となり、入力されたint値が得られる。<br>
     * <br>
     * <table border="1">
     *   <caption>使用イメージ</caption>
     *   <tr><td>呼出しコード</td><td>Keyboard.readInt("整数を入力 =&gt; ");</td></tr>
     *   <tr><td>画面表示</td><td>整数を入力 =&gt; 48<br> (「48」は入力した整数)</td></tr>
     * </table>
     *
     * @param  message メッセージ文字列
     * @return 入力されたint値。入力異常時、および入力された文字列がint値に変換できない場合は、int型整数の最小値(Integer.MIN_VALUE)。
     */
    public static int readInt(String message){
        System.out.print(message);
        return readInt();
    }
}

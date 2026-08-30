import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Keyboardクラスは、簡単に利用できるキーボード入力機能を提供する。
 * Keyboardクラスの各メソッドでは、入力時に発生した異常を戻り値で通知する。
 *
 * @author 山田　洋 2008/5/14
 */
public class Keyboard{
    
    /**
     * Keyboardクラスはクラスメソッドのみ持つので、インスタンス化を禁止。
     */
    private Keyboard(){
    }
    
    /**
     * 入力された文字列を得る。
     * 入力プロンプトが表示され、その右側に入力された文字列が得られる。
     *
     * @return 入力された文字列への参照。異常時はnull。
     */
    public static String readString(){
        String         str = null;
        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print(" => ");
        try{
            str = in.readLine();
        }catch(IOException e){
            str = null;
        }
        return str;
    }
    
    /**
     * メッセージを表示後、入力された文字列を得る。
     * メッセージの右側に入力プロンプトが表示され、その右側に入力された文字列が得られる。
     *
     * @param  message メッセージ文字列
     * @return 入力された文字列への参照。異常時はnull。
     */
    public static String readString(String message){
        System.out.print(message);
        return readString();
    }
    
    /**
     * 入力されたint値を得る。
     * 入力プロンプトが表示され、その右側に入力されたint値が得られる。
     *
     * @return 入力されたint値。入力異常時、および入力された文字列がint値に変換できない場合は、int型整数の最小値(Integer.MIN_VALUE)。
     */
    public static int readInt(){
        String str = readString();
        int    ans = Integer.MIN_VALUE;
        
        if(str != null){
            try{
                ans = Integer.parseInt(str);
            }catch(NumberFormatException e){
                ans = Integer.MIN_VALUE;
            }
        }
        return ans;
    }
    
    /**
     * メッセージを表示後、入力されたint値を得る。
     * メッセージの右側に入力プロンプトが表示され、その右側に入力されたint値が得られる。
     *
     * @param  message メッセージ文字列
     * @return 入力されたint値。入力異常時、および入力された文字列がint値に変換できない場合は、int型整数の最小値(Integer.MIN_VALUE)。
     */
    public static int readInt(String message){
        System.out.print(message);
        return readInt();
    }
    
    /**
     * 入力された文字を得る。
     * 入力プロンプトが表示され、その右側に入力された文字が得られる。
     *
     * @return 入力された文字。2文字以上が入力された場合は、1文字目の文字。異常時は文字コード0。
     */
    public static char readChar(){
        char   ans = (char)0;
        String str = readString();
        
        if(str != null && str.length() != 0){
            ans = str.charAt(0);
        }
        return ans;
    }
    
    /**
     * メッセージを表示後、入力された文字を得る。
     * メッセージの右側に入力プロンプトが表示され、その右側に入力された文字が得られる。
     *
     * @param  message メッセージ文字列
     * @return 入力された文字。2文字以上が入力された場合は、1文字目の文字。異常時は文字コード0。
     */
    public static char readChar(String message){
        System.out.print(message);
        return readChar();
    }
}

import java.io.*;

/**
 * コンピュータとプレイヤーがじゃんけんするプログラム
 */
public class JunkenMethod{
    public static void main(String[] args){
        //最初の表示
        showInitMessage();
        
        //コンピュータの手を決める
        int comp = makeCompHand();
        
        //プレイヤーの手を決める
        int player = inputPlayerHand();
        
        //勝敗判定
        int win = judge(player, comp);
        
        //勝敗表示
        showResult(win);
    }
    
    /**
     * じゃんけんの最初の画面表示を行う。
     */
    public static     showInitMessage(){
    }
    
    /**
     * コンピュータの手を決める。
     * 乱数により、0～3の整数値を返す。
     * 戻り値 0:グー 1:チョキ 2:パー
     */
    public static     makeCompHand(){
    }
    
    /**
     * プレイヤーの手を決める。
     * キーボード入力により、0～3の整数値を返す。
     * 入力文字列が整数に変換できない時は、再度入力させる。
     * 戻り値 0:グー 1:チョキ 2:パー -1:IOException発生
     */
    public static     inputPlayerHand(){
    }
    
    /**
     * 勝敗判定をする。
     * 判定の結果を戻り値で戻す。
     * 引数 プレイヤーの手(-1～2)
     *      コンピュータの手(0～2)
     * 戻り値 -1:手の値が不正のため判定できず
               0:プレイヤーの勝ち 
     *         1:あいこ
     *         2:コンピュータの勝ち
     */
    public static     judge(){
    }
    
    /**
     * 勝敗表示を行う。
     * 引数 勝敗結果(judge()メソッドの戻り値に従う)
     *
     */
    public static     showResult(){
    }
}

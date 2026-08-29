package e_while;

/******************************************************************************
 * 10進数 → 2進数変換
 *****************************************************************************/
public class Kadai2500{
    public static void main(String[] args){
        //変数準備
        int num = Keyboard.readInt("正の整数"); //変換元10進数の入力
        String bin = "";                        //変換後2進数の文字列
        
        //変換処理
        while(num != 0){                        //元値が0になるまで
            int res = num % 2;                  //元値の2の剰余を2進数に繋げる
            bin = res + bin;                    //  (文字列連結)
            num /= 2;                           //元値を2で割る
        }
        
        //結果表示
        System.out.println(bin);
    }
}

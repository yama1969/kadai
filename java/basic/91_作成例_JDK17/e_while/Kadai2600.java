package e_while;

/******************************************************************************
 * 2進数 → 10進数変換
 *****************************************************************************/
public class Kadai2600{
    public static void main(String[] args){
        //変数の準備
        String bin = Keyboard.readString("正の2進整数");//変換元2進数
        int dec = 0;                                    //変換後10進数
        int weight = 1;                                 //処理中桁重み
        
        //変換処理
        while(!bin.equals("")){                         //2進数の桁なくなるまで
            int n = bin.charAt(bin.length() - 1) - 48;  //右端桁を数値変換
            dec += n * weight;                          //10進数へ重み分加算
            weight *= 2;                                //桁重みを次の桁へ
            bin = bin.substring(0, bin.length() - 1);   //2進数を右シフト
        }
        
        //変換結果表示
        System.out.println(dec);
    }
}

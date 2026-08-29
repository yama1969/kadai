/******************************************************************************
 * 無限級数でπを求める
 *****************************************************************************/
public class Kadai5000{
    public static void main(String[] args){
        //変数の準備
        double delta = Keyboard.readInt("1/?"); //計算精度の入力
        delta = 1.0 / delta;                    //項が1/delta未満で計算終了
        
        double part = 0.0;                      //処理中の項の値
        double pi = 0;                          //求めるπの値
        double n = 1.0;                         //処理中の項の分母
        double sign = 1.0;                      //処理中の項の符号(1.0 or -1.0)
        
        //級数の計算
        do{
            part = 4.0 * sign / n;              //項の値を求める
            pi += part;                         //項の値を加算
            System.out.println(pi);             //計算途中結果表示
            
            n += 2.0;                           //次項の分母と符号を求める
            sign *= -1.0;
        }while(Math.abs(part) > delta);         //項が精度未満でなければ続行
        
        //比較のため、Math.PIを表示
        System.out.println();
        System.out.println(Math.PI);
    }
}

/******************************************************************************
 * int型とdouble型の使い方を練習する
 *****************************************************************************/
public class Kadai2300{
    public static void main(String[] args){
        //2つのint型変数に値を代入し、表示
        int a = 53;
        System.out.println("a = " + a);
        int b = 23;
        System.out.println("b = " + b);
        
        //キャストしない場合の割り算結果を表示
        System.out.println("a / b = " + (a / b));
        
        //キャストして計算。int型では小数が扱えないのでbouble型に変換する
        double div = (double)a / (double)b;
        System.out.println("a / b = " + div);
    }
}

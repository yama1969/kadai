package a_hensu;

/******************************************************************************
 * boolean型変数
 *****************************************************************************/
public class Kadai3800{
    public static void main(String[] args){
        //2つのint型変数に入力
        int a = Keyboard.readInt("整数a");
        int b = Keyboard.readInt("整数b");
        
        //boolean型の式(a <= b)の結果をboolean型変数に代入
        boolean result = a <= b;
        System.out.println();
        
        //boolean型変数を表示
        System.out.println("a <= b = " + result);
    }
}

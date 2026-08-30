package a_hensu;

/******************************************************************************
 * ロジック(処理手順)演習  2つの変数の値を入れ替える
 *****************************************************************************/
public class Kadai3000{
    public static void main(String[] args){
        //変数の宣言と値の代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int b = (int)(Math.random() * 10.0);
        
        //変数の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println();
        
        //変数の内容の入れ替え
        int c = a;
        a = b;
        b = c;
        
        //変数の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}

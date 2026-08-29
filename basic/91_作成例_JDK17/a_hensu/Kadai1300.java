package a_hensu;

/******************************************************************************
 * 各算術演算子の確認
 *****************************************************************************/
public class Kadai1300{
    public static void main(String[] args){
        //変数の宣言と代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int b = (int)(Math.random() * 10.0);
        
        //変数の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println();
        
        //各演算結果の表示
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));
    }
}

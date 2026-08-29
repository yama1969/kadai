/******************************************************************************
 * 変数を値(データ)の入れ物として使いまわす
 *****************************************************************************/
public class Kadai1400{
    public static void main(String[] args){
        //2つの変数の宣言と代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int b = (int)(Math.random() * 10.0);
        
        //変数の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + (a + b));
        
        System.out.println();
        
        //変数に別の値を代入
        a = (int)(Math.random() * 10.0);      //整数乱数0～9
        b = (int)(Math.random() * 10.0);
        
        //変数の表示
        System.out.println("c = " + a);
        System.out.println("d = " + b);
        System.out.println("c + d = " + (a + b));
    }
}

/******************************************************************************
 * 変数を用いた式の記述
 *****************************************************************************/
public class Kadai1200{
    public static void main(String[] args){
        //変数の宣言と代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int b = (int)(Math.random() * 10.0);
        
        //変数の表示と合計の表示
        System.out.println("1つ目の乱数 = " + a);
        System.out.println("2つ目の乱数 = " + b);
        System.out.println("合計 = " + (a + b));
    }
}

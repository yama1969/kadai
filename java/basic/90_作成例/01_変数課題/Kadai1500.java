/******************************************************************************
 * 変数を値(データ)の入れ物として使いまわす
 *****************************************************************************/
public class Kadai1500{
    public static void main(String[] args){
        //3つの変数の宣言と代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int b = (int)(Math.random() * 10.0);  //整数乱数0～9
        int sum = a + b;                      //全ての合計値
        
        //変数と合計の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + (a + b));
        System.out.println();
        
        //変数に別の値を代入
        a = (int)(Math.random() * 10.0);      //整数乱数0～9
        b = (int)(Math.random() * 10.0);      //整数乱数0～9
        sum += (a + b);                       //全てを合計
        
        //変数と合計の表示
        System.out.println("c = " + a);       //表示上はc,dであるが、変数はa,b
        System.out.println("d = " + b);
        System.out.println("c + d = " + (a + b));
        System.out.println();
        
        //全合計を表示
        System.out.println("全合計 = " + sum);
    }
}

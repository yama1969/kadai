/******************************************************************************
 * ロジック演習 part2  合計15になる3つの整数乱数を生成
 *****************************************************************************/
public class Kadai3100{
    public static void main(String[] args){
        //合計値の定義
        int max = 15;
        
        //乱数の発生
        int a = (int)(Math.random() * (double)(max + 1));    //0～maxの乱数
        int b = (int)(Math.random() * (double)(max - a + 1));//残りの範囲の乱数
        int c = max - (a + b);                               //a,bにより決まる
        
        //3つの乱数を表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }
}

/******************************************************************************
 * 条件判定
 *****************************************************************************/
public class Kadai3900{
    public static void main(String[] args){
        //2つのサイコロの目を乱数で決める
        int a = (int)(Math.random() * 6.0) + 1;  //整数乱数1～6
        int b = (int)(Math.random() * 6.0) + 1;
        
        //2で割った余りが0ならtrue, そうでなければfalse
        boolean result = (a + b) % 2 == 0;
        
        //結果表示
        System.out.println(a + ", " + b + " の " + result);
    }
}

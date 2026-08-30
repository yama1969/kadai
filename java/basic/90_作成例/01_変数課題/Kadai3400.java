/******************************************************************************
 * 文字列連結の方法
 *****************************************************************************/
public class Kadai3400{
    public static void main(String[] args){
        //2つのchar型変数への代入
        char c1 = 'A';
        char c2 = 'B';
        
        //確認のための表示
        System.out.println("1つ目 = " + c1);
        System.out.println("2つ目 = " + c2);
        System.out.println();
        
        //2つのchar型変数を文字列連結して表示
        //+演算子の片方がString型なら、文字列連結になる
        System.out.println("連結結果 = " + c1 + c2);
    }
}

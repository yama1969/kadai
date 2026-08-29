/******************************************************************************
 * 文字型の実体は文字コードであることの確認
 *****************************************************************************/
public class Kadai3300{
    public static void main(String[] args){
        //2つのchar型変数への代入
        char c1 = 'A';
        char c2 = 'B';
        
        //確認のための表示
        System.out.println("1つ目 = " + c1);
        System.out.println("2つ目 = " + c2);
        System.out.println();
        
        //char型を強引に合計して表示。整数が表示される
        System.out.println("足し算結果 = " + (c1 + c2));
    }
}

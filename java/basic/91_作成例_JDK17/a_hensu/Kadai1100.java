package a_hensu;

/*****************************************************************************
 * 変数への代入と表示
 *****************************************************************************/
public class Kadai1100{
    public static void main(String[] args){
        //変数の宣言と代入
        int a = (int)(Math.random() * 10.0);  //1つめの変数(0～9)
        int b = (int)(Math.random() * 10.0);  //2つめの変数(0～9)
        
        //変数の表示
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}

package a_hensu;

/******************************************************************************
 * 変数を値(データ)の入れ物として使いまわす
 *****************************************************************************/
public class Kadai1600{
    public static void main(String[] args){
        //1つめの乱数を代入
        int a = (int)(Math.random() * 10.0);  //整数乱数0～9
        int sum = a;                          //合計値
        
        //1つめの乱数を表示
        System.out.println("乱数 = " + a);
        System.out.println();
        
        //2つめの乱数を代入
        a = (int)(Math.random() * 10.0);      //整数乱数0～9
        sum = sum + a;                        //合計
        
        //2つめの乱数と合計を表示
        System.out.println("乱数 = " + a);
        System.out.println("合計 = " + sum);
        System.out.println();
        
        //3つめの乱数を代入
        a = (int)(Math.random() * 10.0);      //整数乱数0～9
        sum = sum + a;                        //合計
        
        //3つめの乱数と合計を表示
        System.out.println("乱数 = " + a);
        System.out.println("合計 = " + sum);
    }
}

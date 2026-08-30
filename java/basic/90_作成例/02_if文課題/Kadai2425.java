/******************************************************************************
 * 端数処理
 *****************************************************************************/
public class Kadai2425{
    public static void main(String[] args){
        //2つの浮動小数点型変数に値を代入
        double d1 = 0.57;
        double d2 = 0.52;
        
        //代入された値を表示
        System.out.println("1つめの値 = " + d1);
        System.out.println("2つめの値 = " + d2);
        
        //有効桁未満を四捨五入かつ整数化
        long id1 = Math.round(d1 * 100.0);
        long id2 = Math.round(d2 * 100.0);
        
        //比較と比較結果の表示
        if((id1 - id2) >= 5){
            System.out.println("差は0.05以上");
        }else{
            System.out.println("差は0.05未満");
        }
    }
}

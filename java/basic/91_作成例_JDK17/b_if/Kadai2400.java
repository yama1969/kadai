package b_if;

/******************************************************************************
 * 丸め誤差の実験
 *****************************************************************************/
public class Kadai2400{
    public static void main(String[] args){
        //2つの浮動小数点型変数に値を代入
        double d1 = 0.57;
        double d2 = 0.52;
        
        //代入した値を表示
        System.out.println("1つめの値 = " + d1);
        System.out.println("2つめの値 = " + d2);
        
        //差は0.05以上か？
        if(d1 - d2 >= 0.05){
            System.out.println("差は0.05以上");
        }else{
            System.out.println("差は0.05未満");
        }
    }
}

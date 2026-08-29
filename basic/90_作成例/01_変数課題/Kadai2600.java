/******************************************************************************
 * コンピュータで連立一次方程式を解く
 *****************************************************************************/
public class Kadai2600{
    public static void main(String[] args){
        //入力ガイド表示
        System.out.println("ax + by = c");
        System.out.println("dx + ey = f");
        System.out.println();
        
        //各係数の入力
        int a1 = Keyboard.readInt("aの値");
        int b1 = Keyboard.readInt("bの値");
        int c1 = Keyboard.readInt("cの値");
        int a2 = Keyboard.readInt("dの値");
        int b2 = Keyboard.readInt("eの値");
        int c2 = Keyboard.readInt("fの値");
        System.out.println();
        
        //入力された式の確認表示
        System.out.println(a1 + "x + " + b1 + "y = " + c1);
        System.out.println(a2 + "x + " + b2 + "y = " + c2);
        System.out.println();
        
        //解の計算
        double x = (double)(b2 * c1 - b1 * c2) / (double)(a1 * b2 - b1 * a2);
        double y = (double)(-a2 * c1 + a1 * c2) / (double)(a1 * b2 - b1 * a2);
        
        //解の表示
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}

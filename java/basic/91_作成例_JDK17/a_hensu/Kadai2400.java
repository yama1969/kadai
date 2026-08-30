package a_hensu;

/******************************************************************************
 * 三角形の面積を求める
 *****************************************************************************/
public class Kadai2400{
    public static void main(String[] args){
        //三角形の底辺と高さを入力
        int base = Keyboard.readInt("三角形の底辺[cm]");
        int height = Keyboard.readInt("三角形の高さ[cm]");
        
        //面積を計算
        double area = (double)(base * height) / 2.0;
        
        //面積の表示
        System.out.println("面積 = " + area + " [cm^2]");
    }
}

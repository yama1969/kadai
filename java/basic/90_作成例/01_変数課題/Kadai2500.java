/******************************************************************************
 * 変数を効率良く使う。三角形の面積を2回求める
 *****************************************************************************/
public class Kadai2500{
    public static void main(String[] args){
        //1回目----------------------------------------------------------------
        //底辺と高さの入力
        int base = Keyboard.readInt("1つめの三角形の底辺[cm]");
        int height = Keyboard.readInt("1つめの三角形の高さ[cm]");
        
        //面積の計算
        double area = (double)(base * height) / 2.0;
        
        //面積の表示
        System.out.println("1つめの三角形の面積 = " + area + " [cm^2]");
        System.out.println();
        
        //2回目----------------------------------------------------------------
        //底辺と高さの入力
        base = Keyboard.readInt("2つめの三角形の底辺[cm]");
        height = Keyboard.readInt("2つめの三角形の高さ[cm]");
        
        //面積の計算
        area = (double)(base * height) / 2.0;
        
        //面積の表示
        System.out.println("2つめの三角形の面積 = " + area + " [cm^2]");
    }
}

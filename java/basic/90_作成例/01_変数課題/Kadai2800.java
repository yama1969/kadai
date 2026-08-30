/******************************************************************************
 * 三角関数 ～ 投げ出したボールの飛距離を計算する
 *****************************************************************************/
public class Kadai2800{
    public static void main(String[] args){
        //ボールの投げ出す速さと角度の入力
        int speed = Keyboard.readInt("速さ[m/s]");
        int angle = Keyboard.readInt("角度[°]");
        System.out.println();
        
        //飛距離の計算
        double rpd = Math.PI / 180.0;  //度→radian変換係数(Radians Per Degree)
        double half_g = 4.9;           //重力加速度の半分
        double dist = (double)(speed * speed) * Math.sin(angle * rpd) * Math.cos(angle * rpd) / half_g;
        
        //計算された飛距離の表示
        System.out.println("飛距離 = " + dist + " [m]");
    }
}

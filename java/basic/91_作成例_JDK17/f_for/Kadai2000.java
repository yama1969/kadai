package f_for;
/******************************************************************************
 * sin波グラフを表示
 *****************************************************************************/
public class Kadai2000{
    public static void main(String[] args){
        double cycle = 100.0;
        //波の描画を5回繰り返す
        for(int i = 0; i < 5; i++){
            //波ひとつの描画(1行の描画を100回繰り返す)
            for(double x = 0.0; x < cycle; x += 1.0){
                //1行の描画
                //  yの値(=*の数)を求める
                int y = (int)(40.0 * Math.sin(x * 2.0 * Math.PI / cycle) + 40.0);
                //  *を表示
                for(int j = 0; j < y; j++){
                    System.out.print('*');
                }
                //  _を表示
                for(int j = 0; j < 80 - y; j++){
                    System.out.print('_');
                }
                //  改行
                System.out.println();
            }
        }
    }
}

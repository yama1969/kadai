package h_array;
/******************************************************************************
 * 円を描く
 *****************************************************************************/
public class Kadai3320{
    public static void main(String[] args){
        final int SIZE = 32;
        
        //絵のデータ
        char[][] pic = new char[SIZE][SIZE];
        
        for(int row = 0; row < SIZE; row++){             //行ループ
            for(int col = 0; col < SIZE; col++){         //列ループ
                pic[row][col] = '□';
            }
        }
        
        //円の中心座標
        int x = SIZE / 2;
        int y = SIZE / 2;
        
        //半径の入力
        int r = -1;
        
        do{
            r = Keyboard.readInt("半径(1～" + (SIZE / 2 - 1) + ")");
        }while(r < 1 || r > SIZE / 2 - 1);
        
        //描画
        double dt = 1.0 / (double)r;
        for(double t = 0.0; t < 2.0 * Math.PI; t += dt){
            int px = (int)((double)x + (double)r * Math.sin(t));
            int py = (int)((double)y - (double)r * Math.cos(t));
            pic[py][px] = '■';
        }
        
        //処理後の絵の表示
        for(int row = 0; row < SIZE; row++){             //行ループ
            for(int col = 0; col < SIZE; col++){         //列ループ
                System.out.print(pic[row][col]);
            }
            System.out.println();
        }
    }
}

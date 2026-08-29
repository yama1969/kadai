package h_array;
/******************************************************************************
 * 線分を描く
 *****************************************************************************/
public class Kadai3310{
    public static void main(String[] args){
        final int SIZE = 32;
        
        //絵のデータ
        char[][] pic = new char[SIZE][SIZE];
        
        for(int y = 0; y < SIZE; y++){             //行ループ
            for(int x = 0; x < SIZE; x++){         //列ループ
                pic[y][x] = '□';
            }
        }
        
        //始点・終点の入力
        int sx = -1;
        int sy = -1;
        int ex = -1;
        int ey = -1;
        
        do{
            sx = Keyboard.readInt("始点のX座標(0～" + (SIZE - 1) + ")");
        }while(sx < 0 || sx > SIZE - 1);
        do{
            sy = Keyboard.readInt("始点のY座標(0～" + (SIZE - 1) + ")");
        }while(sx < 0 || sx > SIZE - 1);
        do{
            ex = Keyboard.readInt("終点のX座標(0～" + (SIZE - 1) + ")");
        }while(ex < 0 || ex > SIZE - 1);
        do{
            ey = Keyboard.readInt("終点のY座標(0～" + (SIZE - 1) + ")");
        }while(ey < 0 || ey > SIZE - 1);
        
        //描画
        double len = Math.pow((double)(sx - ex), 2.0) + Math.pow((double)(sy - ey), 2.0);
        len = Math.pow(len, 0.5);
        
        for(double t = 0.0; t < len + 1.0; t = t + 1.0 ){
            int px = sx + (int)((double)(ex - sx) / len * t);
            int py = sy + (int)((double)(ey - sy) / len * t);
            pic[py][px] = '■';
        }
        
        //処理後の絵の表示
        for(int y = 0; y < SIZE; y++){             //行ループ
            for(int x = 0; x < SIZE; x++){         //列ループ
                System.out.print(pic[y][x]);
            }
            System.out.println();
        }
    }
}

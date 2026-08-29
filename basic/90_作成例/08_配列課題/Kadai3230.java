/******************************************************************************
 * 絵の左90°回転
 *****************************************************************************/
public class Kadai3230{
    public static void main(String[] args){
        //絵のデータ
        char[][] pic = {
            "□■■■□".toCharArray(),
            "□■□□□".toCharArray(),
            "□■■■□".toCharArray(),
            "□■□□□".toCharArray(),
            "□■□□□".toCharArray()
        };
        
        //処理前の絵の表示
        for(int y = 0; y < pic.length; y++){             //行ループ
            for(int x = 0; x < pic[y].length; x++){      //列ループ
                System.out.print(pic[y][x]);
            }
            System.out.println();
        }
        System.out.println();
        
/*
        //左上－右下軸斜め反転
        for(int y = 0; y < pic.length; y++){
            for(int x = y + 1; x < pic[y].length; x++){
                char w = pic[y][x];
                pic[y][x] = pic[x][y];
                pic[x][y] = w;
            }
        }
        //上下反転
        for(int x = 0; x < pic[0].length; x++){
            for(int y = 0; y < pic.length / 2; y++){
                char w = pic[y][x];
                pic[y][x] = pic[pic.length - 1 - y][x];
                pic[pic.length - 1 - y][x] = w;
            }
        }
*/
        
        //絵の左90°回転処理
        //  四角の辺をなぞるようにループ
        //  外側の四角から内側の四角へ狭めていくように
        for(int y = 0; y < pic.length / 2; y++){
            for(int x = y; x < pic[y].length - 1 - y; x++){
                //四角の辺の各点を反時計回りに移動
                int ruy = x;                        //右上y
                int rux = pic[ruy].length - 1 - y;  //右上x
                int rly = pic.length - 1 - y;       //右下y
                int rlx = pic[rly].length - 1 - x;  //右下x
                int lly = pic.length - 1 - x;       //左下y
                int llx = y;                        //左下x
                
                char w = pic[ruy][rux];
                pic[ruy][rux] = pic[rly][rlx];
                pic[rly][rlx] = pic[lly][llx];
                pic[lly][llx] = pic[y][x];
                pic[y][x] = w;
            }
        }
        
        //処理後の絵の表示
        for(int y = 0; y < pic.length; y++){
            for(int x = 0; x < pic[y].length; x++){
                System.out.print(pic[y][x]);
            }
            System.out.println();
        }
    }
}

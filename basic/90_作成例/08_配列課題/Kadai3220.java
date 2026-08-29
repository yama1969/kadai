/******************************************************************************
 * 絵の斜め反転
 *****************************************************************************/
public class Kadai3220{
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
        
        //絵の斜め反転処理
        for(int y = 0; y < pic.length; y++){             //行ループ
            for(int x = y + 1; x < pic[y].length; x++){  //列ループ
                //対角線上の要素を入れ替える
                char w = pic[y][x];
                pic[y][x] = pic[x][y];
                pic[x][y] = w;
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

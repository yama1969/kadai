package h_array;
/******************************************************************************
 * 絵の上下反転
 *****************************************************************************/
public class Kadai3210{
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
        
        //上下反転処理
        for(int x = 0; x < pic[0].length; x++){          //列ループ
            for(int y = 0; y < pic.length / 2; y++){     //行ループ
                //端同士の行を入れ替える
                char w = pic[y][x];
                pic[y][x] = pic[pic.length - 1 - y][x];
                pic[pic.length - 1 - y][x] = w;
            }
        }
        
        //処理後の絵の表示
        for(int y = 0; y < pic.length; y++){             //行ループ
            for(int x = 0; x < pic[y].length; x++){      //列ループ
                System.out.print(pic[y][x]);
            }
            System.out.println();
        }
    }
}

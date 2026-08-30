/******************************************************************************
 * 絵の左右反転
 *****************************************************************************/
public class Kadai3200{
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
        
        //絵の左右反転処理
        for(int y = 0; y < pic.length; y++){             //行ループ
            for(int x = 0; x < pic[y].length / 2; x++){  //列ループ
                //端同士の列を入れ替える
                char w = pic[y][x];
                pic[y][x] = pic[y][pic[y].length - 1 - x];
                pic[y][pic[y].length - 1 - x] = w;
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

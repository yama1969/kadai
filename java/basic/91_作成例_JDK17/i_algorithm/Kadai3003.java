package i_algorithm;
/******************************************************************************
 * 配列内容の左右の中の、さらに左右どちら側にあるかを表示
 *****************************************************************************/
public class Kadai3003{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //配列並べ替え(バブルソート)
        //比較終了位置のループ
        for(int end = 0; end < dat.length - 1; end++){
            //比較位置ループ
            for(int comp = dat.length - 1; comp > end; comp--){
                if(dat[comp] > dat[comp - 1]){
                    int w = dat[comp];
                    dat[comp] = dat[comp - 1];
                    dat[comp - 1] = w;
                }
            }
        }
        
        //ソート後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        //データ区分の中央のマーカー表示。
        //jの繰り返し回数の式が不明なので、よい子はマネしてはいけない。
        //何とか6,7,7の繰り返し回数を式で出すために捻り出したもの。
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6 + i - i / 2; j++){
                System.out.print("   ");
            }
            System.out.print("▲ ");
        }
        System.out.println();
        
        //探索値の入力
        int val = Keyboard.readInt("探索値");
        
        //探索値の位置の表示
        int left = 0;
        int right = dat.length - 1;
        for(int i = 0; i < 2; i++){
            int cent = (left + right) / 2;
            if(val > dat[cent]){
                System.out.print("左");
                right = cent - 1;
            }else if(val < dat[cent]){
                System.out.print("右");
                left = cent + 1;
            }else{
                System.out.print("中");
                break;
            }
        }
    }
}

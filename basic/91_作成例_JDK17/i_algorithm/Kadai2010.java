package i_algorithm;
/******************************************************************************
 * 改良バブルソート
 *****************************************************************************/
public class Kadai2010{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //ソート前の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //配列並べ替え(改良バブルソート)
        int n = 0;                          //比較回数カウント
        
        //比較終了位置ループ
        for(int end = 0; end < dat.length - 1; end++){
            int last = dat.length - 1;      //交換位置記録用
            //比較位置ループ
            for(int comp = dat.length - 1; comp > end; comp--){
                if(dat[comp] > dat[comp - 1]){
                    int w = dat[comp];
                    dat[comp] = dat[comp - 1];
                    dat[comp - 1] = w;
                    last = comp;
                }
                n++;                        //比較回数加算
            }
            end = last - 1;
        }
        
        //ソート後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //比較回数表示
        System.out.println("比較回数 = " + n + " (改良なしは435回)");
    }
}

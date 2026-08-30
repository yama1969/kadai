/******************************************************************************
 * バブルソート
 *****************************************************************************/
public class Kadai2005{
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
    }
}

package i_algorithm;
/******************************************************************************
 * 二分探索
 *****************************************************************************/
public class Kadai3010{
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
        //  添字の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(" ");
            System.out.print(i);
            if(i < 9){
                System.out.print(" ");
            }
        }
        System.out.println();
        //  値の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //探索値の入力
        int val = Keyboard.readInt("探索値");
        
        //探索実行
        int left = 0;                       //探索範囲左端添字
        int right = dat.length - 1;         //探索範囲右端添字
        boolean find = false;               //発見フラグ(true:発見)
        do{
            int cent = (left + right) / 2;  //探索範囲中央添字
            if(val > dat[cent]){
                right = cent - 1;
            }else if(val < dat[cent]){
                left = cent + 1;
            }else{
                System.out.println(val + " は添字 " + cent);
                find = true;
            }
        }while(left <= right && !find);
        if(!find){
            System.out.println(val + " はありません");
        }
    }
}

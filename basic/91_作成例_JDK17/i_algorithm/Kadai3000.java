package i_algorithm;
/******************************************************************************
 * 配列内容の左右どちらにあるかを表示
 *****************************************************************************/
public class Kadai3000{
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
        for(int i = 0; i < (dat.length - 1) / 2; i++){
            System.out.print("   ");
        }
        System.out.println("中");
        
        //探索値の入力
        int val = Keyboard.readInt("探索値");
        
        //探索値の位置の表示
        int pos = (dat.length - 1) / 2;
        
        if(val > dat[pos]){
            System.out.println("左");
        }else if(val < dat[pos]){
            System.out.println("右");
        }else{
            System.out.println("中");
        }
    }
}

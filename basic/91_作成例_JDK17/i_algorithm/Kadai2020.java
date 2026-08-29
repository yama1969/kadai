package i_algorithm;
/******************************************************************************
 * 選択ソート
 *****************************************************************************/
public class Kadai2020{
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
        
        //配列並べ替え(選択ソート)
        for(int l = 0; l < dat.length - 1; l++){
            int max_index = l;
            for(int r = l + 1; r < dat.length; r++){
                if(dat[max_index] < dat[r]){
                    max_index = r;
                }
            }
            int w = dat[l];
            dat[l] = dat[max_index];
            dat[max_index] = w;
        }
        
        //ソート後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}

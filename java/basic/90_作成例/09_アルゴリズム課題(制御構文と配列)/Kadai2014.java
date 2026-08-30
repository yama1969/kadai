/******************************************************************************
 * 最大値を配列の左端に移動する2
 *****************************************************************************/
public class Kadai2014{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //処理前の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //最大値探索
        int max = 0;         //最大値候補の添字
        for(int i = 1; i < dat.length; i++){
            if(dat[max] < dat[i]){
                max = i;
            }
        }
        int w = dat[max];
        dat[max] = dat[0];
        dat[0] = w;
        
        //処理後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}

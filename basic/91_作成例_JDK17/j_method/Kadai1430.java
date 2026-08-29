package j_method;
/******************************************************************************
 * クイックソート(再帰呼処理なし版)
 *****************************************************************************/
public class Kadai1430{
    
    /**************************************************************************
     * メインメソッド
     *************************************************************************/
    public static void main(String[] args){
        int[] dat = createArray();
        showArray(dat);
        quick_sort(dat);
        showArray(dat);
    }
    
    /**************************************************************************
     * int型配列を生成する
     * 
     * 引数 : なし
     * 戻値 : 生成したint型配列
     *************************************************************************/
    private static int[] createArray(){
        int[] dat = new int[20];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        return dat;
    }
    
    /**************************************************************************
     * 配列を表示する
     * 
     * 引数 : dat 表示するint型配列
     * 戻値 : なし
     *************************************************************************/
    private static void showArray(int[] dat){
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
    
    /**************************************************************************
     * クイックソート(再帰処理なし)
     * 
     * 引数 : dat   分けるint型配列
     * 戻値 : なし
     *************************************************************************/
    private static void quick_sort(int[] dat){
        if(dat == null){
            return;
        }
        
        //スタックの用意
        int[] stack = new int[dat.length * 2];
        int stack_pos = 0;
        
        //初期設定
        int start = 0;
        int end = dat.length - 1;
        
        //左右に分ける
        while(start < end || stack_pos != 0){
            if(start < end){
                int init_start = start;
                int init_end = end;
                
                int pivod = dat[start];
                int pivod_pos = start;
                start++;
                while(start <= end){
                    if(dat[start] <= pivod){
                        start++;
                    }else if(dat[end] > pivod){
                        end--;
                    }else{
                        int w = dat[start];
                        dat[start] = dat[end];
                        dat[end] = w;
                    }
                }
                
                //基準値を左右の境界へ移動
                int w = dat[end];
                dat[end] = dat[pivod_pos];
                dat[pivod_pos] = w;
                
                //右側範囲をスタックにプッシュ
                stack[stack_pos] = init_end;
                stack_pos++;
                stack[stack_pos] = end + 1;
                stack_pos++;
                
                //左側範囲をさらに分ける
                start = init_start;
                end--;
            }else{
                //分けられないのでスタックからポップ
                stack_pos--;
                start = stack[stack_pos];
                stack_pos--;
                end = stack[stack_pos];
            }
        }
    }
}

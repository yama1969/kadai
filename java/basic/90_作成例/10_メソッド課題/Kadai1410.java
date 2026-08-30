/******************************************************************************
 * 配列の内容を2つに分ける
 *****************************************************************************/
public class Kadai1410{
    
    /**************************************************************************
     * メインメソッド
     *************************************************************************/
    public static void main(String[] args){
        int[] dat = createArray();
        showArray(dat);
        int pivod = divideArray(dat, 0, dat.length - 1);
        showArray(dat);
        System.out.println("基準値の添字は " + pivod);
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
     * 配列の一部の内容を大小で分ける
     * 
     * 引数 : dat   分けるint型配列
     *        start 分ける部分配列の先頭添字
     *        end   分ける部分配列の最後添字
     * 戻値 : 基準値の置かれた添字。部分配列のサイズが1以下なら-1。
     *************************************************************************/
    private static int divideArray(int[] dat, int start, int end){
        if(dat == null || start < 0 || end > dat.length - 1){
            return -1;
        }
        if(start >= end){
            return -1;
        }
        
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
        int w = dat[end];
        dat[end] = dat[pivod_pos];
        dat[pivod_pos] = w;
        return end;
    }
}

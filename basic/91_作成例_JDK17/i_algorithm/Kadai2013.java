package i_algorithm;
/******************************************************************************
 * 最大値の探索
 *****************************************************************************/
public class Kadai2013{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //最大値探索
        int max = 0;         //最大値候補の添字
        for(int i = 1; i < dat.length; i++){
            if(dat[max] < dat[i]){
                max = i;
            }
        }
        System.out.println("最大値の添字 = " + max);
        
        //結果表示
        for(int i = 0; i < dat.length; i++){
            if(i == max){
                System.out.print("○ ");
            }else{
                System.out.print("   ");
            }
        }
        System.out.println();
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}

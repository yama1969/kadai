package i_algorithm;
/******************************************************************************
 * ハッシュ探索 ～ シノニム対策
 *****************************************************************************/
public class Kadai3050{
    public static void main(String[] args){
        final int SIZE = 15;
        final int NODAT = 99;
        
        //配列の初期化
        int[][] dat = new int[SIZE][SIZE];
        for(int i = 0; i < dat.length; i++){
            for(int j = 0; j < dat[i].length; j++){
                dat[i][j] = NODAT;
            }
        }
        
        //整数乱数を生成し、ハッシュテーブルへ格納する
        for(int i = 0; i < SIZE; i++){
            int num = (int)(Math.random() * 80.0) + 10;
            int hash = num % SIZE;
            int sino = 0;
            while(sino < dat[hash].length && dat[hash][sino] != NODAT){
                sino++;
            }
            if(sino < dat[hash].length){
                dat[hash][sino] = num;
            }
        }
        
        //配列の表示
        for(int i = 0; i < dat.length; i++){
            if(i < 10){
                System.out.print(" ");
            }
            System.out.print(i + " : ");
            
            for(int j = 0; j < dat[i].length && dat[i][j] != NODAT; j++){
                System.out.print(dat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        //探索値を入力
        int val = Keyboard.readInt("探索値");
        int hash = val % SIZE;
        int sino = 0;
        while(sino < dat[hash].length && dat[hash][sino] != NODAT && dat[hash][sino] != val){
            sino++;
        }
        if(sino < dat[hash].length && dat[hash][sino] == val){
            System.out.println(val + " は添字 [" + hash + "][" + sino + "]");
        }else{
            System.out.println("その値はありません。");
        }
    }
}

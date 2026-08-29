/******************************************************************************
 * ハッシュ探索
 *****************************************************************************/
public class Kadai3040{
    public static void main(String[] args){
        final int SIZE = 30;
        final int NODAT = 99;
        
        //配列の初期化
        int[] dat = new int[SIZE];
        for(int i = 0; i < dat.length; i++){
            dat[i] = NODAT;
        }
        
        //整数乱数を生成し、ハッシュテーブルへ格納する
        for(int i = 0; i < SIZE; i++){
            int num = (int)(Math.random() * 80.0) + 10;
            int hash = num % SIZE;
            if(dat[hash] == NODAT){
                dat[hash] = num;
            }
        }
        
        //配列の表示
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
            if(dat[i] == NODAT){
                System.out.print("   ");
            }else{
                System.out.print(dat[i] + " ");
            }
        }
        System.out.println();
        System.out.println();
        
        //探索値を入力
        int val = Keyboard.readInt("探索値");
        int hash = val % SIZE;
        if(dat[hash] == val){
            System.out.println(val + " は添字 " + hash);
        }else{
            System.out.println("その値はありません。");
        }
    }
}

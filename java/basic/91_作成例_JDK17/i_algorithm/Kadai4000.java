package i_algorithm;
/******************************************************************************
 * スタック(FILO)の実現に向けて
 * 配列に順番にデータを入れる
 *****************************************************************************/
public class Kadai4000{
    public static void main(String[] args){
        //配列とデータ格納位置の初期化
        int[] stack = new int[10];
        int pos = 0;                 //データ数(次の格納位置)
        
        //データ操作
        String msg = "値[1以上:データ, 0:表示, 負:終了]";
        int dat = 0;
        while((dat = Keyboard.readInt(msg)) > -1){
            if(dat == 0){
                //データの表示
                for(int i = 0; i < pos; i++){
                    System.out.print(" " + stack[i]);
                }
                System.out.println();
            }else{
                //データの格納
                if(pos >= stack.length){
                    System.out.println("もう値は入りません。");
                }else{
                    stack[pos] = dat;
                    pos++;
                }
            }
            System.out.println("値個数 " + pos);
            System.out.println();
        }
    }
}

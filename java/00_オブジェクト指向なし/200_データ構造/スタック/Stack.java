/*
  スタックの動作原理をホワイトボードで説明。
  その後、チーム毎にアクティビティ図を作成。
  アクティビティ図をチーム間で交換し、チームでコーディング。

  整数値データを入力すると、スタックに格納される。
  0を入力すると、1件のデータがスタックから取り出され、表示される。
  表示済みデータは捨てられる。
*/

public class Stack{
    public static void main(String[] args){
        int[] dat = new int[5];  //スタック
        int   sp;                //スタックポインタ
        int   in;                //入力データ
        
        sp = -1;
        while(true){
            in = InputKey.inNum();
            if(in == 0){
                //データ表示
                if(sp == -1){
                    System.out.println("データがありません。");
                }else{
                    System.out.println(dat[sp]);
                    sp -= 1;
                }
            }else{
                //データ格納
                if(sp >= 4){
                    System.out.println("スタックはいっぱいです。");
                }else{
                    sp++;
                    dat[sp] = in;
                }
            }
        }
    }
}

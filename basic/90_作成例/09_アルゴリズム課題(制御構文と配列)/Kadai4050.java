/******************************************************************************
 * キュー(FIFO)の実現に向けて
 * 配列に順番にデータを入れる
 *****************************************************************************/
public class Kadai4050{
    public static void main(String[] args){
        //初期化
        int[] queue = new int[10]; //データ格納用配列
        int pos = 4;               //先頭データの格納位置
        int cnt = 0;               //現在のデータ数
        
        //データ操作
        String msg = "値[1以上:データ, 0:表示, 負:終了]";
        int cmd = 0;
        while((cmd = Keyboard.readInt(msg)) > -1){
            if(cmd == 0){
                //データ表示
                for(int i = 0; i < cnt; i++){
                    System.out.print(" " + queue[(pos + i) % queue.length]);
                }
                System.out.println();
            }else{
                //データ格納
                if(cnt >= queue.length){
                    System.out.println("もう値は入りません。");
                }else{
                    queue[(pos + cnt) % queue.length] = cmd;
                    cnt++;
                }
            }
            System.out.println("値個数 " + cnt);
            System.out.println();
        }
    }
}

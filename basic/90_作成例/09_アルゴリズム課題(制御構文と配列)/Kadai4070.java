/******************************************************************************
 * キュー(FIFO)の実現
 *****************************************************************************/
public class Kadai4070{
    public static void main(String[] args){
        //初期化
        int[] queue = new int[10];  //データ格納用配列
        int pos = 0;                //データ先頭位置
        int cnt = 0;                //データ格納数
        
        //データ操作
        String msg = "値[1以上:データ, 0:表示, -1:取出し, -2以下:終了]";
        int cmd = 0;
        while((cmd = Keyboard.readInt(msg)) > -2){
            if(cmd > 0){
                //データの入力
                if(cnt >= queue.length){
                    System.out.println("もう値は入りません。");
                }else{
                    queue[(pos + cnt) % queue.length] = cmd;
                    cnt++;
                }
            }else if(cmd == 0){
                //データの表示
                for(int i = 0; i < cnt; i++){
                    System.out.print(" " + queue[(pos + i) % queue.length]);
                }
                System.out.println();
            }else{
                //データの取り出し
                if(cnt <= 0){
                    System.out.println("もう値はありません。");
                }else{
                    System.out.println(queue[pos]);
                    pos = ++pos % queue.length;
                    cnt--;
                }
            }
            System.out.println("値個数 " + cnt);
            System.out.println();
        }
    }
}

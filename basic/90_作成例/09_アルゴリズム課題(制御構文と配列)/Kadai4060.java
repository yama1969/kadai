/******************************************************************************
 * キュー(FIFO)の実現に向けて
 * 配列から順にデータを取り出す
 *****************************************************************************/
public class Kadai4060{
    public static void main(String[] args){
        //初期化
        int[] queue = new int[10];                       //データ格納用配列
        for(int i = 0; i < queue.length; i++){
            queue[i] = (int)(Math.random() * 90.0) + 10;
        }
        int pos = 4;                                     //データ先頭位置
        int cnt = 10;                                    //データ数
        
        //データ操作
        String msg = "値[0以上:表示, -1:取出し, -2以下:終了]";
        int cmd = 0;
        while((cmd = Keyboard.readInt(msg)) > -2){
            if(cmd >= 0){
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

package e_while;

/******************************************************************************
 * 3秒待機
 *****************************************************************************/
public class Kadai1900{
    public static void main(String[] args){
        //現在時刻の取得
        long end = System.currentTimeMillis() + 3000L;  //待機終了時刻の計算
        long current = 0L;                              //現在時刻
        
        //待機ループ
        while(current < end){                           //終了時刻前なら繰返し
            System.out.println("待機中");
            current = System.currentTimeMillis();       //現在時刻を取得し直し
        }
        
        //ループを抜けたときが終了のとき
        System.out.println("3秒経過しました。");
    }
}

package e_while;

/******************************************************************************
 * 10秒以内に何問の足し算を正答できるか
 *****************************************************************************/
public class Kadai2400{
    public static void main(String[] args){
        //変数準備
        int cnt = -1;  //正答回数
        int a = 0;     //足し算問題の値a
        int b = 0;     //              b
        int ans = 0;   //足し算回答
        
        //現在時刻から10秒後の時刻を得る
        long time = System.currentTimeMillis() + 10000L;
        
        //足し算回答開始
        while( !(time < System.currentTimeMillis()) && ans == a + b ){
            //終了時刻前かつ回答が正しい場合、繰り返し
            cnt++;
            a = (int)(Math.random() * 80.0) + 10;             //足し算問題作成
            b = (int)(Math.random() * (double)(90 - a)) + 10;
            ans = Keyboard.readInt(a + " + " + b);            //回答入力
        }
        System.out.println();
        
        //結果表示
        System.out.println(cnt + "回正答しました。");
    }
}

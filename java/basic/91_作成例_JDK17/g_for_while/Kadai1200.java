package g_for_while;
/******************************************************************************
 * 秒カウンタ
 *****************************************************************************/
public class Kadai1200{
    public static void main(String[] args){
        //変数の準備
        int s = 0;                                     //秒
        long end = System.currentTimeMillis() + 1000L; //次の秒表示時刻
        System.out.println(s);                         //最初の秒表示
        
        //秒表示を10回繰り返す
        for(int i = 0; i < 10; i++){
            while(System.currentTimeMillis() < end){   //表示時刻まで空ループ
            }
            //次の秒表示時刻
            end += 1000L;
            
            //秒加算と表示
            s++;
            System.out.println(s);
        }
    }
}

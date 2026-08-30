package g_for_while;
/******************************************************************************
 * サイコロの特定の目が出るまでの回数
 *****************************************************************************/
public class Kadai1100{
    public static void main(String[] args){
        //特定の目が出るまでの回数の合計
        int cnt_sum = 0;
        
        //試行を100回繰り返す
        for(int i = 0; i < 100; i++){
            int eye = 0;    //サイコロの目
            int cnt = 0;    //特定の目が出るまでにサイコロを振った回数
            do{
                eye = (int)(Math.random() * 6.0) + 1; //サイコロを振る
                cnt++;                                //振った回数+1
            }while(eye != 1);                         //1が出るまで振る
            cnt_sum += cnt; //振った回数を合計
            
            //振った回数の平均値を表示
            System.out.println((double)cnt_sum / (double)(i + 1));
        }
    }
}

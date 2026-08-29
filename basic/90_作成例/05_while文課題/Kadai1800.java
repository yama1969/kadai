/******************************************************************************
 * サイコロを、1の目が出るまで振り続ける
 *****************************************************************************/
public class Kadai1800{
    public static void main(String[] args){
        int count = 0;                             //サイコロを振った回数
        int eye = 0;                               //サイコロの目
        while(eye != 1){                           //目が1でなければ繰り返す
            eye = (int)(Math.random() * 6.0) + 1;  //サイコロを振る
            System.out.println(eye);               //出た目の表示
            count++;                               //振った回数加算
        }
        
        //結果表示
        System.out.println();
        System.out.println("回数 = " + count);
    }
}

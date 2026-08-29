/******************************************************************************
 * 3回以内で当てるまで、数当てを続ける
 *****************************************************************************/

public class Kadai2300{
    public static void main(String[] args){
        int cnt = 1;  //正答までの回数
        do{
            System.out.println("数当て開始。3回以内に当てて下さい。");
            cnt = 1;                                      //回答回数初期化
            int ans = (int)(Math.random() * 5.0) + 1;     //正解の値を作る
            int num = Keyboard.readInt("1～5のどれか");   //初回回答入力
            while(ans != num){                            //正答まで繰り返し
                System.out.println("正しくない");
                System.out.println();
                cnt++;
                num = Keyboard.readInt("1～5のどれか");   //次の回答入力
            }
            System.out.println("正しい。" + cnt + "回入力しました。");
            System.out.println();
            System.out.println();
        }while(cnt > 3);                            //3回以内でなければもう一度
    }
}

package e_while;

/******************************************************************************
 * 数当て
 *****************************************************************************/
public class Kadai2200{
    public static void main(String[] args){
        int cnt = 1;                                //回答回数
        int ans = (int)(Math.random() * 5.0) + 1;   //正解の値
        int num = Keyboard.readInt("1～5のどれか"); //初回回答入力
        while(ans != num){                          //不正解の間繰り返し
            System.out.println("正しくない");
            System.out.println();
            cnt++;
            num = Keyboard.readInt("1～5のどれか"); //次の回答入力
        }
        System.out.println("正しい。" + cnt + "回入力しました。");
    }
}

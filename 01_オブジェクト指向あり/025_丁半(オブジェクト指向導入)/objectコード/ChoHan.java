/*
  サイコロの丁半を当てるプログラム
*/
import java.util.Scanner;

public class ChoHan{
    public static void main(String[] args) throws Exception{
        final int DICE_NUM = 2;
        
        //準備(各オブジェクト=インスタンスの生成)
        Scanner in = new Scanner(System.in);   //入力
        WinLose wl = new WinLose();            //勝敗
        Dice[] sai = new Dice[DICE_NUM];
        for(int i = 0; i < sai.length; i++){
            sai[i] = new Dice();               //サイコロ
        }
        
        //本処理
        System.out.print("0:丁 1:半 その他:終了 => ");
        int ch = in.nextInt();
        in.nextLine();
        
        while(ch == 0 || ch == 1){
            int sum = 0;
            for(Dice d : sai){                 //サイコロを振る
                sum += d.nextEye();
            }
            boolean result = Dice.judgeChoHan(ch); //当たりはずれ判定
            wl.countWinLose(result);           //勝敗カウント
            
            //画面表示
            String chohan = "半";
            if(sum % 2 == 0){
                chohan = "丁";
            }
            for(Dice d : sai){
                System.out.print(d.getEye() + " ");
            }
            System.out.println("の" + chohan);
            System.out.printf("%d勝%d敗\n\n", wl.getWin(), wl.getLose());
            
            //次の入力
            System.out.print("0:丁 1:半 その他:終了 => ");
            ch = in.nextInt();
            in.nextLine();
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DiceGame{
    public static void main(String[] args){
        System.out.println("サイコロを振るよ。コロコロ・・・");
        System.out.println("はい！出た目を当ててね。");
        int diceNum = (int)(Math.random() * 6) + 1;
        int inNum = 0;
        int count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println();
            System.out.print("1～6の数を入れて。＞");
            try{
                String line = reader.readLine();
                inNum = Integer.parseInt(line);
            }catch(IOException e){
                System.out.println("入力エラーだから中断するね。");
                System.exit(1);
            }catch(NumberFormatException e){
                System.out.println("1～6の数だってば～！");
                continue;
            }
            
            if(inNum < 1 || inNum > 6){
                System.out.println("サイコロなんだから、1～6でしょ。もぉ～！！");
                continue;
            }
            
            if(inNum > diceNum){
                System.out.println("もっと小さい数だよ。");
            }else if(inNum < diceNum){
                System.out.println("もっと大きい数だよ。");
            }else{
                System.out.println("すごい！大当たり～～～！");
                break;
            }
            
            if(++count > 5){
                System.out.println("ダメダメ！　6回やっても当たんないなんて、ありえない～～！");
                break;
            }
        }
    }
}

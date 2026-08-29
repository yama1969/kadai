import java.io.*;

/**
 * 九九の問題を出題するプログラム
 * ユーザが回答として空入力するまで続ける。
 * 最後に成績表示をする。
 */
public class Kuku4{
    public static void main(String[] args){
        int ques_num = 0;     //問題数
        int correct = 0;      //正解数
        boolean roop = true;  //出題を続ける
        
        //-----1問出題を10回繰り返す--------------------------------------------
        while(roop){
            //-----問題を作る---------------------------------------------------
            int a = (int)(Math.random() * 9.0) + 1;
            int b = (int)(Math.random() * 9.0) + 1;
            
            //-----問題を表示する-----------------------------------------------
            System.out.print("[第" + (ques_num + 1) + "問] " + a + " × " + b + " ＝ ");
            
            //-----回答を入力する-----------------------------------------------
            int ans = 0;
            boolean err = true;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                String line = reader.readLine();
                if(line.equals("")){
                    roop = false;
                }
                ans = Integer.parseInt(line);
                err = false;
            }catch(IOException e){
                System.out.println(e);
            }catch(NumberFormatException e){
                System.out.println("数値を入力してください");
            }
            
            //-----正誤判定-----------------------------------------------------
            if(!err){
                ques_num++;
                if(ans == a * b){
                    System.out.println("正解");
                    correct++;
                }else{
                    System.out.println("まちがい");
                }
            }
            
            System.out.println();  //次の問題と1行あける
        }
        
        //-----成績表示---------------------------------------------------------
        System.out.println(ques_num + "問中 " + correct + "問正解でした。");
        System.out.println("正答率は 約" + (correct * 100 / ques_num) + "% です。");
    }
}

import java.io.*;

/**
 * 九九の問題を10問出題するプログラム
 */
public class Kuku2{
    public static void main(String[] args){
        //-----1問出題を10回繰り返す--------------------------------------------
        for(int i = 0; i < 10; i++){
            //-----問題を作る---------------------------------------------------
            int a = (int)(Math.random() * 9.0) + 1;
            int b = (int)(Math.random() * 9.0) + 1;
            
            //-----問題を表示する-----------------------------------------------
            System.out.print("[第" + (i + 1) + "問] " + a + " × " + b + " ＝ ");
            
            //-----回答を入力する-----------------------------------------------
            int ans = 0;
            boolean err = true;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                String line = reader.readLine();
                ans = Integer.parseInt(line);
                err = false;
            }catch(IOException e){
                System.out.println(e);
            }catch(NumberFormatException e){
                System.out.println("数値を入力してください");
            }
            
            //-----正誤判定-----------------------------------------------------
            if(!err){
                if(ans == a * b){
                    System.out.println("正解");
                }else{
                    System.out.println("まちがい");
                }
            }
            
            System.out.println();  //次の問題と1行あける
        }
    }
}

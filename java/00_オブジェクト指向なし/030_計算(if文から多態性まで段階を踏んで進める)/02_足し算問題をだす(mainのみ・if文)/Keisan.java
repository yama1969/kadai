import java.io.*;

public class Keisan{
    public static void main(String[] args){
        final int MIN = 1;       //問題に使う数の最小値
        final int MAX = 9;       //問題に使う数の最大値
        
        boolean error = false;   //エラー発生フラグ(true:発生)
        
        //問題を作る
        int a = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
        int b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
        int ans = 0;
        
        //問題を表示する
        System.out.print(a + " + " + b + " = ");
        
        //回答を入力する
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String line = reader.readLine();
            ans = Integer.parseInt(line);
        }catch(NumberFormatException e){
            error = true;
        }catch(IOException e){
            error = true;
        }
        
        //正誤を判定する
        if(!error){
            if(ans == a + b){
                System.out.println("正解！");
            }else{
                System.out.println("まちがい。。。");
            }
        }
    }
}

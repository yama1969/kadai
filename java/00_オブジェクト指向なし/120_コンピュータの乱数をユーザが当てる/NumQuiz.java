import java.io.*;

public class NumQuiz{
    public static void main(String[] args){
        //-----数を決める-------------------------------------------------------
        int num = (int)(Math.random() * 10000.0);
        
        //-----最初の画面表示---------------------------------------------------
        System.out.println("私が0～9999の数を１つ思い浮かべました。");
        System.out.println("その数を当ててください^^");
        
        //-----ユーザが予想した数を入力→判定(正解まで繰り返し)-----------------
        int stat = 0;         //0:入力中 1:正解 2:不正入力 3:異常終了
        while(stat == 0){
            //-----入力---------------------------------------------------------
            System.out.print("0～9999の数 > ");
            int ans = 0;
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line = reader.readLine();
                ans = Integer.parseInt(line);
            }catch(IOException e){
                System.out.println(e);
                stat = 3;
            }catch(NumberFormatException e){
                stat = 2;
            }
            
            //-----判定---------------------------------------------------------
            switch(stat){
            case 0:           //入力中
                if(num < ans){
                    System.out.println("もっと小さいです。");
                }else if(num > ans){
                    System.out.println("もっと大きいです。");
                }else{
                    System.out.println("あたりです！おめでとう！");
                    stat = 1;
                }
                break;
                
            case 2:           //不正入力
                System.out.println("0～9999の数を入力してくださいね。");
                stat = 0;
                break;
                
            default:          //その他
                System.out.println("異常事態発生！終了します。");
                break;
            }
        }
    }
}

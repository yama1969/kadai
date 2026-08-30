import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HitAndBlow{
    public static void main(String[] args){
        final int PLACE = 4;  //デフォルト桁数
        int place = PLACE;    //問題の桁数
        
        //初期表示
        System.out.println("ヒットアンドブローというゲームです。");
        System.out.println("詳しい説明を読みたかったら、「help」と入力してね。");
        System.out.println("では始めますよ～！");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //桁数指定
        System.out.println();
        System.out.print("まずは桁数を指定してね。＞");
        try{
            String line = reader.readLine();
            place = Integer.parseInt(line);
            if(place < 1 || place > 9){
                System.out.println("いくらなんでも、そんな桁数はないでしょ。");
                System.out.println("しょーがないから、" + PLACE + "桁でいくよ。");
                place = PLACE;
            }
        }catch(IOException e){
            keyError();
        }catch(NumberFormatException e){
            System.out.println("それ、数じゃないじゃん。");
            System.out.println("しょーがないから、" + PLACE + "桁でいくよ。");
        }
        
        //問題作成
        int[] ans = new int[place];
        for(int i = 0; i < ans.length; i++){
            ans[i] = (int)(Math.random() * 10);
        }
        
        //回答入力
        int[] num = new int[place];
        while(true){
            System.out.println();
            System.out.print(place + "桁の整数を入れてね ＞ ");
            try{
                String line = reader.readLine();
                
                if(line.equals("ans")){  //隠しコマンド
                    showAns(ans);
                }
                
                if(line.length() != place){
                    System.out.println(place + "桁じゃなきゃダメだよ。");
                    continue;
                }
                for(int i = 0; i < num.length; i++){
                    num[i] = Integer.parseInt(line.substring(i, i + 1));
                }
            }catch(IOException e){
                keyError();
            }catch(NumberFormatException e){
                System.out.println("数じゃなきゃダメだよ～～。");
                continue;
            }
            
            //回答判定
            int hit = 0;
            int blow = 0;
            for(int i = 0; i < num.length; i++){
                boolean b_hit = false;
                boolean b_blow = false;
                for(int j = 0; j < ans.length; j++){
                    if(num[i] == ans[j]){
                        if(i == j){
                            b_hit = true;
                            break;
                        }else{
                            b_blow = true;
                        }
                    }
                }
                if(b_hit){
                    hit++;
                }else if(b_blow){
                    blow++;
                }
            }
            
            //結果表示
            System.out.println(hit + " Hit, " + blow + " Blowだよ。");
            if(hit == PLACE){
                System.out.println("おめでと～～！！！");
                break;
            }
        }
    }
    
    private static void keyError(){
        System.out.println("キーボードエラーだから、ゲームを中断するね。");
        System.exit(1);
    }
    
    private static void showAns(int[] ans){
        for(int i = 0; i < ans.length; i++){
            System.out.print(ans[i]);
        }
        System.out.println();
    }
}

import java.io.*;

public class Keisan{
    public static void main(String[] args){
        final int QUES_NUM = 10; //出題数
        final int MIN = 1;       //問題に使う数の最小値
        final int MAX = 9;       //問題に使う数の最大値
        
        int correct = 0;         //正答数
        boolean error = false;   //エラー発生フラグ(true:発生)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //足し算問題を繰り返し出す。
        for(int i = 0; i < QUES_NUM; i++){
            //問題を作る
            int a = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            int b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            int ans = 0;
            
            boolean malinput = false;
            do{
                //問題を表示
                System.out.print("[第" + (i + 1) + "問] " + a + " + " + b + " = ");
                
                //回答入力
                try{
                    malinput = false;
                    String line = reader.readLine();
                    ans = Integer.parseInt(line);
                }catch(NumberFormatException e){
                    malinput = true;
                }catch(IOException e){
                    error = true;
                }
            }while(malinput);
            
            //正誤判定
            if(!error){
                if(ans == a + b){
                    System.out.println("正解！");
                    correct++;
                }else{
                    System.out.println("まちがい。。。");
                }
            }else{
                i = QUES_NUM;
            }
        }
        
        //結果表示
        System.out.println();
        if(!error){
            System.out.println(QUES_NUM + "問中、" + correct + "問正解。");
            System.out.println("正答率は" + (int)((double)correct / QUES_NUM * 100) + "%でした。");
        }else{
            System.out.println("キーボードエラーのため、プログラムを中断します。");
        }
    }
}

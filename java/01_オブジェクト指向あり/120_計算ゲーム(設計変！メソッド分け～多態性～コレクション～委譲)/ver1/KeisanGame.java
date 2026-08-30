import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 計算ゲームのクラス（１クラス構成）
 ゲームの初期化、開始メッセージ、問題表示と入力、結果表示・・・全てを処理。
*/
public class KeisanGame{
    private static final int NUM_QUESTION = 10;                                           //問題数
    
    //-----コンストラクタ：各メンバの初期化とゲーム進行管理メソッドの呼び出し-------------
    public KeisanGame(){
        mainGame();
    }
    
    //-----ゲーム進行管理-----------------------------------------------------------------
    public void mainGame(){
        long startTime;                                                                   //開始時刻[ms]
        long endTime;                                                                     //終了時刻[ms]
        int goodAns = 0;                                                                  //正答数
        
        startGame();                                                                      //ゲーム開始処理
        System.out.println();
        
        startTime = System.currentTimeMillis();                                           //問題表示処理
        for(int i = 0; i < NUM_QUESTION; i++){
            goodAns += showQuestion();
        }
        endTime = System.currentTimeMillis();
        System.out.println();
        
        endGame(startTime, endTime, goodAns);                                             //ゲーム終了処理
    }
    
    //-----ゲーム開始---------------------------------------------------------------------
    private void startGame(){
        System.out.println("これから足し算の問題を" + NUM_QUESTION + "問出します。");
        System.out.println("準備が出来たら、Enterキーを押してください！");
        inputNum();
    }
    
    //-----問題１問表示-------------------------------------------------------------------
    private int showQuestion(){
        int a = (int)(Math.random() * 10);                                                //問題に使用する数値は0～9の整数
        int b = (int)(Math.random() * 10);
        
        System.out.printf("%2d + %2d = ? ",a,b);
        int ans = inputNum();
        
        if(ans == a + b){
            System.out.println("正解！");
            return 1;
        }
        System.out.println("まちがい・・・");
        return 0;
    }
    
    //-----ゲーム終了---------------------------------------------------------------------
    private void endGame(long startTime, long endTime, int goodAns){
        double s_time = (double)(endTime- startTime) / 1000.0;
        s_time = floorDecimal2(s_time);                                                   //時間を小数第2位の秒に変換
        
        double score = s_time + (double)(NUM_QUESTION - goodAns) * 2.0;                   //所要時間には不正解する度に2秒のペナルティが付く
        score = score / (double)goodAns;                                                  //スコア＝所要時間÷正答数。
        score = floorDecimal2(score);
        
        System.out.println("正答数：" + goodAns + "問／" + NUM_QUESTION + "問");
        System.out.println("時間　：" + s_time + "秒");
        System.out.println("スコア：" + score);
    }
    
    //-----キーボードからの数値入力(異常時にはintの最小値を返す)--------------------------
    private int inputNum(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));         //キー入力API
        int num = Integer.MIN_VALUE;
        try{
            String line = in.readLine();
            num = Integer.parseInt(line);
        }catch(IOException e){                                                            //異常時にはintの最小値を返す
            num = Integer.MIN_VALUE;
        }catch(NumberFormatException e){
            num = Integer.MIN_VALUE;
        }
        return num;
    }
    
    //-----小数を小数点第2位で切り下げる--------------------------------------------------
    private double floorDecimal2(double num){
        return Math.floor(num * 100.0) / 100.0;
    }
    
    //-----main()：KeisanGameインスタンスの生成のみ---------------------------------------
    public static void main(String[] args){
        KeisanGame kg = new KeisanGame();
    }
}

package janken.motion.atodashi;

import janken.frame.JankenMotion;
import janken.frame.JankenUI;
import janken.frame.JankenHand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 「あと出しじゃんけん」の動作を実現するクラスです。
 */
public class AtodashiMotion implements JankenMotion, Runnable{
    private JankenUI ui = null;
    
    private boolean roll = false;    //回転中
    private boolean answer = true;   //回答済み
    private long stime = 0L;         //回答開始時刻
    private long etime = 0L;         //回答終了時刻
    
    private double best = 10.0;      //ベストタイム
    
    private JankenHand aiteHand = null;
    
    /**
     唯一のコンストラクタ。今までのベストタイムをファイルから読み出します。
     */
    public AtodashiMotion(){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("janken/motion/atodashi/best.txt"));
            String dat = br.readLine();
            if(dat != null){
                best = Double.parseDouble(dat);
            }
        }catch(FileNotFoundException e){
        }catch(IOException e){
        }catch(NumberFormatException e){
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                }
            }
        }
    }
    
    /**
     ユーザーインターフェースモジュールの境界クラスのインスタンスをセットします。<br>
     このメソッドはJankenMotionインターフェースの実装です。
     @param ui ユーザーインターフェースモジュールの境界クラスのインスタンス
     */
    @Override
    public void setUI(JankenUI ui){
        this.ui = ui;
    }
    
    /**
     ゲームを開始します。<br>
     このメソッドはJankenMotionインターフェースの実装です。
     */
    @Override
    public void startGame(){
        ui.setMessage("どれかの手を選択すると、開始します。ベストタイム " + best + "秒");
    }
    
    /**
     プレイヤーが手を選択したときの動作です。<br>
     コンピュータの手が回転していないときには、回転を開始します。
     コンピュータの手の回転が停止した後の最初の選択のときには、結果を表示します。
     このメソッドはJankenMotionインターフェースの実装です。
     @param hand プレイヤーが選択した手
     */
    @Override
    public void selectHand(JankenHand hand){
        if(roll){
            return;
        }
        
        if(answer){
            roll = true;
            ui.clearMessage();
            new Thread(this).start();
            return;
        }
        
        etime = System.currentTimeMillis();
        
        double dtime = (double)(etime - stime) / 1000.0;
        String result = dtime + "秒";
        if(aiteHand == hand){
            result = "あいこです。(参考：" + result + ")";
        }else if((aiteHand == JankenHand.GUU && hand == JankenHand.CHOKI) ||
                 (aiteHand == JankenHand.CHOKI && hand == JankenHand.PAA) ||
                 (aiteHand == JankenHand.PAA && hand == JankenHand.GUU) ){
            result = "負けの手です。(参考：" + result + ")";
        }else{
            if(dtime < best){
                result += "　ベストタイム更新！";
                best = dtime;
                
                try{
                    PrintWriter pw = new PrintWriter(new FileWriter("janken/motion/atodashi/best.txt"));
                    pw.println(best);
                    pw.flush();
                }catch(IOException e){
                }
            }
        }
        
        ui.setMessage(result);
        answer = true;
    }
    
    /**
     コンピュータの手の表示を、ランダムな時間だけ回転します。<br>
     回転終了後、コンピュータの手をランダムに選択し、再度表示します。
     このメソッドは別スレッドで実行するためのものです。Threadクラスのインスタンスのstart()メソッドから呼ばれるものなので、
     直接呼び出すことは禁じられています。
     */
    @Override
    public void run(){
        long time = (long)(Math.random() * 2000.0) + 1000L;
        ui.startRoll();
        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
        }
        ui.stopRoll();
        roll = false;
        
        int r = (int)(Math.random() * 3.0);
        switch(r){
        case 0:
            aiteHand = JankenHand.GUU;
            break;
        case 1:
            aiteHand = JankenHand.CHOKI;
            break;
        case 2:
            aiteHand = JankenHand.PAA;
            break;
        }
        ui.showHand(aiteHand);
        
        answer = false;
        stime = System.currentTimeMillis();
        etime = 0L;
    }
}

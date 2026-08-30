package janken.motion.normal;

import janken.frame.JankenHand;
import janken.frame.JankenUI;
import janken.frame.JankenMotion;

/**
 普通のじゃんけんの動作を実現するクラスです。
 */
public class NormalMotion implements JankenMotion{
    private JankenUI ui = null;
    
    private boolean roll = false;
    
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
     コンピュータの手の表示の回転を始めます。
     このメソッドはJankenMotionインターフェースの実装です。
     */
    @Override
    public void startGame(){
        ui.setMessage("じゃーんけーん・・・");
        roll = true;
        ui.startRoll();
    }
    
    /**
     プレイヤーが手を選択したときの動作です。<br>
     コンピュータの手が回転していないときは、回転を開始します。
     コンピュータの手の回転しているときは、コンピュータの手をランダムに選択し、回転を止めます。
     そのうえで勝敗判定をします。
     このメソッドはJankenMotionインターフェースの実装です。
     @param hand プレイヤーが選択した手
     */
    @Override
    public void selectHand(JankenHand hand){
        if(!roll){
            startGame();
            return;
        }
        
        ui.stopRoll();
        roll = false;
        
        int aite = (int)(Math.random() * 3.0);
        switch(aite){
        case 0:
            ui.showHand(JankenHand.GUU);
            break;
        case 1:
            ui.showHand(JankenHand.CHOKI);
            break;
        case 2:
            ui.showHand(JankenHand.PAA);
            break;
        }
        
        int jibun = 0;
        switch(hand){
        case GUU:
            jibun = 0;
            break;
        case CHOKI:
            jibun = 1;
            break;
        case PAA:
            jibun = 2;
            break;
        }
        
        int result = (aite - jibun + 2) % 3;
        switch(result){
        case 0:
            ui.setMessage("ぽん！　あなたの勝ち！");
            break;
        case 1:
            ui.setMessage("ぽん！　あなたの負け。");
            break;
        case 2:
            ui.setMessage("ぽん！　あいこ");
            break;
        }
    }
    
}

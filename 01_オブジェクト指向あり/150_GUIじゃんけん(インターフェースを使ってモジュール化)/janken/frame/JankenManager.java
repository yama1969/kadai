package janken.frame;

/**
 じゃんけんゲームの、ユーザーインターフェースモジュールとゲーム動作モジュールとの橋渡しをするクラスです。<br>
 ユーザインターフェースモジュールの境界クラスは、JankenUIインターフェースを実装しなくてはなりません。
 また、ゲーム動作モジュールの境界クラスは、JankenMotionインターフェースを実装しなくてはなりません。
 JankenManagerクラスは、ユーザーインターフェースモジュールの境界クラスとゲーム動作モジュールとの境界クラスの
 参照を互いに設定し、相互が必要な時に必要なアクセスをすることができるようにします。<br>
 このクラスはインスタンス化できません。
 */
public class JankenManager{
    private JankenManager(){
    }
    
    /**
     じゃんけんゲームを開始します。<br>
     uiとmotionとの互いの参照を設定したあと、motionのstartGame()メソッドを呼びます。
     引数のuiとmotionが、どちらか一方でもnullの場合、このメソッドは中断し、じゃんけんゲームを終了します。<br>
     
     @param ui ユーザーインターフェースモジュールの境界クラスのインスタンス
     @param motion ゲーム動作モジュールの境界クラスのインスタンス
     */
    public static void startJanken(JankenUI ui, JankenMotion motion){
        if(ui == null || motion == null){
            System.out.println("UIとmotionの指定がなければ起動できません。");
            return;
        }
        
        ui.setMotion(motion);
        motion.setUI(ui);
        
        motion.startGame();
    }
}

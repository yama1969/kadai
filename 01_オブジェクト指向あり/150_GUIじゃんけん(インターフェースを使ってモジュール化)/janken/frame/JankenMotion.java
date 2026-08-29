package janken.frame;

/**
 じゃんけんゲームの、ゲーム動作モジュールの境界クラスが実装するインターフェースです。
 */
public interface JankenMotion{
    /**
     ユーザーインターフェースモジュールの境界クラスのインスタンスの参照をセットします。<br>
     このメソッドは、startGame()メソッドよりも前にJankenManagerクラスから実行されます。
     引数のuiはnullでないことが保証されています。
     @param ui ユーザーインターフェースモジュールの境界クラスのインスタンス
     */
    public void setUI(JankenUI ui);
    
    /**
     じゃんけんゲームを開始します。<br>
     このメソッドは、setUI()の次にJankenManagerクラスから実行されます。
     */
    public void startGame();
    
    /**
     手を選択したときの処理を行います。<br>
     このメソッドは、プレイヤーが手を選択したときに、ユーザーインターフェースモジュールから実行されます。
     @param hand プレイヤーが選択した手
     */
    public void selectHand(JankenHand hand);
}

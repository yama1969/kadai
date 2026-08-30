package janken.frame;

/**
 じゃんけんゲームの、ユーザーインターフェースモジュールの境界クラスが実装するインターフェースです。
 */
public interface JankenUI{
    /**
     ゲーム動作モジュールの境界クラスのインスタンスの参照をセットします。<br>
     このメソッドは、プログラムの起動直後に、JankenManagerクラスから実行されます。
     引数のmotionはnullでないことが保証されています。
     @param motion ゲーム動作モジュールの境界クラスのインスタンス
     */
    public void setMotion(JankenMotion motion);
    
    /**
     じゃんけん対戦相手側の手の、表示の高速回転を開始します。<br>
     このメソッドはゲーム動作モジュールから実行されます。実際にどのような表示が行われるかは、
     JankenUIの実装によります。
     */
    public void startRoll();
    
    /**
     じゃんけん対戦相手側の手の、表示の高速回転を停止します。<br>
     このメソッドはゲーム動作モジュールから実行されます。実際にどのような表示が行われるかは、
     JankenUIの実装によります。
     */
    public void stopRoll();
    
    /**
     じゃんけん対戦相手側の手を表示します。<br>
     このメソッドはゲーム動作モジュールから実行されます。
     @param hand 表示する手
     */
    public void showHand(JankenHand hand);
    
    /**
     画面に表示する文字列をセットします。<br>
     このメソッドはゲーム動作モジュールから実行されます。
     @param mess 表示する文字列
     */
    public void setMessage(String mess);
    
    /**
     画面に表示している文字列を消します。<br>
     このメソッドはゲーム動作モジュールから実行されます。
     */
    public void clearMessage();
}

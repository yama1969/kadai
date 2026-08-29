/**
 *	じゃんけんゲームの表示を担うクラスです。
 */
public class JankenGameDisplay{
	/**
	 *	プレーヤーに手の入力を促すメッセージを表示します。
	 *
	 *	@param count	じゃんけんの回数(何度目のじゃんけんか)
	 */
	public void dispInputMessage(int count){
	}
	
	/**
	 *	プレーヤーに再度の手の入力を促すメッセージを表示します。
	 *
	 *	@param count	じゃんけんの回数(何度目のじゃんけんか)
	 */
	public void dispReInputMessage(int count){
	}
	
	/**
	 *	プレーヤーとコンピュータの手を表示します。
	 *
	 *	@param playerHand	プレーヤーの手オブジェクト
	 *	@param compHand		コンピュータの手オブジェクト
	 */
	public void dispHand(PlayerHand playerHand, ComputerHand compHand){
	}
	
	/**
	 *	勝敗判定結果を表示します。
	 *
	 *	@param hand	勝敗判定済みのプレーヤーの手オブジェクト
	 */
	public void dispJudgement(PlayerHand hand){
	}
	
	/**
	 *	ゲームの最後の表示をします。勝敗分の表示もします。
	 *
	 *	@param win	勝ち数
	 *	@param lose	負け数
	 *	@param draw	あいこ数
	 */
	public void dispEnd(int win, int lose, int draw){
	}
	
	/**
	 *	異常終了時の表示をします。
	 */
	public void dispError(){
	}
}

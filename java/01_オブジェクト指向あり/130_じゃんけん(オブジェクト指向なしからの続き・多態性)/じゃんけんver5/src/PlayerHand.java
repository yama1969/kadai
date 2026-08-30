/**
 *	プレーヤーの出すじゃんけんの手を表すクラスです。
 */
public class PlayerHand{
	/** じゃんけんの手の種類の数 */
	public static final int NUM_TYPE = 3;
	/** グーを表す値 */
	public static final int GUU = 0;
	/** チョキを表す値 */
	public static final int CHOKI = 1;
	/** パーを表す値 */
	public static final int PAA = 2;
	/** 回復可能な異常を表す値 */
	public static final int ILLEGAL = -1;
	/** 回復不可能な異常を表す値 */
	public static final int ERR = -2;
	/** 勝ちを表す値 */
	public static final int WIN = 0;
	/** 負けを表す値 */
	public static final int LOSE = 1;
	/** あいこを表す値 */
	public static final int DRAW = 2;
	
	/**
	 *	PlayerInputオブジェクトを引数にとる、唯一のコンストラクタ。
	 *
	 *	@param input	入力オブジェクト
	 */
	public PlayerHand(PlayerInput input){
	}
	
	/**
	 *	プレーヤーの手を決めます。
	 *
	 *	@return	決められた手(GUU, CHOKI, PAA)
	 */
	public int selectType(){
		return GUU;
	}
	
	/**
	 *	現在のプレーヤーの手を取得します。手の変更は行いません。
	 *
	 *	@return	現在の手(GUU, CHOKI, PAA)
	 */
	public int getType(){
		return GUU;
	}
	
	/**
	 *	コンピュータの手と比較し、勝敗を判定します。
	 *
	 *	@param rival	コンピュータの手オブジェクト
	 *	@return	判定結果(WIN, LOSE, DRAW)
	 */
	public int judge(ComputerHand rival){
		return WIN;
	}
	
	/**
	 *	現在の勝敗状態を取得します。新たな判定はせず、直前の判定結果を返します。
	 *
	 *	@return	現在の勝敗状態(WIN, LOSE, DRAW)
	 */
	public int getJudgement(){
		return WIN;
	}
}

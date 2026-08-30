/**
 *	コンピュータの出すじゃんけんの手を表すクラスです。
 *	ランダムに手を決めます。
 */
public class ComputerHand{
	/** じゃんけんの手の種類の数 */
	public static final int NUM_TYPE = 3;
	/** グーを表す値 */
	public static final int GUU = 0;
	/** チョキを表す値 */
	public static final int CHOKI = 1;
	/** パーを表す値 */
	public static final int PAA = 2;
	
	/**
	 *	引数を取らない、唯一のコンストラクタ。
	 */
	public ComputerHand(){
	}
	
	/**
	 *	コンピュータの手を決めます。
	 *
	 *	@return	決められた手(GUU, CHOKI, PAA)
	 */
	public int selectType(){
		return GUU;
	}
	
	/**
	 *	現在のコンピュータの手を取得します。手の変更は行いません。
	 *
	 *	@return	現在の手(GUU, CHOKI, PAA)
	 */
	public int getType(){
		return GUU;
	}
}

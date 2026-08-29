/**
 *	プレーヤーの入力を行なうクラスです。
 */
public class PlayerInput{
	/** 回復可能な異常入力を表す値 */
	public static final int ILLEGAL = -1;
	/** 回復不可能な異常入力を表す値 */
	public static final int ERR = -2;
	
	/**
	 *	整数値を入力します。数値の範囲は、1　～　引数で指定された最大値　です。
	 *	範囲外の数値の入力や、数値以外の入力があると、回復可能な異常を返します。
	 *
	 *	@param max	入力最大値
	 *	@return	入力値(回復可能異常時:ILLEGAL、回復不可能異常時:ERR)
	 */
	public int inputNum(int max){
		return ERR;
	}
}

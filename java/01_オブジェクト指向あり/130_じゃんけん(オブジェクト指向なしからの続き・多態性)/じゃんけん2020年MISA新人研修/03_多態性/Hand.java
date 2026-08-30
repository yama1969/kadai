//じゃんけんの手
public interface Hand{	//abstractクラス=必ず継承せよ(newできない)
						//interface=全てが抽象メソッド
	//手を変える
	public abstract void changeKind();	//abstractメソッド=必ずオーバーライドせよ
										//interfaceのメソッド=必ずpublic abstract
	
	//勝敗判定
	public abstract Hand judge(Hand aite);
	
	//名前を得る
	public abstract String getName();
	
	//手を得る
	public abstract int getKind();
}

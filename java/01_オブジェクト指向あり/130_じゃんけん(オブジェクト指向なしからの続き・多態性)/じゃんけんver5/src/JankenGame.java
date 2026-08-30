/**
 *	プレーヤーとコンピュータが一定回数じゃんけんをするプログラムの
 *	コントローラです。
 *	
 *	@author 山田
*/
public class JankenGame{
	/**じゃんけん繰返し数*/
	private int round;
	/**表示オブジェクト*/
	private JankenGameDisplay display;
	/**プレーヤーの手*/
	private PlayerHand playerHand;
	/**コンピュータの手*/
	private ComputerHand compHand;
	
	/**
	 *	唯一のコンストラクタ。プログラムに必要なインスタンスと
	 *	じゃんけん回数を引数に取ります。<br>
	 *	<br>
	 *	各引数のオブジェクトを対応するフィールドへ代入します。
	 *
	 *	@param display    表示オブジェクト
	 *	@param playerHand プレーヤーの手オブジェクト
	 *	@param compHand   コンピュータの手オブジェクト
	 *	@param round      じゃんけん回数
	 */
	public JankenGame(JankenGameDisplay display,
	                  PlayerHand playerHand,
	                  ComputerHand compHand,
	                  int round){
		this.round = round;
		this.display = display;
		this.playerHand = playerHand;
		this.compHand = compHand;
	}
	
	/**
	 *	じゃんけんゲームを開始します。<br>
	 *	<br>
	 *	コンストラクタで予め設定されている「じゃんけん回数」分だけ、
	 *	じゃんけんを繰り返します。
	 *	「プレーヤーの手の決定」や「勝敗判定」でエラーが返って来たときは、
	 *	ゲームを中断します。
	 *	各回の対戦履歴は保存しません。勝・敗・分は記録します。
	 */
	public void startGame(){
		//準備
		int win = 0;										//勝ち数
		int lose = 0;										//負け数
		int draw = 0;										//あいこ数
		
		//ゲーム処理
		for(int cnt = 0; cnt < round; cnt++){
			display.dispInputMessage(cnt + 1);				//入力表示
			if(playerHand.selectType() == playerHand.ERR){	//プレーヤーの手を決定する
				display.dispError();
				break;
			}
			compHand.selectType();							//コンピュータの手を決定する。
			display.dispHand(playerHand, compHand);			//双方の手を表示
			switch(playerHand.judge(compHand)){				//勝敗判定
			case PlayerHand.WIN:
				win++;
				break;
			case PlayerHand.LOSE:
				lose++;
				break;
			case PlayerHand.DRAW:
				draw++;
				break;
			default:
				display.dispError();
				cnt = round;
			}
			display.dispJudgement(playerHand);				//勝敗表示
		}
		display.dispEnd(win, lose, draw);
	}
}

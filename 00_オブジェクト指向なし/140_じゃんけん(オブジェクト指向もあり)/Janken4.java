import java.io.*;

/**
 *	じゃんけんプログラムver4
 *	プレーヤーとコンピュータが一定回数じゃんけんをするプログラム
 *	(対戦履歴を最後に表示)
 *	
 *	@author 山田
*/
public class Janken4{
	//ゲーム繰返し数の定義
	private static final int CNT_MAX = 10;				//じゃんけん回数
	
	//手のコード定義
	//(配列で利用するので、正常系のコードはCNT_HAND以上にしないこと)
	private static final int CNT_HAND     = 3;			//手の種類数
	private static final int HAND_GUU     = 0;			//グーのコード
	private static final int HAND_CHOKI   = 1;			//チョキのコード
	private static final int HAND_PAA     = 2;			//パーのコード
	private static final int HAND_ILLEGAL = -1;			//不正な入力
	private static final int HAND_ERR     = -2;			//回復不能なエラー
	
	//勝敗のコード定義
	//(配列で利用するので、コードはCNT_JUDGE以上にしないこと)
	private static final int CNT_JUDGE = 3;				//勝敗の種類数
	private static final int JUDGE_WIN = 0;				//プレーヤーの勝ち
	private static final int JUDGE_LOSE = 1;			//プレーヤーの負け
	private static final int JUDGE_DRAW = 2;			//あいこ
	
	//表示文字列定義
	private static final String S_GUU     = "グー  ";	//グーの表示文字列
	private static final String S_CHOKI   = "チョキ";	//チョキの表示文字列
	private static final String S_PAA     = "パー  ";	//パーの表示文字列
	private static final String S_ILLEGAL = "謎の手";	//不正な手の表示文字列
	
	private static final String S_NO_GUU = "1:";		//手の入力文字
	private static final String S_NO_CHOKI = "2:";
	private static final String S_NO_PAA = "3:";
	private static final String S_INPUT = "1～3のどれかを入力してください。=> ";
	
	private static final String S_LROUND = "【";
	private static final String S_RROUND = "回目のじゃんけん】";
	private static final String S_YOU = "あなたは";
	private static final String S_COMP = "コンピュータは";
	private static final String S_OUT = "を出しました。";
	private static final String S_ERR_MESS = "入力装置エラーが発生！";
	private static final String S_ABORT_MESS = "プログラムを終了します！";
	private static final String S_WIN = "あなたの勝ちです。";
	private static final String S_LOSE = "あなたの負けです。";
	private static final String S_DRAW = "あいこです。";
	private static final String S_NOJUDGE = "判定不可能！！";
	private static final String S_END_MESS = "終わりです。";
	private static final String S_VS = " vs ";
	private static final String S_TOTAL_WIN = "勝";
	private static final String S_TOTAL_LOSE = "敗";
	private static final String S_TOTAL_DRAW = "分";
	private static final String S_FINAL_MESS = "でした。";
	private static final String S_UNKNOWNERR_MESS = "想定外のエラーが発生！";
	
	//---全体処理---------------------------------------------------------------
	/**
	 *	じゃんけんゲームの全体の流れ
	 */
	public static void main(String[] args){
		//手と勝敗の履歴用変数
		int[] playerHand = new int[CNT_MAX];				//プレイヤーの手
		int[] compHand = new int[CNT_MAX];					//コンピュータ手
		int[] judge = new int[CNT_MAX];						//勝敗判定結果
		//勝敗集計用変数
		int[] record = new int[CNT_JUDGE];					//勝敗分集計
		
		//じゃんけんゲーム実行
		for(int cnt = 0; cnt < CNT_MAX; cnt++){
			//プレーヤーの手を決定する
			do{
				dispInputMess(cnt);							//入力表示
				playerHand[cnt] = getPlayerHand();			//手の決定
				dispPlayerHand(playerHand[cnt]);			//手の表示
			}while(playerHand[cnt] == HAND_ILLEGAL);
			
			if(playerHand[cnt] == HAND_ERR){				//エラー時は即終了
				break;
			}
			
			//コンピュータの手を決定する
			compHand[cnt] = getCompHand();					//決定
			dispCompHand(compHand[cnt]);					//手の表示
				
			//勝敗を判定する
			judge[cnt] = getJudgement(playerHand[cnt],compHand[cnt]);	//判定
			dispJudgement(judge[cnt]);									//判定の表示
			
			//勝敗数を集計する
			record[judge[cnt]]++;
		}
		
		//終了表示する
		dispEnd(playerHand, compHand, judge, record);
	}
	
	//---詳細処理---------------------------------------------------------------
	/**
	 *	プレーヤーの手を決定する
	 *
	 *	@return 入力された手(HAND_GUU,HAND_CHOKI,HAND_PAA,HAND_ILLEGAL,HAND_ERR)
	 */
	private static int getPlayerHand(){
		int hand = 0;					//入力された手
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String line = br.readLine();
			hand = Integer.parseInt(line) - 1;
			switch(hand){
			case HAND_GUU:
			case HAND_CHOKI:
			case HAND_PAA:
				break;
			default:
				hand = HAND_ILLEGAL;
			}
		}catch(IOException e){
			hand = HAND_ERR;					//回復不能エラー
		}catch(NumberFormatException e){
			hand = HAND_ILLEGAL;				//回復可能エラー
		}
		
		return hand;
	}
	
	/**
	 *	コンピュータの手を決定する
	 *
	 *	@return コンピュータの手(HAND_GUU,HAND_CHOKI,HAND_PAA)
	 */
	private static int getCompHand(){
		return (int)(Math.random() * 3.0);
	}
	
	/**
	 *	じゃんけんの勝敗を判定する
	 *
	 *	@param player プレーヤーの手(HAND_GUU,HAND_CHOKI,HAND_PAA)
	 *	@param comp   コンピュータの手(HAND_GUU,HAND_CHOKI,HAND_PAA)
	 *	@return 判定結果(JUDGE_WIN,JUDGE_LOSE,JUDGE_DRAW)
	 */
	private static int getJudgement(int player, int comp){
		//勝敗判定表の作成
		//第1パラメタ=プレーヤー,第2パラメタ=コンピュータ
		int[][] judge_table = new int[CNT_HAND][CNT_HAND];
		judge_table[HAND_GUU  ][HAND_GUU  ] = JUDGE_DRAW;
		judge_table[HAND_GUU  ][HAND_CHOKI] = JUDGE_WIN;
		judge_table[HAND_GUU  ][HAND_PAA  ] = JUDGE_LOSE;
		judge_table[HAND_CHOKI][HAND_GUU  ] = JUDGE_LOSE;
		judge_table[HAND_CHOKI][HAND_CHOKI] = JUDGE_DRAW;
		judge_table[HAND_CHOKI][HAND_PAA  ] = JUDGE_WIN;
		judge_table[HAND_PAA  ][HAND_GUU  ] = JUDGE_WIN;
		judge_table[HAND_PAA  ][HAND_CHOKI] = JUDGE_LOSE;
		judge_table[HAND_PAA  ][HAND_PAA  ] = JUDGE_DRAW;
		
		return judge_table[player][comp];
	}
	
	//---表示関連メソッド-------------------------------------------------------
	/**
	 *	プレーヤーの手の入力メッセージを表示する
	 *
	 *	@param cnt 現在のじゃんけんの回数
	 */
	private static void dispInputMess(int cnt){
		System.out.println();
		//ラウンド表示
		System.out.println(S_LROUND + (cnt + 1) + S_RROUND);
		
		//入力ガイド表示
		System.out.print(S_NO_GUU + S_GUU);
		System.out.print(S_NO_CHOKI + S_CHOKI);
		System.out.println(S_NO_PAA + S_PAA);
		
		//入力プロンプト表示
		System.out.print(S_INPUT);
	}
	
	/**
	 *	プレーヤーの手を表示する
	 *
	 *	@param hand プレーヤーの手(HAND_GUU,HAND_CHOKI,HAND_PAA,HAND_ERR)
	 */
	private static void dispPlayerHand(int hand){
		String s_hand = getHandName(hand);
		System.out.println();
		if(hand != HAND_ERR){
			System.out.println(S_YOU + s_hand + S_OUT);
		}else{
			System.out.println(S_ERR_MESS);
			System.out.println(S_ABORT_MESS);
		}
	}
	
	/**
	 *	コンピュータの手を表示する
	 *
	 *	@param hand コンピュータの手(HAND_GUU,HAND_CHOKI,HAND_PAA)
	 */
	private static void dispCompHand(int hand){
		String s_hand = getHandName(hand);
		System.out.println(S_COMP + s_hand + S_OUT);
	}
	
	/**
	 *	手の表示文字列を得る
	 *
	 *	@param hand 手(HAND_GUU,HAND_CHOKI,HAND_PAA)
	 */
	private static String getHandName(int hand){
		String[] s_name = new String[CNT_HAND];
		s_name[HAND_GUU] = S_GUU;
		s_name[HAND_CHOKI] = S_CHOKI;
		s_name[HAND_PAA] = S_PAA;
		
		try{
			return s_name[hand];
		}catch(IndexOutOfBoundsException e){
			return S_ILLEGAL;
		}
	}
	
	/**
	 *	判定結果を表示する
	 *
	 *	@param judge 判定結果(JUDGE_WIN,JUDGE_LOSE,JUDGE_DRAW)
	 */
	private static void dispJudgement(int judge){
		String[] s_result = new String[CNT_JUDGE];
		s_result[JUDGE_WIN] = S_WIN;
		s_result[JUDGE_LOSE] = S_LOSE;
		s_result[JUDGE_DRAW] = S_DRAW;
		
		try{
			System.out.println(s_result[judge]);
		}catch(IndexOutOfBoundsException e){
			System.out.println(S_NOJUDGE);
		}
	}
	
	/**
	 *	ゲーム終了表示する
	 */
	private static void dispEnd(int[] player, int[] comp, int[] judge, int[] record){
		System.out.println();
		//終了表示
		System.out.println(S_END_MESS);
		
		//履歴表示
		String[] hand = new String[CNT_HAND];
		hand[HAND_GUU]   = S_GUU;
		hand[HAND_CHOKI] = S_CHOKI;
		hand[HAND_PAA]   = S_PAA;
		
		String[] s_judge = new String[CNT_JUDGE];
		s_judge[JUDGE_WIN] = S_TOTAL_WIN;
		s_judge[JUDGE_LOSE] = S_TOTAL_LOSE;
		s_judge[JUDGE_DRAW] = S_TOTAL_DRAW;
		
		for(int i = 0; i < CNT_MAX; i++){
			System.out.printf(S_LROUND + "%2d" + S_RROUND, (i + 1));
			System.out.print(hand[player[i]] + S_VS + hand[comp[i]]);
			System.out.println("\t" + s_judge[judge[i]]);
		}
		
		//成績表示
		System.out.print(record[JUDGE_WIN] + S_TOTAL_WIN);
		System.out.print(record[JUDGE_LOSE] + S_TOTAL_LOSE);
		System.out.println(record[JUDGE_DRAW] + S_TOTAL_DRAW + S_FINAL_MESS);
	}
	
	/**
	 *	想定外のエラー発生を表示する
	 */
	private static void dispERR(){
		System.out.println(S_UNKNOWNERR_MESS);
	}
}

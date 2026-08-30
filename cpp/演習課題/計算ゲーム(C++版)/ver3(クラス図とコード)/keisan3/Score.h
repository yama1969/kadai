#ifndef __SCORE__
#define __SCORE__

/******************************************************************************
 * クラスScore
 * 　計算ゲームのスコアを表すクラス
 ******************************************************************************/
class Score{
private:
	float score;							//スコア
	char *name;								//名前
public:
	Score();								//コンストラクタ
	~Score();								//デストラクタ
	float calcScore(int play_t, int right_cnt, int total_ques);
											//スコア算出
	char *inputName();						//名前入力
	int compScore(Score &mate);				//別のスコアと比較する
	float getScore();						//スコアを得る
	char *getName();						//名前を得る
};

#endif
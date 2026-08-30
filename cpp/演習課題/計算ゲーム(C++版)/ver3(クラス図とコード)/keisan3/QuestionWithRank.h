#ifndef __QUESTIONWITHRANK__
#define __QUESTIONWITHRANK__

#include "Question.h"
#include "Ranking.h"

/******************************************************************************
 * クラスQuestionWithRank
 * 　計算ゲームの問題種別とランキングを関連付けるクラス
 ******************************************************************************/
class QuestionWithRank{
private:
	Question *question;						//計算問題オブジェクト
	Ranking  *ranking;						//ランキングオブジェクト
public:
	QuestionWithRank(Question &qe);			//コンストラクタ
	~QuestionWithRank();					//デストラクタ
	int addScore(Score &sc);				//スコア追加
	void showRanking();						//ランキング表示
	void showNextQuestion();				//新しい問題の作成と表示
	int isRightAnswer(int ans);				//正答と引数との比較
};

#endif
#ifndef __RANKING__
#define __RANKING__

#include "Score.h"

/******************************************************************************
 * クラスRanking
 * 　計算ゲームのランキングを表すクラス
 ******************************************************************************/
class Ranking{
public:
	static const int MAX = 10;				//ランキング記録数
private:
	Score *rank[MAX + 1];					//ランキングデータ配列
public:
	Ranking();								//コンストラクタ
	int addScore(Score &sc);				//スコア追加
	void showRanking();						//ランキング表示
};

#endif
#ifndef __CALCGAME__
#define __CALCGAME__

#include "QuestionWithRank.h"

/******************************************************************************
 * クラスCalcGame
 * 　計算ゲーム本体
 ******************************************************************************/
class CalcGame{
public:
	static const int QUEST_NUM = 10;		//問題数
	static const int KIND_NUM = 4;			//計算問題種類数
private:
	Question *quest[KIND_NUM];				//各種問題オブジェクトポインタ
	QuestionWithRank *queran[KIND_NUM];		//各種ランキング付き問題ポインタ

	int inputNum();							//数値を入力する
	int showInitMenu();						//初期メニュー表示
	void showReady(int quest);				//問題開始前メッセージ表示
	void showResult(int right, int time, Score &s);
											//結果表示
	void showRank(int rank, Score &s);		//ランク表示
public:
	CalcGame();								//コンストラクタ
	~CalcGame();							//デストラクタ
	void startGame();						//計算ゲーム開始
};

#endif
#ifndef __HAND__
#define __HAND__

#include "Result.h"

/******************************************************************************
 * クラスHand
 * 　じゃんけんの手を表す基底抽象クラス。
 * 　decideType()で自分の出す手を決定し、inputHand()で相手の手の情報を得、
 * 　自分が次に出す手の判断材料にする。
 ******************************************************************************/
class Hand{
private:
	Result *result;								//勝負分を記録するオブジェクト
protected:
	int handType;								//現在の手の種類
public:
	Hand();										//コンストラクタ
	~Hand();									//デストラクタ(ヒープ解放あり)
	virtual int decideType() = 0;				//手を決定する
	virtual void learnHand(Hand &preHand) = 0;	//相手の直前の手を学習する
	int getType();								//現在の手を得る
	int compareHand(Hand &mate);				//相手との勝敗を判別し、記録する
	int getWin();								//今までの勝ち数を得る
	int getLose();								//今までの負け数を得る
	int getDraw();								//今までの分け数を得る
	int getWinType(int type);					//指定した手に勝つ手を得る

	//このクラスで用いる定数の定義
	static const int TE_NUM = 3;	//手の種類数
	static const int NON    = 99;	//手の種類：指定なし
	static const int GUH    = 0;	//　　　　　グー　(手の種類は0からの連続整数で定義すること)
	static const int PAH    = 1;	//　　　　　パー
	static const int CHO    = 2;	//　　　　　チョキ
	static const int KATI   = 1;	//結果：勝ち
	static const int AIKO   = 0;	//　　　あいこ
	static const int MAKE   = -1;	//　　　負け
};

#endif